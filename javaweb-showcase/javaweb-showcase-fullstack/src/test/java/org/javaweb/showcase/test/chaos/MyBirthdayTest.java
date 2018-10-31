/**
 * $Id: MyBirthdayTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class MyBirthdayTest extends TestCase {

  /**
   * @param name
   */
  public MyBirthdayTest(String name) {
    super(name);
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testMyBirthday() throws Exception {
    SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    Date b = f.parse("19820503");
    Date e = f.parse("20091231");
    
    long days = (e.getTime() - b.getTime())/(24 * 60 * 60 * 1000);
    long hours = days * 24;
    long minutes = hours * 60;
    long seconds = minutes * 60;
    
    System.out.println("days: " + days);
    System.out.println("hours: " + hours);
    System.out.println("minutes: " + minutes);
    System.out.println("seconds: " + seconds);
  }
}
