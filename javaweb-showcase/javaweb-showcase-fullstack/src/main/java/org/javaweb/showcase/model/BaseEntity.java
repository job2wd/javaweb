/*
 * $Id: BaseEntity.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/model/BaseEntity.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.model;

import java.io.Serializable;

import org.javaweb.showcase.util.ObjectToStringUtils;

import com.google.code.ssm.api.CacheKeyMethod;


/**
 * POJO base class.
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2015-9-9 下午8:01:36$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = -7859018446837529523L;

  @CacheKeyMethod
  public abstract Integer getId();

  public abstract void setId(Integer id);
  
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof BaseEntity) {
      if(obj != null) {
        BaseEntity o = (BaseEntity)obj;
        
        Integer id2 = o.getId();
        
        if(id2 == null && this.getId() == null) {
          return true;
        } else if(id2 == null || this.getId() == null) {
          return false;
        }
        
        return id2.intValue() == this.getId().intValue();
      }
    }
    
    return false;
  }

  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }
  
}
