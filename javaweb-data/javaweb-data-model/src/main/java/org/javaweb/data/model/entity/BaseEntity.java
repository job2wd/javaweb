/*
 * $Id: BaseEntity.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/model/BaseEntity.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.javaweb.common.Constant;
import org.javaweb.common.util.JavaDateFormatUtils;
import org.javaweb.common.util.JavaObjectToStringUtils;
import org.javaweb.data.model.annotation.TableColumn;
import org.javaweb.data.model.domain.QueryCondition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * POJO base class.
 *
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2015-9-9 下午8:01:36$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = -7859018446837529523L;
  
  /**
   * 分页开始行（从0开始） MySQL: LIMIT #{getOffset()}, #{getRows()}。真正执行查询翻页用到的是 {@link #getOffset()} 和 {@link #getRows()}
   */
  private Integer pageNum;
  /**
   * 获取的记录数（每页显示记录数：{@link #getRows()}）。真正执行查询翻页用到的是 {@link #getOffset()} 和 {@link #getRows()}
   */
  private Integer pageSize;
  
  /**
   * 记录是否被删除：0 - 未删除；1 - 已删除；默认为0
   */
  @TableColumn("deleted")
  protected Short deleted;
  
  /**
   * 是否为初始化的数据：0 - 否（通过界面创建或导入的数据）；1 - 是。默认为0。
   */
  @TableColumn("predefined")
  protected Short predefined;
  
  /**
   * 排序字段及其排序方式集：key - column; value - ASC/DESC
   */
  @JsonIgnore
  private Map<String, String> orders;

  /**
   * 查询条件集
   */
  @JsonIgnore
  private List<QueryCondition> queryConditions;

  /**
   * 当前查询要返回的结果列集合
   */
  @JsonIgnore
  private List<String> resultColumns;
  
  /**
   * 分页开始行（从0开始） MySQL: LIMIT #{getOffset()}, #{getRows()}。真正执行查询翻页用到的是 {@link #getOffset()} 和 {@link #getRows()}
   */
  @JsonIgnore
  public Integer getOffset() {
    if (getPageNum() == null || getPageSize() == null) {
      return null;
    }
    return (getPageNum() - 1) * getPageSize();
  }
  
  /**
   * 获取的记录数（每页显示记录数：{@link #getRows()}）。真正执行查询翻页用到的是 {@link #getOffset()} 和 {@link #getRows()}
   */
  @JsonIgnore
  public Integer getRows() {
    return getPageSize();
  }

  /**
   * 当前页数（从1开始）
   */
  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  /**
   * 当前页数（从1开始）
   */
  public Integer getPageNum() {
    return pageNum;
  }

  /**
   * 每页显示记录条数，同 {@link #getRows()}
   */
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  
  /**
   * 每页显示记录条数，同 {@link #getRows()}
   */
  public Integer getPageSize() {
    return pageSize;
  }
  
  /**
   * 排序字段及其排序方式集：key - column; value - ASC/DESC
   */
  public Map<String, String> getOrders() {
    return orders;
  }

  /**
   * 排序字段及其排序方式集：key - column; value - ASC/DESC
   */
  public void setOrders(Map<String, String> orders) {
    this.orders = orders;
  }

  /**
   * 记录是否被删除：0 - 未删除；1 - 已删除；默认为0
   */
  public Short getDeleted() {
    return deleted;
  }

  /**
   * 记录是否被删除：0 - 未删除；1 - 已删除；默认为0
   */
  public void setDeleted(Short deleted) {
    this.deleted = deleted;
  }

  /**
   * 是否为初始化的数据：0 - 否（通过界面创建或导入的数据）；1 - 是。默认为0。
   */
  public Short getPredefined() {
    return predefined;
  }

  /**
   * 是否为初始化的数据：0 - 否（通过界面创建或导入的数据）；1 - 是。默认为0。
   */
  public void setPredefined(Short predefined) {
    this.predefined = predefined;
  }

  /**
   * 查询条件集
   */
  public List<QueryCondition> getQueryConditions() {
    return queryConditions;
  }

  /**
   * 查询条件集
   */
  public void setQueryConditions(List<QueryCondition> queryConditions) {
    this.queryConditions = queryConditions;
  }

  public String getFormatedTime(Long time) {
    if(time == null || time == 0l) {
      return null;
    }
    return JavaDateFormatUtils.getFormatedDate(time);
  }

  public boolean isIgnoreTime(Date date) {
    if(date != null && DateUtils.isSameDay(date, Constant.DEFAULT_IGNORE_DATE)) {
      return true;
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return JavaObjectToStringUtils.toString(this);
  }

  public List<String> getResultColumns() {
    return resultColumns;
  }
  
  public void setResultColumns(List<String> resultColumns) {
    this.resultColumns = resultColumns;
  }
  
  public void init() {
    if(deleted == null) {
      deleted = 0;
    }
    if(predefined == null) {
      predefined = 0;
    }
    if (pageNum == null) {
      pageNum = 1;
    }
    if (pageSize == null) {
      pageSize = Constant.DEFAULT_PAGE_SIZE;
    }
    if(orders == null) {
      orders = new HashMap<String, String>();
    }
    if(queryConditions == null) {
      queryConditions = new ArrayList<QueryCondition>();
    }
  }
  
}
