/*
 * $Id: HomeController.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/controller/HomeController.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.javaweb.showcase.model.User;
import org.javaweb.showcase.service.UserService;
import org.javaweb.showcase.util.FbaDateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * 注意：home、 home/、 home/* 及  /home、 /home/、 /home/* 的区别<br>
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-1-28 下午5:12:20$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

  private static final Logger log = LoggerFactory.getLogger(HomeController.class);
  //对于 abstract 类，可使用如下方式创建日志对象：
  //protected final Logger log = LoggerFactory.getLogger(getClass());
  
  @org.springframework.beans.factory.annotation.Autowired
  //@org.springframework.beans.factory.annotation.Qualifier("homeServiceImpl")
  //@javax.annotation.Resource("homeServiceImpl")
  private UserService userService;
  
  @RequestMapping(value="/home", method=RequestMethod.GET)
  public String home(Model model) {
    log.debug("Welcome to home! [{}]", FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(new Date()));
    
    log.debug("webAppRootKey: {}", System.getProperty("webAppRootKey"));
    log.debug("javaweb-showcase-root: {}", System.getProperty("javaweb-showcase-root"));
    log.debug("default time zone: {}", TimeZone.getDefault().getID());
        
    String[] aids = TimeZone.getAvailableIDs();
    for(String id : aids) {
      //log.debug("Tome Zone:{}", id);
    }

    model.addAttribute("msg", "Welcome to home! [" + FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(new Date())  + "]");
    
    return "home";
  }
  
  @RequestMapping(value="/user/list", method=RequestMethod.GET)
  public @ResponseBody List<User> queryUserList(
      @RequestParam(value="page",defaultValue="0", required=false) int pageNum,
      Model model, RedirectAttributes redirectAttributes) {
    try {
    List<User> users = userService.findAllUsers();
    
    log.debug("users:{}", users);
    
    model.addAttribute("users", users);
    
    return users;
    }catch(Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  
  @RequestMapping(value="/user", method=RequestMethod.GET)
  public @ResponseBody String queryUser(
      //@PathVariable("userName") String userName,
      @RequestParam("name") String userName,
      String searchVal, Model model, RedirectAttributes redirectAttributes) {
    
    log.debug("@PathVariable userName:{}", userName);
    
    User user = userService.findByUserName(userName);
    
    log.debug("user:{}" , user);
    
    model.addAttribute("user", user);
        
    return user == null ? "user " + userName + " not found." : user.toString();
  }
  
  @RequestMapping(value="/user/save", method=RequestMethod.GET)
  //在 spring-mvc.xml 中已经做了字符编码（UTF-8）过滤配置
  //, produces = {"text/plain;charset=UTF-8", "text/html;charset=UTF-8", "application/*;charset=UTF-8"})
  public @ResponseBody String saveUser(
      @Valid User user, BindingResult result,
      Model model, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return result.getFieldError().toString();
    }
    
    if(userService.exists(user.getName())) {
      return "user [" + user.getName() + "] exists. 已存在！";
    }
    
    userService.insertUser(user);
    
    log.debug("user:{}", user);
    
    model.addAttribute("user", user);
    redirectAttributes.addFlashAttribute("msg", "save user success");
    
    return user.toString();
  }
  
}
