/*
 * $Id: MultipleDataSourceInterceptor.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/interceptor/MultipleDataSourceInterceptor.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.interceptor;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.javaweb.showcase.dao.base.BaseConfDao;
import org.javaweb.showcase.dao.base.BaseDataDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 利用 AOP 实现动态切换数据源
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-5-26 下午7:55:56$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
//@Component
//@Aspect
public class MultipleDataSourceInterceptor implements MethodInterceptor {//, InitializingBean {

  private static Logger log = LoggerFactory.getLogger(MultipleDataSourceInterceptor.class);//getClass());
  
  //@Before("execution(* org.javaweb..dao..*Dao*.*(..))")
  // @Before("execution(* org.javaweb..dao.base.*Dao*.*(..)) ||
            //execution(* org.javaweb..dao.conf.*Dao*.*(..)) ||
            //execution(* org.javaweb..dao.data.*Dao*.*(..))"))
  public void dynamicSetDataSoruce(JoinPoint joinPoint) throws Exception {
    Class<?> clazz = joinPoint.getThis().getClass();

    if (clazz.isAssignableFrom(Proxy.class)) {
      clazz = joinPoint.getSignature().getDeclaringType();
    }
    
    System.out.println("superclass============" + clazz.getSuperclass());
    System.out.println("interface=============" + clazz.getInterfaces());
    
    Class<?>[] clazzs = clazz.getInterfaces();
    
    for(Class<?> clz : clazzs) {
      System.out.println("clz===>" + clz.getDeclaredClasses());
      if(clz.isAssignableFrom(org.springframework.aop.framework.Advised.class)) {
        System.out.println(clz);
      }
    }
    
    log.debug("execute {}.{}({})", clazz.getName(), joinPoint.getSignature().getName(), joinPoint.getArgs());

    if (clazz.isAssignableFrom(BaseConfDao.class)) {
      DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_CONF);
    } else if (clazz.isAssignableFrom(BaseDataDao.class)) {
      DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_DATA);
    } else {
      DatabaseContextHolder.clearCustomerType();
    }
  }

  //@Override
  public void afterPropertiesSet() throws Exception {
    log.debug("afterPropertiesSet....");
  }

  //@Override
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Class<?> clazz = invocation.getThis().getClass();

    if (clazz.isAssignableFrom(Proxy.class)) {
      clazz = invocation.getMethod().getDeclaringClass();
    }

    log.debug("execute {}.{}({})", clazz.getName(), invocation.getMethod().getName(), invocation.getArguments());

    if (clazz.isAssignableFrom(BaseConfDao.class)) {
      DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_CONF);
    } else if (clazz.isAssignableFrom(BaseDataDao.class)) {
      DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_DATA);
    } else {
      DatabaseContextHolder.clearCustomerType();
    }
    
    return invocation.proceed();
  }
  
}
