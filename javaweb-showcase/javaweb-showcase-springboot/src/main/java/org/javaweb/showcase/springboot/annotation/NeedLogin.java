/*
 * $Id: NeedLogin.java 3673 2018-05-23 00:58:45Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/annotation/NeedLogin.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 登录注解。定义当前用户是否必须登录才可访问包含有该注解的资源（类、接口、方法、字段等）。
 * 
 * @author wangwd
 * @version $Revision: 3673 $, $Date: 2018年5月22日 下午7:29:10$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-23 08:58:45#$
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {

  /**
   * 是否必须登录才可访问包含有该注解的资源（类、接口、方法、字段等）<br>
   * true - 是；false - 否；默认为 true。
   */
  boolean required() default true;
  
}
