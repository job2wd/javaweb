/*
 * $Id: CacheKey4HelloWord.java 134 2017-02-06 06:14:28Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/simple/impl/CacheKey4HelloWord.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple.impl;

import org.javaweb.data.service.cache.simple.CacheComplexKey;
import org.javaweb.data.service.cache.simple.CacheKey;



/**
 * 
 * @author wangwd
 * @version $Revision: 134 $, $Date: 2016-4-21 下午4:46:04$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:14:28#$
 */
public class CacheKey4HelloWord extends CacheKey {

  @Override
  public String getLeadFieldName() {
    return "id";
  }

  @Override
  public String[] getSubfieldsName() {
    return new String[]{"name"};
  }

  @Override
  public CacheComplexKey[] getComplexKey() {
    return new CacheComplexKey4HelloWord[]{new CacheComplexKey4HelloWord()};
  }

}
