/**
 * $Id: CustomerBo.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop.aspectj;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午05:30:47
 */
public interface CustomerBo {

  void addCustomer();

  String addCustomerReturnValue();

  void addCustomerThrowException() throws Exception;

  void addCustomerAround(String name);

}
