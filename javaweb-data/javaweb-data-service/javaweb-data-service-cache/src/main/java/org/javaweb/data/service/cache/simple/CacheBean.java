/*
 * $Id: CacheBean.java 4108 2018-06-13 08:48:48Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/cache/simple/CacheBean.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;


/**
 * 缓存信息维护实体对象。
 * <div>真正的数据对象只存储一份，根据传入的其他对象属性名称，维护了一个主字段（leadField）和 其他子字段（subfields）之间的对应关系网。
 *      对数据的添加、更新、删除、清空等操作，除了要更新主字段维护的数据对象外，还需更新其他子字段对应关系。
 * </div>
 *
 * @author wangwd
 * @version $Revision: 4108 $, $Date: 2016-4-18 下午5:33:19$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-13 16:48:48#$
 */
public class CacheBean {

  /**
   * 可确定对象在缓存中唯一的属性名称。通常为数据库表主键名称
   */
  private String leadField;
  /**
   * 要维护对象在缓存中映射关系的对象所有属性名称，该集合中存储的值必须为对象的真实存在的属性名称。<br>
   * <b>注：缓存中 subfields 中指定的属性，其每个对象该属性值必须与其他对象不同，即必须唯一。</b>
   */
  private Set<String> subfields = new HashSet<String>();//new ConcurrentHashSet<String>();

  /**
   * key - 属性 leadField 在对象中的值; value - POJO（要存储在缓存中的实体对象）
   */
  private Map<Object, Object> leadFieldMap = new ConcurrentHashMap<Object, Object>();
  /**
   * key - subfields 中存在的所有属性名称; value - (key - subfields 中属性的值; value - subfields 中属性值对应的 leadField 的值)
   */
  private Map<String, Map<Object, Object>> subfieldMap = new ConcurrentHashMap<String, Map<Object, Object>>();

  private CacheKey cacheKey;

  public CacheBean(CacheKey cacheKey) {
    this.cacheKey = cacheKey;

    leadField = cacheKey.getLeadFieldName();

    String[] subfieldsName = cacheKey.getSubfieldsName();

    if(subfieldsName != null) {
      for(String subfield : subfieldsName) {
        this.subfields.add(subfield);
      }
    }
  }

  public void add(Object... pojos) {
    for(Object pojo : pojos) {
      if(pojo instanceof Collection) {
        Collection<?> datas = (Collection<?>)pojo;
        for(Object d : datas) {
          add(d);
        }
      } else {
        add(pojo);
      }
    }
  }
  
  public Object get(String field, Object fieldValue) {
    if(fieldValue == null) {
      return null;
    }
    
    if (field.equals(leadField)) {
      return leadFieldMap.get(fieldValue);
    }

    Map<Object, Object> subfieldValueMap = subfieldMap.get(field);

    if (subfieldValueMap == null || subfieldValueMap.isEmpty()) {
      return null;
    }

    Object res = subfieldValueMap.get(fieldValue);

    if(res == null) {
      return null;
    }
    
    if(StringUtils.isNotBlank(leadField)) {
      return leadFieldMap.get(res);
    }

    return res;
  }

  public Collection<?> getAll() {
    if(StringUtils.isNotBlank(leadField)) {
      return leadFieldMap.values();
    }
    
    Collection<Map<Object, Object>> values = subfieldMap.values();
    
    Collection<Object> res = new ArrayList<Object>();
    
    for(Map<Object, Object> m : values) {
      Set<Object> keys = m.keySet();
      
      for(Object key : keys) {
        res.add(m.get(key));
      }
    }
    
    return res;
  }

  public void update(Object pojo) {
    if(pojo == null) {
      return;
    }
    
    remove(pojo);
    add(pojo);
  }

  public void remove(Object pojo) {
    if(pojo == null) {
      return;
    }
    
    Object leadFieldValue = getFieldValue(pojo, leadField);

    if (leadFieldValue != null) {
      leadFieldMap.remove(leadFieldValue);
    }

    for (String subfield : subfields) {
      Object subfieldValue = this.getFieldValue(pojo, subfield);
      removeSubfieldValue(subfield, subfieldValue);
    }

    CacheComplexKey[]  complexKeys = cacheKey.getComplexKey();

    if(complexKeys != null) {
      for(CacheComplexKey key : complexKeys) {
        Object subfieldValue = key.getKeyValue(pojo);
        removeSubfieldValue(key.getKeyName(), subfieldValue);
      }
    }
  }

