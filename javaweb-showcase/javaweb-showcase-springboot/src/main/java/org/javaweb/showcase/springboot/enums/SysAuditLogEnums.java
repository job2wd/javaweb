/*
 * $Id: SysAuditLogEnums.java 3735 2018-05-25 04:22:01Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/enums/SysAuditLogEnums.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.enums;


/**
 * 
 * @author wangwd
 * @version $Revision: 3735 $, $Date: 2018年5月25日 上午9:13:08$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-25 12:22:01#$
 */
public final class SysAuditLogEnums {

  public enum LevelEnum {
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    
    private int level;
    
    LevelEnum(int level) {
      this.level = level;
    }

    public int getLevel() {
      return level;
    }

  }
  
  public enum TypeEnum {
    SYS_LOG(0),
    USER_OP_LOG(1);
    
    private int type;
    
    TypeEnum(int type) {
      this.type = type;
    }

    public int getType() {
      return type;
    }

  }
  
  public enum RiskEnum {
    NO(0),
    YES(1);
    
    private int risk;
    
    RiskEnum(int risk) {
      this.risk = risk;
    }

    public int getRisk() {
      return risk;
    }

  }
  
}
