/*
 * $Id: ValidationFailedException.java 5605 2018-08-31 08:07:54Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/validation/ValidationFailedException.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.exception;

/**
 * 
 * @author wangwd
 * @version $Revision: 5605 $, $Date: 2018年6月7日 上午8:32:03$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-31 16:07:54#$
 */
public class ValidationFailedException extends RequestProcessFail4AjaxException {

  private static final long serialVersionUID = 3490328636837461283L;

  private String targetName;
  private Object targetValue;
  
  public ValidationFailedException() {
    super();
  }

  public ValidationFailedException(String code, String message, Object... messageArgs) {
    super(code, message, messageArgs);
  }

  public ValidationFailedException(String code, String message, Throwable cause, Object... messageArgs) {
    super(code, message, cause, messageArgs);
  }

  public ValidationFailedException(String code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public ValidationFailedException(String message, Throwable cause, Object... messageArgs) {
    super(message, cause, messageArgs);
  }

  public ValidationFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationFailedException(Throwable cause) {
    super(cause);
    if (cause instanceof ValidationFailedException) {
      ValidationFailedException e = (ValidationFailedException) cause;
      targetName = e.getTargetName();
      targetValue = e.getTargetValue();
    }
  }

  public String getTargetName() {
    return targetName;
  }
  
  public void setTargetName(String targetName) {
    this.targetName = targetName;
  }
  
  public Object getTargetValue() {
    return targetValue;
  }
  
  public void setTargetValue(Object targetValue) {
    this.targetValue = targetValue;
  }
  
}
