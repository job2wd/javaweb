/*
 * $Id: JavaPropertiesFileUtils.java 5688 2018-09-06 05:22:25Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaPropertiesFileUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author wangwd
 * @version $Revision: 5688 $, $Date: 2018年9月6日 上午8:56:19$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-06 13:22:25#$
 */
public class JavaPropertiesFileUtils {
  
  public static Properties readPropertiesFile(File propertiesFile) {
    try {
      Properties props = new Properties();
      
      props.load(new FileInputStream(propertiesFile));
      
      return props;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static boolean getBoolean(Properties properties, String key, boolean defaultVal) {
    return MapUtils.getBooleanValue(properties, key, defaultVal);
  }
  
  public static long getLong(Properties properties, String key, long defaultVal) {
    return MapUtils.getLongValue(properties, key, defaultVal);
  }
  
  public static int getInt(Properties properties, String key, int defaultVal) {
    return MapUtils.getIntValue(properties, key, defaultVal);
  }
  
  public static double getDouble(Properties properties, String key, double defaultVal) {
    return MapUtils.getDoubleValue(properties, key, defaultVal);
  }
  
  public static Date getDate(Properties properties, String key, Date defaultVal) {
    String v = get(properties, key);
    return StringUtils.isBlank(v) ? defaultVal : JavaDateUtils.parseDate(v);
  }
  
  public static String get(Properties properties, String key) {
    return properties.getProperty(key);
  }
  
}
