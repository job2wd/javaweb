/*
 * $Id: JavaLibraryUtils.java 4405 2018-06-26 07:13:59Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/util/JavaLibraryUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.util;



/**
 * 系统用到的动态链接库（Windows 为  *.dll，Linux 为 *.so）加载工具类。
 * 
 * @author wangwd
 * @version $Revision: 4405 $, $Date: 2016-4-1 下午3:58:37$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-26 15:13:59#$
 */
public class JavaLibraryUtils {

  /**
   * 指定要加载动态链接库的的文件所在目录，对于指定目录下的文件，会追加到 java.library.path 设置的路径中。
   * 
   * @param libPath 动态链接库所在目录绝对路径
   */
  public static void loadLibraryPath(String libPath) {
    //File classPathFile = new File(System.getProperty("user.dir") + "/web/comm/so");
    //URL url = this.getClass().getResource("/web/comm/so");
    
    //String libPath = url.getPath();

    String libraryPath = System.getProperty("java.library.path");
    String osName = System.getProperty("os.name").toLowerCase();
        
    if (osName.contains("windows")) {
      if(libraryPath.endsWith(";")) {
        libraryPath += libPath + ";";
      } else if(libraryPath.endsWith(".")) {
        libraryPath = libraryPath.substring(0, libraryPath.length() -1);
        
        if(libraryPath.endsWith(";")) {
          libraryPath += libPath + ";";
        } else {
          libraryPath += ";" + libPath + ";";
        }
      } else {
        libraryPath += ";" + libPath + ";";
      }
    } else {
      if(libraryPath.endsWith(":")) {
        libraryPath += libPath + ":";
      } else if(libraryPath.endsWith(".")) {
        libraryPath = libraryPath.substring(0, libraryPath.length() -1);
        
        if(libraryPath.endsWith(":")) {
          libraryPath += libPath + ":";
        } else {
          libraryPath += ":" + libPath + ":";
        }
      } else {
        libraryPath += ":" + libPath + ":";
      }
    }
    
    System.setProperty("java.library.path", libraryPath);
    //System.out.println("java.library.path=" + libraryPath);
  }
  
}
