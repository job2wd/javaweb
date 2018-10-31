/*
 * $Id: XMemcachedRegionFactory.java 128 2017-02-05 03:17:29Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/memcached/xmemcached/XMemcachedRegionFactory.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.data.service.cache.memcached.xmemcached;

import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cfg.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.hibernate.memcached.Config;
import com.googlecode.hibernate.memcached.Memcache;
import com.googlecode.hibernate.memcached.MemcacheClientFactory;
import com.googlecode.hibernate.memcached.MemcachedCache;
import com.googlecode.hibernate.memcached.PropertiesHelper;
import com.googlecode.hibernate.memcached.region.MemcachedCollectionRegion;
import com.googlecode.hibernate.memcached.region.MemcachedEntityRegion;
import com.googlecode.hibernate.memcached.region.MemcachedQueryResultsRegion;
import com.googlecode.hibernate.memcached.region.MemcachedTimestampsRegion;

/**
 * 
 * @author wangwd
 * @version $Revision: 128 $, $Date: 2015-3-30 下午2:05:52$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-05 11:17:29#$
 */
public class XMemcachedRegionFactory implements RegionFactory {

  private static final long serialVersionUID = -54604703998866597L;

  private final Logger log = LoggerFactory.getLogger(XMemcachedRegionFactory.class);
  
  private final ConcurrentMap<String, MemcachedCache> caches = new ConcurrentHashMap<String, MemcachedCache>();
  
  private Properties properties;
  private Memcache client;
  private Settings settings;
  
  public XMemcachedRegionFactory(Properties properties) {
      this.properties = properties;
  }
  
  public XMemcachedRegionFactory() {
  }
  
  @Override
  public void start(Settings settings, Properties properties) throws CacheException {
      this.settings = settings;
      this.properties = properties;
      log.info("Starting MemcachedClient...");
      try {
          client = getMemcachedClientFactory(new Config(new PropertiesHelper(properties)))
                  .createMemcacheClient();
      } catch (Exception e) {
          throw new CacheException("Unable to initialize MemcachedClient", e);
      }
  }

  @Override
  public void stop() {
      if (client != null) {
          log.debug("Shutting down Memcache client");
          client.shutdown();
      }
      client = null;
  }

  @Override
  public boolean isMinimalPutsEnabledByDefault() {
      return true;
  }

  @Override
  public AccessType getDefaultAccessType() {
       return AccessType.READ_WRITE;
  }

  @Override
  public long nextTimestamp() {
      return System.currentTimeMillis() / 100;
  }

  @Override
  public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
      return new MemcachedEntityRegion(getCache(regionName), settings,
                      metadata, properties, client);
  }

  @Override
  public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
      return new MemcachedCollectionRegion(getCache(regionName), settings,
                      metadata, properties, client);
  }

  @Override
  public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
      return new MemcachedQueryResultsRegion(getCache(regionName),
              properties, client);
  }

  @Override
  public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
      return new MemcachedTimestampsRegion(getCache(regionName),
              properties, client);
  }
  
  protected MemcacheClientFactory getMemcachedClientFactory(Config config) {
      String factoryClassName = config.getMemcachedClientFactoryName();

      Constructor<?> constructor;
      try {
          constructor = Class.forName(factoryClassName)
                  .getConstructor(PropertiesHelper.class);
      } catch (ClassNotFoundException e) {
          throw new CacheException(
                  "Unable to find factory class [" + factoryClassName + "]", e);
      } catch (NoSuchMethodException e) {
          throw new CacheException(
                  "Unable to find PropertiesHelper constructor for factory class [" + factoryClassName + "]", e);
      }

      MemcacheClientFactory clientFactory;
      try {
          clientFactory = (MemcacheClientFactory) constructor.newInstance(config.getPropertiesHelper());
      } catch (Exception e) {
          throw new CacheException(
                  "Unable to instantiate factory class [" + factoryClassName + "]", e);
      }

      return clientFactory;
  }
  
  private MemcachedCache getCache(String regionName)
  {
      return caches.get(regionName) == null
              ? new MemcachedCache(regionName, client) : caches.get(regionName);
  }

  @Override
  public NaturalIdRegion buildNaturalIdRegion(String regionName,
      Properties properties, CacheDataDescription metadata)
      throws CacheException {
    return null;
  }

}
