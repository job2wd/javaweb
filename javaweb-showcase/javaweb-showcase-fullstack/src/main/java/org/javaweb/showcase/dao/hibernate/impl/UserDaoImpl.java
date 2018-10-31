/*
 * $Id: UserDaoImpl.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/hibernate/impl/UserDaoImpl.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.dao.hibernate.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.metadata.ClassMetadata;
import org.javaweb.showcase.dao.conf.UserDao;
import org.javaweb.showcase.dao.hibernate.ConfDaoHibernate;
import org.javaweb.showcase.model.User;
import org.springframework.stereotype.Repository;



/**
 * User info DAO.
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午10:02:09$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
@Repository
public class UserDaoImpl extends ConfDaoHibernate<User, Integer> implements UserDao {

  @Override
  public List<User> findAllUsers() {
    Map<String, ClassMetadata> cm = getSessionFactory().getAllClassMetadata();
    
    Iterator<String> keys = cm.keySet().iterator();
    while(keys.hasNext()) {
      String key = keys.next();
      log.debug("{}={}", key, cm.get(key));
    }
    
    return getAll();
  }
  
  @Override
  public User findUserByUserName(String userName) {
    return (User)getSession().createQuery("FROM User WHERE name = :userName").setString("userName", userName).uniqueResult();
  }
  
  @Override
  public void insertUser(User user) {
    save(user);
  }
  
  @Override
  public void updateUser(User user) {
    save(user);
  }
  
  @Override
  public void deleteUserById(Integer id) {
    remove(id);
  }
  
}
