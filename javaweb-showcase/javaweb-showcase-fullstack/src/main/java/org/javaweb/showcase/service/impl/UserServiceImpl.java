package org.javaweb.showcase.service.impl;

import java.util.Date;
import java.util.List;

import org.javaweb.showcase.dao.conf.UserDao;
import org.javaweb.showcase.dao.data.SysLogDao;
import org.javaweb.showcase.model.SysLog;
import org.javaweb.showcase.model.User;
import org.javaweb.showcase.service.UserService;
import org.javaweb.showcase.util.IdFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;


@Service
//@Transactional
public class UserServiceImpl implements UserService {

  private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  //@Inject
  private UserDao userDao;
  
  @Autowired
  //@Inject
  private SysLogDao sysLogDao;
  
  @Override
  @Transactional(readOnly = true)
  public List<User> findAllUsers() {
    return userDao.findAllUsers();
  }

  @Override
  @Transactional(readOnly=true)
  //@Cacheable(cacheName = "USER_CACHE_SPACE")//该注解可放到数据库 service 级别
  //@ReadThroughSingleCache(namespace = "USER_CACHE_SPACE", expiration = 3600)//该注解可放到接口 service 级别
  public User findByUserName(@ParameterValueKeyProvider String userName) {
    //log.debug("return user info from Memcached [USER_CACHE_SPACE] by user name:{}", userName);
        
    return userDao.findUserByUserName(userName);
  }

  @Override
  //@Transactional
  //@TriggersRemove(cacheName = "USER_CACHE_SPACE", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
  //@UpdateSingleCache(namespace = NAMESPACE, expiration = 3600)//expiration = 3600: 过期时间 3600秒（60分钟），设置为0意味着永不过期。
  @InvalidateSingleCache(namespace = "USER_CACHE_SPACE")
  public void deleteUser(@ParameterValueKeyProvider Integer userId) {
    Assert.notNull(userId, "user id must be not null!");
    // delete user by DAO
    log.debug("remove user info from Memcached [USER_CACHE_SPACE] by user id:{}", userId);
    
    userDao.deleteUserById(userId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void insertUser(User user) {
    Date now = new Date();
    
    user.setId(IdFactory.getIntId());
    user.setCreateDate(now);
    
    userDao.insertUser(user);
    
    if(true) {
      //throw new RuntimeException("test transaction exception");
    }
    
    SysLog sysLog = new SysLog();
    
    sysLog.setId(IdFactory.getIntId());
    sysLog.setUserName(user.getName());
    sysLog.setModule("user management");
    sysLog.setAction("add user");
    sysLog.setCreateDate(now);
    
    sysLogDao.insertSysLog(sysLog);
  }

  @Override
  public void updateUser(User user) {
    userDao.updateUser(user);
  }

  @Override
  @Transactional(readOnly=true)
  public boolean exists(String userName) {
    User user = userDao.findUserByUserName(userName);
    return user != null;// userDao.exists(user.getId(), User.class);
  }

}
