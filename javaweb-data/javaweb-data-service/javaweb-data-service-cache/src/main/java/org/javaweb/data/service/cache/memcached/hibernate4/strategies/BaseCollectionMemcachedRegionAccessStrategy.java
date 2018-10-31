package org.javaweb.data.service.cache.memcached.hibernate4.strategies;

import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.javaweb.data.service.cache.memcached.hibernate4.regions.CollectionMemcachedRegion;


/**
 * {@link CollectionRegionAccessStrategy}는 insert, update에는 반응하지 않으면 delete와 load에만 반응한다.
 *
 * @author KwonNam Son (kwon37xi@gmail.com)
 * @see org.hibernate.cache.spi.access.CollectionRegionAccessStrategy
 */
public abstract class BaseCollectionMemcachedRegionAccessStrategy extends MemcachedRegionAccessStrategy implements CollectionRegionAccessStrategy {
    private CollectionMemcachedRegion collectionMemcachedRegion;

    public BaseCollectionMemcachedRegionAccessStrategy(CollectionMemcachedRegion collectionMemcachedRegion) {
        super(collectionMemcachedRegion);
        this.collectionMemcachedRegion = collectionMemcachedRegion;
    }

    @Override
    public CollectionRegion getRegion() {
        return collectionMemcachedRegion;
    }
}
