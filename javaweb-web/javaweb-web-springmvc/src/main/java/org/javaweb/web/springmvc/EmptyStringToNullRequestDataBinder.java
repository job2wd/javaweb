/*
 * $Id: EmptyStringToNullRequestDataBinder.java 4156 2018-06-15 01:22:11Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/EmptyStringToNullRequestDataBinder.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.web.springmvc;

import javax.servlet.ServletRequest;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

/**
 * 
 * @author wangwd
 * @version $Revision: 4156 $, $Date: 2018年6月15日 上午8:52:04$
 * @LastChanged $Author: wangweidong $, $Date:: #$
 */
public class EmptyStringToNullRequestDataBinder extends ExtendedServletRequestDataBinder {

  public EmptyStringToNullRequestDataBinder(Object target) {
    super(target);
  }

  public EmptyStringToNullRequestDataBinder(Object target, String objectName) {
    super(target, objectName);
  }

  @Override
  protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
    super.addBindValues(mpvs, request);
    for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
      if ("".equals(propertyValue.getValue())) {
        propertyValue.setConvertedValue(null);
      }
    }
  }

}
