/*
 * $Id: CacheComplexKey.java 3596 2018-05-18 08:10:27Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/cache/simple/CacheComplexKey.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple;


/**
 * 存关系中维护的 key-value 中key的更复杂的定义。针对无法用 subfield 作为key进行存储的情况，可以实现该类以自定义key。
 * 
 * @author wangwd
 * @version $Revision: 3596 $, $Date: 2016-4-21 下午3:51:40$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-18 16:10:27#$
 */
public abstract class CacheComplexKey {

  /**
   * 复杂 key 名称，可根据需要任意拼装。<br>
   * 
   * @return 拼装后的 key 名称
   */
  public abstract String getKeyName();
  
  /**
   * 针对复杂key，返回其对应的值，以便根据该值存储缓存中实体映射对应关系网。<br>
   * <b>注：针对同一个复杂key，该方法返回的值应该唯一（即针对多个缓存中对象，该方法返回的值都不重复）。</b>
   * 
   * @param pojo 缓存中存储的实体对象。由于该方法返回值可能需要从实体对象中获得，所以该对象做为了参数传入。
   * @return 复杂key对应的值（针对方法 {@link #getKeyName()}返回的值）
   */
  public abstract Object getKeyValue(Object pojo);
  
}
