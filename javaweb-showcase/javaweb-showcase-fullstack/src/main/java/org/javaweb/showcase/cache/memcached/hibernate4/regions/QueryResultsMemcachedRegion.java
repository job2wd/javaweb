package org.javaweb.showcase.cache.memcached.hibernate4.regions;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cfg.Settings;
import org.javaweb.showcase.cache.memcached.hibernate4.CacheNamespace;
import org.javaweb.showcase.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.showcase.cache.memcached.hibernate4.timestamper.HibernateCacheTimestamper;
import org.javaweb.showcase.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * {@link org.hibernate.cache.spi.QueryResultsRegion}은 strategy가 없다.
 *
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class QueryResultsMemcachedRegion extends GeneralDataMemcachedRegion implements QueryResultsRegion {
    private Logger log = LoggerFactory.getLogger(QueryResultsMemcachedRegion.class);

    public QueryResultsMemcachedRegion(String regionName, OverridableReadOnlyProperties properties, Settings settings,
                                       MemcachedAdapter memcachedAdapter, HibernateCacheTimestamper hibernateCacheTimestamper) {
        super(new CacheNamespace(regionName, true), properties, null, settings, memcachedAdapter, hibernateCacheTimestamper);
    }

    /**
     * {@inheritDoc}
     *
     * Because query cache key contains all the SQL statement and addtional information,
     * it's too long for memcached cache key.
     * So shorten the key by hashing.
     */
    @Override
    protected String refineKey(Object key) {
        String refinedKey = DigestUtils.md5Hex(String.valueOf(key)) + "_" + String.valueOf(key.hashCode());
        log.debug("QueryResultCache refineKey original {} to {}", key, refinedKey);
        return refinedKey;
    }
}
