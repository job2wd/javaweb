/*
 * $Id: JavaWebUtils.java 5688 2018-09-06 05:22:25Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaWebUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.javaweb.common.PropCfg;
import org.javaweb.common.util.JavaDateFormatUtils;
import org.javaweb.data.model.exception.GenericException;
import org.javaweb.showcase.springboot.common.Constant;
import org.javaweb.showcase.springboot.common.I18nResourceBundleMessageSource;
import org.javaweb.showcase.springboot.enums.ClientTypeEnum;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * java web utils, extends:
 * <code>{@link org.springframework.web.util.WebUtils}</code>
 * 
 * @author wangwd
 * @version $Revision: 5688 $, $Date: 2018年6月4日 下午4:46:03$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-06 13:22:25#$
 */
public final class JavaWebUtils extends org.springframework.web.util.WebUtils {

  public static HttpServletRequest getHttpServletRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes == null) {
      return null;
    }
    return ((ServletRequestAttributes) requestAttributes).getRequest();
  }
  
  /**
   * etc: http://192.168.1.58:80
   */
  public static String getHttpRequestURIRoot() {
    HttpServletRequest reqeust = getHttpServletRequest();
    
    return reqeust.getScheme() + "://" + reqeust.getLocalAddr() + ":" + reqeust.getServerPort()
        + JavaWebUtils.getServletContext().getContextPath();
  }
  
  public static WebApplicationContext getWebApplicationContext() {
    return ContextLoader.getCurrentWebApplicationContext();
  }
  
  public static ServletContext getServletContext() {
    return getWebApplicationContext().getServletContext();
    //return RequestContextUtils.findWebApplicationContext(getHttpServletRequest()).getServletContext();
  }
  
  public static I18nResourceBundleMessageSource getI18nMessageSource() {
    //return (I18nResourceBundleMessageSource) getWebApplicationContext().getBean("messageSource");
    return (I18nResourceBundleMessageSource) RequestContextUtils.findWebApplicationContext(getHttpServletRequest())
        .getBean("messageSource");
  }
  
  public static boolean isAjaxRequest() {
    String xr = getHttpServletRequest().getHeader(Constant.HTTP_HEADER_AJAX_x_requested_with);
    
    if (Constant.HTTP_HEADER_AJAX_XMLHttpRequest.equalsIgnoreCase(xr)) {
      return true;
    }
    
    return false;
  }
  
  public static File getFile(String fileWebRelativePath) {
    try {
      return getWebApplicationContext().getResource(fileWebRelativePath).getFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String getFileRealPath(String fileWebRelativePath) {
    try {
      /*Resource ur = new FileUrlResource(fileRelativePath);
      ur = new ClassPathResource(fileRelativePath);"classpath:" + fileRelativePath);
      ur = new FileSystemResource(fileRelativePath);
      File file = ur.getFile();
      file = ResourceUtils.getFile(fileRelativePath);*/
      
      //return getFile(fileWebRelativePath).getAbsolutePath();
      return getServletContext().getRealPath(fileWebRelativePath);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String getHttpRequestParameterValue(String name) {
    try {
      HttpServletRequest request = getHttpServletRequest();
      
      if (request == null) {
        return null;
      }
      
      String v = request.getParameter(name);
      
      if (StringUtils.isBlank(v)) {
        v = request.getHeader(name);
      }
      
      if(StringUtils.isBlank(v)) {
        //Map<String, String> map = readHttpRequestBodyJson(Map.class);
        //v = map.get(name);
      }
      
      if (StringUtils.isNotBlank(v)) {
        //v = URLDecoder.decode(v, BaseResource.ENCODING);
      }

      return v;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String getHttpRequestParameterValue(String key, String defaultValue) {
    String v = getHttpRequestParameterValue(key);

    return StringUtils.isNotBlank(v) ? v : defaultValue;
  }

  public static String[] getHttpRequestParameterValues(String key) {
    return getHttpServletRequest().getParameterValues(key);
  }

  public static InputStream getHttpRequestInputStream() throws Exception {
    return getHttpServletRequest().getInputStream();
  }
  
  public static BufferedReader getHttpRequestBufferedReader() throws Exception {
    return new BufferedReader(new InputStreamReader(getHttpRequestInputStream()));
  }
  
  public static String readHttpRequestBodyStream() throws Exception {
    BufferedReader br = getHttpRequestBufferedReader();
    
    StringBuffer sb = new StringBuffer();
    int len = -1;
    char[] c = new char[256];
    
    while ((len = br.read(c)) != -1) {
      sb.append(c, 0, len);
    }
    
    return sb.toString();
  }
  
  public static <T> T readHttpRequestBodyJson(Class<T> valueType) {
    try {
      if (Constant.HTTP_HEADER_CONTENT_TYPE_JSON.equalsIgnoreCase(getHttpServletRequest().getContentType())) {
        String jsonString = readHttpRequestBodyStream();
        
        ObjectMapper om = new ObjectMapper();
        
        return om.readValue(jsonString, valueType);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
    return null;
  }
  
  public static ClientTypeEnum getClientType() {
    HttpServletRequest request = getHttpServletRequest();
    
    String userAgent = request.getHeader(Constant.HTTP_HEADER_USER_AGENT);
    String clientType = request.getHeader(Constant.HTTP_HEADER_CLIENT_TYPE);
    
    if(StringUtils.isBlank(clientType)) {//brower(mobile/pc)
      if (userAgent.toLowerCase().contains(Constant.HTTP_HEADER_USER_AGENT_MOBILE)) {
        return ClientTypeEnum.MOBILE_BROWER;
      } else {
        return ClientTypeEnum.PC_BROWER;
      }
    } else {//app(mobile/pc)
      if (clientType.contains(Constant.HTTP_HEADER_CLIENT_TYPE_PC_APP)) {//pc app(PC-APP/QT-PROGRAM)
        return ClientTypeEnum.PC_APP;
      } else {//modile app(MOBILE-APP/IOS-PROGRAM)(MOBILE-APP/ANDROID-PROGRAM)
        return ClientTypeEnum.MOBILE_APP;
      }
    }
  }
  
  public static boolean isBrower() {
    ClientTypeEnum cte = getClientType();
    
    if (cte == ClientTypeEnum.MOBILE_BROWER || cte == ClientTypeEnum.PC_BROWER) {
      return true;
    }
    
    return false;
  }
  
  public static boolean isApp() {
    return !isBrower();
  }
  
  public static boolean isPCApp() {
    return getClientType() == ClientTypeEnum.PC_APP ? true : false;
  }
  
  public static boolean isPCBrower() {
    /*
     * String userAgent = request.getHeader("User-Agent").trim();
     * 
     * if(userAgent.contains("Mozilla") ||
     * userAgent.contains("AppleWebKit") ||
     * userAgent.contains("AppleWebKit") ||
     * userAgent.contains("Chrome") ||
     * userAgent.contains("Safari") ||
     * userAgent.contains("KHTML")) {
     * return true;
     * }
     */
    
    return getClientType() == ClientTypeEnum.PC_BROWER ? true : false;
  }
  
  public static boolean isMobileApp() {
    return getClientType() == ClientTypeEnum.MOBILE_APP ? true : false;
  }
  
  public static boolean isMobileBrower() {
    return getClientType() == ClientTypeEnum.MOBILE_BROWER ? true : false;
  }
  
  public static void downloadFile(String fileRealPath, HttpServletResponse response) {
    InputStream inputStream = null;
    
    try {
      File file = new File(fileRealPath);
      
      String fileName = file.getName();
      String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
      
      response.reset();
      response.setContentType("application/octet-stream");
      response.setHeader("Pragma", "public");
      response.setHeader("Cache-Control", "private");
      response.setHeader("Content-type", "application/" + fileType);
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Accept-Ranges", "bytes");
      response.setHeader("Content-Length", file.length() + "");
      response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
      response.setHeader("Connection", "close");
      
      inputStream = new FileInputStream(file);
      
      IOUtils.copyLarge(inputStream, response.getOutputStream());
    } catch (Exception e) {
      throw new GenericException(e);
    } finally {
      if(inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          throw new GenericException(e);
        }
      }
    }
  }
  
  /**
   * 返回文件上传指定真实目录路径，如：<br>
   * <p>Windows:  <b style="padding-left:10px;">D:/tmp/resources/uploads/{dirCategory}/{yyyyMMdd}/</b></p>
   * <p>Linux:    <b style="padding-left:40px;">/tmp/resources/uploads/{dirCategory}/{yyyyMMdd}/</b></p>
   */
  public static String getUploadRealPath(String dirCategory) {
    String dir = StringUtils.isBlank(dirCategory) ? File.separator : File.separator + dirCategory + File.separator;
    
    String fileUploadDir = PropCfg.getFileUploadDir();
    
    if(StringUtils.isBlank(fileUploadDir)) {
      fileUploadDir = getFileRealPath(Constant.RESOURCES_UPLOADS_RELATIVE_DIRECTORY);
    }
    
    return fileUploadDir + dir + JavaDateFormatUtils.FORMAT_yyyyMMdd.format(PropCfg.getDate()) + File.separator;
  }
  
  /**
   * 返回文件上传指定真实目录路径，如：<br>
   * <p>Windows:  <b style="padding-left:10px;">D:/tmp/resources/downloads/{dirCategory}/{yyyyMMdd}/</b></p>
   * <p>Linux:    <b style="padding-left:40px;">/tmp/resources/downloads/{dirCategory}/{yyyyMMdd}/</b></p>
   */
  public static String getDownloadRealPath(String dirCategory) {
    String dir = StringUtils.isBlank(dirCategory) ? File.separator : File.separator + dirCategory + File.separator;
    
    String fileDownloadDir = PropCfg.getFileDownloadDir();
    
    if(StringUtils.isBlank(fileDownloadDir)) {
      fileDownloadDir = getFileRealPath(Constant.RESOURCES_DOWNLOADS_RELATIVE_DIRECTORY);
    }
    
    return fileDownloadDir + dir + JavaDateFormatUtils.FORMAT_yyyyMMdd.format(PropCfg.getDate()) + File.separator;
  }

}
