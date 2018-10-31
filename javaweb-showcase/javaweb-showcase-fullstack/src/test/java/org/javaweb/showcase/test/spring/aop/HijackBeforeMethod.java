/**
 * $Id: HijackBeforeMethod.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午04:50:24
 */
public class HijackBeforeMethod implements MethodBeforeAdvice {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method,
   * java.lang.Object[], java.lang.Object)
   */
  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("HijackBeforeMethod : Before method hijacked!");
  }

}
