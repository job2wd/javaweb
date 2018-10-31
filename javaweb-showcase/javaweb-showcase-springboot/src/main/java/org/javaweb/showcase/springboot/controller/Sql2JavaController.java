/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 下午2:50:17$
 * @LastChanged $Author:$, $Date::                    #$
 */
@Controller
@RequestMapping("/sql2java")
public class Sql2JavaController {
  
  @GetMapping
  public String index() {
    return "sql2java";
  }
  
  @PostMapping("/go")
  @ResponseBody
  public String go(MultipartFile file) {
    if (file.isEmpty()) {
      return "文件内容为空";
    }
    
    return "Successfully!";
  }
  
  @PostMapping("/uploads")
  @ResponseBody
  public String uploads(HttpServletRequest request) {
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
    
    return "Successfully!";
  }
  
  @GetMapping("/download")
  @ResponseBody
  public String download(HttpServletRequest request, HttpServletResponse response) {
    
    return "Successfully!";
  }
  
}
