/*
 * $Id: BaseEntity4BigIntegerKey.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/model/BaseEntity4BigIntegerKey.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.entity;

import java.math.BigInteger;

import org.javaweb.common.util.JavaObjectToStringUtils;

/**
 * 
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2018年8月23日 下午5:48:38$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public abstract class BaseEntity4BigIntegerKey extends BaseEntity4Key<BigInteger> {
  
  private static final long serialVersionUID = -467625367139883474L;
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BaseEntity4BigIntegerKey) {
      if(obj != null) {
        BaseEntity4BigIntegerKey o = (BaseEntity4BigIntegerKey) obj;

        BigInteger id2 = o.getId();

        if(id2 == null && this.getId() == null) {
          return true;
        } else if(id2 == null || this.getId() == null) {
          return false;
        }
        
        if (id2.equals(this.getId())) {
          return true;
        }
        
        return id2.compareTo(this.getId()) == 0;
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return JavaObjectToStringUtils.toString(this);
  }
  
}
