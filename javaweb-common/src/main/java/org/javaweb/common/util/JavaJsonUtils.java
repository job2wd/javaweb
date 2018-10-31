/*
 * $Id: JavaJsonUtils.java 5959 2018-09-20 05:15:03Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaJsonUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author wangwd
 * @version $Revision: 5959 $, $Date: 2018年8月24日 上午9:46:19$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:15:03#$
 */
public class JavaJsonUtils {
  
  @SuppressWarnings("unchecked")
  public static List<Map<String, Object>> toListMap(String jsonString) {
    if (StringUtils.isBlank(jsonString)) {
      return new ArrayList<Map<String, Object>>();
    }
    
    try {
      ObjectMapper mapper = new ObjectMapper();
      
      //DeserializationConfig config = mapper.getDeserializationConfig();
      //mapper.setConfig(config.with(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
      //mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
      
      JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Map.class);
      
      List<Map<String, Object>> res = (List<Map<String, Object>>) mapper.readValue(jsonString, javaType);
      
      return res;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static <T> T toObject(String jsonString, Class<T> clazz) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(jsonString, clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static <T> List<T> toObjectList(String jsonString, Class<T> clazz) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      
      JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
      
      return mapper.readValue(jsonString, javaType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static String toJsonString(Object o) {
    if (o == null) {
      return null;
    }
    
    try {
      ObjectMapper mapper = new ObjectMapper();
      
      return mapper.writeValueAsString(o);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
    
  /**
   * json 字符串压缩，去除回车换行等，将其压缩为一行。
   */
  public static String compress(String json) {
    String _json = StringUtils.trimToNull(json);
    
    if (StringUtils.isBlank(_json)) {
      return _json;
    }
    
    StringBuilder sb = new StringBuilder();
    boolean skip = true;// true 允许截取(表示未进入string双引号)
    boolean escaped = false;// 转义符
    
    for (int i = 0; i < _json.length(); i++) {
      char c = _json.charAt(i);
      
      if (!escaped && c == '\\') {
        escaped = true;
      } else {
        escaped = false;
      }
      
      if (skip) {
        if (c == ' ' || c == '\r' || c == '\n' || c == '\t') {
          continue;
        }
      }
      
      sb.append(c);
      
      if (c == '"' && !escaped) {
        skip = !skip;
      }
    }
    
    return sb.toString().replaceAll("\r\n", "\\\\r\\\\n");
  }
  
}
