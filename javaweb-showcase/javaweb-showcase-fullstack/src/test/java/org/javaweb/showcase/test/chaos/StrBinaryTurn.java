/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年4月20日 下午4:28:17$
 * @LastChanged $Author:$, $Date:: #$
 */
public class StrBinaryTurn {

  //将二进制字符串转化为中文
  //中文转二进制在线工具  http://www.5ixuexiwang.com/str/binary.php
  // 待处理的字符串
  public static final String SOURCE = "101111001110100101001101001110100100000001110111111111000011001001011101010010110011000100101100101101011111010011010011110111111111100001100101100100101001110110110101111100111101010101101100100000100110011000101111101111101010010100011011110111111000000000010";

  public static void main(String[] args) throws Exception {
    bin2Chinese();
    chinese2Bin();
    
    binaryStream2PlainText();
  }
  
  public static void bin2Chinese() throws UnsupportedEncodingException {
 // 定义正则表达式
    // 匹配所有由1或0组成的8位字符
    Pattern p = Pattern.compile("[10]{8}");

    // 定义匹配器，与源字符串关连上
    Matcher m = p.matcher(SOURCE);

    // 安放匹配结果
    //List&lt;Byte&gt; list = new ArrayList&lt;Byte&gt;();  //方法一 用泛型

    List list = new ArrayList(); //方法二 将泛型拿掉，表示所有数据以Object类型存储

    // 开始搜寻pattern
    // 先将8位的字符串按2进制扫描为Integer
    // 由于后面的须求数字再强制转成byte
    // 存入list中
    while (m.find()) {
      list.add((byte) Integer.parseInt(m.group(), 2));
    }

    // 准备将list转为byte数组
    // 由于String构造器参数类型的限制
    byte[] b = new byte[list.size()];

    // 开始转换
    for (int j = 0; j < b.length; j++) {
      //b[j] = list.remove(0);   //方法一 去掉泛型后这里会报错，因为取出的是Object类型

      b[j] = (Byte) list.remove(0); //方法二 把Object强制转成Byte就可以了
    }
    /*
     * List.remove(int index)是将指定位置的元素删除,
     * 然后右边所有剩下的数据向左移一位，填补第一个数据的空缺。
     * remove(0)中0表示第一个元素，不停的调用remove(0)导致所有元素被删光，
     * 剩下一个空集合。除了删除指定元素外，同时也具有返回值，就是被删掉的元素。
     */

    // 将数组转换为String输出
    // 故意不指定字符集(GBK)，让编绎器按系统默认打印
    System.out.println(new String(b, "GBK"));
  }

  public static void chinese2Bin() {
    String string = "1234567880801576654562啊";
    byte[] b = null;
    try {
      b = string.getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 0; i < b.length; i++) {
      System.out.print(Integer.toBinaryString(b[i]));
    }
    String fString = new String(b);
    System.out.print("\n" + fString);
  }
  
  public static void binaryStream2PlainText() throws IOException {
    String binStr = "1234567880801576654562";
    
    ByteArrayInputStream bin = new ByteArrayInputStream(binStr.getBytes());
    
    byte[] b = new byte[bin.available()];
    bin.read(b);
    b.clone();
    
    String fString = new String(b);
    System.out.print("\n" + fString);
    
  }

}
