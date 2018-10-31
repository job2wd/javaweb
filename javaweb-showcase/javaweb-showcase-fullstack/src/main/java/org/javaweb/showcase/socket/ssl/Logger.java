package org.javaweb.showcase.socket.ssl;


public class Logger {

  public static void debug(String msg) {
    System.out.println(msg);
  }
  
  public static void info(String msg) {
    System.out.println(msg);
  }
  
  public static void error(String msg) {
    System.err.println(msg);
  }
  
  public static void error(String msg, Throwable e) {
    System.out.println(msg);
    if(e != null) {
      e.printStackTrace();
    }
  }
  
}
