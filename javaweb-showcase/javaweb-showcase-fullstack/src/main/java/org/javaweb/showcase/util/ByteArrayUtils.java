/*
 * $Id: ByteArrayUtils.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/ByteArrayUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.nio.charset.Charset;
import java.util.Arrays;


/**
 * byte[] 和 java 基本类型之间的转换工具类。
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2014-8-31 下午2:37:37$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class ByteArrayUtils {
  
  public static byte[] getBytes(short data)
  {
      byte[] bytes = new byte[2];
      bytes[0] = (byte) (data & 0xff);
      bytes[1] = (byte) ((data & 0xff00) >> 8);
      return bytes;
  }

  public static byte[] getBytes(char data)
  {
      byte[] bytes = new byte[2];
      bytes[0] = (byte) (data);
      bytes[1] = (byte) (data >> 8);
      return bytes;
  }

  public static byte[] getBytes(int data)
  {
      byte[] bytes = new byte[4];
      bytes[0] = (byte) (data & 0xff);
      bytes[1] = (byte) ((data & 0xff00) >> 8);
      bytes[2] = (byte) ((data & 0xff0000) >> 16);
      bytes[3] = (byte) ((data & 0xff000000) >> 24);
      return bytes;
  }

  public static byte[] getBytes(long data)
  {
      byte[] bytes = new byte[8];
      bytes[0] = (byte) (data & 0xff);
      bytes[1] = (byte) ((data >> 8) & 0xff);
      bytes[2] = (byte) ((data >> 16) & 0xff);
      bytes[3] = (byte) ((data >> 24) & 0xff);
      bytes[4] = (byte) ((data >> 32) & 0xff);
      bytes[5] = (byte) ((data >> 40) & 0xff);
      bytes[6] = (byte) ((data >> 48) & 0xff);
      bytes[7] = (byte) ((data >> 56) & 0xff);
      return bytes;
  }

  public static byte[] getBytes(float data)
  {
      int intBits = Float.floatToIntBits(data);
      return getBytes(intBits);
  }

  public static byte[] getBytes(double data)
  {
      long intBits = Double.doubleToLongBits(data);
      return getBytes(intBits);
  }

  public static byte[] getBytes(String data, String charsetName)
  {
      Charset charset = Charset.forName(charsetName);
      return data.getBytes(charset);
  }

  public static byte[] getBytes(String data)
  {
      return getBytes(data, "UTF-8");
  }

  
  public static short getShort(byte[] bytes, int offset)
  {
      return (short) ((0xff & bytes[offset + 0]) |
          (0xff00 & (bytes[offset + 1] << 8)));
  }

  public static char getChar(byte[] bytes, int offset)
  {
      return (char) ((0xff & bytes[offset + 0]) |
          (0xff00 & (bytes[offset + 1] << 8)));
  }

  public static int getInt(byte[] bytes, int offset)
  {
      return (0xff & bytes[offset + 0]) |
          (0xff00 & (bytes[offset + 1] << 8)) |
          (0xff0000 & (bytes[offset + 2] << 16)) |
          (0xff000000 & (bytes[offset + 3] << 24));
  }
 
  public static long getLong(byte[] bytes, int offset)
  {
      return(0xffL & bytes[offset + 0]) |
          (0xff00L & ((long)bytes[offset + 1] << 8)) |
          (0xff0000L & ((long)bytes[offset + 2] << 16)) |
          (0xff000000L & ((long)bytes[offset + 3] << 24)) |
          (0xff00000000L & ((long)bytes[offset + 4] << 32)) |
       (0xff0000000000L & ((long)bytes[offset + 5] << 40)) |
       (0xff000000000000L & ((long)bytes[offset + 6] << 48)) |
       (0xff00000000000000L & ((long)bytes[offset + 7] << 56));
  }

  public static float getFloat(byte[] bytes, int offset)
  {
      return Float.intBitsToFloat(getInt(bytes, offset));
  }

  public static double getDouble(byte[] bytes, int offset)
  {
      long l = getLong(bytes, offset);
      return Double.longBitsToDouble(l);
  }

  public static String getString(byte[] bytes, int offset, int length, String charsetName) {
    return new String(bytes, offset, length, Charset.forName(charsetName));
  }

  public static String getString(byte[] bytes, int offset, int length)
  {
      return getString(bytes, offset, length, "UTF-8");
  }

  
  public static void main(String[] args)
  {
      short s = 122;
      int i = 122;
      long l = 1222222;

      char c = 'a';

      float f = 122.22f;
      double d = 122.2243d;

      String string = "我是好孩子a";
      System.out.println(s);
      System.out.println(i);
      System.out.println(l);
      System.out.println(c);
      System.out.println(f);
      System.out.println(d);
      System.out.println(string);

      System.out.println("**************");

      System.out.println(getShort(getBytes(s), 0));
      System.out.println(getInt(getBytes(i), 0));
      System.out.println(getLong(getBytes(l), 0));
      System.out.println(getChar(getBytes(c), 0));
      System.out.println(getFloat(getBytes(f), 0));
      System.out.println(getDouble(getBytes(d), 0));
      byte[] bytes = getBytes(string);
            
      System.out.println(getString(bytes, 0, 16));
      
      byte[] ss = Arrays.copyOfRange(getBytes(string + "b"), 0, 10);
      System.out.println(new String(ss));
  }
  
  public static int getInt2(byte[] array, int offset) {
    return
      ((array[offset]   & 0xff) << 24) |
      ((array[offset+1] & 0xff) << 16) |
      ((array[offset+2] & 0xff) << 8) |
       (array[offset+3] & 0xff);
  }
  
  public static long getLong2(byte[] array, int offset) {
    return
      ((long)(array[offset]   & 0xff) << 56) |
      ((long)(array[offset+1] & 0xff) << 48) |
      ((long)(array[offset+2] & 0xff) << 40) |
      ((long)(array[offset+3] & 0xff) << 32) |
      ((long)(array[offset+4] & 0xff) << 24) |
      ((long)(array[offset+5] & 0xff) << 16) |
      ((long)(array[offset+6] & 0xff) << 8) |
      (array[offset+7] & 0xff);
  }
  
}
