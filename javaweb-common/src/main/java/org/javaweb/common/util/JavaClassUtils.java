/*
 * $Id: JavaClassUtils.java 4405 2018-06-26 07:13:59Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/util/JavaClassUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.sql.Date;


/**
 * 
 * @author wangwd
 * @version $Revision: 4405 $, $Date: 2018年6月7日 上午10:44:06$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-26 15:13:59#$
 */
public final class JavaClassUtils {

  /**
   * 判断是否为基本类型(boolean, byte, char, short, int, long, float, and double)：包括 String、Date
   * @param clazz clazz
   * @return true：是;   false：不是
   */
  public static boolean isPrimite(Class<?> clazz){
    return clazz.isPrimitive()
        || clazz == String.class
        || clazz == Date.class
        || clazz == Short.class
        || clazz == Long.class
        || clazz == Double.class
        || clazz == Float.class
        || clazz == Integer.class
        || clazz == Byte.class;
  }
  
}
