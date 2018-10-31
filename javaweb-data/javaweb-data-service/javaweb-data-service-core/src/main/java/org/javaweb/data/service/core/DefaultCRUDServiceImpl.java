/*
 * $Id: DefaultCRUDServiceImpl.java 6602 2018-10-30 02:25:55Z wangweidong $
 * $HeadURL:
 * https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90
 * %E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/service/impl/DefaultCRUDServiceImpl.java $
 * Copyright (c) 2014 Company, vito. All Rights Reserved.
 */
package org.javaweb.data.service.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.javaweb.common.Constant;
import org.javaweb.data.model.JavaResponseUtils;
import org.javaweb.data.model.domain.QueryCondition;
import org.javaweb.data.model.domain.ResponseBean4Pagination;
import org.javaweb.data.model.entity.BaseEntity4Key;
import org.javaweb.data.model.exception.GenericDaoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author wangwd
 * @version $Revision: 6602 $, $Date: 2018年5月17日 下午12:53:15$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-10-30 10:25:55#$
 */
@Service
@Transactional
public abstract class DefaultCRUDServiceImpl<T extends BaseEntity4Key<K>, K extends Serializable> extends BaseServiceImpl
    implements DefaultCRUDService<T, K> {
  
  protected Class<T> persistentClass;
  
  public DefaultCRUDServiceImpl() {
    persistentClass = getEntityClass(getClass());
  }
  
  @Override
  @Transactional(readOnly = true)
  public Integer total(T t) {
    return getCRUDDao().total(t);
  }
  
  @Override
  public T findOne(K k) {
    try {
      T t = persistentClass.newInstance();
      t.setId(k);
      return this.findOne(t);
    } catch (Exception e) {
      throw JavaResponseUtils.createGenericException(e);
    }
  }
  
  @Override
  @Transactional(readOnly = true)
  public T findOne(T t) {
    List<T> list = this.find(t);
    
    if (list == null || list.size() == 0) {
      return null;
    }
    
    if (list.size() == 1) {
      return list.get(0);
    }
    
    throw new GenericDaoException("{error.code.dao.result.not_unique}", "{error.msg.dao.result.not_unique}");
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<T> find(T t) {
    return getCRUDDao().find(t);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<T> findAll() {
    try {
      return this.find(persistentClass.newInstance());
    } catch (Exception e) {
      throw new GenericDaoException(e);
    }
  }
  
  @Override
  @Transactional(readOnly = true)
  public ResponseBean4Pagination<T> findPage(T t) {
	  
    if (t.getPageNum() == null) {
      //请求封装对象中未设置上该值，则从请求参数中获取。如：请求体中提交数据为JSON数据，但并未设置该值，则试图获取请求参数中提供的值，若请求参数中该值为空，则使用默认值。
      //String pageNum = JavaWebUtils.getHttpRequestParameterValue("pageNum", Integer.valueOf(1).toString());
      t.setPageNum(1);//Integer.valueOf(pageNum));
    }
    
    if (t.getPageSize() == null) {
      //String pageSize = JavaWebUtils.getHttpRequestParameterValue("pageSize", Integer.valueOf(Constant.DEFAULT_PAGE_SIZE).toString());
      t.setPageSize(Constant.DEFAULT_PAGE_SIZE);//Integer.valueOf(pageSize));
    }
    

    List<T> list = this.find(t);
    
    Integer total = null;
    
    if (t.getPageNum() == 1) {
      total = this.total(t);
    }
    
    ResponseBean4Pagination<T> page = new ResponseBean4Pagination<T>();
    
    page.setStatus(1);
    page.setPageNum(t.getPageNum());
    page.setPageSize(t.getPageSize());
    page.setTotal(total);
    page.setData(list);
    
    return page;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer insert(T... ts) {
    if (ts == null || ts.length == 0) {
      return 0;
    }
    
    return this.insert(Arrays.asList(ts));
  }
  
 
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer insert(List<T> list) {
    if (list == null || list.size() == 0) {
      return 0;
    }
    
    int count = 0;
    int offset = 0;
    List<T> inserts = null;
    int length = list.size();
    int insertLength = 100;//一次插入100条数据
    
    if (length > insertLength) {
      //分批插入，避免一次插入数据量太大导致数据库请求数据包太大而出错。
      while ((length - offset) > insertLength) {
        inserts = list.subList(offset, offset + insertLength);
        
        this.initList(inserts);
        
        count += getCRUDDao().insert(inserts);
        
        offset += insertLength;
      }
    }
    
    if (offset > 0) {
      inserts = list.subList(offset - 1, length - 1);//插入剩余的数据（不足1000条）
    } else {
      inserts = list;
    }
    
    this.initList(inserts);
    
    count += getCRUDDao().insert(inserts);
    
    return count;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer update(T... ts) {
    int count = 0;
    for (T t : ts) {
      t.init();
      count += getCRUDDao().update(t);
    }
    return count;
  }
  
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer update(List<T> ts) {
    int count = 0;
    for (T t : ts) {
      t.init();
      count += getCRUDDao().update(t);
    }
    return count;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public Integer insertOrUpdate(T... ts) {
    return this.insertOrUpdate(Arrays.asList(ts));
  }
  
  @Override
  public Integer insertOrUpdate(List<T> list) {
    List<T> inserts = new ArrayList<T>();
    List<T> updates = new ArrayList<T>();
    
    for (T t : list) {
      if (t.getId() == null) {
        inserts.add(t);
      } else if (new BigInteger(t.getId().toString()).compareTo(BigInteger.ZERO) <= 0) {
        inserts.add(t);
      } else {
        updates.add(t);
      }
    }
    
    int count = this.insert(inserts);
    count += this.update(updates);
    
    return count;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer delete(K... ids) {
    if (ids == null || ids.length == 0) {
      return 0;
    }
    return getCRUDDao().delete(ids);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Transactional(rollbackFor = Throwable.class)
  public Integer deleteByConditions(T... ts) {
    if (ts == null || ts.length == 0) {
      return 0;
    }
    return this.deleteByConditions(Arrays.asList(ts));
  }
  
  @Override
  public Integer deleteByConditions(List<T> list) {
    if (list == null || list.size() == 0) {
      return 0;
    }
    
    int count = 0;
    for (T t : list) {
      count += getCRUDDao().deleteByCondition(t);
    }
    
    return count;
  }
  
  @Override
  @Transactional(readOnly = true)
  public boolean exists(T t) {
    if (t == null) {
      return false;
    }
    return this.total(t) > 0;
  }
  
  @Override
  public void addOrder(T t, String column, boolean asc) {
    if (t.getOrders() == null) {
      Map<String, String> orders = new HashMap<String, String>();
      t.setOrders(orders);
    }
    String order = "ASC";
    
    if (!asc) {
      order = "DESC";
    }
    
    t.getOrders().put(column, order);
  }
  
  @Override
  public void addQueryCondition(T t, String column, QueryCondition.OPEnum op, Object value) {
    if (value == null || "".equals(value)) {
      if (!(op == QueryCondition.OPEnum.IS_NULL || op == QueryCondition.OPEnum.IS_NOT_NULL)) {
        return;
      }
    }
    
    if (t.getQueryConditions() == null) {
      List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
      t.setQueryConditions(queryConditions);
    }
    
    QueryCondition qc = new QueryCondition();
    
    qc.setColumn(column);
    qc.setOp(op.getOp());
    qc.setValue(value);
    
    t.getQueryConditions().add(qc);
  }
  
  @Override
  public void addResultColumn(T t, String column) {
    if (t.getResultColumns() == null) {
      List<String> resultColumns = new ArrayList<String>();
      t.setResultColumns(resultColumns);
    }
    
    t.getResultColumns().add(column);
  }
  
  @SuppressWarnings("unchecked")
  private Class<T> getEntityClass(Class<?> tClass) {
    ParameterizedType pt = (ParameterizedType) tClass.getGenericSuperclass();
    Class<T> entity = (Class<T>) pt.getActualTypeArguments()[0];
    return entity;
  }
  
  private void initList(List<T> list) {
	  
    list.forEach(new Consumer<T>() {
      
      @Override
      public void accept(T t) {
        t.init();
      }
      
    });
  }
  
}
