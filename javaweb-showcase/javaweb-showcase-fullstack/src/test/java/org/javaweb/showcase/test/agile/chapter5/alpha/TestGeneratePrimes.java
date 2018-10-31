/**
 * $Id: TestGeneratePrimes.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.alpha;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TestGeneratePrimes extends TestCase {

  /**
   * @param name
   */
  public TestGeneratePrimes(String name) {
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

  public static void main(String args[]) {
    //junit.swingui.TestRunner.main(new String[]{"TestGeneratePrimes"});
  }
  
  public void testGeneratePrimes() {
    int[] nullArray = GeneratePrimes.generatePrimes(0);
    assertEquals(nullArray.length, 0);
    
    int[] minArray = GeneratePrimes.generatePrimes(2);
    assertEquals(minArray.length, 1);
    assertEquals(minArray[0], 2);
    
    int[] threeArray = GeneratePrimes.generatePrimes(3);
    assertEquals(threeArray.length, 2);
    assertEquals(threeArray[0], 2);
    assertEquals(threeArray[1], 3);
    
    int[] centArray = GeneratePrimes.generatePrimes(100);
    assertEquals(centArray.length, 25);
    assertEquals(centArray[24], 97);
  }
  
  public void testPrintPrimes() {
    int[] nullArray = GeneratePrimes.generatePrimes(0);
    System.out.println("The nullArray: ");
    printArray(nullArray);
    
    int[] minArray = GeneratePrimes.generatePrimes(2);
    System.out.println("\nThe minArray: ");
    printArray(minArray);
    
    int[] threeArray = GeneratePrimes.generatePrimes(3);
    System.out.println("\nThe threeArray: ");
    printArray(threeArray);
    
    int[] centArray = GeneratePrimes.generatePrimes(100);
    System.out.println("\nThe centArray: ");
    printArray(centArray);
  }
  
  private void printArray(int[] o) {
    if (o == null) {
      System.out.println("The Array is null");
    } else if (o.length == 0) {
      System.out.println("The Array is empty");
    } else {
      for (int i = 0; i < o.length; i++) {
        if (((i + 1) % 10) != 0) {
          System.out.print(o[i] + " ");
        } else {
          System.out.print(o[i] + "\n");
        }
      }
    }
  }
}
