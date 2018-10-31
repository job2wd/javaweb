/**
 * $Id: ComputingCount.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.math.BigInteger;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class ComputingCount extends TestCase {

  /**
   * @param name
   */
  public ComputingCount(String name) {
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
  
  public void testComputingCount() throws Exception {
    long start = System.currentTimeMillis();
    for (int i = 1; ;i++) {
      long curr = System.currentTimeMillis();
      if ((curr - start) == 1000) {
        System.out.println("Start: " + start + "    End: " + curr);
        System.out.println("Count: " + i );
        break;
      }
    }
  }
  
  public void test16Convert10() throws Exception {
    BigInteger bi2 = new BigInteger("363cbc1c24d6a754", 16);
    String res_10 = bi2.toString(10);
   
   BigInteger bi1 = new BigInteger("3908205405713901396", 10);
   String res_16 = bi1.toString(16);
   
   System.out.println("res_10: " + res_10);
   System.out.println("res_16: " + res_16);
  }

}
