/*
 * $Id: SysLogDao.java 136 2017-02-06 06:16:43Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/dao/data/SysLogDao.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.dao.data;

import java.util.List;

import org.javaweb.showcase.dao.base.BaseDataDao;
import org.javaweb.showcase.model.SysLog;



/**
 * 
 * @author wangwd
 * @version $Revision: 136 $, $Date: 2016-5-25 下午4:43:52$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:16:43#$
 */
public interface SysLogDao extends BaseDataDao {

  void insertSysLog(SysLog log);
  
  List<SysLog> findAllSysLogs();
  
}
