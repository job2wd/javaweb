package org.javaweb.data.service.cache.memcached.hibernate4.strategies;

import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.javaweb.data.service.cache.memcached.hibernate4.regions.NaturalIdMemcachedRegion;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 * @see org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy
 */
public abstract class BaseNaturalIdMemcachedRegionAccessStrategy extends MemcachedRegionAccessStrategy implements NaturalIdRegionAccessStrategy {
    private NaturalIdMemcachedRegion naturalIdMemcachedRegion;

    public BaseNaturalIdMemcachedRegionAccessStrategy(NaturalIdMemcachedRegion naturalIdMemcachedRegion) {
        super(naturalIdMemcachedRegion);
        this.naturalIdMemcachedRegion = naturalIdMemcachedRegion;
    }

    @Override
    public NaturalIdRegion getRegion() {
        return naturalIdMemcachedRegion;
    }
}
