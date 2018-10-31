/*
 * $Id: SimpleCacheManagerTest.java 154 2017-02-17 03:38:40Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/test/java/org/javaweb/common/test/SimpleCacheManagerTest.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.test;

import org.javaweb.data.service.cache.simple.CacheManager;
import org.javaweb.data.service.cache.simple.impl.CacheComplexKey4HelloWord;
import org.javaweb.data.service.cache.simple.impl.CacheComplexKey4HelloWord.HelloWord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author wangwd
 * @version $Revision: 154 $, $Date: 2017年2月6日 下午2:46:53$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-17 11:38:40#$
 */
public class SimpleCacheManagerTest extends BaseJunitTest {

  @Before
  public void before() {
    
  }
  
  @Test
  public void testHelloWordCache() {
    HelloWord helloword = new CacheComplexKey4HelloWord().new HelloWord();
    
    helloword.setId(1);
    helloword.setName("helloword");
    helloword.setSrcIp("192.168.1.10");
    helloword.setDesIp("192.168.1.100");
    helloword.setDesPort("2000");
    
    CacheManager.initAllHelloWords();
    
    CacheManager.addHelloWord(helloword);
    
    Assert.assertNotNull(CacheManager.getHelloWordById(1));
    Assert.assertNotNull(CacheManager.getHelloWordByName("helloword"));
    Assert.assertNotNull(CacheManager.getHelloWordByIpPort("192.168.1.10-192.168.1.100:2000"));
    Assert.assertNotNull(CacheManager.getHelloWordByIpPort("192.168.1.10", "192.168.1.100", "2000"));
  }
  
}
