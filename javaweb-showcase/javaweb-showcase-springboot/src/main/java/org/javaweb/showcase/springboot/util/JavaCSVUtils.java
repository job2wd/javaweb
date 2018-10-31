/*
 * $Id: JavaCSVUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaCSVUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2018年6月20日 下午5:40:25$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaCSVUtils {
  
  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final Character DEFAULT_CELL_SEPARATOR = ',';

  public static List<Map<String, String>> read(String csvFile) {
    return read(csvFile, DEFAULT_CELL_SEPARATOR, DEFAULT_CHARSET);
  }
  
  public static List<Map<String, String>> read(String csvFile, Character cellSeparator, String charset) {
    List<Map<String, String>> res = new ArrayList<Map<String, String>>();
    CsvReader csvReader = null;
    
    try {
      csvReader = new CsvReader(csvFile, cellSeparator, Charset.forName(charset));
      
      String[] headers = null;
      
      if(csvReader.readHeaders()) {
        headers = csvReader.getHeaders();
      }
      
      while(csvReader.readRecord()) {
        String[] values = csvReader.getValues();
        Map<String, String> map = new LinkedHashMap<String, String>();
        
        for(String header : headers) {
          map.put(header, values[csvReader.getIndex(header)]);
        }
        
        res.add(map);
      }
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if(csvReader != null){
        csvReader.close();
      }
    }
    
    return res;
  }
  
  public static void write(String[] headers, List<String[]> datas, String destCsvFile) {
    write(headers, datas, destCsvFile, DEFAULT_CELL_SEPARATOR, DEFAULT_CHARSET);
  }
  
  public static void write(String[] headers, List<String[]> datas, String destCsvFile, Character cellSeparator, String charset) {
    CsvWriter csvWriter = null;
    
    try {
      FileUtils.forceMkdirParent(new File(destCsvFile));
      
      csvWriter = new CsvWriter(destCsvFile, cellSeparator, Charset.forName(charset));
      
      if(headers != null && headers.length > 0) {
        csvWriter.writeRecord(headers);
      }
            
      for(String[] rowData : datas) {
        csvWriter.writeRecord(rowData);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if(csvWriter != null) {
        csvWriter.close();
      }
    }
    
  }
  
  public static void main(String[] args) {
    //testRead();
    testWrite();
  }

  private static void testRead() {
    List<Map<String, String>> res = JavaCSVUtils.read("D:\\HAPR\\medicine_info.csv", ',', "GBK");
    
    for(int i = 0; i < res.size(); i++) {
      Map<String, String> m = res.get(i);
      Set<String> headers = m.keySet();
      
      if(i == 0) {
        for(String header : headers) {
          System.out.print("  " + header + "  ");
        }
        System.out.println();
      }
      
      for(String header : headers) {
        System.out.print("  " + m.get(header) + "  ");
      }
      
      System.out.println();
    }
  }
  
  private static void testWrite() {
    List<Map<String, String>> res = JavaCSVUtils.read("D:\\HAPR\\medicine_info.csv", ',', "GBK");
    
    System.out.println(res.size());
    
    String[] headers = null;//{"medicine_name", "medicine_code", "pin_yin", "medicine_type", "feature", "smell", "gui_jing", "functions", "poison", "dose"};
    List<String[]> datas = new ArrayList<String[]>();
    
    for(int i = 0; i < res.size(); i++) {
      Map<String, String> m = res.get(i);
            
      if(i == 0) {
        Set<String> _headers = m.keySet();
        headers = new String[_headers.size()];
        headers = _headers.toArray(headers);
      }
      
      int index = 0;
      String[] d = new String[headers.length];
      
      for(String header : headers) {
        d[index++] = m.get(header);
      }
      
      datas.add(d);
    }
    
    System.out.println(datas.size());
    
    JavaCSVUtils.write(headers, datas, "D:/test/test.csv", ',', "GBK");
    
    System.out.println("OK!");
  }
  
}
