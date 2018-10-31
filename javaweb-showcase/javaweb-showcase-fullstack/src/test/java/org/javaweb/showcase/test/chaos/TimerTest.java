/**
 * $Id: TimerTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TimerTest extends TestCase {

  /**
   * @param name
   */
  public TimerTest(String name) {
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

  public void testTimer() throws Exception {
    Timer timer = new Timer();
    timer.schedule(new MyTask(), 1000, 2000);
    int num = 1;
    while (true) {
      int input = System.in.read();
      if (num == 5) {
    	  System.out.println("The Timer Task \"MyTask\" auto be stoped! num: " + num);
    	  break;
      }
      if (input - 'c' == 0) {
        timer.cancel();
        System.out.println("The Timer Task \"MyTask\" be stoped!");
        break;
      }
      num++;
    }
  }
  
  class MyTask extends TimerTask {

    @Override
    public void run() {
      System.out.println("The Timer Task \"MyTask\" running... \nPlease input 'c' and press 'Enter' cancel run!\n");
    }
    
  }
  
}
