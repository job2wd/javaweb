/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月5日 下午7:10:22$
 * @LastChanged $Author:$, $Date::                    #$
 */
@Entity(name = "user_info")
public class UserInfo implements Serializable {
  
  private static final long serialVersionUID = -9013134527535405965L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  @Column(name = "login_name", nullable = false)
  private String loginName;
  
  @Column(name = "login_password", nullable = false)
  private String loginPassword;
  
  @Column(name = "full_name", nullable = true)
  private String fullName;
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Long getId() {
    return id;
  }
  
  public String getLoginName() {
    return loginName;
  }
  
  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }
  
  public String getLoginPassword() {
    return loginPassword;
  }
  
  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
}
