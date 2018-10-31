/*
 * $Id: SimpleSpringMappingExceptionResolver.java 5990 2018-09-27 08:20:10Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/SimpleSpringMappingExceptionResolver.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javaweb.common.util.JavaExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


/**
 * 
 * @author wangwd
 * @version $Revision: 5990 $, $Date: 2018年5月31日 下午1:48:01$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-27 16:20:10#$
 */
public class SimpleSpringMappingExceptionResolver extends SimpleMappingExceptionResolver {

  private Logger log = LoggerFactory.getLogger(SimpleSpringMappingExceptionResolver.class);
  
  private List<Class<?>> excludeLogExceptions;
  
  @Override
  protected String buildLogMessage(Exception ex, HttpServletRequest request) {
    return JavaExceptionUtils.getMessage(ex);
  }

  @Override
  protected void logException(Exception ex, HttpServletRequest request) {
    if(excludeLogExceptions.contains(ex.getClass())) {
      return;
    }
    log.warn("{}[{}]: {}", request.getRequestURI(), request.getRemoteAddr(), buildLogMessage(ex, request));
  }

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
      Exception ex) {
    return super.doResolveException(request, response, handler, ex);
  }

  public List<Class<?>> getExcludeLogExceptions() {
    return excludeLogExceptions;
  }

  public void setExcludeLogExceptions(List<Class<?>> excludeLogExceptions) {
    this.excludeLogExceptions = excludeLogExceptions;
  }

}
