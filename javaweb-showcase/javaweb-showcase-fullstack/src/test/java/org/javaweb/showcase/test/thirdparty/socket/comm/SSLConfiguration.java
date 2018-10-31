/*
 * $Id: SSLConfiguration.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/thirdparty/socket/comm/SSLConfiguration.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test.thirdparty.socket.comm;


/**
 * Socket TSL/SSL configuration.
 * 
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2015-9-14 下午4:54:30$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$
 */
public class SSLConfiguration {

  private String[] protocols = null;
  
  private boolean needClientAuth = false;
  
  private boolean wantClientAuth = false;

  public String[] getProtocols() {
    return protocols;
  }

  public void setProtocols(String[] protocols) {
    this.protocols = protocols;
  }

  public boolean isNeedClientAuth() {
    return needClientAuth;
  }

  public void setNeedClientAuth(boolean needClientAuth) {
    this.needClientAuth = needClientAuth;
  }

  public boolean isWantClientAuth() {
    return wantClientAuth;
  }

  public void setWantClientAuth(boolean wantClientAuth) {
    this.wantClientAuth = wantClientAuth;
  }
}
