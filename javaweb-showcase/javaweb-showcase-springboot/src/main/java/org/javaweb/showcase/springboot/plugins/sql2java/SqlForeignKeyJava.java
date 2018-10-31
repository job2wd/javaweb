/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.plugins.sql2java;

import java.util.Map;

/**
 * <pre><code>
 * CONSTRAINT `FORGIN_STUDENT_ID` FOREIGN KEY (`student_id`) REFERENCES `student` (`id1`) ON DELETE CASCADE ON UPDATE CASCADE
 * </code></pre>
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 上午10:55:34$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class SqlForeignKeyJava {
  
  private String constraint;
  private String foreignKey;
  
  private String references;
  private String referencesColumn;
  
  private Map<String, String> cascads;
  
  public String getConstraint() {
    return constraint;
  }
  
  public void setConstraint(String constraint) {
    this.constraint = constraint;
  }
  
  public String getForeignKey() {
    return foreignKey;
  }
  
  public void setForeignKey(String foreignKey) {
    this.foreignKey = foreignKey;
  }
  
  public String getReferences() {
    return references;
  }
  
  public void setReferences(String references) {
    this.references = references;
  }
  
  public String getReferencesColumn() {
    return referencesColumn;
  }
  
  public void setReferencesColumn(String referencesColumn) {
    this.referencesColumn = referencesColumn;
  }
  
  public Map<String, String> getCascads() {
    return cascads;
  }
  
  public void setCascads(Map<String, String> cascads) {
    this.cascads = cascads;
  }
  
}
