/*
 * $Id: JavaPasswordUtils.java 5809 2018-09-12 07:30:58Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaPasswordUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

/**
 * 密码加解密相关的工具栏
 * 
 * @author wangwd
 * @version $Revision: 5809 $, $Date: 2018年9月12日 下午12:47:37$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-12 15:30:58#$
 */
public class JavaPasswordUtils {
  
  private static String getHexString(String str) {
    
    StringBuffer sb = new StringBuffer(2 * str.length());
    
    for (int i = 0; i < str.length(); i++) {
      String c = Integer.toHexString(str.charAt(i));
      sb.append(c);
      sb.append("g");
    }
    
    return sb.toString();
  }
  
  public static String getStringFromHex(String str) {
    if (str == null || str.equals("")) {
      return str;
    }
    StringBuffer sb = new StringBuffer(str.length());
    String arr[] = str.split("g");
    
    for (int i = 0; i < arr.length; i++) {
      try {
        char c = (char) Integer.parseInt(arr[i], 16);
        sb.append(c);
      } catch (Exception ex) {
      }
    }
    
    return sb.toString();
  }
  
  private static String desc(String str) {
    if (str == null) {
      return null;
    }
    StringBuffer sb = new StringBuffer(str.length());
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      //为了不处理编码问题，和便于在C和Java间加解密，只对ASCII符进行处理
      if (0 == (c & 0xff80)) {
        c ^= 0x35;
        c = (char) (127 - c);
        c ^= 0x5F;
        c ^= 0x69;
      }
      sb.append(c);
    }
    
    return sb.toString();
  }
  
  public static String encode(String str) {
    if (str == null) {
      return str;
    }
    String encode_str = desc(str);
    encode_str = getHexString(encode_str);
    return encode_str;
  }
  
  /**
   * 解密 159-x
   * @param str 待加密或解密的数
   * @return    (参数说明)
   * @return String    返回类型
   * @throws
   */
  public static String decode(String str) {
    if (str == null) {
      return str;
    }
    String decode_str = getStringFromHex(str);
    decode_str = desc(decode_str);
    return decode_str;
  }
  
  public static void main(String[] agrs) {
    String c = "abcd待加密或3a解密的数";
    try {
      String s = new String(c.getBytes(), "UTF-8");
      System.out.println(Integer.toHexString(s.charAt(0)));
      
    } catch (Exception e) {
      e.printStackTrace();
      // TODO: handle exception
    }
    System.out.println("加密前：" + c);
    System.out.println("加密后：" + JavaPasswordUtils.encode(c));
    System.out.println("解密后：" + JavaPasswordUtils.decode(JavaPasswordUtils.encode(c)));
  }
  
}
