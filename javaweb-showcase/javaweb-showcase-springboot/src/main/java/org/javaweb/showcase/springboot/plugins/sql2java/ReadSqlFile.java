/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.plugins.sql2java;


/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月29日 上午10:41:30$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class ReadSqlFile {
  
  private static final String DEFAULT_OUTPUT_PATH = System.getProperty("java.io.tmpdir");
  
  /**
   * 如果提供 args 参数，则校验必须为以下格式的值：</>
   * <li>args[0]: user name (creator name)</li>
   * <li>args[1]: base package name (etc:org.javaweb.showcase.springboot)</li>
   * <li>args[2]: ouput directory</li></p>
   * 如果未提供 args 参数，在程序执行处理前需要选择操作：
   * <pre>
   * 0 - 默认输出（或回车）；
   * 1 - 自定义输出；
   * </pre>
   * 若选择默认输出，则使用默认基本包名称（org.javaweb.showcase.springboot）和默认输出路径（java.io.tmpdir）；
   * 若选择自定义输出，则按照如下步骤提供相应参数：
   * <pre>
   * 请输入用户（创建者）名称（回车即表示使用默认值 ${user}）：
   * 请输入基本包名称（回车即表示使用默认值 org.javaweb.showcase.springboot）：
   * 请输入结果输出目录（回车即表示使用默认值 java.io.tmpdir）：
   * 确认以上参数输入无误？（Y/N）
   * </pre>
   */
  public static void main(String[] args) {
    if (args != null && args.length > 0) {
      
    } else {
      
    }
    
  }
  
}
