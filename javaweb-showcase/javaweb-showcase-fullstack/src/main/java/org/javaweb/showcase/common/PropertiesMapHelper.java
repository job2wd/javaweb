/*
 * $Id: PropertiesMapHelper.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/common/PropertiesMapHelper.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 存放系统所有 properties 文件中配置信息，可直接获取其中的属性值。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-12-24 下午6:20:24$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class PropertiesMapHelper {

  private static final Logger log = LoggerFactory.getLogger(PropertiesMapHelper.class);

  private static Map<String, String> propertiesMap = new HashMap<String, String>();
  
  private static Map<String, File> propesFile = new HashMap<String, File>();
  
  public static boolean getBoolean(String key, boolean defaultVal) {
    return MapUtils.getBooleanValue(propertiesMap, key, defaultVal);
  }
  
  public static long getLong(String key, long defaultVal) {
    return MapUtils.getLongValue(propertiesMap, key, defaultVal);
  }
  
  public static int getInt(String key, int defaultVal) {
    return MapUtils.getIntValue(propertiesMap, key, defaultVal);
  }
  
  public static double getDouble(String key, double defaultVal) {
    return MapUtils.getDoubleValue(propertiesMap, key, defaultVal);
  }
  
  public static <T extends Enum<T>> T getEnum(String key, Class<T> type, T defaultValue) {
    String val = get(key);
    return val == null ? defaultValue : Enum.valueOf(type, val);
  }
  
  public String findValue(String... keys) {
    for (String key : keys) {
      String value = get(key);
      if (value != null) {
        return value;
      }
    }
    return null;
  }
  
  /**
   * 获得系统 properties 文件中，配置 key 对应的 value。
   * @param key
   * @return
   */
  public static String get(String key) {
    return propertiesMap.get(key);
  }
  
  /**
   * 获得系统 properties 文件中，配置 key 对应的 value。
   * @param key
   * @return
   */
  public static String get(String key, String defaultValue) {
    String val = propertiesMap.get(key);
    if(val == null || val.length() < 1){
      val = defaultValue;
    }
    return val;
  }
  
  /**
   * 将属性配置对添加到 Map 中，若存在，则替换。
   * @param key
   * @param value
   */
  public static String put(String key, String value) {
    if(propertiesMap.containsKey(key)) {
      log.warn("property key [" + key + "] has exist, will place it.");
    }
    return propertiesMap.put(key, value);
  }
  
  /**
   * 从 Map 中移除配置的属性对。若不存在，则返回空
   * @param key
   * @return
   */
  public static String remove(String key) {
    if(!propertiesMap.containsKey(key)) {
      log.warn("property key [" + key + "] is not exist.");
      return "";
    }
    return propertiesMap.remove(key);
  }
  
  public static boolean wirteProperties(String fileName, Map<String, String> pairs) {
    if (pairs == null || pairs.size() == 0) {
      return false;
    }
    try {
      if (propesFile.containsKey(fileName)) {
        File f = propesFile.get(fileName);

        Properties props = new Properties();
        props.load(new FileInputStream(f));

        Set<String> keys = pairs.keySet();
        for (String key : keys) {
          props.setProperty(key, pairs.get(key));
        }

        props.store(new FileOutputStream(f), "属性文件配置，最后修改日期：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  public static void putPropertiesFile(String fileName, File file) {
    propesFile.put(fileName, file);
  }

}
