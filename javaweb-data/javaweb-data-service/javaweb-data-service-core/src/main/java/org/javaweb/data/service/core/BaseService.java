/*
 * $Id: BaseService.java 5341 2018-08-22 06:40:57Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/service/BaseService.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.service.core;

import java.util.List;
import java.util.Map;


/**
 *
 * @author wangwd
 * @version $Revision: 5341 $, $Date: 2018年4月26日 上午9:17:55$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-22 14:40:57#$
 */
public interface BaseService {

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
