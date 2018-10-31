/*
 * $Id: ClientConfiguration.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/thirdparty/socket/comm/ClientConfiguration.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test.thirdparty.socket.comm;


/**
 * 
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2015-9-14 下午4:50:47$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$
 */
public class ClientConfiguration {

  private SSLConfiguration sslConfiguration;

  public SSLConfiguration getSslConfiguration() {
    return sslConfiguration;
  }

  public void setSslConfiguration(SSLConfiguration sslConfiguration) {
    this.sslConfiguration = sslConfiguration;
  }

}
