/*
 * $Id: SSMMemcachedFactory.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/cache/memcached/ssm/SSMMemcachedFactory.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.cache.memcached.ssm;

import java.util.concurrent.TimeoutException;

import com.google.code.ssm.providers.CacheException;


/**
 * 缓存工厂类，用于统一获取缓存对象。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午10:03:59$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
//@Component("fbaMemcachedFactory")
@Deprecated
public class SSMMemcachedFactory {

  public static final String SID_KEY = "FBA_MEMCACHED_OBJ_";
  
  //@Autowired
  private com.google.code.ssm.Cache cache;
  
  //@Autowired
  public UserCache userInfoCache;
  
  /**
   * 删除用户信息
   * 
   * @param ssoId
   * @throws TimeoutException
   * @throws CacheException
   */
  public void delUserInfo(String ssoId) throws TimeoutException, CacheException {
    cache.delete(SID_KEY+ssoId);
  }
  
}
