/*
 * $Id: FbaDateFormatUtils.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/FbaDateFormatUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;


/**
 * 日期格式化工具类，扩展了 {@link org.apache.commons.lang3.time.DateFormatUtils}
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-1-29 上午9:37:50$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class FbaDateFormatUtils extends DateFormatUtils {

  /**
   * ISO8601 formatter for date-time without time zone.
   * The format used is <tt>yyyy-MM-dd HH:mm:ss</tt>.
   */
  public static final FastDateFormat ISO_DATETIME_NO_T_FORMAT
          = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");//, TimeZone.getDefault(), Locale.CHINA);
  
}
