/**
 * $Id: HijackAroundMethod.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午05:21:59
 */
public class HijackAroundMethod implements MethodInterceptor {

  /*
   * (non-Javadoc)
   * 
   * @see
   * 
   * 
   * 
   * 
   * 
   * org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept
   * .MethodInvocation)
   */
  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    System.out.println("Method name : " + methodInvocation.getMethod().getName());
    System.out.println("Method arguments : " + Arrays.toString(methodInvocation.getArguments()));

    // same with MethodBeforeAdvice
    System.out.println("HijackAroundMethod : Before method hijacked!");

    try {
      // proceed to original method call
      Object result = methodInvocation.proceed();

      // same with AfterReturningAdvice
      System.out.println("HijackAroundMethod : Before after hijacked!");

      return result;

    } catch (IllegalArgumentException e) {
      // same with ThrowsAdvice
      System.out.println("HijackAroundMethod : Throw exception hijacked!");
      throw e;
    }
  }

}
