package org.javaweb.data.service.cache.memcached.hibernate4.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.GeneralDataRegion;
import org.hibernate.cfg.Settings;
import org.javaweb.data.service.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.data.service.cache.memcached.hibernate4.Hibernate4MemcachedRegionFactory;
import org.javaweb.data.service.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.data.service.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.data.service.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class GeneralDataMemcachedRegion extends MemcachedRegion implements GeneralDataRegion {
    private Logger log = LoggerFactory.getLogger(GeneralDataMemcachedRegion.class);

    private int expirySeconds;

    public GeneralDataMemcachedRegion(CacheNamespace cacheNamespace, OverridableReadOnlyProperties properties, CacheDataDescription metadata,
                                      Settings settings, MemcachedAdapter memcachedAdapter, HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(cacheNamespace, properties, metadata, settings, memcachedAdapter, hibernateCacheTimestamper);
        populateExpirySeconds(properties);
    }

    void populateExpirySeconds(OverridableReadOnlyProperties properties) {
        String regionExpirySecondsKey = Hibernate4MemcachedRegionFactory.REGION_EXPIRY_SECONDS_PROPERTY_KEY_PREFIX + "." + getCacheNamespace().getName();
        String expirySecondsProperty = properties.getProperty(regionExpirySecondsKey);
        if (expirySecondsProperty == null) {
            expirySecondsProperty = properties.getProperty(Hibernate4MemcachedRegionFactory.REGION_EXPIRY_SECONDS_PROPERTY_KEY_PREFIX);
        }
        if (expirySecondsProperty == null) {
            throw new IllegalStateException(regionExpirySecondsKey + " or " + Hibernate4MemcachedRegionFactory.REGION_EXPIRY_SECONDS_PROPERTY_KEY_PREFIX
                    + "(for default expiry seconds) required!");
        }

        expirySeconds = Integer.parseInt(expirySecondsProperty);
        log.info("expirySeconds of cache region [{}] - {} seconds.", getCacheNamespace().getName(), expirySeconds);
    }

    @Override
    public Object get(Object key) throws CacheException {
        String refinedKey = refineKey(key);

        log.debug("Cache get [{}] : key[{}]", getCacheNamespace(), refinedKey);
        Object cachedData = getMemcachedAdapter().get(getCacheNamespace(), refinedKey);

        if (cachedData == null) {
            return null;
        }

        if (!(cachedData instanceof CacheItem)) {
            log.debug("get cachedData is not CacheItem.");
            return cachedData;
        }

        CacheItem cacheItem = (CacheItem) cachedData;
        boolean targetClassAndCurrentJvmTargetClassMatch = cacheItem.isTargetClassAndCurrentJvmTargetClassMatch();
        log.debug("cacheItem and targetClassAndCurrentJvmTargetClassMatch : {} / {}", targetClassAndCurrentJvmTargetClassMatch, cacheItem);

        if (cacheItem.isTargetClassAndCurrentJvmTargetClassMatch()) {
            return cacheItem.getCacheEntry();
        }

        return null;
    }

    @Override
    public void put(Object key, Object value) throws CacheException {

        Object valueToCache = value;

        boolean classVersionApplicable = CacheItem.checkIfClassVersionApplicable(value, getSettings().isStructuredCacheEntriesEnabled());

        if (classVersionApplicable) {
            valueToCache = new CacheItem(value, getSettings().isStructuredCacheEntriesEnabled());
        }

        String refinedKey = refineKey(key);
        log.debug("Cache put [{}] : key[{}], value[{}], classVersionApplicable : {}", getCacheNamespace(), refinedKey,
                valueToCache, classVersionApplicable);
        getMemcachedAdapter().set(getCacheNamespace(), refinedKey, valueToCache, getExpiryInSeconds());
    }

    @Override
    public void evict(Object key) throws CacheException {
        String refinedKey = refineKey(key);
        log.debug("Cache evict[{}] : key[{}]", getCacheNamespace(), refinedKey);
        getMemcachedAdapter().delete(getCacheNamespace(), refinedKey);
    }

    @Override
    public void evictAll() throws CacheException {
        log.debug("Cache evictAll [{}].", getCacheNamespace());
        getMemcachedAdapter().evictAll(getCacheNamespace());
    }

    /**
     * Read expiry seconds from configuration properties
     */
    protected int getExpiryInSeconds() {
        return expirySeconds;
    }

    /**
     * Memcached has limitation of key size. Shorten the key to avoid the limitation if needed.
     */
    protected String refineKey(Object key) {
        return String.valueOf(key);
    }
}
