
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(10) NOT NULL auto_increment COMMENT '主键，用于数据维护，无实际业务含义，不能为空。',
  `login_name` varchar(50) default NULL COMMENT '用户设置的可用于登录系统的登录名，只能为英文字母、数字或下划线，由于用户可用手机号或身份证号登录，故该值可为空。',
  `login_password` varchar(100) default NULL COMMENT '用户登录系统密码（进行了加密处理）',
  `full_name` varchar(50) default NULL COMMENT '用户真实姓名',
  PRIMARY KEY  (`id`)
) COMMENT='用户信息表';
