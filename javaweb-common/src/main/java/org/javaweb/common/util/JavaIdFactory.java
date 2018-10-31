/*
 * $Id: JavaIdFactory.java 5301 2018-08-20 09:51:05Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaIdFactory.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 自动生成 ID 工厂类。
 * 
 * @author wangwd
 * @version $Revision: 5301 $, $Date: 2015-5-20 上午11:28:02$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-20 17:51:05#$
 */
public class JavaIdFactory {

  private static AtomicLong initAtomicLong = new AtomicLong(System.currentTimeMillis());
  private static AtomicInteger initAtomicInteger = new AtomicInteger(new Long(System.currentTimeMillis()).intValue());
  
  public static long getLongId() {
    return initAtomicLong.getAndIncrement();
  }
  
  public static int getIntId() {
    int id = initAtomicInteger.getAndIncrement();
    return Math.abs(id);
  }
  
  public static int getRandomId() {
    return new Random().nextInt(Integer.MAX_VALUE);
  }
  
  public static String getRandomCode() {
    String uuid = UUID.randomUUID().toString().toUpperCase();
    return uuid.substring(4, 28).replaceAll("-", "");
  }
  
}
