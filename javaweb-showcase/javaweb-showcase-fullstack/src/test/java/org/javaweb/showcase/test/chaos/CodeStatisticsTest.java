/*
 * $Id: CodeStatisticsTest.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/CodeStatisticsTest.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.javaweb.showcase.test.BaseJunitTest;
import org.junit.Test;



/**
 * 代码行数统计测试类。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-9-22 下午8:33:46$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class CodeStatisticsTest  extends BaseJunitTest {

//记录注释行数
  static long annotationLine = 0;
    
  // 记录空白行数
  static long blankLine = 0;
    
  // 记录有效代码的行数
  static long codeLine = 0;
    
  // 代码总行数
  static long totalLine = 0;
    
  // 文件总数
  static long fileCount = 0;

  
  @Test(timeout=100000L)
  public void testCodeStatistics() throws Exception {
    System.out.println("请输入要统计代码量的java文件或java目录（输入exit退出）：");
    
    Scanner in = new Scanner(System.in);
    String filePath = "";
    File file = null;
    
    while(in.hasNextLine()) {
      filePath = in.nextLine();
      
      if(StringUtils.isBlank(filePath)) {
        System.out.println("请输入要统计代码量的java文件或java目录（输入exit退出）：");
      } else {
        if("exit".equals(filePath)) {
          System.exit(0);
        }
        file = new File(filePath);
        
        if(file.isDirectory() || file.isFile()) {
          break;
        } else {
          System.out.println("请输入合法的文件或目录路径！（输入exit退出）");
        }
      }
    }
    //String filePath = in.nextLine();
      
    //File file = new File(filePath);
    // 根据用户输入的文件名和目录执行代码量统计
    codeStat(file);
      
    System.out.println("－－－－－－－－－－统计结果－－－－－－－－－");
    System.out.println("文件数量：" + fileCount + "个");
    System.out.println(file + "文件/目录总行数：" + totalLine);
    System.out.println("代码行数：" + codeLine);
    System.out.println("注释行数：" + annotationLine);
    System.out.println("空白行数：" + blankLine);
    long otherLine = totalLine - (codeLine + annotationLine + blankLine);
    System.out.println("其它行数：" + otherLine);
    
  }
  
  private static void codeStat(File file) throws FileNotFoundException {
    if (file == null || !file.exists()) {
      throw new FileNotFoundException(file + "，文件不存在！");
    }
      
    fileCount ++;   // 文件数累加
      
    if (file.isDirectory()) {
        File[] files = file.listFiles(new FileFilter() {
              
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java") || pathname.isDirectory();
            }
        });
          
        for (File target : files) {
            codeStat(target);
        }
    } else {
        BufferedReader bufr = null;
        try {
            // 将指定路径的文件与字符流绑定
            bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file + "，文件不存在！" + e);
        }
          
        // 定义匹配每一行的正则匹配器
        Pattern annotationLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
                Pattern.MULTILINE + Pattern.DOTALL);    // 注释匹配器(匹配单行、多行、文档注释)
          
        Pattern blankLinePattern = Pattern.compile("^\\s*$");   // 空白行匹配器（匹配回车、tab键、空格）
          
        Pattern codeLinePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
                Pattern.MULTILINE + Pattern.DOTALL); // 代码行匹配器（以分号结束为一行有效语句,但不包括import和package语句）
          
        // 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
        String line = null;
        try {
            while((line = bufr.readLine()) != null) {
                if (annotationLinePattern.matcher(line).find()) {
                    annotationLine ++;
                }
                  
                if (blankLinePattern.matcher(line).find()) {
                    blankLine ++;
                }
                  
                if (codeLinePattern.matcher(line).matches()) {
                    codeLine ++;
                }
                  
                totalLine ++;
            }
              
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！" + e);
        } finally {
            try {
                bufr.close();   // 关闭文件输入流并释放系统资源
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }
        }
    }
}
  
}
