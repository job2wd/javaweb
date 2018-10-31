/*
 * $Id: JavaUUIDUtilsTest.java 171 2017-02-17 07:22:14Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/test/java/org/javaweb/common/test/JavaUUIDUtilsTest.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.test;

import org.javaweb.common.util.JavaUUIDUtils;
import org.junit.Test;



/**
 * 
 * @author wangwd
 * @version $Revision: 171 $, $Date: 2017-1-17 下午6:24:12$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-17 15:22:14#$
 */
public class JavaUUIDUtilsTest extends BaseJunitTest {

  @Test
  public void testGetId() {
    System.out.println(JavaUUIDUtils.getLongId());
    System.out.println(JavaUUIDUtils.getIntId());
    System.out.println(JavaUUIDUtils.getRandomId());
    System.out.println(JavaUUIDUtils.getRandomCode());
  }
  
}
