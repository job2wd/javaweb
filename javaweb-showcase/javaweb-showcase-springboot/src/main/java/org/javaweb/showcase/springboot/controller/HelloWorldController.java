/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年10月8日 下午5:31:35$
 * @LastChanged $Author:$, $Date::                    #$
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
  
  @GetMapping("/world")
  @ResponseBody
  public String helloWorld() {
    return "Hello World!";
  }
  
}
