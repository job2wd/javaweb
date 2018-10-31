/*
 * $Id: ResponseBean4Validation.java 5565 2018-08-30 05:06:15Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/domain/ResponseBean4Validation.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.domain;


/**
 * 
 * @author wangwd
 * @version $Revision: 5565 $, $Date: 2018年6月8日 上午11:52:35$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-30 13:06:15#$
 */
public class ResponseBean4Validation extends ResponseBean {

  private static final long serialVersionUID = -2770331719070903970L;

  private String targetName;
  private Object targetValue;

  public String getTargetName() {
    return targetName;
  }

  public void setTargetName(String targetName) {
    this.targetName = targetName;
  }

  public Object getTargetValue() {
    return targetValue;
  }

  public void setTargetValue(Object targetValue) {
    this.targetValue = targetValue;
  }

}
