/*
 * $Id: SSMMemcached.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/cache/memcached/ssm/SSMMemcached.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.cache.memcached.ssm;

import java.io.Serializable;


/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午10:03:10$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
@Deprecated
public interface SSMMemcached<K extends Serializable, V extends Serializable> {
  
  void put(K k, V v);
  
  V get(K k);
  
  void update(K k, V v);
  
  void remove(K k);
  
}
