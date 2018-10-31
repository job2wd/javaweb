/*
 * $Id: IpRange.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/IpRange.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.util;


/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-5-20 上午11:47:38$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class IpRange {

  private Long startIp;
  private Long endIp;
  
  public IpRange(Long startIp, Long endIp) {
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
