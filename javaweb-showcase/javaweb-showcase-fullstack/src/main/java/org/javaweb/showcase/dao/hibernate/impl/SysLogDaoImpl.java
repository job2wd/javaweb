/*
 * $Id: SysLogDaoImpl.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/hibernate/impl/SysLogDaoImpl.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.hibernate.impl;

import java.util.List;

import org.javaweb.showcase.dao.data.SysLogDao;
import org.javaweb.showcase.dao.hibernate.DataDaoHibernate;
import org.javaweb.showcase.model.SysLog;
import org.springframework.stereotype.Repository;



/**
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2015-8-14 下午1:15:44$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
@Repository
public class SysLogDaoImpl extends DataDaoHibernate<SysLog, Integer> implements SysLogDao {

  @Override
  public void insertSysLog(SysLog log) {
    save(log);
  }
  
  @Override
  public List<SysLog> findAllSysLogs() {
    return getAll();
  }
  
}
