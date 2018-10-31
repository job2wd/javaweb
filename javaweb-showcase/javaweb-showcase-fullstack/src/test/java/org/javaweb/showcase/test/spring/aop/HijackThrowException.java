/**
 * $Id: HijackThrowException.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop;

import org.springframework.aop.ThrowsAdvice;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午05:14:30
 */
public class HijackThrowException implements ThrowsAdvice {

  public void afterThrowing(IllegalArgumentException e) throws Throwable {
    System.out.println("HijackThrowException : Throw exception hijacked!");
  }

}
