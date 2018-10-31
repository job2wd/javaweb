/*
 * $Id: JavaDateFormatUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaDateFormatUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;


/**
 * 日期格式化工具类，扩展了 {@link org.apache.commons.lang3.time.DateFormatUtils}
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2015-1-29 上午9:37:50$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaDateFormatUtils extends DateFormatUtils {

  /**
   * The format used is <tt>yyyy-MM-dd HH:mm:ss</tt>.
   */
  public static final FastDateFormat FORMAT_yyyy_MM_dd_HH_mm_ss
          = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");//, TimeZone.getDefault(), Locale.CHINA);
  
  /**
   * The format used is <tt>yyyyMMddHHmmss</tt>.
   */
  public static final FastDateFormat FORMAT_yyyyMMddHHmmss
          = FastDateFormat.getInstance("yyyyMMddHHmmss");//, TimeZone.getDefault(), Locale.CHINA);
  
  /**
   * The format used is <tt>yyyy-MM-dd</tt>.
   */
  public static final FastDateFormat FORMAT_yyyy_MM_dd = FastDateFormat.getInstance("yyyy-MM-dd");
  
  /**
   * The format used is <tt>yyyyMMdd</tt>.
   */
  public static final FastDateFormat FORMAT_yyyyMMdd = FastDateFormat.getInstance("yyyyMMdd");
  
  /**
   * The format used is <tt>HH:mm:ss</tt>.
   */
  public static final FastDateFormat FORMAT_HH_mm_ss = FastDateFormat.getInstance("HH:mm:ss");
  
  /**
   * The format used is <tt>HHmmss</tt>.
   */
  public static final FastDateFormat FORMAT_HHmmss = FastDateFormat.getInstance("HHmmss");
  
  /**
   * 格式化日期参数为 yyyyMMddHHmmss 格式，且转换为 long 型返回。
   */
  public static long format2Number(Date date) {
    return Long.valueOf(FORMAT_yyyyMMddHHmmss.format(date));
  }
  
  /**
   * 将  yyyyMMddHHmmss 格式日期转换为日期对象
   */
  public static Date getDate4yyyyMMddHHmmss(Long date) {
    try {
      return JavaDateFormatUtils.FORMAT_yyyyMMddHHmmss.parse(date.toString());
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * 将  yyyyMMddHHmmss 格式日期转换为以 yyyy-MM-dd HH:mm:ss 格式表示的字符串
   */
  public static String getFormatedDate(Long date) {
    return JavaDateFormatUtils.FORMAT_yyyy_MM_dd_HH_mm_ss.format(getDate4yyyyMMddHHmmss(date));
  }
    
}
