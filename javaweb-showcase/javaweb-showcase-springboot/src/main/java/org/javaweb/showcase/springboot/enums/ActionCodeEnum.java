/*
 * $Id: ActionCodeEnum.java 4062 2018-06-12 03:20:19Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/enums/ActionCodeEnum.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.enums;


/**
 * 执行操作的事件编码枚举（存储在表 action_info 中），如：IMPORT/DELETE/CREATE/UPDATE/ENABLED/DISABLED
 * 
 * @author wangwd
 * @version $Revision: 4062 $, $Date: 2018年5月25日 上午10:44:34$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-12 11:20:19#$
 */
public enum ActionCodeEnum {
  
  EMPTY(""),
  
  CREATE("CREATE"),
  UPDATE("UPDATE"),
  DELETE("DELETE"),
  VIEW("VIEW"),
  SAVE("SAVE"),
  
  ENABLED("ENABLED"),
  DISABLED("DISABLED"),
  
  IMPORT("IMPORT"),
  EXPORT("EXPORT"),
  
  SIGNUP("SIGNUP"),
  LOGIN("LOGIN"),
  LOGOUT("LOGOUT");
  
  private String code;
  
  ActionCodeEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
