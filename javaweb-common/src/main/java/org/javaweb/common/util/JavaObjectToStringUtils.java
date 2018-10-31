/*
 * $Id: JavaObjectToStringUtils.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaObjectToStringUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 该类用于实现了 Object 类的 {@link #toString()} 方法中调用，
 * 用来构造对象中所有提供了 getXxxx() 方法的属性名称及其值。使用了 java 反射机制。
 * 
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2014-9-1 下午3:23:18$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public final class JavaObjectToStringUtils {

  public static String toString(Object o) {
    return toString(o, false);
  }
  
  public static String toThisString(Object o) {
    StringBuffer sb = new StringBuffer();
    
    sb.append("{");
    addValues(o, o.getClass(), sb);
    sb.append("}");
    
    return sb.toString();
  }
  
  private static String toStringViewClassName(Object o) {
    return toString(o, true);
  }
  
  private static String toThisStringViewClassName(Object o) {
    StringBuffer sb = new StringBuffer();
    
    Class<?> clazz = o.getClass();
    
    sb.append(clazz.getName());
    sb.append(": {");
    addValues(o, clazz, sb);
    sb.append("}");
    
    return sb.toString();
  }
  
  private static String toAllString(Object o) {
    return toString(o, false);
  }
  
  private static String toAllStringViewClassName(Object o) {
    return toString(o, true);
  }
  
  private static String toStringBySuperDepth(Object o, int superDepth) {
    StringBuffer sb = new StringBuffer();
    
    if(superDepth > 0) {
      sb.append("{");
      
      Class<?> clazz = o.getClass();
      for(int i = superDepth; i > 0; i--) {
        if(clazz == null || clazz.isAssignableFrom(Object.class)) {
          break;
        }
        addValues(o, clazz, sb);
        clazz = clazz.getSuperclass();
      }
      
      sb.append("}");
    }
    
    return sb.toString();
  }
  
  private static String toStringBySuperDepthViewClassName(Object o, int superDepth) {
    StringBuffer sb = new StringBuffer();
    
    if(superDepth > 0) {
      Class<?> clazz = o.getClass();
      for(int i = superDepth; i > 0; i--) {
        sb.append(clazz.getName()).append(": {");
        addValues(o, clazz, sb);
        sb.append("}");
        
        clazz = clazz.getSuperclass();
        if(clazz == null || clazz.isAssignableFrom(Object.class)) {
          break;
        }
        sb.append("\n");
      }
    }
    
    return sb.toString();
  }
  
  private static String toString(Object o, boolean viewClassName) {
    StringBuffer sb = new StringBuffer();
    
    Class<? extends Object> clazz = o.getClass();
    
    if(!viewClassName) {
      sb.append("{");
    }
    
    addValues(o, clazz, sb, viewClassName);
    
    sb.append("}");
    
    return sb.toString();
  }
  
  private static void addValues(Object o, Class<?> clazz, StringBuffer sb, boolean viewClassName) {
    if(viewClassName) {
      sb.append(clazz.getName()).append(": {");
    }
    
    addValues(o, clazz, sb);
    
    Class<?> superclass = clazz.getSuperclass();
    
    if(superclass == null || superclass.isAssignableFrom(Object.class)) {
      return;
    }
    
    if(viewClassName) {
      sb.append("}\n");
    } else {
      sb.append(", ");
    }
        
    addValues(o, superclass, sb, viewClassName);
  }
  
  private static void addValues(Object o, Class<?> clazz, StringBuffer sb) {
    Field[] fields = clazz.getDeclaredFields();
    int count = 1;
    int len = fields.length;

    for (int i = 0; i < len; i++) {
      Field f = fields[i];
      String fn = f.getName();

      String fnUpper = fn.substring(0, 1).toUpperCase() + fn.substring(1);

      Object v = null;
      try {
        Method m = clazz.getMethod("get" + fnUpper);
        v = m.invoke(o);
      } catch (NoSuchMethodException e) {
        count++;
        continue;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      sb.append("\"").append(fn).append("\"").append(":").append(v);

      if (count < len) {
        sb.append(", ");
      }

      count++;
    }
  }
  
}