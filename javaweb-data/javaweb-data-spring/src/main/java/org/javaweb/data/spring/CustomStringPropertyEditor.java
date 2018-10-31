/*
 * $Id: CustomStringPropertyEditor.java 5411 2018-08-24 10:05:57Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/CustomStringPropertyEditor.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.spring;

import java.beans.PropertyEditorSupport;


/**
 * 
 * @author wangwd
 * @version $Revision: 5411 $, $Date: 2018年8月24日 下午12:55:34$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-24 18:05:57#$
 */
public class CustomStringPropertyEditor extends PropertyEditorSupport {
  
  @Override
  public void setValue(Object value) {
    if (value != null && value instanceof String) {
      if ("".equals(value) || "null".equals(value)) {
        super.setValue(null);
        return;
      }
    }
    
    super.setValue(value);
  }
  
  @Override
  public Object getValue() {
    Object value = super.getValue();
    
    if (value != null && value instanceof String) {
      if ("".equals(value) || "null".equals(value)) {
        return null;
      }
    }
    
    return value;
  }
  
  @Override
  public String getAsText() {
    String text = super.getAsText();
    
    if ("".equals(text) || "null".equals(text)) {
      return null;
    }
    return text;
  }
  
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if ("".equals(text) || "null".equals(text)) {
      super.setAsText(null);
    } else {
      super.setAsText(text);
    }
  }
  
}
