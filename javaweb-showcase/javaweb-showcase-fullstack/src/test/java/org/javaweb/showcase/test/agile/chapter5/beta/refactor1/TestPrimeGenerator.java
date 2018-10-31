/**
 * $Id: TestPrimeGenerator.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.beta.refactor1;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TestPrimeGenerator extends TestCase {

  /**
   * @param name
   */
  public TestPrimeGenerator(String name) {
    super(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testGeneratePrimes() {
    int[] nullArray = PrimeGenerator.generatePrimes(0);
    assertEquals(nullArray.length, 0);

    int[] minArray = PrimeGenerator.generatePrimes(2);
    assertEquals(minArray.length, 1);
    assertEquals(minArray[0], 2);

    int[] threeArray = PrimeGenerator.generatePrimes(3);
    assertEquals(threeArray.length, 2);
    assertEquals(threeArray[0], 2);
    assertEquals(threeArray[1], 3);

    int[] centArray = PrimeGenerator.generatePrimes(100);
    assertEquals(centArray.length, 25);
    assertEquals(centArray[24], 97);
  }

}
