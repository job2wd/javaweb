package org.javaweb.showcase.test.socket.ssl;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import org.javaweb.showcase.socket.ssl.Logger;

public class ServerJob implements Runnable {

  private Socket socket;

  public ServerJob(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    Properties p = Configuration.getConfig();
    String encoding = p.getProperty("socketStreamEncoding");

    DataInputStream input = null;
    DataOutputStream output = null;
    try {
      input = SocketIO.getDataInput(socket);

      //int length = input.read();
      
      //byte[] bytes = new byte[length];
      //input.read(bytes);
      
      //byte[] bytes = new byte[input.available()];
      //input.read(bytes);
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      int iR = input.read();
      while(iR != -1){
          baos.write(iR);
          iR = input.read();
      }
      
      String user = new String(baos.toByteArray(), encoding).trim();
      int pwd = input.read();

      String result = null;
      if (null != user && !user.equals("") && user.equals("name") && pwd == 123) {
        result = "login success";
      } else {
        result = "login failed";
      }
      Logger.info("request user:" + user);
      Logger.info("request pwd:" + pwd);

      output = SocketIO.getDataOutput(socket);

      byte[] bytes = result.getBytes(encoding);
      int length = (short) bytes.length;
      output.writeShort(length);
      output.write(bytes);

      Logger.info("response info:" + result);
    } catch (Exception e) {
      e.printStackTrace();
      Logger.error("business thread run exception");
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
        Logger.error("server socket close error");
      }
    }
  }
}
