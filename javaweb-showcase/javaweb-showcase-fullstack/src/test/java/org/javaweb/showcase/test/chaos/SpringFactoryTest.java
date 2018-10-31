/*
 * $Id: SpringFactoryTest.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/SpringFactoryTest.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * 
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2016-4-20 下午3:26:03$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$
 */
public class SpringFactoryTest implements FactoryBean<Object>, InitializingBean, DisposableBean {

  @Override
  public void destroy() throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Object getObject() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Class<?> getObjectType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isSingleton() {
    // TODO Auto-generated method stub
    return false;
  }

}
