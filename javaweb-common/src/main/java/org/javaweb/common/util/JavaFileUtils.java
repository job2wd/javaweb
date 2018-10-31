/*
 * $Id: JavaFileUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaFileUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.javaweb.common.util.JavaFileUtils.FileLineReadCallback;


/**
 * 文件处理工具类，扩展了类 {@link org.apache.commons.io.FileUtils org.apache.commons.io.FileUtils} 中没有的功能，如：按行读取文件并接受回调
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2015-3-19 下午1:12:03$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaFileUtils extends FileUtils {
  
  public static void readFile(File file, FileLineReadCallback callback) throws IOException {
    readFile(file, "UTF-8", callback);
  }
  
  /**
   * 以行为单位读取文件，适用于读面向行的格式化文件。<br>
   * <p>
   * 在每读取一行会回调一次 {@link org.javaweb.JavaFileUtils.util.FbaFileUtils.FileLineReadCallback#customProcess(long, String)
   *  org.javaweb.showcase.web.util.FbaFileUtils.FileLineReadCallback.processLine(long, String)} 方法
   * </p>
   * @param file 要按行读取的文件对象
   * @param callback 回调函数对象
   * @throws IOException
   */
  public static void readFile(File file, String charset, FileLineReadCallback callback) throws IOException {
    BufferedReader reader = null;
    try {
      callback.beforeReadFile();
      
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
      String line = "";
      long lineIndex = 0;
      
      while(true) {
        callback.beforeReadLine();
        
        line = reader.readLine();
        
        if(line == null) {
          break;
        }
        
        callback.readLine(lineIndex++, line);
        
        callback.afterReadLine();
      }
    } catch (IOException e) {
      throw e;
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    
    callback.afterReadFile();
  }
  
  public static void copyFile(String srcFileRealPath, String destFileRealPath) {
    try {
      FileUtils.copyFile(new File(srcFileRealPath), new File(destFileRealPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String getFileType(File file) {
    if(file.isDirectory()) {
      return null;
    }
    String name = file.getName();
    return name.substring(name.lastIndexOf("."), name.length());
  }
  
  public static String getFileType(String fileName) {
    return getFileType(new File(fileName));
  }
  
  /**
   * 文件按行读取时的回调接口
   * 
   * @author wangwd
   * @version $Revision: 5096 $, $Date: 2015-3-19 下午1:23:42$
   * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
   */
  public interface FileLineReadCallback {
    
    void afterReadFile();

    void beforeReadFile();
    
    /**
     * 读取全部文件内容之前会回调该方法，做一些前期预处理。
     * @throws IOException
     */
    void beforeReadLine() throws IOException;
    
    /**
     * 客户化处理每一行读取的内容，在每读取一行文件数据时会调用一次该方法，
     * 详见：{@link org.javaweb.JavaFileUtils.util.FbaFileUtils#readFile(File, FileLineReadCallback)
     *  org.javaweb.showcase.web.util.FbaFileUtils.readLines(File, FileLineReadCallback)}
     * @param lineIndex 当前读取行的索引（从 0 开始）
     * @param line 当前读取行的内容
     */
    void readLine(long lineIndex, String line) throws IOException;
    
    /**
     * 读取完全部文件内容之后会回调该方法，做一些后期处理。
     * @throws IOException
     */
    void afterReadLine() throws IOException;
    
  }
  
}
