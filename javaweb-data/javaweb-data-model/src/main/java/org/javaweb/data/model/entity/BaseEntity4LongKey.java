/*
 * $Id: BaseEntity4LongKey.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/model/BaseEntity4LongKey.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.entity;

import org.javaweb.common.util.JavaObjectToStringUtils;


/**
 *
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2018年4月27日 上午8:32:42$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public abstract class BaseEntity4LongKey extends BaseEntity4Key<Long> {

  private static final long serialVersionUID = 1483181921105903953L;

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BaseEntity4LongKey) {
      if(obj != null) {
        BaseEntity4LongKey o = (BaseEntity4LongKey)obj;

        Long id2 = o.getId();

        if(id2 == null && this.getId() == null) {
          return true;
        } else if(id2 == null || this.getId() == null) {
          return false;
        }

        return id2.longValue() == this.getId().longValue();
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
