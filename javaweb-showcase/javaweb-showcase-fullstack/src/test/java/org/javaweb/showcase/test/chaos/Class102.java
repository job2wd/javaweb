package org.javaweb.showcase.test.chaos;

import org.javaweb.showcase.util.ObjectToStringUtils;



public class Class102 extends Class101 {

  private int a102;
  private String s102;
  
  private Class101 class101;
  
  public int getA102() {
    return a102;
  }
  
  public void setA102(int a102) {
    this.a102 = a102;
  }
  
  public String getS102() {
    return s102;
  }
  
  public void setS102(String s102) {
    this.s102 = s102;
  }

  public Class101 getClass101() {
    return class101;
  }

  public void setClass101(Class101 class101) {
    this.class101 = class101;
  }
  
  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }
  
}
