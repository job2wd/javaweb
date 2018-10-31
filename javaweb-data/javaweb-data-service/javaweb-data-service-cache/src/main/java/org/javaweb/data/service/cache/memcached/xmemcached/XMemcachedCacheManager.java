/*
 * $Id: XMemcachedCacheManager.java 128 2017-02-05 03:17:29Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/memcached/xmemcached/XMemcachedCacheManager.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.data.service.cache.memcached.xmemcached;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;


/**
 * 使用 Memcached（XMemcached） 对 Spring Cache 进行的实现。 since：<br>
 * {@link org.springframework.cache.Cache org.springframework.cache.Cache}<br>
 * {@link net.rubyeye.xmemcached.MemcachedClient net.rubyeye.xmemcached.MemcachedClient}
 * 
 * @author wangwd
 * @version $Revision: 128 $, $Date: 2015-3-25 下午3:46:48$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-05 11:17:29#$
 */
public class XMemcachedCacheManager extends AbstractTransactionSupportingCacheManager {

  private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
  private Map<String, Integer> expireMap = new HashMap<String, Integer>();

  private MemcachedClient memcachedClient;

  public XMemcachedCacheManager()
  {
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.support.AbstractCacheManager#loadCaches()
   */
  @Override
  protected Collection<? extends Cache> loadCaches() {
    Collection<Cache> values = cacheMap.values();
    return values;
  }

  @Override
  public Cache getCache(String name) {
    Cache cache = cacheMap.get(name);
    if (cache == null) {
      Integer expire = expireMap.get(name);
      if (expire == null) {
        expire = Integer.valueOf(0);
        expireMap.put(name, expire);
      }

      cache = new MemcachedCache(name, expire.intValue(), memcachedClient);
      cacheMap.put(name, cache);
    }
    return cache;
  }

  public void setMemcachedClient(MemcachedClient memcachedClient) {
    this.memcachedClient = memcachedClient;
  }

  public void setConfigMap(Map<String, Integer> configMap) {
    this.expireMap = configMap;
  }
  
}
