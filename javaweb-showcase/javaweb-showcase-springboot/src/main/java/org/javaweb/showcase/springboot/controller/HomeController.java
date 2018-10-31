/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.controller;

import java.util.Date;
import java.util.List;

import org.javaweb.showcase.springboot.dao.UserInfoRepository;
import org.javaweb.showcase.springboot.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://zhuanlan.zhihu.com/p/30159585
 * http://www.cnblogs.com/ityouknow/p/5833560.html
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月5日 上午9:38:25$
 * @LastChanged $Author:$, $Date::                    #$
 */
//@RestController
@Controller
@RequestMapping
public class HomeController {
  
  @Autowired
  private UserInfoRepository userInfoRepository;
  
  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("name", "首页");
    return "index";
  }
  
  @GetMapping("/dispatch/{path}")
  public String welcome(Model model, @PathVariable(name = "path", required = true) String path) {
    String[] paths = path.split("[!]");
    
    String url = "";
    
    if (paths.length == 1) {
      url = path;
    } else {
      for (String p : paths) {
        url += p + "/";
      }
      
      url = url.substring(0, url.length() - 1);
    }
    
    System.out.println("url: " + url);
    
    return url;
  }
  
  @GetMapping("/crud")
  //@ResponseBody
  public String testCRUD(Model model) {
    UserInfo user = new UserInfo();
    //user.setId(4l);
    user.setLoginName("wangwd");
    user.setLoginPassword(null);
    user.setFullName(null);
    
    user = userInfoRepository.save(user);
    
    user.setLoginPassword(null);
    user.setFullName("Victor Wong_" + new Date());
    user.setLoginPassword("123");
    user = userInfoRepository.saveAndFlush(user);
    
    Sort sort = Sort.by(new Sort.Order(Direction.DESC, "fullName"));
    
    List<UserInfo> users = userInfoRepository.findAll(sort);
    
    model.addAttribute("users", users);
    
    return "crud";
  }
  
}
