/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.plugins.sql2java;


/**
 * <pre><code>
 * DROP TABLE IF EXISTS `book`;
   CREATE TABLE `book` (
    `id` int(10) unsigned NOT NULL auto_increment COMMENT 'ID',
    `name` varchar(10) default NULL COMMENT 'name',
    `student_id` int(10) NOT NULL,
    `teacher_id` int(10) NOT NULL,
  
    PRIMARY KEY  (`id1`),
    --PRIMARY KEY  (`student_id`,`teacher_id`),主键
  
    KEY `INDEX_NAME` USING BTREE (`name`),
    KEY `INDEX_ADDRESS_MOBILE` USING HASH (`address`,`mobile`),--索引
  
    CONSTRAINT `FORGIN_STUDENT_ID` FOREIGN KEY (`student_id`) REFERENCES `student` (`id1`) ON DELETE CASCADE ON UPDATE CASCADE,--外键
    CONSTRAINT `FORGIN_TEACHER_ID` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Book table';
 * </code></pre>
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 上午10:42:24$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class SqlTableJava {
  
  private String javaFileName;
  private String tableName;
  private String engine;
  private String defaultCharset;
  private String comment;
  
  private String[] primaryKeys;
  private SqlForeignKeyJava[] foreignKeys;
  private SqlIndexJava[]      indexs;
  
  public String getJavaFileName() {
    return javaFileName;
  }
  
  public void setJavaFileName(String javaFileName) {
    this.javaFileName = javaFileName;
  }
  
  public String getTableName() {
    return tableName;
  }
  
  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
  
  public String getEngine() {
    return engine;
  }
  
  public void setEngine(String engine) {
    this.engine = engine;
  }
  
  public String getDefaultCharset() {
    return defaultCharset;
  }
  
  public void setDefaultCharset(String defaultCharset) {
    this.defaultCharset = defaultCharset;
  }
  
  public String getComment() {
    return comment;
  }
  
  public void setComment(String comment) {
    this.comment = comment;
  }
  
  public String[] getPrimaryKeys() {
    return primaryKeys;
  }
  
  public void setPrimaryKeys(String[] primaryKeys) {
    this.primaryKeys = primaryKeys;
  }
  
  public SqlForeignKeyJava[] getForeignKeys() {
    return foreignKeys;
  }
  
  public void setForeignKeys(SqlForeignKeyJava[] foreignKeys) {
    this.foreignKeys = foreignKeys;
  }
  
  public SqlIndexJava[] getIndexs() {
    return indexs;
  }
  
  public void setIndexs(SqlIndexJava[] indexs) {
    this.indexs = indexs;
  }

}
