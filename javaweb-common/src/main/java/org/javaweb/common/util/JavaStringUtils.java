/*
 * $Id: JavaStringUtils.java 5959 2018-09-20 05:15:03Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaStringUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * @author wangwd
 * @version $Revision: 5959 $, $Date: 2018年6月7日 上午9:11:14$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:15:03#$
 */
public final class JavaStringUtils extends StringUtils {

  /**
   * 英文逗号 “,” 分隔的字符串转换为 Integer 数组
   * 
   * @param splitString
   * @return
   */
  public static Integer[] covertSplitString2IntegerArray(String splitString) {
    String[] str_arr = splitString.split("[,]");
    Integer[] int_arr = new Integer[str_arr.length];
    
    for(int i = 0; i < str_arr.length; i++) {
      if(StringUtils.isBlank(str_arr[i])) {
        continue;
      }
      int_arr[i] = Integer.valueOf(StringUtils.trim(str_arr[i]));
    }
    
    return int_arr;
  }
  
  public static String trimRNT(String str) {
    String _s = StringUtils.trimToNull(str);
    
    if (StringUtils.isBlank(_s)) {
      return _s;
    }
    
    return _s.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
  }
  
  public static void main(String[] args) {
    String s = " [\r\n" +
        "    {\r\n" +
        "        \"user_name\": \"ads fad\"\r\n" +
        "    },\r\n" +
        "    {\r\n" +
        "        \"name\": \"adf\"\r\n" +
        "    }\r\n" +
        "] \r\n" +
        "";
    System.out.println(":" + trimRNT(s) + ":");
    
    System.out.println(":" + JavaJsonUtils.compress(s) + ":");
  }
  
}
