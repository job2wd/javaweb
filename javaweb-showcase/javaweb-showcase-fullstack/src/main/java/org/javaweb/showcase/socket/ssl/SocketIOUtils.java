package org.javaweb.showcase.socket.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIOUtils {
  
  public static void close(InputStream in, OutputStream out) {
    try {
      if(in != null) {
        in.close();
      }
      if(out != null) {
        out.close();
      }
    } catch (IOException e) {
      Logger.error("close input stream or output stream occur error!", e);
    }
  }
  
  public static void close(Socket socket) {
    try {
      if(socket != null) {
        socket.close();
        Logger.debug("close socket:" + socket.getInetAddress().getHostAddress());
      }
    } catch (IOException e) {
      Logger.error("close socket occur error!", e);
    }
  }
  
}