  public void remove(String field, Object fieldValue) {
    if(fieldValue == null) {
      return;
    }
    
    Object pojo = this.get(field, fieldValue);
    this.remove(pojo);
  }

  public void clear() {
    leadFieldMap.clear();
    subfieldMap.clear();
    subfields.clear();
  }

  private Object getFieldValue(Object pojo, String field) {
    if(pojo == null || StringUtils.isBlank(field)) {
      return null;
    }
    
    try {
      String fnUpper = field.substring(0, 1).toUpperCase() + field.substring(1);
      Method m = pojo.getClass().getMethod("get" + fnUpper);

      return m.invoke(pojo);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void add(Object pojo) {
    this.addLeadFieldValue(pojo);

    this.addSubFieldValues(pojo);
    
    this.addComplexKeyFieldValues(pojo);
  }
  
  private void addLeadFieldValue(Object pojo) {
    if(StringUtils.isBlank(leadField)) {
      return;
    }
    
    Object leadFieldValue = getFieldValue(pojo, leadField);
    
    if(leadFieldValue == null) {
      return;
    }
    
    if((leadFieldValue instanceof String) && "".equals(leadFieldValue)) {
      return;
    }
    
    this.checkContainsKey(leadFieldMap, leadFieldValue);
        
    leadFieldMap.put(leadFieldValue, pojo);
   }
  
  private void addSubFieldValues(Object pojo) {
    for (String subfield : subfields) {
      Object subfieldValue = getFieldValue(pojo, subfield);
      addSubfieldValue(subfield, subfieldValue, pojo);
    }
  }

  private void addSubfieldValue(String subfield, Object subfieldValue, Object pojo) {
    if (StringUtils.isBlank(subfield) || subfieldValue == null) {
      return;
    }
    
    if((subfieldValue instanceof String) && "".equals(subfieldValue)) {
      return;
    }
    
    Map<Object, Object> subfieldValueMap = subfieldMap.get(subfield);

    if (subfieldValueMap == null) {
      subfieldValueMap = new HashMap<Object, Object>();
      subfieldMap.put(subfield, subfieldValueMap);
    }
    
    if(StringUtils.isNotBlank(leadField)) {
      Object leadFieldValue = getFieldValue(pojo, leadField);
      
      if(leadFieldValue != null) {
        this.checkContainsKey(subfieldValueMap, subfieldValue);
        subfieldValueMap.put(subfieldValue, leadFieldValue);
      } else {
        this.checkContainsKey(subfieldValueMap, subfieldValue);
        subfieldValueMap.put(subfieldValue, pojo);
      }
    } else {
      this.checkContainsKey(subfieldValueMap, subfieldValue);
      subfieldValueMap.put(subfieldValue, pojo);
    }
  }

  private void addComplexKeyFieldValues(Object pojo) {
    CacheComplexKey[]  complexKeys = cacheKey.getComplexKey();

    if(complexKeys == null) {
      return;
    }
    
    for(CacheComplexKey key : complexKeys) {
      String complexKeyName = key.getKeyName();
      Object complexKeyValue = key.getKeyValue(pojo);
      addComplexKeyFieldValue(complexKeyName, complexKeyValue, pojo);
    }
  }
  
  private void addComplexKeyFieldValue(String complexKeyName, Object complexKeyValue, Object pojo) {
    this.addSubfieldValue(complexKeyName, complexKeyValue, pojo);
  }

  private void checkContainsKey(Map<Object, Object> map, Object key) {
    if(map.containsKey(key)) {
      throw new RuntimeException("key '" + key + "' has exists.");
    }
  }

  private void removeSubfieldValue(String subfield, Object subfieldValue) {
    Map<Object, Object> subfieldValueMap = subfieldMap.get(subfield);

    if (subfieldValueMap != null) {
      subfieldValueMap.remove(subfieldValue);
    }
  }

}
