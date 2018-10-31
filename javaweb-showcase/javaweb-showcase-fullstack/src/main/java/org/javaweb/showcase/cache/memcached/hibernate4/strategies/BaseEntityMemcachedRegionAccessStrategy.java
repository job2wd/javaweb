package org.javaweb.showcase.cache.memcached.hibernate4.strategies;

import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.javaweb.showcase.cache.memcached.hibernate4.regions.EntityMemcachedRegion;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 * @see org.hibernate.cache.spi.access.EntityRegionAccessStrategy
 */
public abstract class BaseEntityMemcachedRegionAccessStrategy extends MemcachedRegionAccessStrategy implements EntityRegionAccessStrategy {
    private EntityMemcachedRegion entityMemcachedRegion;

    public BaseEntityMemcachedRegionAccessStrategy(EntityMemcachedRegion entityMemcachedRegion) {
        super(entityMemcachedRegion);
        this.entityMemcachedRegion = entityMemcachedRegion;
    }

    @Override
    public EntityRegion getRegion() {
        return entityMemcachedRegion;
    }
}
