/*
 * $Id: BaseDao.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/dao/BaseDao.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.dao.mybatis;

import java.util.List;
import java.util.Map;


/**
 *
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2016-5-26 下午8:13:24$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public interface BaseDao {

  /**
   * 查询数据库中所有表的名字
   * @return
   */
  List<String> showTables();
  
  /**
   * select table info for mysql database
   */
  Map<String, Object> selectTableInfo(String tableName);

}
