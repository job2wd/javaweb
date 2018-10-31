/*
 * $Id: JavaAssertUtils.java 6039 2018-09-29 00:36:45Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaAssertUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author wangwd
 * @version $Revision: 6039 $, $Date: 2018年8月28日 下午3:22:40$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-29 08:36:45#$
 */
public class JavaAssertUtils {
  
  /**
   * 是否是无效的 Integer 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isInvalidUnsignedInteger(Integer v) {
    return v != null && v <= 0;
  }
  
  /**
   * 是否是无效的 Short 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isInvalidUnsignedShort(Short v) {
    return v != null && v <= 0;
  }
  
  /**
   * 是否是无效的 Long 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isInvalidUnsignedLong(Long v) {
    return v != null && v <= 0;
  }
  
  /**
   * 是否是无效的 Float 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isInvalidUnsignedFloat(Float v) {
    return v != null && v <= 0;
  }
  
  /**
   * 是否是无效的 Double 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isInvalidUnsignedDouble(Double v) {
    return v != null && v <= 0;
  }
  
  /**
   * 是否是 Null 或无效的 Integer 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isNullOrInvalidUnsignedInteger(Integer id) {
    return id == null || isInvalidUnsignedInteger(id);
  }
  
  /**
   * 是否是 Null 或无效的 Short 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isNullOrInvalidUnsignedShort(Short id) {
    return id == null || isInvalidUnsignedShort(id);
  }
  
  /**
   * 是否是 Null 或无效的 Long 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isNullOrInvalidUnsignedLong(Long id) {
    return id == null || isInvalidUnsignedLong(id);
  }
  
  /**
   * 是否是 Null 或无效的 Float 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isNullOrInvalidUnsignedFloat(Float id) {
    return id == null || isInvalidUnsignedFloat(id);
  }
  
  /**
   * 是否是 Null 或无效的 Double 类型值。无效的值为不等于 Null 且小于等于 0 的值。
   */
  public static boolean isNullOrInvalidUnsignedDouble(Double id) {
    return id == null || isInvalidUnsignedDouble(id);
  }
  
  /**
   * 是否为无效的字符串。无效的字符串包括英文特殊字符和中文特殊字符（如：\\\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\|;\\:'\",\\<\\>\\.\\/\\?   "
   *       + "·！￥……（）——【】｛｝、；：‘’“”，《》。？ \r\t\n ）
   */
  public static boolean isInvalidString(String s) {
    if (StringUtils.isNotBlank(s)) {
      String regex = "[\\\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\|;\\:'\",\\<\\>\\.\\/\\?   "
          + "·！￥……（）——【】｛｝、；：‘’“”，《》。？ \r\t\n ]+?";
      return s.matches(regex);
    }
    return false;
  }
  
  /**
   * 是否是为空或无效的字符串。为空的字符串包括（null, "", " "），无效的字符串包括无效的字符串包括英文特殊字符和中文特殊字符（如：\\\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\|;\\:'\",\\<\\>\\.\\/\\?   "
   *       + "·！￥……（）——【】｛｝、；：‘’“”，《》。？ \r\t\n ）
   */
  public static boolean isBlankOrInvalidString(String s) {
    return StringUtils.isBlank(s) || isInvalidString(s);
  }
  
}
