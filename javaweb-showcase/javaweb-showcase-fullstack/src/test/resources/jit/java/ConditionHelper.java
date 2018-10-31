/*
 * @(#) com.focus.main.common.dao#ConditionHelper.java    
 * Copyright 2009 FOCUS Information Technologies Co.,Ltd. All rights reserved.
 * JIT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.FOCUS.com.cn
 * mailto:wangwdxa@163.com
 * v1.0  2009-6-12
 */
package com.bop.petbook.web.common.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.Criterion;

/**
 * 对执行数据库查询时查询条件的分装
 * 
 * @see
 * @author wangwd
 * @date 2009-6-12 上午09:22:22
 * @since JDK1.5.0_08
 * @version v1.0
 */
public class ConditionHelper {

  private Set<Criterion> conditions;

  public ConditionHelper() {
    conditions = new HashSet<Criterion>();
  }

  public boolean appendCondition(Criterion criterion) {
    if (criterion == null) {
      return false;
    }
    return conditions.add(criterion);
  }

  public Set<Criterion> getConditions() {
    return this.conditions;
  }

  public void setConditions(Set<Criterion> conditions) {
    this.conditions = conditions;
  }

}
