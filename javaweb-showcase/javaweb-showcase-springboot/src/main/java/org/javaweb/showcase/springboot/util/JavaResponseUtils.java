/*
 * $Id: JavaResponseUtils.java 5898 2018-09-14 07:22:06Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaResponseUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.util;

import org.apache.commons.lang3.StringUtils;
import org.javaweb.data.model.domain.ResponseBean;
import org.javaweb.data.model.domain.ResponseBean4Validation;
import org.javaweb.data.model.exception.GenericException;
import org.javaweb.data.model.exception.RequestProcessFail4AjaxException;
import org.javaweb.data.model.exception.ValidationFailedException;
import org.javaweb.showcase.springboot.common.Constant;

/**
 * 响应封装接口，包括响应抛出异常（{@link GenericException}）的封装和响应返回统一对象（{@link ResponseBean}）的封装
 * 
 * @author wangwd
 * @version $Revision: 5898 $, $Date: 2018年8月24日 下午2:56:22$
 * @LastChanged $Author: wangweidong $, $Date:: #$
 */
public class JavaResponseUtils {
  
  public static ResponseBean createResponseBean4Success() {
    return createResponseBean4Success(null, null);
  }
  
  public static ResponseBean createResponseBean4Success(Object data) {
    return createResponseBean4Success(null, data);
  }
  
  public static ResponseBean createResponseBean4Success(String message, Object data, Object... messageArgs) {
    String msg = null;//messageSource.getMessage(StringUtils.isBlank(msgKey) ? "{system.common.ok}" : msgKey, args);
    
    if (StringUtils.isNotBlank(message)) {
      msg = JavaWebUtils.getI18nMessageSource().getMessage(message, messageArgs);
    }
    
    ResponseBean response4View = new ResponseBean();
    
    response4View.setStatus(Constant.RESPONSE_STATUS_SUCCESS);
    
    response4View.setMessage(msg);
    response4View.setData(data);
    
    return response4View;
  }
  
  public static ResponseBean createResponseBean4Failed(GenericException e) {
    ResponseBean response4View = new ResponseBean();
    
    response4View.setStatus(Constant.RESPONSE_STATUS_FAILED);
    
    response4View.setCode(e.getCode());
    response4View.setMessage(e.getMessage());
    response4View.setDetailMessage(e.getDetailMessage());
    
    return response4View;
  }
  
  public static ResponseBean4Validation createValidationResponseBean4Failed(ValidationFailedException e) {
    return createValidationResponseBean4Failed(e.getCode(), e.getMessage(), e.getTargetName(), e.getTargetValue(),
        e.getDetailMessage());
  }
  
  public static ResponseBean4Validation createValidationResponseBean4Failed(String code, String message, Object targetValue,
      String targetName, Object... messageArgs) {
    return createValidationResponseBean4Failed(code, message, targetName, targetValue, null, messageArgs);
  }
  
  public static ResponseBean4Validation createValidationResponseBean4Failed(String code, String message, String targetName,
      Object targetValue, String detailMessage, Object... messageArgs) {
    ResponseBean4Validation response4View = new ResponseBean4Validation();
    
    response4View.setStatus(Constant.RESPONSE_STATUS_FAILED);
    
    response4View.setCode(JavaWebUtils.getI18nMessageSource().getMessage(code));
    response4View.setMessage(JavaWebUtils.getI18nMessageSource().getMessage(message, messageArgs));
    response4View.setDetailMessage(detailMessage);
    
    response4View.setTargetName(targetName);
    response4View.setTargetValue(targetValue);
    
    return response4View;
  }
  
  public static GenericException createGenericException(String code, String message, Object... messageArgs) {
    return createGenericException(code, message, null, messageArgs);
  }
  
  public static GenericException createGenericException(String code, String message, String detailMessage,
      Object... messageArgs) {
    GenericException e = new GenericException(code, message, messageArgs);
    
    e.setDetailMessage(detailMessage);
    
    e.setResponse4View(createResponseBean4Failed(e));
    
    return e;
  }
  
  public static GenericException createGenericException(Throwable throwable) {
    if (throwable instanceof GenericException) {
      return (GenericException) throwable;
    }
    return new GenericException(throwable);
  }
  
  public static RequestProcessFail4AjaxException createRequestProcessFail4AjaxException(String code, String message,
      Object... messageArgs) {
    return createRequestProcessFail4AjaxException(code, message, null, messageArgs);
  }
  
  public static RequestProcessFail4AjaxException createRequestProcessFail4AjaxException(String code, String message,
      String detailMessage, Object... messageArgs) {
    RequestProcessFail4AjaxException e = new RequestProcessFail4AjaxException(code, message, messageArgs);
    
    e.setDetailMessage(detailMessage);
    
    e.setResponse4View(createResponseBean4Failed(e));
    
    return e;
  }
  
  public static RequestProcessFail4AjaxException createRequestProcessFail4AjaxException(Throwable throwable) {
    if (throwable instanceof RequestProcessFail4AjaxException) {
      return (RequestProcessFail4AjaxException) throwable;
    }
    return new RequestProcessFail4AjaxException(throwable);
  }
  
  public static ValidationFailedException createValidationFailedException(String code, String message, String detailMessage,
      Object... messageArgs) {
    return createValidationFailedException(code, message, null, null, detailMessage, messageArgs);
  }
  
  public static ValidationFailedException createValidationFailedException(String code, String message, Object... messageArgs) {
    return createValidationFailedException(code, message, null, null, null, messageArgs);
  }
  
  public static ValidationFailedException createValidationFailedException(String code, String message, String targetName,
      Object targetValue, Object... messageArgs) {
    return createValidationFailedException(code, message, targetName, targetValue, null, messageArgs);
  }
  
  public static ValidationFailedException createValidationFailedException(String code, String message, String targetName,
      Object targetValue, String detailMessage, Object... messageArgs) {
    ValidationFailedException e = new ValidationFailedException(code, message, messageArgs);
    
    e.setTargetName(targetName);
    e.setTargetValue(targetValue);
    e.setDetailMessage(detailMessage);
    
    e.setResponse4View(createValidationResponseBean4Failed(e));
    
    return e;
  }
  
  public static ValidationFailedException createValidationFailedException(Throwable throwable) {
    if (throwable instanceof ValidationFailedException) {
      return (ValidationFailedException) throwable;
    }
    return new ValidationFailedException(throwable);
  }
  
  /**
   * 直接封装 {@link GenericException} 异常并抛出
   */
  public static void throwGenericException(String code, String message, Object... messageArgs) {
    throw createGenericException(code, message, messageArgs);
  }
  
  /**
   * 直接封装 {@link RequestProcessFail4AjaxException} 异常并抛出
   */
  public static void throwRequestProcessFail4AjaxException(String code, String message, Object... messageArgs) {
    throw createRequestProcessFail4AjaxException(code, message, messageArgs);
  }
  
  /**
   * 直接抛出 {@link ValidationFailedException} 异常
   */
  public static void throwValidationFailedException(String code, String message, String targetName, Object targetValue,
      Object... messageArgs) {
    throw createValidationFailedException(code, message, targetName, targetValue, messageArgs);
  }
  
}
