/**
 * $Id: PrimeGenerator.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.release;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class PrimeGenerator {

  private static boolean[] crossedOut;

  private static int[] result;

  /**
   * 
   */
  public PrimeGenerator() {
    // TODO Auto-generated constructor stub
  }

  public static int[] generatePrimes(int maxVlue) {
    if (maxVlue >= 2) {
      uncrossIntegersUpTo(maxVlue);
      crossOutMultiples();
      putUncrossedIntegerIntoResult();
      return result;
    } else {
      return new int[0];
    }
  }

  private static void uncrossIntegersUpTo(int maxVlue) {
    crossedOut = new boolean[maxVlue + 1];
    for (int i = 2; i < crossedOut.length; i++) {
      crossedOut[i] = false;
    }
  }

  private static void crossOutMultiples() {
    int limit = determineIterationLimit();
    for (int i = 2; i <= limit; i++) {
      if (notCrossed(i)) {
        crossOutMutiplesOf(i);
      }
    }
  }

  private static int determineIterationLimit() {
    double iterrationLimit = Math.sqrt(crossedOut.length);
    return (int) iterrationLimit;
  }

  private static boolean notCrossed(int i) {
    return crossedOut[i] == false;
  }

  private static void crossOutMutiplesOf(int i) {
    for (int mutiple = 2 * i; mutiple < crossedOut.length; mutiple += i) {
      crossedOut[mutiple] = true;
    }
  }

  private static void putUncrossedIntegerIntoResult() {
    result = new int[numberOfUncrossedIntegers()];
    for (int i = 2, j = 0; i < crossedOut.length; i++) {
      if (notCrossed(i)) {
        result[j++] = i;
      }
    }
  }

  private static int numberOfUncrossedIntegers() {
    int count = 0;
    for (int i = 2; i < crossedOut.length; i++) {
      if (notCrossed(i)) {
        count++;
      }
    }
    return count;
  }

}
