/*
 * $Id: EmptyStringToNullProcessorRegistry.java 4156 2018-06-15 01:22:11Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/EmptyStringToNullProcessorRegistry.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.web.springmvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 
 * @author wangwd
 * @version $Revision: 4156 $, $Date: 2018年6月15日 上午8:53:31$
 * @LastChanged $Author: wangweidong $, $Date:: #$
 */
public class EmptyStringToNullProcessorRegistry implements ApplicationContextAware, BeanFactoryPostProcessor {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);

    List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();

    List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<HandlerMethodArgumentResolver>();

    for (HandlerMethodArgumentResolver resolver : resolvers) {
      newResolvers.add(resolver);
    }
    EmptyStringToNullModelAttributeMethodProcessor processor = new EmptyStringToNullModelAttributeMethodProcessor(true);
    processor.setApplicationContext(applicationContext);
    newResolvers.add(0, processor);
    requestMappingHandlerAdapter.setArgumentResolvers(Collections.unmodifiableList(newResolvers));
  }

}
