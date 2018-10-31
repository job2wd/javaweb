/*
 * $Id: BaseEntity4StringKey.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/model/BaseEntity4StringKey.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.entity;

import org.javaweb.common.util.JavaObjectToStringUtils;

/**
 * 
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2018年9月4日 下午5:41:45$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public abstract class BaseEntity4StringKey extends BaseEntity4Key<String> {
  
  private static final long serialVersionUID = 9121249516618871017L;
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BaseEntity4StringKey) {
      if (obj != null) {
        BaseEntity4StringKey o = (BaseEntity4StringKey) obj;
        
        String id2 = o.getId();
        
        if (id2 == null && this.getId() == null) {
          return true;
        } else if (id2 == null || this.getId() == null) {
          return false;
        }
        
        return id2.equals(this.getId());
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
