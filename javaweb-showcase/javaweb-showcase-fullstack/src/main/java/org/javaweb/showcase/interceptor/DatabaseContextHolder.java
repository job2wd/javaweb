/*
 * $Id: DatabaseContextHolder.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/interceptor/DatabaseContextHolder.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.interceptor;


/**
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-5-26 下午8:04:33$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class DatabaseContextHolder {

  public final static String DATA_SOURCE_CONF = "dataSource_conf";
  public final static String DATA_SOURCE_DATA = "dataSource_data";
  
  private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
  
  public static void setCustomerType(String customerType) {
      contextHolder.set(customerType);
  }
    
  public static String getCustomerType() {
      return contextHolder.get();
  }
    
  public static void clearCustomerType() {
      contextHolder.remove();
  }
  
}
