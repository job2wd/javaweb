/*
 * $Id: PropCfg.java 5887 2018-09-14 03:48:09Z liuyuanke $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/PropCfg.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.common;

import java.util.Date;

/**
 * 项目属性文件 (.properties) 配置信息获取快捷工具类。
 * 
 * @author wangwd
 * @version $Revision: 5887 $, $Date: 2014-12-24 下午6:22:53$
 * @LastChanged $Author: liuyuanke $, $Date:: 2018-09-14 11:48:09#$
 */
public class PropCfg {

  /**
   * 属性文件 jdbc.properties 中 jdbc.db.ip 配置了当前访问的数据库IP地址
   */
  public static String getDatabaseIp() {
    return PropertiesMapHelper.get("jdbc.db.ip");
  }
  
  /**
   * 属性文件 jdbc.properties 中 jdbc.db.port 配置了当前访问的数据库PORT
   */
  public static int getDatabasePort() {
    return PropertiesMapHelper.getInt("jdbc.db.port", 3306);
  }
  
  /**
   * 属性文件 jdbc.properties 中 jdbc.username 配置了当前访问的数据库登录用户名
   */
  public static String getDatabaseUsername() {
    return PropertiesMapHelper.get("jdbc.username");
  }
  
  /**
   * 属性文件 jdbc.properties 中 jdbc.password 配置了当前访问的数据库登录密码
   */
  public static String getDatabasePassword() {
    return PropertiesMapHelper.get("jdbc.password");
  }
  
  /**
   * 属性文件 jdbc.properties 中 jdbc.db.name 配置了当前访问的数据库名称
   */
  public static String getDatabaseName() {
    return PropertiesMapHelper.get("jdbc.db.name");
  }
  
  /**
   * 属性文件 system.properties 中 system.env.profile.default 设置了系统默认运行环境：
   * <p>
   * test（单元测试）、functional（功能测试）、development（开发）、production（生产）
   * </p>
   * 
   * @return ""/test/functional/development
   */
  public static String getSystemEnvProfile() {
    return PropertiesMapHelper.get("system.env.profile.default");
  }
  
  public static boolean isTestEnv() {
    if ("test".equals(getSystemEnvProfile())) {
      return true;
    }
    return false;
  }
  
  public static boolean isFunctionalEnv() {
    if ("functional".equals(getSystemEnvProfile())) {
      return true;
    }
    return false;
  }
  
  public static boolean isDevelopmentEnv() {
    if ("development".equals(getSystemEnvProfile())) {
      return true;
    }
    return false;
  }
  
  public static boolean isProductionEnv() {
    if ("production".equals(getSystemEnvProfile())) {
      return true;
    }
    return false;
  }
  
  /**
   * 获取属性文件（system.properties）中配置的系统运行过程中上传的文件存放目录（属性 file.dir.upload 的值）
   */
  public static String getFileUploadDir() {
    return PropertiesMapHelper.get("file.dir.upload");
  }
  
  /**
   * 获取属性文件（system.properties）中配置的系统运行过程中待下载的文件存放目录（属性 file.dir.download 的值）
   */
  public static String getFileDownloadDir() {
    return PropertiesMapHelper.get("file.dir.download");
  }
  
  /**
   * 获取属性文件（system.properties）中配置的系统运行过程中产生的临时文件存放目录（属性 file.dir.tmp 的值，linux 可为 /data/tmp）
   * @return 若配置未取到，则默认返回 System.getProperty("java.io.tmpdir") 指定的目录
   */
  public static String getFileTempDir() {
    return PropertiesMapHelper.get("file.dir.tmp", System.getProperty("java.io.tmpdir"));
  }
  
  public static Date getDate() {
    return PropertiesMapHelper.getDate("test.date.now", new Date());
  }
  
  public static Long getSessionTimeoutMilliseconds() {
    return PropertiesMapHelper.getLong("system.login.session.timeout.milliseconds",
        Constant.DEFAULT_SESSION_TIMEOUT_MILLISECONDS);
  }
  
	  //////// 找回密码,发email相关   ///////
	  // 获取发送email源
	  public static String getEmailFrm()
	  {
		  return PropertiesMapHelper.get("send.email.frm");
	  }
	  // email别名
	  public static String getEmailAlias()
	  {
		  return PropertiesMapHelper.get("send.email.alias");
	  }
	  
	  public static String getEmailSubject()
	  {
		  return PropertiesMapHelper.get("send.email.subject");
	  }
	  // email内容前段
	  public static String getEmailPreTxt()
	  {
		  return PropertiesMapHelper.get("send.email.pre.txt");
	  }
  
	 //email内容中段
	 public static String getEmailTxt1()
	 {
		  return PropertiesMapHelper.get("send.email.txt1");
	 }
	//email内容中段
	public static String getEmailTxt2()
	{
		  return PropertiesMapHelper.get("send.email.txt2");
	}
	 //email内容后段
	 public static String getEmailSuffTxt()
	 {
		  return PropertiesMapHelper.get("send.email.suff.txt");
	 }
	 // captcha 有效时间
	 public static  int  getCaptchaPeriod()
	 {
		 return Integer.parseInt(PropertiesMapHelper.get("send.captcha.period"));
	 }
}
