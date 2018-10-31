/*
 * $Id: RequestProcessFailException.java 5301 2018-08-20 09:51:05Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/exception/RequestProcessFailException.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.exception;



/**
 * 
 * @author wangwd
 * @version $Revision: 5301 $, $Date: 2018年5月29日 下午12:59:16$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-20 17:51:05#$
 */
public class RequestProcessFailException extends GenericException {

  private static final long serialVersionUID = 3841448418553700276L;

  public RequestProcessFailException() {
    super();
  }

  public RequestProcessFailException(String code, String message, Object... messageArgs) {
    super(code, message, messageArgs);
  }

  public RequestProcessFailException(String code, String message, Throwable cause, Object... messageArgs) {
    super(code, message, cause, messageArgs);
  }

  public RequestProcessFailException(String code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public RequestProcessFailException(String message, Throwable cause, Object... messageArgs) {
    super(message, cause, messageArgs);
  }

  public RequestProcessFailException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestProcessFailException(Throwable cause) {
    super(cause);
  }

}