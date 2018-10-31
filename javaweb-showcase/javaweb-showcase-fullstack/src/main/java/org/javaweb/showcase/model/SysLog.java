/*
 * $Id: SysLog.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/model/SysLog.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.javaweb.showcase.util.ObjectToStringUtils;



/**
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2015-8-14 下午1:10:34$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
@Entity
@Table(name="sys_log")
@org.hibernate.annotations.Cache(usage =org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)// 使用 hibernate 二级缓存
public class SysLog extends BaseEntity {

  private static final long serialVersionUID = -5774606619130311758L;

  @Id
  //@GeneratedValue(strategy=GenerationType.AUTO)
  //@SequenceGenerator(name="EMP_SEQ", allocationSize=25)
  @Column(name = "id")
  private Integer id;
  
  @NotBlank(message = "模块名称不能为空")
  @Length(min = 3, max = 64, message = "操作名称必须介于 3 个字符到 64 个字符之间")
  private String module;
  
  @NotBlank(message = "操作名称不能为空")
  @Length(min = 3, max = 64, message = "操作名称必须介于 3 个字符到 64 个字符之间")
  private String action;
  
  @NotBlank(message = "用户名称不能为空")
  @Length(min = 3, max = 30, message = "用户名称必须介于 3 个字符到 30 个字符之间")
  private String userName;
  
  //@javax.validation.constraints.Past()
  private Date createDate;
  
  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }
  
}
