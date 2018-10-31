/*
 * $Id: LibraryUtils.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/LibraryUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;



/**
 * 系统用到的动态链接库（Windows 为  *.dll，Linux 为 *.so）加载工具类。
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-4-1 下午3:58:37$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class LibraryUtils {

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
