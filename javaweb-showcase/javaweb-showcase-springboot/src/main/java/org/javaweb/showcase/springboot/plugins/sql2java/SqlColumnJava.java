/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.plugins.sql2java;


/**
 * <pre><code>
 * 
 * `id` int(10) unsigned NOT NULL auto_increment COMMENT 'ID',
 * `name` varchar(10) default NULL COMMENT 'name',
 * `storage_remain_num` decimal(8,2) unsigned DEFAULT NULL COMMENT '药品当前库存余量（克）',
 * `storage_date` datetime DEFAULT NULL COMMENT '入库日期时间',
 * 
 * </code></pre>
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 上午10:44:49$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class SqlColumnJava {
  
  private String columnName;
  private String javaPropertyName;
  
  private String columnType;
  private String javaPropertyType;
  
  private String columnLength;
  
  private Boolean notNull;
  private String  defaultValue;
  private Boolean unsigned;
  
  private String comment;
  
  public String getColumnName() {
    return columnName;
  }
  
  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }
  
  public String getJavaPropertyName() {
    return javaPropertyName;
  }
  
  public void setJavaPropertyName(String javaPropertyName) {
    this.javaPropertyName = javaPropertyName;
  }
  
  public String getColumnType() {
    return columnType;
  }
  
  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }
  
  public String getJavaPropertyType() {
    return javaPropertyType;
  }
  
  public void setJavaPropertyType(String javaPropertyType) {
    this.javaPropertyType = javaPropertyType;
  }
  
  public String getColumnLength() {
    return columnLength;
  }
  
  public void setColumnLength(String columnLength) {
    this.columnLength = columnLength;
  }
  
  public Boolean getNotNull() {
    return notNull;
  }
  
  public void setNotNull(Boolean notNull) {
    this.notNull = notNull;
  }
  
  public String getDefaultValue() {
    return defaultValue;
  }
  
  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }
  
  public Boolean getUnsigned() {
    return unsigned;
  }
  
  public void setUnsigned(Boolean unsigned) {
    this.unsigned = unsigned;
  }
  
  public String getComment() {
    return comment;
  }
  
  public void setComment(String comment) {
    this.comment = comment;
  }
  
}
