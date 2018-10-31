/**
 * $Id: CustomerBoImpl.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop.aspectj;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午05:31:43
 */
public class CustomerBoImpl implements CustomerBo {

  /*
   * (non-Javadoc)
   * 
   * @see com.bop.demo.web.spring.aop.aspectj.CustomerBo#addCustomer()
   */
  @Override
  public void addCustomer() {
    System.out.println("addCustomer() is running ");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.bop.demo.web.spring.aop.aspectj.CustomerBo#addCustomerAround(java.lang
   * .String)
   */
  @Override
  public void addCustomerAround(String name) {
    System.out.println("addCustomerAround() is running, args : " + name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.bop.demo.web.spring.aop.aspectj.CustomerBo#addCustomerReturnValue()
   */
  @Override
  public String addCustomerReturnValue() {
    System.out.println("addCustomerReturnValue() is running ");
    return "abc";
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.bop.demo.web.spring.aop.aspectj.CustomerBo#addCustomerThrowException()
   */
  @Override
  public void addCustomerThrowException() throws Exception {
    System.out.println("addCustomerThrowException() is running ");
    throw new Exception("Generic Error");
  }

}
