/*
 * $Id: FileZipUtils.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/FileZipUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;


/**
 * zip 文件压缩和解压缩工具类。
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2016-7-27 下午4:36:18$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class FileZipUtils {

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
      if(dst == null) {
        dst = new File(srcFile.getAbsolutePath() + ".zip");
      }
      
      out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE));
            
      String baseName = srcFile.getParent();
      
      addArchive(srcFile, out, baseName.endsWith(File.separator) ? baseName.length() : baseName.length() + 1);
      
      out.closeArchiveEntry();
    } catch(Exception e) {
      throw new RuntimeException(e);
    } finally {
      IOUtils.closeQuietly(out);
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
      IOUtils.closeQuietly(is);
    }
  }
  
  /**
   * zip 文件解压缩
   * 
   * @param zipFile zip 压缩文件
   * @param destDir 解压缩后文件存放目录，可为 null。为 null 时解压目录为和参数 zipFile 相同的上级目录。
   * @return 解压缩后的文件存放目录（如果 destDir 不为 null，则返回 destDir）
   */
  public static File unzip(File zipFile, File destDir) {
    ZipArchiveInputStream is = null;
    File dst = destDir;
    
    try {
      if(dst == null) {
        dst = zipFile.getParentFile();
      }
      
      is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), FileZipUtils.BUFFER_SIZE));
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
            IOUtils.closeQuietly(os);
          }
        }
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    } finally {
      IOUtils.closeQuietly(is);
    }
    
    return dst;
  }
  
}
