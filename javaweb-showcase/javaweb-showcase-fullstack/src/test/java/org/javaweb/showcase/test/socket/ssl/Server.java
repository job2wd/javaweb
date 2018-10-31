package org.javaweb.showcase.test.socket.ssl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.javaweb.showcase.socket.ssl.Logger;

public class Server {

  private SSLContext             sslContext;
  private SSLServerSocketFactory sslServerSocketFactory;
  private SSLServerSocket        sslServerSocket;
  private final Executor         executor;

  public Server() throws Exception {
    Properties p = Configuration.getConfig();
    Integer serverListenPort = Integer.valueOf(p.getProperty("serverListenPort"));
    Integer serverThreadPoolSize = Integer.valueOf(p.getProperty("serverThreadPoolSize"));
    Integer serverRequestQueueSize = Integer.valueOf(p.getProperty("serverRequestQueueSize"));
    Integer authority = Integer.valueOf(p.getProperty("authority"));

    executor = Executors.newFixedThreadPool(serverThreadPoolSize);

    sslContext = ServerAuth.getSSLContext();
    
    sslServerSocketFactory = sslContext.getServerSocketFactory();
    //只是创建一个TCP连接，SSL handshake还没开始
    //客户端或服务端第一次试图获取socket输入流或输出流时，
    //SSL handshake才会开始
    sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket();
    String[] pwdsuits = sslServerSocket.getSupportedCipherSuites();
    sslServerSocket.setEnabledCipherSuites(pwdsuits);
    //默认是client mode，必须在握手开始之前调用
    sslServerSocket.setUseClientMode(false);
    if (authority.intValue() == 2) {
      //只有设置为server mode，该配置才生效
      //如果客户端不提供其证书，通信将会结束
      sslServerSocket.setNeedClientAuth(true);
    } else {
      //只有设置为server mode，该配置才生效
      //即使客户端不提供其证书，通信也将继续
      sslServerSocket.setWantClientAuth(true);
    }

    sslServerSocket.setReuseAddress(true);
    sslServerSocket.setReceiveBufferSize(128 * 1024);
    sslServerSocket.setPerformancePreferences(3, 2, 1);
    sslServerSocket.bind(new InetSocketAddress(serverListenPort), serverRequestQueueSize);

    Logger.info("Server start up!");
    Logger.info("server port is:" + serverListenPort);
  }

  private void service() {
    while (true) {
      SSLSocket socket = null;
      try {
        Logger.debug("Wait for client request!");
        socket = (SSLSocket) sslServerSocket.accept();
        Logger.debug("Get client request!");

        Runnable job = new ServerJob(socket);
        executor.execute(job);
      } catch (Exception e) {
        Logger.error("server run exception");
        try {
          socket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    Server server;
    try {
      server = new Server();
      server.service();
    } catch (Exception e) {
      e.printStackTrace();
      Logger.error("server socket establish error!");
    }
  }
}
