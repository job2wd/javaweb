package org.javaweb.showcase.socket.ssl;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SocketTCPClient {

  private SSLContext sslContext;
  private SSLSocket  socket;
  private Properties p;

  public SocketTCPClient() throws Exception {
    p = Configuration.getConfig();

    sslContext = SocketTCPClientAuthenticator.getSSLContext();
    SSLSocketFactory factory = sslContext.getSocketFactory();
    socket = (SSLSocket) factory.createSocket();
    String[] pwdsuits = socket.getSupportedCipherSuites();
    //socket可以使用所有支持的加密套件
    socket.setEnabledCipherSuites(pwdsuits);
    //默认就是true
    socket.setUseClientMode(true);

    SocketAddress address = new InetSocketAddress(p.getProperty("serverHostname"), Integer.valueOf(p.getProperty("serverListenPort")));
    socket.connect(address, 0);

    SocketHandshakeListener listener = new SocketHandshakeListener();
    socket.addHandshakeCompletedListener(listener);
  }

  public byte[] request(InputStream requestData) throws Exception {
    OutputStream output = null;
    InputStream input = null;
    byte[] response = null;

    try {
      output = socket.getOutputStream();

      byte[] buffer = new byte[512];
      int len = -1;

      while ((len = requestData.read(buffer, 0, buffer.length)) != -1) {
        //output.write(length);
        output.write(buffer, 0, len);
      }
      output.flush();
      output.close();

      input = socket.getInputStream();

      response = new byte[input.available()];
      int serverResponseLength = input.read(response);

      Logger.debug("server response data lenght:" + serverResponseLength);
    } catch (Exception e) {
      //SocketIOUtils.close(input, output);
      //SocketIOUtils.close(socket);
      throw e;
    } finally {
      //建立长连接，不关闭输入输出流和 socket 连接，若需要每次请求都重新连接请在请求发送完成后关闭  socket
      //SocketIOUtils.close(input, output);
      //SocketIOUtils.close(socket);
    }

    return response;
  }

}
