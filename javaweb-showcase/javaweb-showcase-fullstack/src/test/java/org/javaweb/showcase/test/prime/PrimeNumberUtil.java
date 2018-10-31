/**
 * $Id: PrimeNumberUtil.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail mail2wd@163.com
 */
package org.javaweb.showcase.test.prime;

import java.util.Scanner;

/**
 * 素数（又称质数）计算</p>
 * 
 * 素数：只能被1和它本身整除的数为素数
 * 
 * @author David
 * @version v1.0
 * @date 2011-7-6 下午05:15:54
 */
public final class PrimeNumberUtil {

  /**
   * 检索指定数字以内的所有素数
   * 
   * @param num
   * @return
   */
  public static int[] retrievePrimeNumbers(int num) {
    if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
      throw new IllegalArgumentException("Invalid argument");
    }
    int[] tmp = new int[num / 2];
    int length = 0;
    for (int i = 2; i <= num; i++) {
      if (isPrimeNumber(i)) {
        tmp[length] = i;
        length++;
      }
    }
    int[] primes = new int[length];
    System.arraycopy(tmp, 0, primes, 0, length);
    return primes;
  }

  /**
   * 检测指定数字是否为质数
   * 
   * @param num
   * @return
   */
  public static boolean isPrimeNumber(int num) {
    int limit = (int) Math.sqrt(num);//用(int) Math.sqrt(n)求出循环上限
    for (int i = 2; i <= limit; i++) {
      if (num % i == 0) {
        //存在约数，可以被整除
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long number, i, sum;
    String answer;
    boolean con = false;
    do {
      System.out.println("请输入一个大于2的整数：");
      number = in.nextLong();
      sum = number;
      /* 判断质数 */
      for (i = 2; i <= sum; i++) {
        if (number % i == 0) {
          con = true;
          System.out.println("约数为：" + i);
          break;
        } else {
          sum = number / i;
        }
      }
      if (con) {
        System.out.println(number + "不是质数");
      } else {
        System.out.println(number + "是质数");
      }
      System.out.println("\n是否继续(y/n)");
      answer = in.next();
      con = false;
    } while (answer.equals("y"));
    System.out.println("程序结束!~");
  }

}
