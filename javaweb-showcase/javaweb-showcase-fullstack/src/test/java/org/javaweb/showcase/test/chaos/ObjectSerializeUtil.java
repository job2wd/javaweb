package org.javaweb.showcase.test.chaos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @作者 王建明
 * @创建日期 2012-10-12
 * @创建时间 上午10:48:41
 * @版本号 V 1.0
 */
public class ObjectSerializeUtil {
  /**
   * @param args
   * @throws IOException
   * @throws ClassNotFoundException
   * @作者 王建明
   * @创建日期 2012-10-12
   * @创建时间 上午10:48:41
   * @描述 ——
   */
  public static void main(String[] args) throws IOException,
      ClassNotFoundException {
    Customer customer = new Customer("阿蜜果", 24);
    String serStr = getStrFromObj(customer);
    
    System.out.println("serialized str:" + serStr);

    customer = (Customer) getObjFromStr(serStr);
    
    System.out.println("dserialized obj:" + customer);
  }

  /**
   * @param serStr
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws ClassNotFoundException
   * @作者 王建明
   * @创建日期 2012-10-12
   * @创建时间 上午10:56:27
   * @描述 —— 将字符串反序列化成对象
   */
  public static Object getObjFromStr(String serStr)
      throws UnsupportedEncodingException, IOException,
      ClassNotFoundException {
    String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
        redStr.getBytes("ISO-8859-1"));
    ObjectInputStream objectInputStream = new ObjectInputStream(
        byteArrayInputStream);
    Object result = objectInputStream.readObject();
    objectInputStream.close();
    byteArrayInputStream.close();

    return result;
  }

  /**
   * @return
   * @throws IOException
   * @throws UnsupportedEncodingException
   * @作者 王建明
   * @创建日期 2012-10-12
   * @创建时间 上午10:51:07
   * @描述 —— 将对象序列化成字符串
   */
  public static String getStrFromObj(Object obj) throws IOException,
      UnsupportedEncodingException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        byteArrayOutputStream);
    objectOutputStream.writeObject(obj);
    String serStr = byteArrayOutputStream.toString("ISO-8859-1");
    serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

    objectOutputStream.close();
    byteArrayOutputStream.close();
    return serStr;
  }
}

/**
 * @作者 王建明
 * @创建日期 2012-10-12
 * @创建时间 上午10:59:07
 * @描述 —— 测试对象封装
 */
class Customer implements Serializable {
  private String name;
  private int age;

  public Customer(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "name=" + name + ", age=" + age;
  }
}