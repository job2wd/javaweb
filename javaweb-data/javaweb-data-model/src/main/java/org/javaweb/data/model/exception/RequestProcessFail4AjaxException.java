/*
 * $Id: RequestProcessFail4AjaxException.java 5301 2018-08-20 09:51:05Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/exception/RequestProcessFail4AjaxException.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.exception;


/**
 * 
 * @author wangwd
 * @version $Revision: 5301 $, $Date: 2018年6月4日 下午1:58:31$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-20 17:51:05#$
 */
public class RequestProcessFail4AjaxException extends GenericException {

  private static final long serialVersionUID = 6346851621725348L;

  public RequestProcessFail4AjaxException() {
    super();
  }

  public RequestProcessFail4AjaxException(String code, String message, Object... messageArgs) {
    super(code, message, messageArgs);
  }

  public RequestProcessFail4AjaxException(String code, String message, Throwable cause, Object... messageArgs) {
    super(code, message, cause, messageArgs);
  }

  public RequestProcessFail4AjaxException(String code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public RequestProcessFail4AjaxException(String message, Throwable cause, Object... messageArgs) {
    super(message, cause, messageArgs);
  }

  public RequestProcessFail4AjaxException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestProcessFail4AjaxException(Throwable cause) {
    super(cause);
  }

}
