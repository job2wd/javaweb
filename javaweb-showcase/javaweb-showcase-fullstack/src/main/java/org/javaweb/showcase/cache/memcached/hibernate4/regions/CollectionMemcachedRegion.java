package org.javaweb.showcase.cache.memcached.hibernate4.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cfg.Settings;
import org.javaweb.showcase.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.showcase.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.showcase.cache.memcached.hibernate4.strategies.NonstrictReadWriteCollectionRegionAccessStrategy;
import org.javaweb.showcase.cache.memcached.hibernate4.strategies.ReadOnlyCollectionRegionAccessStrategy;
import org.javaweb.showcase.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.showcase.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class CollectionMemcachedRegion extends TransactionalDataMemcachedRegion implements CollectionRegion {
    public CollectionMemcachedRegion(String regionName, OverridableReadOnlyProperties properties, CacheDataDescription metadata, Settings settings, MemcachedAdapter memcachedAdapter, HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(new CacheNamespace(regionName, true), properties, metadata, settings, memcachedAdapter, hibernateCacheTimestamper);
    }

    @Override
    public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        switch (accessType) {
            case READ_ONLY:
                return new ReadOnlyCollectionRegionAccessStrategy(this);
            case NONSTRICT_READ_WRITE:
                return new NonstrictReadWriteCollectionRegionAccessStrategy(this);
            default:
                throw new CacheException("Unsupported access strategy : " + accessType + ".");
        }
    }
}
