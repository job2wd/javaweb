/**
 * $Id: PrimeGenerator.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.beta.refactor2;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class PrimeGenerator {

  private static boolean[] f;

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
      putUncrossedIntegerIntoResult();
      return result;
    } else {
      return new int[0];
    }
  }

  public static void initializeArrayOfInteger(int maxVlue) {
    f = new boolean[maxVlue + 1];
    for (int i = 0; i < f.length; i++) {
      f[i] = true;
    }
    f[0] = f[1] = false;

  }

  public static void crossOutMultiples() {
    int i;
    int j;
    for (i = 2; i < Math.sqrt(f.length) + 1; i++) {
      for (j = 2 * i; j < f.length; j += i) {
        f[j] = false;
      }
    }
  }

  public static void putUncrossedIntegerIntoResult() {
    int i;
    int j;
    int count = 0;
    for (i = 0; i < f.length; i++) {
      if (f[i]) {
        count++;
      }
    }
    result = new int[count];
    for (i = 0, j = 0; i < f.length; i++) {
      if (f[i]) {
        result[j++] = i;
      }
    }
  }

}
