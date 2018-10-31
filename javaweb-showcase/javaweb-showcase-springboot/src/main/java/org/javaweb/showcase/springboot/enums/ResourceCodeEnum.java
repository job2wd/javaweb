/*
 * $Id: ResourceCodeEnum.java 5340 2018-08-22 06:40:49Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/enums/ResourceCodeEnum.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.enums;


/**
 * 事件发生资源（模块）编码枚举类（存储在表 resource_info 中），如：MALWARE_LIST_FILE/MALWARE_LIST_URL/MALWARE_LIST_IP
 * 
 * @author wangwd
 * @version $Revision: 5340 $, $Date: 2018年5月25日 上午10:47:40$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-22 14:40:49#$
 */
public enum ResourceCodeEnum {
  
  EMPTY(""),
  
  Home("Home"),
  
  Basic_Info_Medc("Basic_Info_Medc"),
  Basic_Info_MedcHouse("Basic_Info_MedcHouse"),
  Basic_Info_SympDict("Basic_Info_SympDict"),
  Basic_Info_Symp("Basic_Info_Symp"),
  
  Basic_Info_BasicCMC("CMC"),
  Basic_Info_WorkCMC("Basic_Info_WorkCMC"),
  
  System("System"),
  System_User("System_User"),
  System_Role("System_Role"),
  System_Resource("System_Resource"),
  System_WorkOrg("System_WorkOrg"),
  System_Log("System_Log"),
  System_Restore("System_Restore"),
  
  Medicalcase_work("Medicalcase_work");
  
  private String code;
  
  ResourceCodeEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
