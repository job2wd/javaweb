/*
 * $Id: DataDaoHibernate.java 136 2017-02-06 06:16:43Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/hibernate/DataDaoHibernate.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.hibernate;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;


/**
 * 针对 fba_data 库的 DAO Hibernate 支持实现基类。<br>
 * 可用于获取 Hibernate SessionFactory 和 Session 等。
 * 
 * @author wangwd
 * @version $Revision: 136 $, $Date: 2015-8-13 下午6:19:43$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:16:43#$
 */
public class DataDaoHibernate<T, PK extends Serializable> extends GenericDaoHibernateBase<T, PK> {

  @Resource(name = "sessionFactory")
  protected SessionFactory sessionFactory;

  @Override
  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Override
  //@Required
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

}
