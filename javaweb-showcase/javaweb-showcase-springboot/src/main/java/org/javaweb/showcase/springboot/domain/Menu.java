/*
 * $Id: Menu.java 3841 2018-05-30 04:03:43Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/domain/Menu.java $
 * Copyright (c) 2014 Company, vito. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.domain;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author wangwd
 * @version $Revision: 3841 $, $Date: 2018年5月17日 下午7:31:04$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-30 12:03:43#$
 */
public class Menu implements Serializable {

  private static final long serialVersionUID = 8322083214811591225L;

  public static final Integer TOP_MENU_ID = 0;

  private Integer id;
  private String code;
  private Integer parentId;
  private String name;
  private String className;
  private String url;
  
  /**
   * 角色对该资源的权限：0 - 只读权限；1 - 写权限；2 - 读写权限；默认为 0。
   */
  private Short permission;

  private List<Menu> subMenus;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Short getPermission() {
    return permission;
  }

  public void setPermission(Short permission) {
    this.permission = permission;
  }

  public List<Menu> getSubMenus() {
    return subMenus;
  }

  public void setSubMenus(List<Menu> subMenus) {
    this.subMenus = subMenus;
  }

}
