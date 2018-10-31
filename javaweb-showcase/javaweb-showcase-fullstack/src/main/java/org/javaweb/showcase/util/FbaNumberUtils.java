/*
 * $Id: FbaNumberUtils.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/FbaNumberUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;


/**
 * Fba Number utils extends {@link org.apache.commons.lang3.math.NumberUtils org.apache.commons.lang3.math.NumberUtils}
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-4-23 下午8:41:19$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class FbaNumberUtils extends NumberUtils {

  /**
   * 实现 Double 的四舍五入
   * @param v 要四舍五入的原值
   * @param scale 小数位数
   * @return
   */
  public static BigDecimal round(Double v, int scale) {
    return new BigDecimal(v).setScale(scale, BigDecimal.ROUND_HALF_UP);
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
  
}
