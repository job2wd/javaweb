/*
 * $Id: DefaultCRUDDao.java 6607 2018-10-30 05:09:04Z liuyuanke $
 * $HeadURL:
 * https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90
 * %E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/dao/DefaultCRUDDao.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.dao.mybatis;

import java.io.Serializable;
import java.util.List;

import org.javaweb.data.model.entity.BaseEntity4Key;


/**
 *
 * @author wangwd
 * @version $Revision: 6607 $, $Date: 2018年5月16日 上午9:47:38$
 * @LastChanged $Author: liuyuanke $, $Date:: 2018-10-30 13:09:04#$
 */
public interface DefaultCRUDDao<T extends BaseEntity4Key<K>, K extends Serializable> extends BaseDao {

  /**
   * 根据条件检索符合指定条件的数据条数
   * @param t
   * @return
   */
  Integer total(T t);

  /**
   * 根据条件（可以包含分页参数）检索一批数据
   * @param t
   * @return
   */
  List<T> find(T t);

  /**
   * 批量插入数据
   * @param list
   */
  Integer insert(List<T> list);

  /**
   * 更新数据
   * @param t
   */
  Integer update(T t);

  /**
   * 根据提供的数据库表主键集批量删除数据
   * @param ids
   */
  Integer delete(K[] ids);

  Integer deleteByCondition(T t);
  
  /**
   * 根据指定条件删除一条或多条数据
   * @param t
   */
  void delete(T t);

}
