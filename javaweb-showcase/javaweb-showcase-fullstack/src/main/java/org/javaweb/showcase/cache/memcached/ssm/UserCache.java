/*
 * $Id: UserCache.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/cache/memcached/ssm/UserCache.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.cache.memcached.ssm;

import javax.annotation.PostConstruct;

import org.javaweb.showcase.model.User;
import org.javaweb.showcase.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午10:07:47$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
//@Component
public class UserCache implements SSMMemcached<String, User> {

  /**
   * 信息在 Memcached 中的过期时间（秒）。设置为0意味着永不过期，默认为0。
   */
  private static final int EXPIRATION = 0;
  
  private static final Logger log = LoggerFactory.getLogger(UserCache.class);
  
  @Autowired
  private UserService homeService;

  //@Autowired
  private com.google.code.ssm.Cache cache;
  
  /**
   * 该类实例化后，调用该方法将所有错误码放入 Memcached 中
   */
  @PostConstruct
  public void init() {
    /*
    List<ErrorCode> errorCodes = errorCodeService.findAllErrorCode();

    for (ErrorCode e : errorCodes) {
      put(e.getCodeAlias(), e);
    }
    */
  }
  
  @Override
  //@ReadThroughSingleCache(namespace = ERROR_CODE_CACHE_SPACE, expiration = EXPIRATION)
  public void put(String k, User v) {
    /*
    try {
      cache.set(codeAlias, EXPIRATION, errorCode, SerializationType.JAVA);
      if (log.isDebugEnabled()) {
        log.debug("putting error code in memcached. {}", errorCode);
      }
    } catch (Exception e) {
      log.warn("set error code in memcached occur error: "
          + e.getMessage());
    }
    */
  }

  @Override
  //@ReadThroughSingleCache(namespace = ERROR_CODE_CACHE_SPACE, expiration = EXPIRATION)
  public User get(String k) {
    /*
     ErrorCode ec = null;
    try {
      ec = cache.get(codeAlias, SerializationType.JAVA);
      if (log.isDebugEnabled()) {
        log.debug("got error code from memcached. {}", ec);
      }
    } catch (Exception e) {
      log.warn("get error code from memcached occur error: "
          + e.getMessage());
    }
    if (ec == null) {
      ec = errorCodeService.findErrorCodeByCodeAlias(codeAlias);
      log.warn("got error code from database. {}", ec);
    }
    return ec;
     */
    return null;
  }

  @Override
  //@UpdateSingleCache(namespace = ERROR_CODE_CACHE_SPACE, expiration =
  // EXPIRATION)
  // @ParameterValueKeyProvider
  // @ParameterDataUpdateContent
  public void update(String k, User v) {
    this.put(k, v);
  }

  @Override
  //@InvalidateSingleCache(namespace = ERROR_CODE_CACHE_SPACE)
  public void remove(String k) {
    /*
    try {
      cache.delete(codeAlias);
      if (log.isDebugEnabled()) {
        log.debug(
            "removed error code from memcached. error code alias: [{}]",
            codeAlias);
      }
    } catch (Exception e) {
      log.error("remov error code from memcached occur error: "
          + e.getMessage());
    }
    */
  }

}
