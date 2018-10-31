/*
 * $Id: PropCfg.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/common/PropCfg.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.common;



/**
 * 项目属性文件 (.properties) 配置信息获取快捷工具类。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-12-24 下午6:22:53$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class PropCfg {

  /**
   * 属性文件 module.properties 中 test.enabled 设置为了是否启用测试。
   * @return true/false
   */
  public static boolean isEnabledTest() {
    return PropertiesMapHelper.getBoolean("test.enabled", false);
  }
  
  /**
   * 获取属性文件（module.properties）中配置的系统运行过程中产生的临时文件存放目录（属性 file.dir.tmp 的值，linux 可为 /data/tmp）
   * @return 若配置未取到，则默认返回 System.getProperty("java.io.tmpdir") 指定的目录
   */
  public static String getFileTempDir() {
    return PropertiesMapHelper.get("file.dir.tmp", System.getProperty("java.io.tmpdir"));
  }
  
}
