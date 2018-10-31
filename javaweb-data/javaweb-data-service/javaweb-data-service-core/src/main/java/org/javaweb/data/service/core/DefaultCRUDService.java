/*
 * $Id: DefaultCRUDService.java 6498 2018-10-24 01:39:32Z wangweidong $
 * $HeadURL:
 * https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90
 * %E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/service/DefaultCRUDService.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.service.core;

import java.io.Serializable;
import java.util.List;

import org.javaweb.data.dao.mybatis.DefaultCRUDDao;
import org.javaweb.data.model.domain.QueryCondition;
import org.javaweb.data.model.domain.ResponseBean4Pagination;
import org.javaweb.data.model.entity.BaseEntity4Key;


/**
 *
 * @author wangwd
 * @version $Revision: 6498 $, $Date: 2018年5月16日 下午1:37:43$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-10-24 09:39:32#$
 */
public interface DefaultCRUDService<T extends BaseEntity4Key<K>, K extends Serializable> extends BaseService {

  <D extends DefaultCRUDDao<T, K>> DefaultCRUDDao<T, K> getCRUDDao();
  
  /**
   * 根据指定条件查询数据条数
   * @param t
   * @return
   */
  Integer total(T t);
  
  /**
   * 通过主键获取唯一记录
   */
  T findOne(K k);
  
  /**
   * 根据指定条件查询一条数据<br>
   * <b>如果未找到指定条件的数据或找到数据多于一条，都将返回 NULL。</b>
   * @param t
   * @return
   */
  T findOne(T t);

  /**
   * 根据指定条件查询一批数据（可根据翻页、排序等查询）
   * @param t
   * @return
   */
  List<T> find(T t);

  /**
   * 查找表中全部数据
   * @return
   */
  List<T> findAll();

  ResponseBean4Pagination<T> findPage(T t);
  
  /**
   * 批量插入
   * 
   * @param ts
   *          要插入的数据对象POJO集合
   */
  @SuppressWarnings("unchecked")
  Integer insert(T... ts);
  
  /**
   * 批量插入
   * 
   * @param list
   *          要插入的数据对象POJO集合
   */
  Integer insert(List<T> list);

  /**
   * 批量更新<br>
   * <b>目前没法实现一次性批量更新，只能在实际实现时循环逐条更新。</b>
   * 
   * @param ts
   *          要更新的数据对象POJO集合
   */
  @SuppressWarnings("unchecked")
  Integer update(T... ts);
  
  Integer update(List<T> ts);
  
  /**
   * 批量插入或批量更新
   * 
   * @param ts
   *          要插入的数据对象POJO集合
   */
  @SuppressWarnings("unchecked")
  Integer insertOrUpdate(T... ts);
  
  /**
   * 批量插入或批量更新
   * 
   * @param list
   *          要插入的数据对象POJO集合
   */
  Integer insertOrUpdate(List<T> list);
  
  /**
   * 根据提供的表主键集合（ids）批量删除多条记录
   * 
   * @param ids
   *          要删除的数据ID集合
   */
  @SuppressWarnings("unchecked")
  Integer delete(K... ids);
  
  @SuppressWarnings("unchecked")
  Integer deleteByConditions(T... ts);
  
  Integer deleteByConditions(List<T> list);

  /**
   * 判断提供的实体对象在数据库映射表中是否存在
   * @param t
   * @return
   */
  boolean exists(T t);
  
  /**
   * 添加排序条件到对象 T 中
   */
  void addOrder(T t, String column, boolean asc);
  
  /**
   * 添加查询条件到对象 T 中
   */
  void addQueryCondition(T t, String column, QueryCondition.OPEnum op, Object value);
  
  /**
   * 添加需要返回的查询列到对象 T 中
   */
  void addResultColumn(T t, String column);
  
  
}
