package org.javaweb.showcase.test.chaos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ByteStream2ObjectUtils {

  public static String inputStream2String(InputStream in) throws IOException {
    InputStreamReader isr = new InputStreamReader(in);
    
    char[] cbuf = new char[in.available()];
    isr.read(cbuf);
    isr.close();
    
    return new String(cbuf);
  }
  
  /**
   * 提取IP（十进制表示法）二进制值，一个IP在java中二进制为60位（IP中一个数字占6位）
   */
  public static String pickupIp(String binaryString) {
    String s = binaryString.substring(0, 60);
    
    String res = "";
    for(int i = 0; i < 60; i+=6) {
      res += binaryString2HexString(s.substring(i, i + 6));
    }
    
    return res;
  }
  
  /**
   * 提取端口二进制值，一个端口在java中二进制为24位（端口中一个数字占6位）
   */
  public static String pickupPort(String binaryString) {
    String s = binaryString.substring(0, 24);
    
    String res = "";
    for(int i = 0; i < 24; i+=6) {
      res += binaryString2HexString(s.substring(i, i + 6));
    }
    
    return res;
  }
  
  /**
   * 提取时间戳二进制值，一个时间戳在java中二进制为78位（时间戳中一个数字占6位）
   */
  public static String pickupTime(String binaryString) {
    String s = binaryString.substring(0, 78);
    
    String res = "";
    for(int i = 0; i < 78; i+=6) {
      res += binaryString2HexString(s.substring(i, i + 6));
    }
    
    return res;
  }
  
  /**
   * 提取英文字母二进制值，一个字母在java中二进制为7位
   */
  public static String pickupChar(String binaryString) {
    String s = binaryString.substring(0, 7);
    
    String res = "";
    for(int i = 0; i < 7; i+=7) {
      res += binaryString2HexString(s.substring(i, i + 7));
    }
    
    return res;
  }
  
  /**
   * 提取汉字二进制值，一个汉字在java中二进制为15位
   */
  public static String pickupChinese(String binaryString) {
    String s = binaryString.substring(0, 15);
    
    String res = "";
    for(int i = 0; i < 15; i+=15) {
      res += binaryString2HexString(s.substring(i, i + 15));
    }
    
    return res;
  }
  
  /**
   * 将二进制字符串转换成 int 数组
   * @param binaryString 二进制字符串（一个字符转换后的二进制数据，java中为6位）
   * @return
   */
  private static int[] binaryString2IntArray(String binaryString) {
    char[] temp = binaryString.toCharArray();
    int[] result = new int[temp.length];
    
    for (int i = 0; i < temp.length; i++) {
      result[i] = temp[i] - 48;
    }
    
    return result;
  }

  /**
   * 将二进制转换成字符串
   * @param binaryString 二进制字符串（一个字符转换后的二进制数据，java中为6位）
   * @return
   */
  private static String binaryString2HexString(String binaryString) {
    int[] temp = binaryString2IntArray(binaryString);
    int sum = 0;
    
    for (int i = 0; i < temp.length; i++) {
      sum += temp[temp.length - 1 - i] << i;
    }
    
    return String.valueOf((char)sum);
  }
      
  public static void main(String[] args) throws Exception {
    String plainBinaryString = marshallingBinaryPlain4Test();//marshallingBinaryPlain4Test2();
    
    String binaryString = inputStream2String(new ByteArrayInputStream(plainBinaryString.getBytes()));
         
    System.out.println(binaryString);
    
    System.out.println(pickupIp(binaryString));
    System.out.println(pickupPort(binaryString.substring(60)));
    System.out.println(pickupTime(binaryString.substring(84)));
    
    System.out.println(pickupChar(binaryString.substring(162)));
    System.out.println(pickupChinese(binaryString.substring(169)));
  }
  
  private static String marshallingBinaryPlain4Test() {
    String[] ips = "192.168.200.100".split("[.]");
    //ips = "0.0.0.0".split("[.]");
    //ips = "255.255.255.255".split("[.]");
    
    String ip = (Long.valueOf(ips[0]) << 24) + (Long.valueOf(ips[1]) << 16) + (Long.valueOf(ips[2]) << 8) + Long.valueOf(ips[3]) + "";
    String port = "8080";
    String time = System.currentTimeMillis() + "";
    
    //java中二进制位数：一个数字6位，一个字母7位，一个汉字15位
    
    System.out.println("ip="  + ip);
    System.out.println("port="  + port);
    System.out.println("time="  + time);
    
    String ip_b = "";
    String port_b = "";
    String time_b = "";
    
    char[] charArray = ip.toCharArray();
    for(int i = 0; i < charArray.length; i++) {
      ip_b += Integer.toBinaryString(charArray[i]);
    }
    System.out.println("ip_b=" + ip_b);
    
    charArray = port.toCharArray();
    for(int i = 0; i < charArray.length; i++) {
      port_b += Integer.toBinaryString(charArray[i]);
    }
    System.out.println("port_b=" + port_b);
   
    charArray = time.toCharArray();
    for(int i = 0; i < charArray.length; i++) {
      time_b += Integer.toBinaryString(charArray[i]);
    }
    System.out.println("time_b=" + time_b);
    
    charArray = "1".toCharArray();
    String char_b = "";
    for(int i = 0; i < charArray.length; i++) {
      char_b += Integer.toBinaryString(charArray[i]);
    }
    System.out.println("char_b=" + char_b);
    
    charArray = "中".toCharArray();
    String chinese_b = "";
    for(int i = 0; i < charArray.length; i++) {
      chinese_b += Integer.toBinaryString(charArray[i]);
    }
    System.out.println("chinese_b=" + chinese_b);
   
    return ip_b + port_b + time_b + char_b + chinese_b;
  }
  
  private static String marshallingBinaryPlain4Test2() {
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
