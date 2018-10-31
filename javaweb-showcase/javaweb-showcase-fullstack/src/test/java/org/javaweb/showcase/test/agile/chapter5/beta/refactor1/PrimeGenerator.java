/**
 * $Id: PrimeGenerator.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.agile.chapter5.beta.refactor1;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class PrimeGenerator {

  private static int s;

  private static boolean[] f;

  private static int[] primes;

  /**
   * 
   */
  public PrimeGenerator() {
    // TODO Auto-generated constructor stub
  }

  public static int[] generatePrimes(int maxVlue) {
    if (maxVlue >= 2) {
      initializeSieve(maxVlue);
      sieve();
      loadPrimes();
      return primes;
    } else {
      return new int[0];
    }
  }

  public static void initializeSieve(int maxVlue) {
    s = maxVlue + 1;
    f = new boolean[s];
    for (int i = 0; i < s; i++) {
      f[i] = true;
    }
    f[0] = f[1] = false;

  }

  public static void sieve() {
    int i;
    int j;
    for (i = 2; i < Math.sqrt(s) + 1; i++) {
      for (j = 2 * i; j < s; j += i) {
        f[j] = false;
      }
    }
  }

  public static void loadPrimes() {
    int i;
    int j;
    int count = 0;
    for (i = 0; i < s; i++) {
      if (f[i]) {
        count++;
      }
    }
    primes = new int[count];
    for (i = 0, j = 0; i < s; i++) {
      if (f[i]) {
        primes[j++] = i;
      }
    }
  }

}
