/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.showcase.socket.ssl;


/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年5月11日 上午11:41:44$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class TestServer {

  public static void main(String[] args) {
    SocketTCPServer server;
    try {
      Configuration.configLocation = "D:/conf.properties";
      
      server = new SocketTCPServer();
      server.startup();
    } catch (Exception e) {
      Logger.error("server socket establish error!", e);
    }
  }

}
