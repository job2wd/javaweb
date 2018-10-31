/*
 * $Id: AuditLog.java 4062 2018-06-12 03:20:19Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/annotation/AuditLog.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.javaweb.showcase.springboot.enums.ActionCodeEnum;
import org.javaweb.showcase.springboot.enums.ResourceCodeEnum;
import org.javaweb.showcase.springboot.enums.SysAuditLogEnums;


/**
 * 系统审计日志注解类，在需要记录日志到表 sys_audit_log（对应映射实体为 {@link com.hapr.cmc.model.SysAuditLog}）中的方法上提供该注解即可。<br>
 * 支持从国际化（I18N）资源文件中读取日志备注KEY
 * 
 * @author wangwd
 * @version $Revision: 4062 $, $Date: 2018年5月23日 下午4:59:53$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-12 11:20:19#$
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {
  
  /**
   * 日志级别：1 - 低级；2 - 中级；3 - 高级；默认为1。
   */
  SysAuditLogEnums.LevelEnum level() default SysAuditLogEnums.LevelEnum.LOW;
  
  /**
   * 日志类型，目前暂时为：0 - 任务日志（系统日志）；1 - 操作日志；默认为1。
   */
  SysAuditLogEnums.TypeEnum type() default SysAuditLogEnums.TypeEnum.USER_OP_LOG;
  
  /**
   * 是否敏感（有风险的）操作：0 - 否；1 - 是；默认为0。
   */
  SysAuditLogEnums.RiskEnum risk() default SysAuditLogEnums.RiskEnum.NO;
  
  /**
   * 执行操作的事件编码（存储在表 action_info 中），如：IMPORT/DELETE/CREATE/UPDATE/ENABLED/DISABLED
   */
  ActionCodeEnum actionCode() default ActionCodeEnum.EMPTY;
  
  /**
   * 事件发生资源（模块）编码（存储在表 resource_info 中），如：MALWARE_LIST_FILE/MALWARE_LIST_URL/MALWARE_LIST_IP
   */
  ResourceCodeEnum resourceCode() default ResourceCodeEnum.EMPTY;
  
  /**
   * 被注解的方法参数的名字，用来在日志中记录操作的具体数据。<br>
   * 记录日志时会根据提供的值列表获取对应的参数的值，然后组装为JSON格式数据存储在 auditDetail 字段中。
   */
  String[] dataArgs() default {};
  
  /**
   * 事件日志备注， 支持国际化 I18N（以 “{” 开始并以 “}” 结束，则从国际化资源文件中获取相应KEY对应的值）<br>
   * <b>注意：
   *   国际化（I18N）支持占位符设置，占位符从0开始，即{0},{1}，占位符中的参数值来自于属性 {@link #remarkArgs()}
   * </b>
   */
  String remark() default "";
    
  /**
   * 针对 {@link #remark()} 中占位符按照顺序提供需要替换的值，该值为被注解方法的参数名字或对象参数属性名字.<br>
   * 如：
   * <p>
   * <code>remark = "删除ID为 {0} 的用户"，remarkArgs = {"id"}</code><br>
   * <code>remark = "更新了用户 {0} 的信息，用户类型为 {1}"，remarkArgs = {"user.name", "user.type"}</code>
   * </p>
   * <b>注：该属性中的值支持国际化（I18N）</b>
   */
  String[] remarkArgs() default {};
  
}
