/*
 * $Id: ResponseBean4Pagination.java 5565 2018-08-30 05:06:15Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/domain/ResponseBean4Pagination.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 分页数据封装实体类
 * 
 * @author wangwd
 * @version $Revision: 5565 $, $Date: 2018年4月27日 下午2:01:25$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-30 13:06:15#$
 */
public class ResponseBean4Pagination<T> extends ResponseBean {

  @JsonIgnore
  private static final long serialVersionUID = -8868654168407920928L;
  /**
   * 总记录数
   */
  @JsonIgnore
  private Integer total;
  /**
   * 当前页数
   */
  @JsonIgnore
  private Integer pageNum;
  /**
   * 每页显示记录数
   */
  @JsonIgnore
  private Integer pageSize;
  
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }
  
  public Integer getPageNum() {
    return pageNum;
  }
  
  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }
  
  /**
   * 总页数
   */
  @JsonIgnore
  public Integer getTotalPages() {
    return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getPageSize());
  }
  
  /**
   * 当前页开始记录条数
   */
  @JsonIgnore
  public Integer getStartSize() {
    return (pageNum - 1) * pageSize + 1;
  }
  
  /**
   * 当前页结束的记录条数
   */
  @JsonIgnore
  public Integer getEndSize() {
    if (pageNum * pageSize > total) {//最后一页，记录数不够 pageSize
      return total;
    }
    return pageNum * pageSize;
  }
  
  public Integer getPageSize() {
    return pageSize;
  }
  
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  
  @JsonIgnore
  public boolean hasPreviousPage() {
    return !isFirstPage();
  }
  
  @JsonIgnore
  public boolean hasNextPage() {
    return !isLastPage();
  }
  
  @JsonIgnore
  public boolean isFirstPage() {
    return pageNum == 1;
  }
  
  @JsonIgnore
  public boolean isLastPage() {
    return pageNum == getTotalPages();
  }
    
  public Integer getCount() {
    return this.total;
  }
  
  public Integer getLimit() {
    return this.pageSize;
  }
  
}
