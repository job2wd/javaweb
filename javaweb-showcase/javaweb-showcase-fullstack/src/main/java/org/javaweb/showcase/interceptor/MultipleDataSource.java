/*
 * $Id: MultipleDataSource.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/interceptor/MultipleDataSource.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.interceptor;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-5-26 下午8:09:55$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DatabaseContextHolder.getCustomerType();
  }

}
