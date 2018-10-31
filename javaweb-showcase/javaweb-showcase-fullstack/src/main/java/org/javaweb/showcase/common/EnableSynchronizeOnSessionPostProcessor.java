/*
 * $Id: EnableSynchronizeOnSessionPostProcessor.java 135 2017-02-06 06:15:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/common/EnableSynchronizeOnSessionPostProcessor.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


/**
 * 
 * @author wangwd
 * @version $Revision: 135 $, $Date: 2016-5-26 下午3:29:06$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:15:11#$
 */
public class EnableSynchronizeOnSessionPostProcessor {//implements BeanPostProcessor {
  private static Logger logger = LoggerFactory
          .getLogger(EnableSynchronizeOnSessionPostProcessor.class);

  //@Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      // NO-OP
      return bean;
  }

  //@Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      if (bean instanceof RequestMappingHandlerAdapter) {
          RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
          logger.info("enable synchronizeOnSession => {}", adapter);
          adapter.setSynchronizeOnSession(true);
      }
      return bean;
  }

}

