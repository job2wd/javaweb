/*
 * $Id: JavaZipUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaZipUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;


/**
 * zip 文件压缩和解压缩工具类。
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2016-7-27 下午4:36:18$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaZipUtils {

  private static final int BUFFER_SIZE = 2048;

  /**
   * zip 文件压缩
   * 
   * @param srcFile 要压缩的源文件或目录
   * @param destZipFile 压缩后目的 zip 文件名称，可为 null。为 null 时压缩文件名为和参数 srcFile 相同的名称。
   * @return 压缩后的文件（如果 destZipFile 不为 null，则返回 destZipFile）
   */
  public static File zip(File srcFile, File destZipFile) {
    ZipArchiveOutputStream out = null;
    File dst = destZipFile;
    
    try {
      if(dst == null || dst.isDirectory()) {
        dst = new File(srcFile.getAbsolutePath() + ".zip");
      }
      
      if(!dst.getParentFile().exists()) {
        FileUtils.forceMkdir(dst.getParentFile());
      }
      
      out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE));
            
      String baseName = srcFile.getParent();
      
      addArchive(srcFile, out, baseName.endsWith(File.separator) ? baseName.length() : baseName.length() + 1);
      
      out.closeArchiveEntry();
    } catch(Exception e) {
      throw new RuntimeException(e);
    } finally {
      JavaIOUtils.close(out);
    }
    
    return dst;
  }
  
  private static void addArchive(File file, ZipArchiveOutputStream out, int baseNameLength) throws IOException {
    InputStream is = null;
    
    try {
      String entryName = file.getAbsolutePath().substring(baseNameLength);
            
      ZipArchiveEntry entry = new ZipArchiveEntry(file, entryName);
      
      entry.setSize(file.length());
      out.putArchiveEntry(entry);
      
      if(file.isFile()) {
        is = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
        IOUtils.copy(is, out);
      } else {
        File[] files = file.listFiles();
                
        for(File f : files) {
          addArchive(f, out, baseNameLength);
        }
      }
    } finally {
      JavaIOUtils.close(is);
    }
  }
  
  public static List<String> getInternalFilesName(File zipFile, String internalFileSuffix) {
    ZipArchiveInputStream is = null;
    List<String> fileNames = new ArrayList<String>();
    
    try {
      is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), JavaZipUtils.BUFFER_SIZE));
      ZipArchiveEntry entry = null;
      
      while ((entry = is.getNextZipEntry()) != null) {
        String name = entry.getName();
        if(name.toLowerCase().endsWith(internalFileSuffix.toLowerCase())) {
          fileNames.add(name);
        }
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    } finally {
      JavaIOUtils.close(is);
    }
    
    return fileNames;
  }
  
  public static List<String> getIinnerFileNames(File zipFile) {
    ZipArchiveInputStream is = null;
    List<String> names = new ArrayList<String>();
    
    try {
      is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), JavaZipUtils.BUFFER_SIZE));
      ZipArchiveEntry entry = null;
      
      while ((entry = is.getNextZipEntry()) != null) {
        names.add(entry.getName());
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    } finally {
      JavaIOUtils.close(is);
    }
    
    return names;
  }
  
  /**
   * zip 文件解压缩
   * 
   * @param zipFile zip 压缩文件
   * @return 解压缩后的文件存放目录（解压目录为和参数 zipFile 相同的目录）
   */
  public static File unzip(File zipFile) {
    return unzip(zipFile, null);
  }
  
  /**
   * zip 文件解压缩
   * 
   * @param zipFile zip 压缩文件
   * @param destDir 解压缩后文件存放目录，可为 null。为 null 时解压目录为和参数 zipFile 相同的目录。
   * @return 解压缩后的文件存放目录（如果 destDir 不为 null，则返回 destDir）
   */
  public static File unzip(File zipFile, File destDir) {
    ZipArchiveInputStream is = null;
    File dst = destDir;
    
    try {
      if(dst == null || dst.isFile()) {
        dst = zipFile.getParentFile();
      }
      
      if(!dst.exists()) {
        dst.mkdir();
      }
      
      is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), JavaZipUtils.BUFFER_SIZE));
      ZipArchiveEntry entry = null;
      
      while ((entry = is.getNextZipEntry()) != null) {
        File file = new File(dst, entry.getName());
        
        if (entry.isDirectory()) {
          file.mkdirs();
        } else {
          OutputStream os = null;
          try {
            os = new BufferedOutputStream(new FileOutputStream(file), BUFFER_SIZE);
            IOUtils.copy(is, os);
          } finally {
            JavaIOUtils.close(os);
          }
        }
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    } finally {
      JavaIOUtils.close(is);
    }
    
    return dst;
  }
  
  //zip,rar,tar,gzip,war,tgz
  public static boolean isZipFile(String fileName) {
    if(fileName.toLowerCase().endsWith(".zip")) {
      return true;
    }
    return false;
  }
  
  public static boolean isZipFile(File file) {
    if(!file.isFile()) {
      return false;
    }
    
    if(file.getName().toLowerCase().endsWith(".zip")) {
      return true;
    }
    
    return false;
  }
  
}
