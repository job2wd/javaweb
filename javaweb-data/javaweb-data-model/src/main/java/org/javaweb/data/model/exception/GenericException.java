/*
 * $Id: GenericException.java 5990 2018-09-27 08:20:10Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/exception/GenericException.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.exception;

import org.apache.commons.lang3.StringUtils;
import org.javaweb.common.Constant;
import org.javaweb.common.util.JavaExceptionUtils;
import org.javaweb.data.model.domain.ResponseBean;


/**
 * 系统全局异常类
 * 
 * @author wangwd
 * @version $Revision: 5990 $, $Date: 2018年5月7日 上午8:36:17$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-27 16:20:10#$
 */
public class GenericException extends RuntimeException {

  private static final long serialVersionUID = 4818951890578430598L;

  private String code;
  private String message;
  private String detailMessage;
  private Object[] messageArgs;
  
  private ResponseBean response4View;
  
  private Throwable cause;
    
  public GenericException() {
    super();
  }
  
  public GenericException(String code, String message, Object... messageArgs) {
    super(message);
    this.code = code;
    this.message = message;
    this.messageArgs = messageArgs;
    localizedException();
  }
  
  public GenericException(String message, Throwable cause) {
    super(message, cause);
    this.message = message;
    processThrowableException(cause);
    localizedException();
  }

  public GenericException(String message, Throwable cause, Object... messageArgs) {
    super(message, cause);
    this.message = message;
    this.messageArgs = messageArgs;
    processThrowableException(cause);
    localizedException();
  }

  public GenericException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
    processThrowableException(cause);
    localizedException();
  }

  public GenericException(Throwable cause) {
    super(cause);
    processThrowableException(cause);
    localizedException();
  }
  
  public GenericException(String code, String message, Throwable cause, Object... messageArgs) {
    super(message, cause);
    this.code = code;
    this.message = message;
    this.messageArgs = messageArgs;
    processThrowableException(cause);
    localizedException();
  }

  @Override
  public String getMessage() {
    return StringUtils.isNotBlank(this.message) ? this.message : super.getMessage();
  }
  
  public void clone(GenericException e) {
    e.setCode(code);
    e.setMessage(message);
    e.setDetailMessage(detailMessage);
    e.setMessageArgs(messageArgs);
    e.setResponse4View(response4View);
  }

  @Override
  public String getLocalizedMessage() {
    localizedException();
    return message;//super.getLocalizedMessage();
  }
  
  public void localizedException() {
    /*if(StringUtils.isNotBlank(code) && I18nResourceBundleMessageSource.isI18nCode(code)) {
      code = JavaWebUtils.getI18nMessageSource().getMessage(code);
    }
    
    if(StringUtils.isNotBlank(message) && I18nResourceBundleMessageSource.isI18nCode(message)) {
      message = JavaWebUtils.getI18nMessageSource().getMessage(message, messageArgs);
    }*/
    
    createResponse4View();
  }
  
  public void processThrowableException(Throwable cause) {
    if(cause == null) {
      return;
    }
    if(cause instanceof GenericException) {
      GenericException ge = (GenericException)cause;
      this.code = ge.getCode();
      this.message = ge.getMessage();
      this.detailMessage = ge.getDetailMessage();
      this.messageArgs = ge.getMessageArgs();
      this.response4View = ge.getResponse4View();
    } else {
      code = "{error.code.sys_error}";
      message = "{error.msg.sys_error}";
      
      //message = cause.getMessage();
      detailMessage = JavaExceptionUtils.getMessage(cause);
    }
  }

  public void createResponse4View() {
    if (response4View != null) {
      return;
    }
    
    response4View = new ResponseBean();
    
    response4View.setCode(code);
    // 若要在返回给客户端（PC客户端、浏览器）的错误消息中携带错误码，请用以下代码替换 response4View.setMessage(message); 中 message 参数！
    // JavaWebUtils.getI18nMessageSource().getMessage("{error.msg.hyphen.has_error_code}", message, code);
    response4View.setMessage(message);
    response4View.setStatus(Constant.RESPONSE_STATUS_FAILED);
    response4View.setDetailMessage(detailMessage);
    response4View.setData(null);
  }

  public String getCode() {
    return code;
  }

  public ResponseBean getResponse4View() {
    return response4View;
  }

  public void setResponse4View(ResponseBean response4View) {
    this.response4View = response4View;
  }

  public Object[] getMessageArgs() {
    return messageArgs;
  }

  public void setMessageArgs(Object[] messageArgs) {
    this.messageArgs = messageArgs;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetailMessage() {
    return detailMessage;
  }

  public void setDetailMessage(String detailMessage) {
    this.detailMessage = detailMessage;
  }
  
  @Override
  public Throwable getCause() {
    return cause;
  }
  
  public void setCause(Throwable cause) {
    this.cause = cause;
  }

}
