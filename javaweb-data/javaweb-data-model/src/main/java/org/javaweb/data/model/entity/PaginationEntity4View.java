/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.data.model.entity;

import java.util.List;


/**
 * 分页数据封装实体类
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年4月27日 下午2:01:25$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class PaginationEntity4View<T> {

  /**
   * 总记录数
   */
  private Integer total;
  /**
   * 当前页数
   */
  private Integer currentPage;
  /**
   * 总页数
   */
  private Integer totalPage;
  /**
   * 每页显示记录数
   */
  private Integer pageSize;
  /**
   * 分页显示数据
   */
  private List<T> datas;
  
  public Integer getTotal() {
    return total;
  }
  
  public void setTotal(Integer total) {
    this.total = total;
  }
  
  public Integer getCurrentPage() {
    return currentPage;
  }
  
  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }
  
  public Integer getTotalPage() {
    return totalPage;
  }
  
  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }
  
  public Integer getPageSize() {
    return pageSize;
  }
  
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  
  public List<T> getDatas() {
    return datas;
  }
  
  public void setDatas(List<T> datas) {
    this.datas = datas;
  }
  
}
