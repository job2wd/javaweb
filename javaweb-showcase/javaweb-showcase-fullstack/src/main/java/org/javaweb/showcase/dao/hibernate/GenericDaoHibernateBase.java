/*
 * $Id: GenericDaoHibernateBase.java 136 2017-02-06 06:16:43Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/hibernate/GenericDaoHibernateBase.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.mail.search.SearchException;

import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;


/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.appfuse.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author wangwd
 * @version $Revision: 136 $, $Date: 2015-8-13 下午5:21:20$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:16:43#$
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public abstract class GenericDaoHibernateBase<T, PK extends Serializable> implements GenericDaoHibernate<T, PK>{

  protected final Logger log = LoggerFactory.getLogger(getClass());
  
  //protected Analyzer defaultAnalyzer;

  abstract SessionFactory getSessionFactory();
  
  abstract void setSessionFactory(SessionFactory sessionFactory);
  
  protected Class<T> persistentClass;// = getEntityClass(getClass());
  
  public GenericDaoHibernateBase() {
    //defaultAnalyzer = new StandardAnalyzer();//Version.LUCENE_35);
    persistentClass = getEntityClass(getClass());
  }
  
  @SuppressWarnings("unchecked")
  public Class<T> getEntityClass(Class<?> tClass) {
    ParameterizedType pt = (ParameterizedType) tClass.getGenericSuperclass();
    Class<T> entity = (Class<T>)pt.getActualTypeArguments()[0];
    return entity;
  }
  
  public Session getSession() throws HibernateException {
      Session session = getSessionFactory().getCurrentSession();
      if (session == null) {
        session = getSessionFactory().openSession();
      }
      return session;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<T> getAll() {
      Session session = getSession();
      return session.createCriteria(persistentClass).list();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<T> getAllDistinct() {
      Collection<T> result = new LinkedHashSet<T>(getAll());
      return new ArrayList<T>(result);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<T> search(String searchTerm) throws SearchException {//SearchException {
      return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public T get(PK id) {
      Session session = getSession();
      IdentifierLoadAccess byId = session.byId(persistentClass);
      T entity = (T) byId.load(id);

      if (entity == null) {
          log.warn("Uh oh, '" + persistentClass + "' object with id '" + id + "' not found...");
          throw new ObjectRetrievalFailureException(persistentClass, id);
      }

      return entity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean exists(PK id) {
      Session session = getSession();
      IdentifierLoadAccess byId = session.byId(persistentClass);
      T entity = (T) byId.load(id);
      return entity != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public T save(T object) {
      Session session = getSession();
      return (T) session.merge(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(T object) {
      Session session = getSession();
      session.delete(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void remove(PK id) {
      Session session = getSession();
      IdentifierLoadAccess byId = session.byId(persistentClass);
      T entity = (T) byId.load(id);
      session.delete(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
      Session session = getSession();
      Query namedQuery = session.getNamedQuery(queryName);

      for (String s : queryParams.keySet()) {
          namedQuery.setParameter(s, queryParams.get(s));
      }

      return namedQuery.list();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public void reindex() {
      //HibernateSearchTools.reindex(persistentClass, getSessionFactory().getCurrentSession());
  }


  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public void reindexAll(boolean async) {
      //HibernateSearchTools.reindexAll(async, getSessionFactory().getCurrentSession());
  }

}
