package org.javaweb.showcase.test.chaos;

import org.javaweb.showcase.util.ObjectToStringUtils;



public class Class101 extends Class1 {

  private int a01;
  private String s01;
  
  public int getA01() {
    return a01;
  }
  
  public void setA01(int a01) {
    this.a01 = a01;
  }
  
  public String getS01() {
    return s01;
  }
  
  public void setS01(String s01) {
    this.s01 = s01;
  }
  
  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }
  
}
