package org.javaweb.data.service.cache.memcached.hibernate4.strategies;

import org.javaweb.data.service.cache.memcached.hibernate4.regions.CollectionMemcachedRegion;

/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class ReadOnlyCollectionRegionAccessStrategy extends BaseCollectionMemcachedRegionAccessStrategy {
    public ReadOnlyCollectionRegionAccessStrategy(CollectionMemcachedRegion collectionMemcachedRegion) {
        super(collectionMemcachedRegion);
    }
}