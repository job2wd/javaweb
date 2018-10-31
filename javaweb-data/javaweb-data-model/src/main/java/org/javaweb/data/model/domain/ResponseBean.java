/*
 * $Id: ResponseBean.java 5411 2018-08-24 10:05:57Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/domain/ResponseBean.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * 请求响应结果封装类，可以继承该类并实现自己的响应封装类，以期添加更多参数到响应结果。
 * 
 * @author wangwd
 * @version $Revision: 5411 $, $Date: 2018年5月4日 上午11:18:57$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-24 18:05:57#$
 */
public class ResponseBean implements Serializable {

  @JsonIgnore
  private static final long serialVersionUID = 6376057578634387421L;
  
  /**
   * 响应状态码，目前为：0 - 失败；1 - 成功；
   */
  private Integer status;
  
  /**
   * 响应结果编码：如成功时的特定业务编码，失败时的特定异常编码等。
   */
  private String code;
  
  /**
   * 成功时的提示信息 或 错误时的错误信息
   */
  private String message;
  
  private String detailMessage;
  
  private Object data;
    
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getDetailMessage() {
    return detailMessage;
  }

  public void setDetailMessage(String detailMessage) {
    this.detailMessage = detailMessage;
  }

}
