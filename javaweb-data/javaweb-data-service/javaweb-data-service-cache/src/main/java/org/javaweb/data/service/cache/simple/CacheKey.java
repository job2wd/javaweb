/*
 * $Id: CacheKey.java 5169 2018-08-14 01:52:26Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/cache/simple/CacheKey.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple;


/**
 * 缓存key对象。定义了能确定对象在缓存中唯一的对象属性名称、要维护缓存关系的其他属性名称、缓存关系中维护的 key-value 中key的更复杂的定义。
 * 
 * @author wangwd
 * @version $Revision: 5169 $, $Date: 2016-4-21 下午4:18:53$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-14 09:52:26#$
 */
public abstract class CacheKey {

  /**
   * 能确定对象在缓存中唯一的对象属性名称。<br>
   * <b>注：该属性在缓存对象中的值必须具有唯一性（即同一个namespace中不同的对象，该属性返回的值不能重复）</b>
   * 
   * @return 通常为实体映射为数据库表主键的名称。必须是添加到缓存中对象的属性名称，否则将抛出异常。
   */
  public abstract String getLeadFieldName();
  
  /**
   * 维护缓存关系的其他属性名称（其他属性在对象中的值必须要和其他对象不同，即同一个 namespace 下所有对象该值具有唯一性）<br>
   * <b>注：该数组中的属性在缓存的对象中的值最好具有唯一性，即同一个namespce中的所有对象，其同名属性的值都不能相同。</b>
   * 
   * @return 可以为空或为null。
   *         若为空或null，则不处理，也不会报错；
   *         若不为空，则返回的数组中的名称必须是添加到缓存中对象的属性名称，否则将抛出异常。
   */
  public String[] getSubfieldsName() {
    return null;
  }
  
  /**
   * 
   * 缓存关系中维护的 key-value 中key的更复杂的定义。具体参见 {@link org.javaweb.common.cache.simple.CacheComplexKey.fba.cache.service.fba.FbaCacheComplexKey FbaCacheComplexKey}<br>
   * 
   * @return 可根据需要实现对复杂key的定义，若返回为空或为null，则在向缓存中添加实体对象时不会处理复杂映射关系网，也不会报错。
   */
  public CacheComplexKey[] getComplexKey() {
    return null;
  }
  
}
