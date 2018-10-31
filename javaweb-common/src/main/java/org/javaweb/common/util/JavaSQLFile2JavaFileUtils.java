/*
 * $Id: JavaSQLFile2JavaFileUtils.java 6072 2018-09-29 05:50:11Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaSQLFile2JavaFileUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.javaweb.common.util.JavaFileUtils.FileLineReadCallback;

/**
 * <pre><code>
 * DROP TABLE IF EXISTS `book`;
   CREATE TABLE `book` (
    `id` int(10) unsigned NOT NULL auto_increment COMMENT 'ID',
    `name` varchar(10) default NULL COMMENT 'name',
    `student_id` int(10) NOT NULL,
    `teacher_id` int(10) NOT NULL,
  
    PRIMARY KEY  (`id1`),
    --PRIMARY KEY  (`student_id`,`teacher_id`),主键
  
    KEY `INDEX_NAME` USING BTREE (`name`),
    KEY `INDEX_ADDRESS_MOBILE` USING HASH (`address`,`mobile`),--索引
  
    CONSTRAINT `FORGIN_STUDENT_ID` FOREIGN KEY (`student_id`) REFERENCES `student` (`id1`) ON DELETE CASCADE ON UPDATE CASCADE,--外键
    CONSTRAINT `FORGIN_TEACHER_ID` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Book table';
 * </code></pre>
 * 
 * 
 * @author wangwd
 * @version $Revision: 6072 $, $Date: 2018年9月29日 上午10:07:45$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-29 13:50:11#$
 */
public class JavaSQLFile2JavaFileUtils {

  public static void covert(File sqlFile, String outputJavaFileDir) throws Exception {
    covert(sqlFile, "UTF-8", outputJavaFileDir);
  }
  
