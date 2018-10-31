/*
 * $Id: JavaIpRange.java 4405 2018-06-26 07:13:59Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/util/JavaIpRange.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.common.util;


/**
 * 
 * @author wangwd
 * @version $Revision: 4405 $, $Date: 2015-5-20 上午11:47:38$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-26 15:13:59#$
 */
public class JavaIpRange {

  private Long startIp;
  private Long endIp;
  
  public JavaIpRange(Long startIp, Long endIp) {
    this.startIp = startIp;
    this.endIp = endIp;
  }
  public Long getStartIp() {
    return startIp;
  }
  public void setStartIp(Long startIp) {
    this.startIp = startIp;
  }
  public Long getEndIp() {
    return endIp;
  }
  public void setEndIp(Long endIp) {
    this.endIp = endIp;
  }
  
  public Long getRangeSize(){
    return (endIp - startIp + 1);
  }
  @Override
  public String toString() {
    return "IpRange [startIp=" + startIp + ", endIp=" + endIp + "]";
  }
  
}
