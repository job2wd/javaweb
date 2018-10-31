/*
 * $Id: BaseServiceImpl.java 5932 2018-09-17 07:10:49Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/service/impl/BaseServiceImpl.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.service.core;

import java.util.List;
import java.util.Map;

import org.javaweb.data.dao.mybatis.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author wangwd
 * @version $Revision: 5932 $, $Date: 2018年4月26日 上午9:18:19$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-17 15:10:49#$
 */
@Service
@Transactional
public abstract class BaseServiceImpl implements BaseService {

  @Autowired
  //@Inject
  @Qualifier("baseDaoImpl")
  private BaseDao baseDao;
  
  /* (non-Javadoc)
   * @see com.hapr.cmc.service.BaseService#showTables()
   */
  @Override
  public List<String> showTables() {
    return baseDao.showTables();
  }

  @Override
  public Map<String, Object> selectTableInfo(String tableName) {
    return baseDao.selectTableInfo(tableName);
  }
    
}
