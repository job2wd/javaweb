/*
 * $Id: EnableSynchronizeOnSessionPostProcessor.java 3596 2018-05-18 08:10:27Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/EnableSynchronizeOnSessionPostProcessor.jav $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


/**
 * 
 * @author wangwd
 * @version $Revision: 3596 $, $Date: 2016-5-26 下午3:29:06$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-18 16:10:27#$
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

