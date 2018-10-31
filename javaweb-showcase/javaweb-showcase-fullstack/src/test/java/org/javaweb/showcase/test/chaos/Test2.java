/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年4月20日 下午4:17:34$
 * @LastChanged $Author:$, $Date:: #$
 */
public class Test2 {

  public static void main(String[] args) {
    //十进制转换成其他进制
    System.out.println("十进制转换成二进制：" + Integer.toBinaryString(1));
    System.out.println("十进制转换成十六进制：" + Integer.toHexString(1));
    System.out.println("十进制转换成八进制：" + Integer.toOctalString(1));

    System.out.println("");

    //其他进制转换成十进制
    System.out.println("二进制转换成十进制：" + Integer.parseInt("111001", 2));
    System.out.println("八进制转换成十进制：" + Integer.parseInt("27", 8));
    System.out.println("十六进制转换成十进制：" + Integer.parseInt("A8", 16));

    System.out.println("");

    System.out.println("二进制转换成十进制：" + Integer.valueOf("111001", 2).toString());
    System.out.println("八进制转换成十进制：" + Integer.valueOf("27", 8).toString());
    System.out.println("十六进制转换成十进制：" + Integer.valueOf("A8", 16).toString());
  }

}
