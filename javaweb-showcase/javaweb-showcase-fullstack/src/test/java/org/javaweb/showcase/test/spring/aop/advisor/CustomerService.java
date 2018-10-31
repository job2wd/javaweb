/**
 * $Id: CustomerService.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop.advisor;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午04:24:05
 */
public class CustomerService {

  private String name;
  private String url;

  public void setName(String name) {
    this.name = name;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void printName() {
    System.out.println("Customer name : " + this.name);
  }

  public void printURL() {
    System.out.println("Customer website : " + this.url);
  }

  public void printThrowException() {
    throw new IllegalArgumentException("Empty illegal argument exception.");
  }

}
