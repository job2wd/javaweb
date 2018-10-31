/*
 * $Id: JavaExceptionUtils.java 5937 2018-09-17 09:12:48Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaExceptionUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 
 * @author wangwd
 * @version $Revision: 5937 $, $Date: 2018年9月17日 下午12:58:53$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-17 17:12:48#$
 */
public class JavaExceptionUtils extends ExceptionUtils {
  
  public static String getMessage(final Throwable th) {
    if (th == null) {
      return "";
    }
    String message = th.getMessage();
    
    if (StringUtils.isBlank(message) || "null".equals(message)) {
      message = getRootCauseMessage(th);
    }
    
    if (StringUtils.isBlank(message) || "null".equals(message)) {
      message = getDetailMessage(th);
    }
    
    return message;
  }
  
  public static String getDetailMessage(final Throwable th) {
    return getStackTrace(th);
  }
  
}
