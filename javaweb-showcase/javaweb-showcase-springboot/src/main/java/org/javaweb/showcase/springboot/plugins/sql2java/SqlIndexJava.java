/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.plugins.sql2java;


/**
 * <pre><code>
 * KEY `INDEX_ADDRESS_MOBILE` USING HASH (`address`,`mobile`)
 * </code></pre>
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 上午10:56:31$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class SqlIndexJava {
  
  private String   key;
  private String   using;
  private String[] columns;
  
  public String getKey() {
    return key;
  }
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public String getUsing() {
    return using;
  }
  
  public void setUsing(String using) {
    this.using = using;
  }
  
  public String[] getColumns() {
    return columns;
  }
  
  public void setColumns(String[] columns) {
    this.columns = columns;
  }
}
