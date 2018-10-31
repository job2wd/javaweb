/*
 * $Id: EmptyStringToNullModelAttributeMethodProcessor.java 4156 2018-06-15 01:22:11Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/EmptyStringToNullModelAttributeMethodProces $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.common;

import javax.servlet.ServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

/**
 * 
 * @author wangwd
 * @version $Revision: 4156 $, $Date: 2018年6月15日 上午8:26:27$
 * @LastChanged $Author: wangweidong $, $Date:: #$
 */
public class EmptyStringToNullModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  public EmptyStringToNullModelAttributeMethodProcessor(boolean annotationNotRequired) {
    super(annotationNotRequired);
  }

  @Override
  protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
    EmptyStringToNullRequestDataBinder toNullRequestDataBinderBinder = new EmptyStringToNullRequestDataBinder(binder.getTarget(),
        binder.getObjectName());
    RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
    requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(toNullRequestDataBinderBinder);
    toNullRequestDataBinderBinder.bind(request.getNativeRequest(ServletRequest.class));
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

}
