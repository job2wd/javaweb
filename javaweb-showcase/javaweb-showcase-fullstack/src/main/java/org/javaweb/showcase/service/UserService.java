package org.javaweb.showcase.service;

import java.util.List;

import org.javaweb.showcase.model.User;



/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-3-5 上午9:51:06$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public interface UserService {

  List<User> findAllUsers();
  User findByUserName(String userName);
  void insertUser(User user);
  void updateUser(User user);
  void deleteUser(Integer userId);
  boolean exists(String userName);
  
}
