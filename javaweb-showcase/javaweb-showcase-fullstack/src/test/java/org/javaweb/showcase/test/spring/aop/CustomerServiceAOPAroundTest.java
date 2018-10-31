/**
 * $Id: CustomerServiceAOPAroundTest.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.spring.aop;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-23 下午04:44:55
 */
public class CustomerServiceAOPAroundTest {

  private ApplicationContext appContext;

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    appContext = new ClassPathXmlApplicationContext(new String[] { "com/bop/demo/web/spring/aop/Spring-Customer-around.xml" });
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testCustService() {
    CustomerService cust = (CustomerService) appContext.getBean("customerServiceProxy");

    System.out.println("*************************");
    cust.printName();
    System.out.println("*************************");
    cust.printURL();
    System.out.println("*************************");
    try {
      cust.printThrowException();
    } catch (Exception e) {
      //System.out.println(e.getMessage());
    }
  }

}
