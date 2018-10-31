/**
 * $Id: HijackAfterMethod.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午05:10:23
 */
public class HijackAfterMethod implements AfterReturningAdvice {

  /*
   * (non-Javadoc)
   * 
   * @see
   * 
   * org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object
   * , java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
   */
  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    System.out.println("HijackAfterMethod : After method hijacked!");
  }

}
