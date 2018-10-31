package org.javaweb.showcase.socket.ssl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

public class SocketTCPServerJob implements Runnable {

  private Socket socket;

  public SocketTCPServerJob(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    InputStream input = null;
    OutputStream output = null;
    
    try {
      Properties p = Configuration.getConfig();
      
      String encoding = p.getProperty("socketStreamEncoding");

      input = socket.getInputStream();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      
      int iR = input.read();
      while(iR != -1){
          baos.write(iR);
          iR = input.read();
      }
            
      //此处对接收到客户端发送的数据进行处理
      String receivedData = new String(baos.toByteArray(), encoding).trim();
      
      Logger.debug("receive client request data:" + receivedData);
      
      
      
      
      
      //回应客户端
      output = socket.getOutputStream();
      
      output.write("SUCCESS".getBytes(encoding));
      
      output.flush();
    } catch (Exception e) {
      Logger.error("receive client requst error!", e);
      SocketIOUtils.close(input, output);
      SocketIOUtils.close(socket);
    }/*
    finally {
      try {
        socket.close();
      } catch (IOException e) {
        Logger.error("server socket close error", e);
      }
    }*/
  }
}
