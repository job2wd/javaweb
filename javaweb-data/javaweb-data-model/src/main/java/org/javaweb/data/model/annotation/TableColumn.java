/*
 * $Id: TableColumn.java 6039 2018-09-29 00:36:45Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/annotation/TableColumn.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.model.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author wangwd
 * @version $Revision: 6039 $, $Date: 2018年7月25日 下午6:08:05$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-29 08:36:45#$
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumn {

  //@AliasFor("name")
  String value() default "";
  
  //@AliasFor("value")
  String name() default "";
  
  String type() default "";
  
  String length() default "";
  
  String defaultValue() default "";
  
  boolean isPrimaryKey() default false;
  
  boolean isForeignKey() default false;
  
}
