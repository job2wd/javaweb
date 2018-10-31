/*
 * $Id: DataCacher.java 3688 2018-05-23 06:46:48Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/cache/simple/DataCacher.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



/**
 * 内存缓存信息维护。<br>
 * 
 * <b>注：</b>
 *   <li>1. 在调用该类中除方法 {@link #putCache(String, CacheKey)} 外的其他方法之前，需要先调用该方法，以保证缓存中已存在对该对象缓存分配的空间；</li>
 *   <li>2. 该缓存系统为内存缓存，但不等同于类似 memcached 或 redis 的缓存，只是一个简单的基于 JVM 内存缓存的实现；</li>
 *   <li>3. 该缓存中的数据目前只是简单的以 key-value 形式存储于 java.util.Map 中；</li>
 *   <li>4. 该缓存不支持分布式，即内存缓存中对象无法实现分布式系统内存缓存共享，每个 JVM 都会有一份相同内容的缓存信息，但相互之间不保持同步；</li>
 *   <li>5. 对同一个 namespace 中缓存信息的更新（add、update、remove、clear等），同时会更新缓存中维护的关系网；</li>
 *   <li>6. 所有存储在同一个 namespce 下的对象，他们的 leadField 在 pojo 中的值都必须是唯一的。</li>
 *   <li>7. 为了减小内存占用，该缓存系统中存储的对象信息只会有一份，且缓存中通过实现的 {@link org.javaweb.common.cache.simple.CacheKey CacheKey} 维护了缓存对象关系网，如下描述：
 *       <div>
 *         1) 当调用 {@link #add(String, Object...)} 的时候，首先会根据
 *            {@link org.javaweb.common.cache.simple.CacheKey#getLeadFieldName() CacheKey.getLeadFieldName()}
 *            中返回的值以 key-value 形式维护一个实体对象映射 Map；
 *       </div>
 *       <div>
 *         2) 然后根据 {@link org.javaweb.common.cache.simple.CacheKey#getSubfieldsName() CacheKey.getSubfieldsName()} 中返回的值以
 *            key-value 的形式维护多个 subfield 和 leadField 的映射关系 Map；
 *       </div>
 *       <div>
 *         3) 实际上  subfield 和 leadField 的映射关系 Map 中还维护了以 key-value 形式存储的多个 subfield 在 pojo 中的值和 leadField
 *           在 pojo 中的值集合（一个 subfield 的值可能会映射多个 leadField 的值 ） Map；
 *       </div>
 *   </li>
 * <style type="text/css">div{margin-left:20px;}</style>
 * 
 * @author wangwd
 * @version $Revision: 3688 $, $Date: 2016-4-18 下午5:03:42$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-23 14:46:48#$
 * @see org.javaweb.common.cache.simple.CacheBean
 * @see org.javaweb.common.cache.simple.CacheKey
 * @see org.javaweb.common.cache.simple.CacheComplexKey
 */
public class DataCacher {
  
  private static ConcurrentMap<String, CacheBean> cacher = new ConcurrentHashMap<String, CacheBean>();
  
  /**
   * 为要加入缓存的对象分配存储空间，以保证该类中其他方法能精准找到该对象。
   * 
   * @param namespace 缓存对象在缓存中唯一命名空间，不能设定一个已经存在的 namespace，否则会抛出运行时异常(Namespace [...] cache has exists.)。
   *        要判断一个 namespace 是否已存在，可调用方法 {@link #isExistsNamespace(String)}
   * @param cacheKey 缓存信息key封装对象，详细说明见 {@link org.javaweb.common.cache.simple.CacheKey}
   */
  public static void putCache(String namespace, CacheKey cacheKey) {
    if(isExistsNamespace(namespace)) {
      throw new RuntimeException("Namespace [" + namespace + "] cache has exists.");
    }
    
    CacheBean cacheBean = new CacheBean(cacheKey);
    cacher.put(namespace, cacheBean);
  }
  
  /**
   * 判断指定的 namespace 是否已存在。
   * 
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @return true - 已存在；false - 不存在。
   */
  public static boolean isExistsNamespace(String namespace) {
    return cacher.containsKey(namespace);
  }
  
