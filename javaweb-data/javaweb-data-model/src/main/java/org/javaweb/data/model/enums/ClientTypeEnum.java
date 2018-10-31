/*
 * $Id: ClientTypeEnum.java 6082 2018-09-29 09:58:16Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/enums/ClientTypeEnum.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.enums;


/**
 * 
 * @author wangwd
 * @version $Revision: 6082 $, $Date: 2018年8月7日 上午9:56:17$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-29 17:58:16#$
 */
public enum ClientTypeEnum {
  
  PC_BROWER("PC-BROWER"),
  
  PC_APP("PC-APP"),
  
  MOBILE_BROWER("MOBILE-BROWER"),
  
  MOBILE_APP("MOBILE-APP");
  
  private String code;
  
  ClientTypeEnum(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return code;
  }
  
}
