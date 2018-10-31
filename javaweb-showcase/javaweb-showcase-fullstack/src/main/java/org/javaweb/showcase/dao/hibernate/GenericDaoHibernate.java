/*
 * $Id: GenericDaoHibernate.java 136 2017-02-06 06:16:43Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/hibernate/GenericDaoHibernate.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.mail.search.SearchException;


/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 * @author wangwd
 * @version $Revision: 136 $, $Date: 2015-8-13 下午4:47:55$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:16:43#$
 */
public interface GenericDaoHibernate<T, PK extends Serializable> {

  /**
   * Generic method used to get all objects of a particular type. This
   * is the same as lookup up all rows in a table.
   * @return List of populated objects
   */
  List<T> getAll();

  /**
   * Gets all records without duplicates.
   * <p>Note that if you use this method, it is imperative that your model
   * classes correctly implement the hashcode/equals methods</p>
   * @return List of populated objects
   */
  List<T> getAllDistinct();

  /**
   * Gets all records that match a search term. "*" will get them all.
   * @param searchTerm the term to search for
   * @return the matching records
   * @throws SearchException
   */
  List<T> search(String searchTerm) throws Exception;//SearchException;

  /**
   * Generic method to get an object based on class and identifier. An
   * ObjectRetrievalFailureException Runtime Exception is thrown if
   * nothing is found.
   *
   * @param id the identifier (primary key) of the object to get
   * @return a populated object
   * @see org.springframework.orm.ObjectRetrievalFailureException
   */
  T get(PK id);

  /**
   * Checks for existence of an object of type T using the id arg.
   * @param id the id of the entity
   * @return - true if it exists, false if it doesn't
   */
  boolean exists(PK id);

  /**
   * Generic method to save an object - handles both update and insert.
   * @param object the object to save
   * @return the persisted object
   */
  T save(T object);


  /**
   * Generic method to delete an object
   * @param object the object to remove
   */
  void remove(T object);

  /**
   * Generic method to delete an object
   * @param id the identifier (primary key) of the object to remove
   */
  void remove(PK id);

  /**
   * Find a list of records by using a named query
   * @param queryName query name of the named query
   * @param queryParams a map of the query names and the values
   * @return a list of the records found
   */
  List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

  /**
   * Generic method to regenerate full text index of the persistent class T
   */
  void reindex();

  /**
   * Generic method to regenerate full text index of all indexed classes
   * @param async true to perform the reindexing asynchronously
   */
  void reindexAll(boolean async);

}
