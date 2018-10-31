/*
 * $Id: BaseDaoImpl.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/dao/mybatis/impl/BaseDaoImpl.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javaweb.common.PropCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2018年4月24日 上午11:05:55$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
@Repository
public class BaseDaoImpl implements BaseDao {
  
  @Autowired
  protected org.mybatis.spring.SqlSessionTemplate sqlSession;
  
  @Override
  public List<String> showTables() {
    return sqlSession.selectList("com.hapr.cmc.dao.BaseDao.showTables");
  }

  @Override
  public Map<String, Object> selectTableInfo(String tableName) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("TABLE_SCHEMA", PropCfg.getDatabaseName());
    map.put("TABLE_NAME", tableName);
    return sqlSession.selectOne("com.hapr.cmc.dao.BaseDao.selectTableInfo", map);
  }
  
  public org.mybatis.spring.SqlSessionTemplate getSqlSession() {
    return sqlSession;
  }

  public void setSqlSession(org.mybatis.spring.SqlSessionTemplate sqlSession) {
    this.sqlSession = sqlSession;
  }

}
