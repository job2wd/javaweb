/*
 * $Id: BaseJunitTest.java 139 2017-02-06 06:17:48Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/BaseJunitTest.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;


/**
 * 
 * @author wangwd
 * @version $Revision: 139 $, $Date: 2014-8-9 上午10:35:06$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:48#$
 */
public class BaseJunitTest {
  
  protected static long allStartTime = 0L;
  protected static long allEndTime = 0L;
  
  protected long startTime = 0L;
  protected long endTime = 0L;
  
  protected static boolean PRINT_STATISTICS_LOG = true;
  
  @Rule
  public TestName testName = new TestName();
    
  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    if(PRINT_STATISTICS_LOG) {
      allStartTime = System.currentTimeMillis();
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if(PRINT_STATISTICS_LOG) {
      allEndTime = System.currentTimeMillis();
      
      long time = allEndTime - allStartTime;
      
      System.err.println("|============================================|");
      System.err.println("|总计耗时：[" + time + "] 毫秒，" + "合：[" + time / 1000 + "] 秒|");
      System.err.println("|============================================|\n");
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    if(PRINT_STATISTICS_LOG) {
      System.err.println("|------ Test [" + testName.getMethodName() + "] Starting....");
      //System.out.println("Test [" + testName.getMethodName() + "] Starting.... \n");
      
      startTime = System.currentTimeMillis();
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    if(PRINT_STATISTICS_LOG) {
      endTime = System.currentTimeMillis();
      
      //System.out.println("\nTest [" + testName.getMethodName() + "]  Finished!");
      
      long time = endTime - startTime;
      
      System.err.println("单元测试耗时：[" + time + "] 毫秒，" + "合：[" + time / 1000 + "] 秒");
      System.err.println("Test [" + testName.getMethodName() + "]  Finished! ------|\n");
    }
  }
  
}