  /**
   * 添加任意多个实体对象到指定 namespace 的缓存中。<br>
   * <b>注：若指定的 namespace 不存在，则抛出运行时异常（namespace [...] is not exists. please call method [DataCacher.putCache] at first.）。</b>
   * 
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @param pojos 实体对象集合，可以为 {@link java.util.Collection} 的实现。<br>
   *        <b>注：
   *           <li>1）添加该集合中对象到缓存中的同时，
   *                 会根据{@link org.javaweb.common.cache.simple.CacheKey}的实现建立相应对象关系网；</li>
   *           <li>2）所有存储在同一个 namespce 下的对象，他们的 leadField 在 pojo 中的值都必须是唯一的；</li>
   *           <li>3）若某一个pojo 对应的 leadField 在该 pojo 中的值已存在，则会抛出运行时异常，进而终止集合中后续 pojo 的添加；</li>
   *       </b>
   */
  public static void add(String namespace, Object... pojos) {
    checkNamespace(namespace);
    cacher.get(namespace).add(pojos);
  }
  
  /**
   * 更新缓存中指定 namespace 的对象。<br>
   * <b>注：若指定的 namespace 不存在，则抛出运行时异常（namespace [...] is not exists. please call method [DataCacher.putCache] at first.）。</b>
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @param pojo 要更新的实体对象，为指定 namespace 中唯一存在的对象。
   */
  public static void update(String namespace, Object pojo) {
    checkNamespace(namespace);
    cacher.get(namespace).update(pojo);
  }
  
  /**
   * 从缓存中移除指定 namespace 的对象。<br>
   * <b>注：若指定的 namespace 不存在，则抛出运行时异常（namespace [...] is not exists. please call method [DataCacher.putCache] at first.）。</b>
   * 
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @param pojo 要移除的实体对象，为指定 namespace 中唯一存在的对象，若该 pojo 在缓存中不唯一，则会全部删除（是否唯一，是根据 leadField 进行的判断）。
   */
  public static void remove(String namespace, Object pojo) {
    checkNamespace(namespace);
    cacher.get(namespace).remove(pojo);
  }
    
  /**
   * 根据指定对象属性和属性值，删除缓存中相应对象。
   * <b>注：若指定的 namespace 不存在，则抛出运行时异常（namespace [...] is not exists. please call method [DataCacher.putCache] at first.）。</b>
   * 
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @param field 要删除的对象属性（缓存中必须已经根据该属性有了相应的存储信息或关系网）。该属性可以为 leadField，也可以为 subfields 中任意一个。
   * @param fieldValue 要删除对象属性 field 的值。注意：该值在作为以 field 存储的缓存中最好唯一，否则，将删除全部 namespace 下面 filed 字段为该值的对象。
   */
  public static void remove(String namespace, String field, Object fieldValue) {
    checkNamespace(namespace);
    cacher.get(namespace).remove(field, fieldValue);
  }
  
  /**
   * 清空指定 namespace 下面所有缓存数据，包括实体对象数据和关系网。
   * @param namespace 缓存系统中的唯一命名空间
   */
  public static void clear(String namespace) {
    checkNamespace(namespace);
    cacher.get(namespace).clear();
    cacher.remove(namespace);
  }
  
  /**
   * 获取缓存中指定 namespace 下对象属性为 field，且属性值为 fieldValue 的缓存对象。
   * <b>注：若指定的 namespace 不存在，则抛出运行时异常（namespace [...] is not exists. please call method [DataCacher.putCache] at first.）。</b>
   * 
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @param field 要获取的对象属性（缓存中必须已经根据该属性有了相应的存储信息或关系网）。该属性可以为 leadField，也可以为 subfields 中任意一个。
   * @param fieldValue 要获取的对象属性 field 的值。注意：该值在作为以 field 存储的缓存中最好唯一，否则，将返回全部 namespace 下面 filed 字段为该值的所有对象中第一个对象。
   * @return 若不存在，则返回 null；否则返回实际存在的对象（或取到的对象集合中第一个对象）；
   */
  public static Object get(String namespace, String field, Object fieldValue) {
    return cacher.get(namespace).get(field, fieldValue);
  }
  
  /**
   * 获取缓存中指定 namespace 下所有对象。
   * @param namespace 指定对象在缓存系统中的唯一命名空间
   * @return 指定 namespace 下所有对象集合。若没找到，则返回为空的 List，该 List 不会为null（即List.size() == 0）。
   */
  public static List<?> getAll(String namespace) {
    checkNamespace(namespace);
    
    List<Object> list = new ArrayList<Object>();
    list.addAll(cacher.get(namespace).getAll());
    
    return list;
  }
  
  private static void checkNamespace(String namespace) {
    if(!cacher.containsKey(namespace)) {
      throw new RuntimeException("namespace [" + namespace + "] is not exists. please call method [DataCacher.putCache] at first.");
    }
  }
  
}
