/*
 * $Id: QueryCondition.java 5629 2018-09-03 06:48:08Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/domain/QueryCondition.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.domain;


/**
 * 
 * @author wangwd
 * @version $Revision: 5629 $, $Date: 2018年5月10日 下午3:54:04$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-03 14:48:08#$
 */
public class QueryCondition {

  private String column;
  
  private Object value;
  
  /**
   * 枚举 {@link QueryCondition.OPEnum#getOp()} 的值
   * IN / NOT IN / IS NULL / IS NOT NULL / EXIST / NOT EXIST / LIKE / BETWEEN AND / = / <> / > / < / >= / <=
   */
  private String op;

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }
  
  public enum OPEnum {
    /**
     * IN
     */
    IN("IN"),
    /**
     * NOT IN
     */
    NOT_IN("NOT IN"),
    /**
     * EXISST
     */
    EXIST("EXIST"),
    /**
     * NOT EXIST
     */
    NOT_EXIST("NOT EXIST"),
    /**
     * LIKE %...%
     */
    LIKE("LIKE"),
    /**
     * LIKE %...
     */
    LIKE_BEGIN("LIKE_BEGIN"),
    /**
     * LIKE ...%
     */
    LIKE_END("LIKE_END"),
    /**
     * BETWEEN
     */
    BETWEEN("BETWEEN"),
    /**
     * =
     */
    EQ("="),
    /**
     * <>
     */
    NEQ("<>"),
    /**
     * >
     */
    GT(">"),
    /**
     * <
     */
    LT("<"),
    /**
     * >=
     */
    GT_EQ(">="),
    /**
     * <=
     */
    LT_EQ("<="),
    
    /**
     * IS NULL
     */
    IS_NULL("IS NULL"),
    /**
     * IS NOT NULL
     */
    IS_NOT_NULL("IS NOT NULL");
    
    private String op;
    
    OPEnum(String op) {
      this.op = op;
    }

    public String getOp() {
      return op;
    }

  }
    
}
