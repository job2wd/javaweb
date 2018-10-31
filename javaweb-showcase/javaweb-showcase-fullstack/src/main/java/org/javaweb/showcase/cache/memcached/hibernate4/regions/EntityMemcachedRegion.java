package org.javaweb.showcase.cache.memcached.hibernate4.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cfg.Settings;
import org.javaweb.showcase.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.showcase.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.showcase.cache.memcached.hibernate4.strategies.NonstrictReadWriteEntityRegionAccessStrategy;
import org.javaweb.showcase.cache.memcached.hibernate4.strategies.ReadOnlyEntityRegionAccessStrategy;
import org.javaweb.showcase.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.showcase.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class EntityMemcachedRegion extends TransactionalDataMemcachedRegion implements EntityRegion {
    public EntityMemcachedRegion(String regionName, OverridableReadOnlyProperties properties, CacheDataDescription metadata, Settings settings, MemcachedAdapter memcachedAdapter, HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(new CacheNamespace(regionName, true), properties, metadata, settings, memcachedAdapter, hibernateCacheTimestamper);
    }

    @Override
    public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        switch (accessType) {
            case READ_ONLY:
                return new ReadOnlyEntityRegionAccessStrategy(this);
            case NONSTRICT_READ_WRITE:
                return new NonstrictReadWriteEntityRegionAccessStrategy(this);
            default:
                throw new CacheException("Unsupported access strategy : " + accessType + ".");
        }
    }
}
