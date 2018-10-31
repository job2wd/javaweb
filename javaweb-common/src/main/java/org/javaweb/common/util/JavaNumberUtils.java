/*
 * $Id: JavaNumberUtils.java 5590 2018-08-31 03:15:14Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaNumberUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;


/**
 * Fba Number utils extends {@link org.apache.commons.lang3.math.NumberUtils org.apache.commons.lang3.math.NumberUtils}
 * 
 * @author wangwd
 * @version $Revision: 5590 $, $Date: 2015-4-23 下午8:41:19$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-31 11:15:14#$
 */
public class JavaNumberUtils extends NumberUtils {

  /**
   * 实现 Double 的四舍五入
   * @param v 要四舍五入的原值
   * @param scale 小数位数
   * @return
   */
  public static Double round(Double v, int scale) {
    if (v == null || v == 0) {
      return 0d;
    }
    return new BigDecimal(v).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  
  /**
   * 实现 BigDecimal 的四舍五入
   * @param v 要四舍五入的原值
   * @param scale 小数位数
   * @return
   */
  public static BigDecimal round(BigDecimal v, int scale) {
    return v.setScale(scale, BigDecimal.ROUND_HALF_UP);
  }
  
  /**
   * 格式化流量值，返回的值后添加了单位后缀：KB、MB、GB、TB、PB。<br>
   * 如：b=2048，则返回2KB。
   * @param b
   * @return
   */
  public static String formatFlow(long b) {
    return formatFlow(b, 2);
  }
  
  public static String formatFlow(long b, int scale) {
    double ret = b * 1.00;
    String unit = "B";
    
    if(ret >= 1024) {
      ret = ret / 1024;
      unit = "KB";
    }
    if(ret >= 1024) {
      ret = ret / 1024;
      unit = "MB";
    }
    if(ret >= 1024) {
      ret = ret / 1024;
      unit = "GB";
    }
    if(ret >= 1024) {
      ret = ret / 1024;
      unit = "TB";
    }
    if(ret >= 1024) {
      ret = ret / 1024;
      unit = "PB";
    }
    return round(ret, scale) + unit;
  }

  public static boolean isEquals(Integer v1, Integer v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.intValue() == v2.intValue();
  }
  
  public static boolean isEquals(Long v1, Long v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.longValue() == v2.longValue();
  }
  
  public static boolean isEquals(Short v1, Short v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.shortValue() == v2.shortValue();
  }
  
  public static boolean isEquals(Float v1, Float v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.floatValue() == v2.floatValue();
  }
  
  public static boolean isEquals(Double v1, Double v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.doubleValue() == v2.doubleValue();
  }
  
  public static boolean isEquals(BigDecimal v1, BigDecimal v2) {
    if (v1 == null && v2 == null) {
      return true;
    }
    if (v1 == null || v2 == null) {
      return false;
    }
    return v1.equals(v2) || v1.compareTo(v2) == 0;
  }
  
}
