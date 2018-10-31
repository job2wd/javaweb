package org.javaweb.showcase.test.chaos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Bs2outilsBak {

  public static String inputStream2String(InputStream in) throws IOException {
    InputStreamReader isr = new InputStreamReader(in);
    
    char[] cbuf = new char[in.available()];
    isr.read(cbuf);
    isr.close();
    
    return new String(cbuf);
  }
  
  public static void main(String[] args) throws Exception {
    String plainBinaryString = marshallingBinaryPlain4Test();
    
    String binaryString = inputStream2String(new ByteArrayInputStream(plainBinaryString.getBytes()));
         
    System.out.println(binaryString);
  }
  
  private static String marshallingBinaryPlain4Test() {
    String[] ips = "192.168.200.100".split("[.]");
    String ip = (Long.valueOf(ips[0]) << 24) + (Long.valueOf(ips[1]) << 16) + (Long.valueOf(ips[2]) << 8) + Long.valueOf(ips[3]) + "";
    String port = "8080";
    String time = System.currentTimeMillis() + "";
    
    System.out.println("ip="  + ip);
    System.out.println("port="  + port);
    System.out.println("time="  + time);
        
    String plainBinaryString = ip + port + time;
    System.out.println("plainBinaryString=" + plainBinaryString);
    
    return plainBinaryString;
  }
      
}
