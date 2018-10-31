/*
 * $Id: ObjectToStringUtils.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/ObjectToStringUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 该类用于实现了 Object 类的 {@link #toString()} 方法中调用，
 * 用来构造对象中所有提供了 getXxxx() 方法的属性名称及其值。使用了 java 反射机制。
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2014-9-1 下午3:23:18$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public final class ObjectToStringUtils {

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
