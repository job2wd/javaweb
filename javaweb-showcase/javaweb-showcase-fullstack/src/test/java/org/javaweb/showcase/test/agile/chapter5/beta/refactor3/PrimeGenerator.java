/**
 * $Id: PrimeGenerator.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.beta.refactor3;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class PrimeGenerator {

  private static boolean[] isCrossed;

  private static int[] result;

  /**
   * 
   */
  public PrimeGenerator() {
    // TODO Auto-generated constructor stub
  }

  public static int[] generatePrimes(int maxVlue) {
    if (maxVlue >= 2) {
      initializeArrayOfInteger(maxVlue);
      crossOutMultiples();
      putCrossedIntegerIntoResult();
      return result;
    } else {
      return new int[0];
    }
  }

  private static void initializeArrayOfInteger(int maxVlue) {
    isCrossed = new boolean[maxVlue + 1];
    for (int i = 2; i < isCrossed.length; i++) {
      isCrossed[i] = true;
    }
  }

  private static void crossOutMultiples() {
    int maxPrimeFactor = calcMaxPrimeFactor();
    for (int i = 2; i < maxPrimeFactor; i++) {
      if (isCrossed[i]) {
        crossOutMutiplesOf(i);
      }
    }
  }

  private static int calcMaxPrimeFactor() {
    double maxPrimeFactor = Math.sqrt(isCrossed.length) + 1;
    return (int) maxPrimeFactor;
  }

  private static void crossOutMutiplesOf(int i) {
    for (int mutiple = 2 * i; mutiple < isCrossed.length; mutiple += i) {
      isCrossed[mutiple] = false;
    }
  }

  private static void putCrossedIntegerIntoResult() {
    result = new int[numberOfCrossedIntegers()];
    for (int i = 0, j = 0; i < isCrossed.length; i++) {
      if (isCrossed[i]) {
        result[j++] = i;
      }
    }
  }

  private static int numberOfCrossedIntegers() {
    int count = 0;
    for (int i = 0; i < isCrossed.length; i++) {
      if (isCrossed[i]) {
        count++;
      }
    }
    return count;
  }

}
