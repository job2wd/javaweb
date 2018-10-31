/*
 * @(#) com.focus.main.common.dao#OrderHelper.java    
 * Copyright 2009 FOCUS Information Technologies Co.,Ltd. All rights reserved.
 * JIT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.FOCUS.com.cn
 * mailto:wangwdxa@163.com
 * v1.0  2009-6-12
 */
package com.bop.petbook.web.common.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.Order;

/**
 * 对执行数据库查询时排序条件的分装
 * 
 * @see
 * @author wangwd
 * @date 2009-6-12 上午09:22:46
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class OrderHelper {

  private Set<Order> orders;

  public OrderHelper() {
    orders = new HashSet<Order>();
  }

  public boolean appendOrder(Order order) {
    if (order == null) {
      return false;
    }
    return orders.add(order);
  }

  public Set<Order> getOrders() {
    return this.orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }

}
