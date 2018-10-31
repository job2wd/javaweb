/*
 * $Id: CmcConstant.java 5369 2018-08-23 07:42:15Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/CmcConstant.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.common;

import java.util.Date;

import org.javaweb.common.util.JavaDateUtils;


/**
 *
 * @author wangwd
 * @version $Revision: 5369 $, $Date: 2018年4月27日 下午5:44:09$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-23 15:42:15#$
 */
public final class Constant {

  public static final String HTTP_SESSION_USER_LOGIN_INFO = "HTTP_SESSION_USER_LOGIN_INFO";
  public static final String HTTP_SESSION_USER = "HTTP_SESSION_USER";
  public static final String HTTP_SESSION_MENUS = "HTTP_SESSION_MENUS";
  public static final String HTTP_SESSION_RESOURCES = "HTTP_SESSION_RESOURCES";
  public static final String HTTP_SESSION_ROLES = "HTTP_SESSION_ROLES";
  
  /**
   * 默认 Token 过期时长（登录超时时间）：1小时
   */
  public static final long DEFAULT_SESSION_TIMEOUT_MILLISECONDS = 60 * 1000 * 60;
  
  public static final Integer ORG_ROOT_PARENT_ID = 0;
  public static final String  ORG_IDPATH_SEPERATOR = "|";
  
  public static final String HTTP_HEADER_TOKEN_ID = "TOKEN_ID";
  public static final String HTTP_HEADER_TOKEN    = "TOKEN";
  
  public static final String HTTP_HEADER_USER_AGENT      = "User-Agent";
  public static final String HTTP_HEADER_CLIENT_TYPE     = "Client-Type";
  public static final String HTTP_HEADER_CLIENT_TYPE_PC_APP     = "PC-APP";     //PC-APP/QT-PROGRAM
  public static final String HTTP_HEADER_CLIENT_TYPE_MOBILE_APP = "MOBILE-APP"; //MOBILE-APP/IOS-PROGRAM, MOBILE-APP/ANDROID-PROGRAM
  public static final String HTTP_HEADER_USER_AGENT_MOBILE  = "mobile";
  
  public static final String HTTP_HEADER_AJAX_x_requested_with = "x-requested-with";
  public static final String HTTP_HEADER_AJAX_XMLHttpRequest   = "XMLHttpRequest";
  
  public static final String HTTP_HEADER_CONTENT_TYPE_JSON = "application/json";

  /**
   * 默认页面显示记录条数
   */
  public static final int DEFAULT_PAGE_SIZE = 10;
  
  public static final String DATE_FORMAT_PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
  public static final String DATE_FORMAT_PATTERN_yyyyMMddHHmmssSSS_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
  
  /**
   * 响应状态码 1 - 成功
   */
  public static final Integer RESPONSE_STATUS_SUCCESS = 1;
  /**
   * 响应状态码 0 - 失败
   */
  public static final Integer RESPONSE_STATUS_FAILED = 0;
  
  public static final String RESOURCES_UPLOADS_RELATIVE_DIRECTORY = "/resources/uploads/";
  public static final String RESOURCES_DOWNLOADS_RELATIVE_DIRECTORY = "/resources/downloads/";
  
  public static final String UPLOADS_DIRECTORY_CATEGORY_DATAIMPORT = "dataimport";

  public static final Character CSV_FILE_SEPARATOR = ',';
  
  public static final String DEFAULT_CHINESE_CHARSET = "GBK";
  
  public static final Date DEFAULT_IGNORE_DATE = JavaDateUtils.parseDate("1899-12-30 00:00:00", "yyyy-MM-dd HH:mm:ss");
  
}
