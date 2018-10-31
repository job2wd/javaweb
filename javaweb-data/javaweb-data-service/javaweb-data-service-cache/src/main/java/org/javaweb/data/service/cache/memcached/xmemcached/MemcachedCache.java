/*
 * $Id: MemcachedCache.java 128 2017-02-05 03:17:29Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/memcached/xmemcached/MemcachedCache.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.data.service.cache.memcached.xmemcached;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;



/**
 * 使用 Memcached（XMemcached） 对 Spring Cache 进行的实现。 since：<br>
 * {@link org.springframework.cache.Cache org.springframework.cache.Cache}<br>
 * {@link net.rubyeye.xmemcached.MemcachedClient net.rubyeye.xmemcached.MemcachedClient}
 * 
 * @author wangwd
 * @version $Revision: 128 $, $Date: 2015-3-25 下午3:49:10$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-05 11:17:29#$
 */
public class MemcachedCache implements Cache {

  private static Logger log = LoggerFactory.getLogger(MemcachedCache.class);
  
  private Set<String> keySet = new HashSet<String>();
  
  private final String name;
  private final int expire;
  
  private final MemcachedClient memcachedClient;
  
  public MemcachedCache(String name, int expire, MemcachedClient memcachedClient)
  {
      this.name = name;
      this.expire = expire;
      this.memcachedClient = memcachedClient;
  }
  
  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#getNativeCache()
   */
  @Override
  public MemcachedClient getNativeCache() {
    return memcachedClient;
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#get(java.lang.Object)
   */
  @Override
  public ValueWrapper get(Object key) {
    ValueWrapper wrapper = null;
    
    try {
      Object value = memcachedClient.get(this.getKey(key.toString()));
      if (value != null) {
        wrapper = new SimpleValueWrapper(value);
      }
    } catch (TimeoutException e) {
      log.warn("获取 Memcached 缓存超时", e);
    } catch (InterruptedException e) {
      log.warn("获取 Memcached 缓存被中断", e);
    } catch (MemcachedException e) {
      log.warn("获取 Memcached 缓存错误", e);
    }
    return wrapper;
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#get(java.lang.Object, java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(Object key, Class<T> type) {
    try {
      return (T) memcachedClient.get(this.getKey(key.toString()), memcachedClient.getTranscoder());
    } catch (TimeoutException e) {
      log.warn("获取 Memcached 缓存超时", e);
    } catch (InterruptedException e) {
      log.warn("获取 Memcached 缓存被中断", e);
    } catch (MemcachedException e) {
      log.warn("获取 Memcached 缓存错误", e);
    }
    return null;
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
   */
  @Override
  public void put(Object key, Object value) {
    try {
      String k = this.getKey(key.toString());
      memcachedClient.setWithNoReply(k, expire, value);
      keySet.add(k);
    } catch (InterruptedException e) {
      log.warn("更新 Memcached 缓存被中断", e);
    } catch (MemcachedException e) {
      log.warn("更新 Memcached 缓存错误", e);
    }
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#putIfAbsent(java.lang.Object, java.lang.Object)
   */
  @Override
  public ValueWrapper putIfAbsent(Object key, Object value) {
    ValueWrapper wrapper = null;
    try {
      String k = this.getKey(key.toString());
      memcachedClient.setWithNoReply(k, expire, value);
      keySet.add(k);
      wrapper = new SimpleValueWrapper(value);
    } catch (InterruptedException e) {
      log.warn("更新 Memcached 缓存被中断", e);
    } catch (MemcachedException e) {
      log.warn("更新 Memcached 缓存错误", e);
    }
    return wrapper;
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#evict(java.lang.Object)
   */
  @Override
  public void evict(Object key) {
    try {
      memcachedClient.deleteWithNoReply(this.getKey(key.toString()));
      keySet.remove(key);
    } catch (InterruptedException e) {
      log.warn("删除 Memcached 缓存被中断", e);
    } catch (MemcachedException e) {
      log.warn("删除 Memcached 缓存错误", e);
    }
  }

  /* (non-Javadoc)
   * @see org.springframework.cache.Cache#clear()
   */
  @Override
  public void clear() {
    Set<String> delKeySet = new HashSet<String>();
    
    for (String key : keySet) {
      try {
        memcachedClient.deleteWithNoReply(this.getKey(key));
        delKeySet.add(key);
      } catch (InterruptedException e) {
        log.warn("删除 Memcached 缓存被中断", e);
      } catch (MemcachedException e) {
        log.warn("删除 Memcached 缓存错误", e);
      }
    }
    
    for(String key : delKeySet) {
      keySet.remove(key);
    }
  }

  public boolean exists(Object key) {
    Object value = get(key);
    return value != null;
  }
  
  private String getKey(String key) {
    return name + "_" + key;
  }

  @Override
  public <T> T get(Object key, Callable<T> valueLoader) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
