/*
 * $Id: BaseEntity4Key.java 5669 2018-09-04 11:23:20Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/model/BaseEntity4Key.java $
 * Copyright (c) 2014 Company, vito. All Rights Reserved.
 */
package org.javaweb.data.model.entity;


/**
 *
 * @author wangwd
 * @version $Revision: 5669 $, $Date: 2018年5月17日 下午5:16:21$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-04 19:23:20#$
 */
public abstract class BaseEntity4Key<K extends java.io.Serializable> extends BaseEntity {

  private static final long serialVersionUID = 4901168056621132556L;

  public abstract K getId();

  public abstract void setId(K id);

}
