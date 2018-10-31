package org.javaweb.showcase.cache.memcached.hibernate4.regions;

import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.TransactionalDataRegion;
import org.hibernate.cfg.Settings;
import org.javaweb.showcase.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.showcase.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.showcase.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.showcase.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class TransactionalDataMemcachedRegion extends GeneralDataMemcachedRegion implements TransactionalDataRegion {

    public TransactionalDataMemcachedRegion(CacheNamespace cacheNamespace, OverridableReadOnlyProperties properties, CacheDataDescription metadata, Settings settings,
                                            MemcachedAdapter memcachedAdapter, HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(cacheNamespace, properties, metadata, settings, memcachedAdapter, hibernateCacheTimestamper);
    }

    @Override
    public boolean isTransactionAware() {
        return false;
    }

    @Override
    public CacheDataDescription getCacheDataDescription() {
        return getMetadata();
    }
}
