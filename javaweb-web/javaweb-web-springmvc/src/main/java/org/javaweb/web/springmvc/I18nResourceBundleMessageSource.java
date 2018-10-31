/*
 * $Id: I18nResourceBundleMessageSource.java 5898 2018-09-14 07:22:06Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/I18nResourceBundleMessageSource.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.web.springmvc;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;


/**
 * 
 * @author wangwd
 * @version $Revision: 5898 $, $Date: 2018年5月15日 上午11:46:21$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-14 15:22:06#$
 */
public class I18nResourceBundleMessageSource extends ResourceBundleMessageSource {

  private Locale locale;
  
  /**
   * 支持 code 国际化（以 “{” 开始，以 “}” 结束）
   */
  public String getMessage(String code) {
    if(StringUtils.isBlank(code)) {
      return null;
    }
    return getMessage(parseCode(code), null, locale);
  }
  
  /**
   * 支持 code 和 args 中的值国际化（以 “{” 开始，以 “}” 结束）
   */
  public String getMessage(String code, Object... args) {
    if(StringUtils.isBlank(code)) {
      return null;
    }
    
    Object[] codeArgs = parseArgs(args);
    
    return getMessage(parseCode(code), codeArgs, locale);
  }
  
  /**
   * 支持 code 和 args 中的值国际化（以 “{” 开始，以 “}” 结束）
   */
  public String getMessage(String code, String... args) {
    Object[] oa = args;
    return getMessage(code, oa);
  }
  
  /**
   * code 和 message 根据国家化配置进行连接拼接
   */
  public String getMessage4Error(String errorCode, String errorMessage) {
    if(StringUtils.isBlank(errorCode)) {
      return getMessage("{error.msg.hyphen.no_error_code}", errorMessage);
    }
        
    return getMessage("{error.msg.hyphen.has_error_code}", errorMessage, errorCode);
  }

  public static boolean isI18nCode(String code) {
    if(StringUtils.isBlank(code)) {
      return false;
    }
    return code.startsWith("{") && code.endsWith("}");
  }
  
  private String parseCode(String code) {
    if(isI18nCode(code)) {
      return code.replace("{", "").replace("}", "");
    }
    
    return code;
  }
  
  private Object[] parseArgs(Object[] args) {
    if(args == null || args.length == 0) {
      return new Object[0];
    }
    
    Object[] codeArgs = new Object[args.length];
    
    for(int i = 0; i < args.length; i++) {
      codeArgs[i] = args[i];
      
      if(args[i] instanceof String) {
        String a = (String)args[i];
        
        if(isI18nCode(a)) {
          codeArgs[i] = this.getMessage(a);
        }
      }
    }
    
    return codeArgs;
  }
  
  public Locale getLocale() {
    return locale;
  }
  
  public void setLocale(Locale locale) {
    this.locale = locale;
  }
  
}
