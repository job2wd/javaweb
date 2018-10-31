/*
 * $Id: BaseJunitTest.java 134 2017-02-06 06:14:28Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/test/java/org/javaweb/common/test/BaseJunitTest.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.test;

import java.text.MessageFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * 
 * @author wangwd
 * @version $Revision: 134 $, $Date: 2014-8-9 上午10:35:06$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:14:28#$
 */
public class BaseJunitTest {
  
  protected static long allStartTime = 0L;
  protected static long allEndTime = 0L;
  
  protected long startTime = 0L;
  protected long endTime = 0L;
  
  protected static boolean PRINT_STATISTICS_LOG = true;
  
  @Rule
  public TestName testName = new TestName();
  
  @Rule
  public Timeout globalTimeout= new Timeout(2 * 60 * 1000);//2分钟即为测试超时
  
  @Rule
  public TestWatcher testWatcher = new TestWatcher(){

    @Override
    public Statement apply(Statement base, Description description) {
      log("apply:{0}.{1}", description.getClassName(), testName.getMethodName());
      return super.apply(base, description);
    }

    @Override
    protected void succeeded(Description description) {
      super.succeeded(description);
      log("succeeded:{0}.{1}", description.getClassName(), testName.getMethodName());
    }

    @Override
    protected void failed(Throwable e, Description description) {
      super.failed(e, description);
      log("failed:" + description.getClassName());
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
      super.skipped(e, description);
      log("skipped:" + description.getClassName());
    }

    @Override
    protected void starting(Description description) {
      super.starting(description);
      log("starting:{0}.{1}", description.getClassName(), testName.getMethodName());
    }

    @Override
    protected void finished(Description description) {
      super.finished(description);
      log("finished:{0}.{1}", description.getClassName(), testName.getMethodName());
    }
    
  };
    
  public static void log(String msg, Object... args) {
    if(args != null && args.length > 0) {
      msg = MessageFormat.format(msg, args);
    }
    System.err.println(msg);
  }
  
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
      
      log("============================================");
      log("总计耗时：[" + time + "] 毫秒，" + "合：[" + time / 1000 + "] 秒|");
      log("============================================\n");
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    if(PRINT_STATISTICS_LOG) {
      log("======= Test [" + testName.getMethodName() + "] Starting =======");
      //log("Test [" + testName.getMethodName() + "] Starting.... \n");
      
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
      
      //log("\nTest [" + testName.getMethodName() + "]  Finished!");
      
      long time = endTime - startTime;
      
      log("单元测试耗时：[{0}] 毫秒，合：[{1}] 秒", time, time / 1000);
      log("======= Test [{0}] Finished =======\n", testName.getMethodName());
    }
  }
  
}
