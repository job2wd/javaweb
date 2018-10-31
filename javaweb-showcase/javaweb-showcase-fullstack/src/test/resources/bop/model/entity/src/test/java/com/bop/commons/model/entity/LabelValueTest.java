/**
 * $Id: LabelValueTest.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package com.bop.commons.model.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Used Junit4
 * 
 * @author David
 * @version 1.0
 * @date 2012-1-30 上午10:38:53
 */
public class LabelValueTest {

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println("set up before class....");
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("tear down after class");
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    System.out.println("set up....");
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    System.out.println("tear down");
  }

  @Test
  public void testEquals() {
    LabelValue lv = new LabelValue();
    LabelValue otherlv = new LabelValue();

    assertFalse(lv.equals(null));    
    assertTrue(lv.equals(lv));
    assertTrue(lv.equals(otherlv));//compare used property: label
    
    System.out.println("OK!");
  }

}
