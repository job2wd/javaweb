/*
 * $Id: RoleEnum.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/enums/RoleEnum.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.enums;


/**
 * 角色编码枚举类型
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2018年5月22日 下午7:25:14$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public enum RoleEnum {
  
  /**
   * 所有角色
   */
  ROLE_ALL,

  ROLE_SUPER_ADMIN,
  ROLE_ADMIN,
  ROLE_AUDIT_ADMIN,
  ROLE_OPER_ADMIN,
  
  ROLE_NORMAL_USER,
  
  /**
   * 医生
   */
  ROLE_DOCTOR,
  /**
   * 护士
   */
  ROLE_NURSE;
  
}