  public static void covert(File sqlFile, String charset, String outputJavaFileDir) throws Exception {
    JavaFileUtils.readFile(sqlFile, charset, new FileLineReadCallback() {

      private String tableName = "";
      private String column = "";
      
      private String javaClassName = "";
      private String javaClassComment = "";
      
      private String fieldName = "";
      private String fieldNameComment = "";
            
      private List<String> lines = new ArrayList<String>();
      private String lineEnding = "\n";
      private boolean append = false;
      
      private Map<String, String> fieldNameAndComments = new LinkedHashMap<String, String>();
      private Map<String, Class<?>> fieldNameAndClassType = new LinkedHashMap<String, Class<?>>();
      private Map<String, String> fieldNameAndTableCoumns = new LinkedHashMap<String, String>();
      
      @Override
      public void beforeReadLine() throws IOException {
        fieldName = "";
        fieldNameComment = "";
      }

      @Override
      public void readLine(long lineIndex, String line) throws IOException {
        line = line.trim();
        
        String regex = "`*`";
        
        if(line.startsWith("CREATE TABLE")) {
          Pattern pattern = Pattern.compile(regex);
          String[] tokens = pattern.split(line);
          tableName = tokens[1];///////////////
          
          if(tableName.contains("_")) {
            String[] tns = tableName.split("[_]");
            
            javaClassName = tns[0];
            
            for(int i = 1; i < tns.length; i++) {
              javaClassName += tns[i].substring(0, 1).toUpperCase() + tns[i].substring(1);
            }
          } else {
            javaClassName = tableName;
          }
          
          javaClassName = javaClassName.substring(0, 1).toUpperCase() + javaClassName.substring(1);
        } else if(line.startsWith(") ENGINE")) {
          regex = "'*'";
          Pattern pattern = Pattern.compile(regex);
          String[] tokens = pattern.split(line);
          javaClassComment = tokens[1];
                    
          lines.add("/*");
          lines.add(" * $" + "Id:" + "$");
          lines.add(" * $" + "HeadURL:" + "$");
          lines.add(" * Copyright (c) 2018 Company, HAPR. All Rights Reserved.");
          lines.add(" */");
          lines.add("package com.hapr.cmc.model;\n");
          lines.add("import com.fasterxml.jackson.annotation.JsonAlias;\n");
          lines.add("import com.hapr.cmc.annotation.TableEntity;");
          lines.add("import com.hapr.cmc.annotation.TableColumn;\n\n");
          lines.add("/**");
          lines.add(" * " + javaClassComment);
          lines.add(" * <p>表（" + tableName + "）映射实体类</p>");
          lines.add(" * ");
          lines.add(" * @author ${user}");
          lines.add(" * @version $" + "Revision:" + "$, $Date: "
              + JavaDateFormatUtils.FORMAT_yyyy_MM_dd_HH_mm_ss.format(new Date()) + "$");
          lines.add(" * @LastChanged $" + "Author:" + "$, $" + "Date::                    " + "#$");
          lines.add(" */");

          lines.add("@TableEntity(\"" + tableName + "\")");
          
          if(fieldNameAndClassType.containsKey("id")) {
            if(fieldNameAndClassType.get("id").equals(Integer.class)) {
              lines.add("public class " + javaClassName + " extends BaseEntity4IntegerKey {");
            } else if(fieldNameAndClassType.get("id").equals(Long.class)) {
              lines.add("public class " + javaClassName + " extends BaseEntity4LongKey {");
            } else {
              lines.add("public class " + javaClassName + " extends BaseEntity {");
            }
          } else {
            lines.add("public class " + javaClassName + " extends BaseEntity {");
          }
          
          //fieldGetMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
          //fieldSetMethod = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
          
          Set<String> fields = fieldNameAndComments.keySet();
          
          lines.add("");
          
          for(String field : fields) {//field
            if(field.equals("deleted") || field.equals("predefined")) {//BaseEntity 中已经有了
              continue;
            }
            
            lines.add("  /**");
            lines.add("   * " + fieldNameAndComments.get(field));
            lines.add("   */");
            
            lines.add("  @TableColumn(\"" + fieldNameAndTableCoumns.get(field) + "\")");
            
            if(field.equals("innerId")) {
              lines.add("  @JsonAlias(\"id\")");
            }
            if(field.equals("rfid")) {
              lines.add("  @JsonAlias(\"RFID\")");
            }
            
            lines.add("  private " + fieldNameAndClassType.get(field).getSimpleName() + " " + field + ";\n");
          }
          
          //lines.add("  //以下属性非表中字段，只用于维护对象信息之用。\n");
          
          for(String field : fields) {//method(getter/setter)
            if(field.equals("deleted")) {//BaseEntity 中已经有了
              continue;
            }
            
            lines.add("  /**");
            lines.add("   * " + fieldNameAndComments.get(field));
            lines.add("   * @return " + fieldNameAndComments.get(field));
            lines.add("   */");
            
            if(field.equals("id")) {
              if(fieldNameAndClassType.get("id").equals(Integer.class)) {
                lines.add("  @Override");
              } else if(fieldNameAndClassType.get("id").equals(Long.class)) {
                lines.add("  @Override");
              } else {
                //nothing
              }
            }
            
            lines.add("  public " + fieldNameAndClassType.get(field).getSimpleName() + " " +
                "get" + field.substring(0, 1).toUpperCase() + field.substring(1) + "() {");
            lines.add("    return " + field + ";");
            lines.add("  }\n");//getter
            
            lines.add("  /**");
            lines.add("   * " + fieldNameAndComments.get(field));
            lines.add("   * @param " + field + " " + fieldNameAndComments.get(field));
            lines.add("   */");
            
            if(field.equals("id")) {
              if(fieldNameAndClassType.get("id").equals(Integer.class)) {
                lines.add("  @Override");
              } else if(fieldNameAndClassType.get("id").equals(Long.class)) {
                lines.add("  @Override");
              } else {
                //nothing
              }
            }
            
            lines.add("  public void " +
                "set" + field.substring(0, 1).toUpperCase() + field.substring(1) + "(" +
                fieldNameAndClassType.get(field).getSimpleName() + " " + field + ") {");
            lines.add("    this." + field + " = " + field + ";");
            lines.add("  }\n");//setter
          }
          
          lines.add("  /**");
          lines.add("   * 初始化当前对象中的属性值");
          lines.add("   */");
          lines.add("  @Override");
          lines.add("  public void init() {");
          lines.add("    super.init();");
          lines.add("  }\n");
          
          lines.add("}");
          
          File outputJavaFile = new File(outputJavaFileDir + javaClassName + ".java");
          
          FileUtils.writeLines(outputJavaFile, charset, lines, lineEnding, append);
          
          initGloab();
          
          System.out.println("outputJavaFile: " + outputJavaFile);
        } else if(line.startsWith("PRIMARY KEY")){
          //nothing to to
        } else if(line.startsWith("DROP TABLE")) {
          //nothing to to
        } else {
          regex = "`*`";
          Pattern pattern = Pattern.compile(regex);
          String[] tokens = pattern.split(line);
          
          if(tokens.length > 1) {
            column = tokens[1];
            
            if(column.contains("_")) {
              String[] columns = column.split("[_]");
              
              fieldName = columns[0];
              
              for(int i = 1; i < columns.length; i++) {
                fieldName += columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1);
              }
            } else {
              fieldName = column;
            }
            
            regex = "^[A-Z]+.*";//以大写字母开头，任意字符结尾。
            pattern = Pattern.compile(regex);
            
            if(pattern.matcher(fieldName).matches()) {
              fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1, fieldName.length());
              /*if(fieldName.length() == 1) {
                fieldName = fieldName.toLowerCase();
              } else {
                fieldName = fieldName.substring(0, 2).toLowerCase() + fieldName.substring(2, fieldName.length());
              }*/
            }
            
            regex = "'*'";
            pattern = Pattern.compile(regex);
            tokens = pattern.split(line);
            if(tokens.length == 3) {
              fieldNameComment = tokens[1];
            } else if(tokens.length > 3) {
              fieldNameComment = tokens[3];
            }
            
            fieldNameAndComments.put(fieldName, fieldNameComment);
            fieldNameAndTableCoumns.put(fieldName, column);
            
            checkFileClassType(line);
          }
        }
        
      }
      
      private void checkFileClassType(String line) {
        line = line.toLowerCase();
        
        if(line.contains("bigint")) {
          fieldNameAndClassType.put(fieldName, Long.class);
        } else if (line.contains("varchar") || line.contains("text") || line.contains("char")) {
          fieldNameAndClassType.put(fieldName, String.class);
        } else if(line.contains("tinyint")) {
          fieldNameAndClassType.put(fieldName, Short.class);
        } else if(line.contains("float")) {
          fieldNameAndClassType.put(fieldName, Float.class);
        } else if(line.contains("double") || line.contains("decimal")) {
          fieldNameAndClassType.put(fieldName, Double.class);
        } else if((line.contains("date") || line.contains("datetime") || line.contains("time")) &&
            !line.contains("update")) {
          fieldNameAndClassType.put(fieldName, Date.class);
        } else if(line.contains("bit")) {
          fieldNameAndClassType.put(fieldName, Short.class);
        } else if (line.contains("int") || line.contains("integer") || line.contains("smallint")) {
          fieldNameAndClassType.put(fieldName, Integer.class);
        } else if(line.contains("decimal")) {
          fieldNameAndClassType.put(fieldName, Double.class);
        }//...add more
      }

      private void initGloab() {
        tableName = "";
        column = "";
        javaClassName = "";
        javaClassComment = "";
                  
        fieldNameAndComments = new LinkedHashMap<String, String>();
        fieldNameAndClassType = new LinkedHashMap<String, Class<?>>();
        
        lines = new ArrayList<String>();
      }

      @Override
      public void afterReadLine() throws IOException {
      }

      @Override
      public void afterReadFile() {
      }

      @Override
      public void beforeReadFile() {
      }
      
    });
  }
  
  public static void main(String[] args) {
    try {
      JavaSQLFile2JavaFileUtils.covert(new File("D:/temp/herbal_rule.sql"), "UTF-8", "D:/");
    } catch (Exception e) {
    }
  }
  
}
