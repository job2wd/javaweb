/**
 * $Id: AddressTest.java 80 2017-01-20 04:21:11Z job2wd $
 * @mail job2wd@gmail.com
 */
package com.bop.commons.model.entity;

import junit.framework.TestCase;


/**
 * Used Junit3
 * 
 * @author David
 * @version 1.0
 * @date 2012-1-30 上午10:59:42
 */
public class AddressTest extends TestCase {

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    System.out.println("set up....");
    //super.setUp();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    System.out.println("tear down");
    //super.tearDown();
  }

  public void testEquals() {
    Address add = new Address();
    
    assertTrue(add.equals(add));
    
    System.out.println("OK!");
  }
  
}
