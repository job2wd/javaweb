package org.javaweb.data.service.cache.memcached.hibernate4.regions;

import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cfg.Settings;
import org.javaweb.data.service.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.data.service.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.data.service.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.data.service.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;

/**
 * {@link org.hibernate.cache.spi.TimestampsRegion} has no concurrency strategy.
 * It deals <code>[cache-region-prefix.]org.hibernate.cache.spi.UpdateTimestampsCache</code>.
 * <p/>
 *
 * This region should have very long expiry seconds.
 *
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class TimestampMemcachedRegion extends GeneralDataMemcachedRegion implements TimestampsRegion {
    public TimestampMemcachedRegion(String regionName, OverridableReadOnlyProperties properties, Settings settings,
                                    MemcachedAdapter memcachedAdapter,
                                    HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(new CacheNamespace(regionName, false), properties, null, settings, memcachedAdapter,
              hibernateCacheTimestamper);
    }
}