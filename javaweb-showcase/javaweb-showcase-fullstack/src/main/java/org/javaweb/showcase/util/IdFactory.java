/*
 * $Id: IdFactory.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/IdFactory.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 自动生成 ID 工厂类。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-5-20 上午11:28:02$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class IdFactory {

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
    return uuid.substring(4, 28);
  }
  
}
