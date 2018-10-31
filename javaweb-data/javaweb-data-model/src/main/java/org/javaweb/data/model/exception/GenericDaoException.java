/*
 * $Id: GenericDaoException.java 5464 2018-08-28 03:22:37Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/exception/GenericDaoException.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.exception;


/**
 * 
 * @author wangwd
 * @version $Revision: 5464 $, $Date: 2018年8月28日 上午10:09:55$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-28 11:22:37#$
 */
public class GenericDaoException extends GenericException {
  
  private static final long serialVersionUID = -8830084224857148666L;
  
  public GenericDaoException() {
    super();
  }
  
  public GenericDaoException(String code, String message, Object... messageArgs) {
    super(code, message, messageArgs);
  }
  
  public GenericDaoException(String code, String message, Throwable cause, Object... messageArgs) {
    super(code, message, cause, messageArgs);
  }
  
  public GenericDaoException(String code, String message, Throwable cause) {
    super(code, message, cause);
  }
  
  public GenericDaoException(String message, Throwable cause, Object... messageArgs) {
    super(message, cause, messageArgs);
  }
  
  public GenericDaoException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public GenericDaoException(Throwable cause) {
    super(cause);
  }
  
}
