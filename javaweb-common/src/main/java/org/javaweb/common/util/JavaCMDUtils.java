/*
 * $Id: JavaCMDUtils.java 5932 2018-09-17 07:10:49Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaCMDUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangwd
 * @version $Revision: 5932 $, $Date: 2018年8月15日 下午6:31:35$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-17 15:10:49#$
 */
public class JavaCMDUtils {
  
  protected static Logger log = LoggerFactory.getLogger(JavaCMDUtils.class);
  
  public static String execute(String... commands) {
    StringBuffer output = new StringBuffer();

    BufferedReader bufferedReader = null;
    Process process = null;
    
    try {
      String[] cmds = getCommands(commands);
       
      process = Runtime.getRuntime().exec(cmds);
      process.waitFor();
      
      bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      log.error("exec cmd [{}] exception:{}", commands, JavaExceptionUtils.getMessage(e));
      throw new RuntimeException(e);
    } finally {
      try {
        if(bufferedReader != null) {
          bufferedReader.close();
        }
        if(process != null) {
          process.destroy();
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    
    log.debug("CMD OUTPUT:{}", output);
    
    return output.toString();
  }
  
  private static String[] getCommands(String... commands) {
    String[] cmds = new String[3];
    
    String os = System.getProperty("os.name").toLowerCase();
    boolean isWindows = os.contains("windows");
    
    if(isWindows) {
      cmds[0] =  "cmd";
      cmds[1] = "/c";
    } else {
      cmds[0] =  "/bin/sh";
      cmds[1] = "-c";
    }
    
    String cmd = "";
    
    for(int i = 0; i < commands.length; i++) {
      if(!commands[i].endsWith(";")) {
        cmd += commands[i] + (isWindows ? " " : "; ");
      }
    }
    
    cmds[2] = cmd;
    log.debug("CMD:{}", cmd);
    
    return cmds;
  }
  
}
