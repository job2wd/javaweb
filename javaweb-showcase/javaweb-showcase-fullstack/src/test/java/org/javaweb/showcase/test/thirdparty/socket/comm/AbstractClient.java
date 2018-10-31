/*
 * $Id: AbstractClient.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/thirdparty/socket/comm/AbstractClient.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test.thirdparty.socket.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;


/**
 * Abstract Socket client implements.
 * 
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2015-9-14 下午4:23:32$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$
 */
public abstract class AbstractClient implements Client {

  private String host;
  private int port;
  
  private SocketFactory socketFactory;
  protected Socket socket;
  
  private ClientConfiguration clientConfiguration;
  
  public AbstractClient(String host, int port) {
    this.host = host;
    this.port = port;
  }
  
  @Override
  public void connect() {
    try {
      if(socketFactory == null) {
        socketFactory = SocketFactory.getDefault();
      }
      
      this.socket = this.socketFactory.createSocket();
      
      this.config();
      
      this.socket.connect(this.getSocketAddress());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void send(byte[] data) {
    OutputStream os = null;
    try {
      os = socket.getOutputStream();
      os.write(data);
      os.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      this.close(os);
    }
  }

  @Override
  public void send(InputStream is) {
    OutputStream os = null;
    try {
      os = socket.getOutputStream();
      
      byte[] b = new byte[512];
      int len = -1;
      
      while((len = is.read(b, 0, b.length)) != -1) {
        os.write(b, 0, len);
      }
      
      os.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      this.close(os);
    }
  }
  
  public void setClientConfiguration(ClientConfiguration clientConfiguration) {
    this.clientConfiguration = clientConfiguration;
  }

  protected void config() {
    if(clientConfiguration != null) {
      // TODO configuration for connection....
      
      //this.socket.setSoTimeout(timeout);
    }
    if(clientConfiguration.getSslConfiguration() != null) {
      if(this.socket instanceof SSLSocket) {
        // TODO configuration for TSL/SSL....
        
        SSLSocket s = (SSLSocket)this.socket;
        s.setEnabledProtocols(this.clientConfiguration.getSslConfiguration().getProtocols());
        s.setNeedClientAuth(this.clientConfiguration.getSslConfiguration().isNeedClientAuth());
        s.setWantClientAuth(this.clientConfiguration.getSslConfiguration().isWantClientAuth());
      }
    }
  }
  
  private SocketAddress getSocketAddress() {
    return new InetSocketAddress(this.host, this.port);
  }
  
  private void close(InputStream is) {
    if(is != null) {
      try {
        is.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  
  private void close(OutputStream os) {
    if(os != null) {
      try {
        os.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  
}
