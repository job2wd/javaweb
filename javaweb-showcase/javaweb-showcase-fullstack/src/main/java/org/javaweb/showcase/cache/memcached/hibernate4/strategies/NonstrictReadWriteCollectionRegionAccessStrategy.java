package org.javaweb.showcase.cache.memcached.hibernate4.strategies;

import org.javaweb.showcase.cache.memcached.hibernate4.regions.CollectionMemcachedRegion;

/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public class NonstrictReadWriteCollectionRegionAccessStrategy extends BaseCollectionMemcachedRegionAccessStrategy {
    public NonstrictReadWriteCollectionRegionAccessStrategy(CollectionMemcachedRegion collectionMemcachedRegion) {
        super(collectionMemcachedRegion);
    }
}