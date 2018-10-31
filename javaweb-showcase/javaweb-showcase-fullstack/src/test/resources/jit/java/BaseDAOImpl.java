/*
 * @(#) com.focus.main.common.dao#BaseDAOImpl.java    
 * Copyright 2009 FOCUS Information Technologies Co.,Ltd. All rights reserved.
 * JIT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.FOCUS.com.cn
 * mailto:wangwdxa@163.com
 * v1.0  2009-6-1
 */
package com.bop.petbook.web.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * DAO 具体实现类
 * 
 * @param <T>
 * @param <ID>
 * 
 * @see
 * @author wangwd
 * @date 2009-6-1 下午11:12:33
 * @since JDK1.5.0_08
 * @version v1.0
 */
public abstract class BaseDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements IBaseDAO<T, ID> {

  private Log log = LogFactory.getLog(BaseDAOImpl.class);

  /**
   * 当前操作的持久化类
   */
  private Class<T> presentClass;

  @SuppressWarnings("unchecked")
  public BaseDAOImpl() {
    Type type = getClass().getGenericSuperclass();
    this.presentClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    log.debug("get present class : " + presentClass.getName());
  }

  @SuppressWarnings("unchecked")
  public ID addObject(T entity) {
    return (ID) getHibernateTemplate().save(entity);
  }

  public void deleteObjectById(ID id){
    String hql = " delete " + presentClass.getName() + " where id='"+id+"'";
    getHibernateTemplate().bulkUpdate(hql);
  }
  
  public void deleteObject(T entity) {
    getHibernateTemplate().delete(entity);
  }

  public int deleteObjectsByIds(ID[] ids) {
    String hql = " delete " + presentClass.getName() + " where id in(:idvalue)";
    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    Query query = session.createQuery(hql);
    query.setParameterList("idvalue", ids);
    return query.executeUpdate();
  }

  public void updateObject(T entity) {
    getHibernateTemplate().update(entity);
  }

  @SuppressWarnings("unchecked")
  public T findObjectById(ID id) {
    return (T) getHibernateTemplate().get(presentClass, id);
  }

  @SuppressWarnings("unchecked")
  public List<T> findAllObjects() {
    return getHibernateTemplate().find("from " + this.presentClass.getName());
  }

  @SuppressWarnings("unchecked")
  public List<T> findObjects(ConditionHelper ch, OrderHelper oh, int firstResult, int maxResults) {
    DetachedCriteria criteria = DetachedCriteria.forClass(presentClass);
    this.addConditions(criteria, ch);
    this.addOrders(criteria, oh);
    if (firstResult >= 0 && maxResults > 0) {
      return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    } else {
      return getHibernateTemplate().findByCriteria(criteria);
    }

  }

  private void addConditions(DetachedCriteria criteria, ConditionHelper ch) {
    if (ch != null) {
      Set<Criterion> conditions = ch.getConditions();
      Iterator<Criterion> iter = conditions.iterator();
      while (iter.hasNext()) {
        criteria.add((Criterion) iter.next());
      }
    }
  }

  private void addOrders(DetachedCriteria criteria, OrderHelper oh) {
    if (oh != null) {
      Set<Order> orders = oh.getOrders();
      Iterator<Order> iter = orders.iterator();
      while (iter.hasNext()) {
        criteria.addOrder((Order) iter.next());
      }
    }
  }

}
