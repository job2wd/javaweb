/*
 * $Id: UserDao.java 136 2017-02-06 06:16:43Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/conf/UserDao.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.conf;

import java.util.List;

import org.javaweb.showcase.dao.base.BaseConfDao;
import org.javaweb.showcase.model.User;



/**
 * 
 * @author wangwd
 * @version $Revision: 136 $, $Date: 2016-5-25 下午4:44:11$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:16:43#$
 */
public interface UserDao extends BaseConfDao {

  List<User> findAllUsers();
  
  User findUserByUserName(String userName);
  
  void insertUser(User user);
  
  void updateUser(User user);
  
  void deleteUserById(Integer id);
  
}
