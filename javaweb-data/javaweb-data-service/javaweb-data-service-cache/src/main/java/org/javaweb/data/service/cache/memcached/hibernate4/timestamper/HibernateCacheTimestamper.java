package org.javaweb.data.service.cache.memcached.hibernate4.timestamper;

import org.hibernate.cfg.Settings;
import org.javaweb.data.service.cache.memcached.hibernate4.MemcachedAdapter;
import org.javaweb.data.service.cache.memcached.hibernate4.util.OverridableReadOnlyProperties;


/**
 * @author KwonNam Son (kwon37xi@gmail.com)
 */
public interface HibernateCacheTimestamper {

    void setSettings(Settings settings);

    void setProperties(OverridableReadOnlyProperties properties);

    void setMemcachedAdapter(MemcachedAdapter memcachedAdapter);

    /** initialize timestamp object */
    void init();

    /** get next timestamp */
    long next();
}