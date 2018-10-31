/*
 * $Id: Permission.java 3735 2018-05-25 04:22:01Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/annotation/Permission.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.javaweb.showcase.springboot.enums.RoleEnum;


/**
 * 权限注解。定义当前用户是否在包含有该注解的资源（类、接口、方法、字段等）上有可访问权限。
 * 
 * @author wangwd
 * @version $Revision: 3735 $, $Date: 2018年5月22日 下午7:15:00$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-25 12:22:01#$
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

  /**
   * 指定角色编码集合，只有在集合中的角色才可以访问提供了该注解的资源（类、接口、方法、字段等）
   */
  RoleEnum[] value();
  
  /**
   * 指定是否必须满足 value 中指定的所有角色才可以访问<br>
   * true - 必须全部满足才可以访问相关资源；false - 只要满足其中一个角色就可以访问该资源；默认为 false。
   */
  boolean all() default false;
  
}
