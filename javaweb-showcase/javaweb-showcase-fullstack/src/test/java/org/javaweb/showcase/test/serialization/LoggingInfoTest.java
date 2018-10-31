/**
 * $Id: LoggingInfoTest.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-24 下午04:00:55
 */
public class LoggingInfoTest {

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
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testSerializationLoggingInfo() {
    LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
    System.out.println(logInfo.toString());
    try {
      ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("D:/Tmp/logInfo.out"));
      o.writeObject(logInfo);
      o.close();
    } catch (Exception e) {
      //deal with exception}
    }

    //To read the object back, we can write

    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/Tmp/logInfo.out"));
      LoggingInfo log = (LoggingInfo) in.readObject();
      System.out.println(log.toString());
      in.close();
    } catch (Exception e) {//deal with exception}
    }
  }

}
