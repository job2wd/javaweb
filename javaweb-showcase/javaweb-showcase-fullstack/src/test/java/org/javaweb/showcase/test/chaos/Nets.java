/*
 * $Id: Nets.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/Nets.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;


/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-10-15 下午4:52:12$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class Nets {

  private String StartIP;
  private String EndIP;
  private String NetMask;
  public String getStartIP() {
      return StartIP;
  }
  public void setStartIP(String startIP) {
      StartIP = startIP;
  }
  public String getEndIP() {
      return EndIP;
  }
  public void setEndIP(String endIP) {
      EndIP = endIP;
  }
  public String getNetMask() {
      return NetMask;
  }
  public void setNetMask(String netMask) {
      NetMask = netMask;
  }
  
}
