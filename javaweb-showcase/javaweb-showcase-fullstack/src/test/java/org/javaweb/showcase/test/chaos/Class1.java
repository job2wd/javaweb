package org.javaweb.showcase.test.chaos;

import org.javaweb.showcase.model.BaseEntity;
import org.javaweb.showcase.util.ObjectToStringUtils;


public class Class1 extends BaseEntity {

  private static final long serialVersionUID = -4506402400769806758L;

  private Integer id;
  private int a;
  private String s;
  
  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public int getA() {
    return a;
  }
  
  public void setA(int a) {
    this.a = a;
  }
  
  public String getS() {
    return s;
  }
  
  public void setS(String s) {
    this.s = s;
  }
  
  @Override
  public String toString() {
    return ObjectToStringUtils.toString(this);
  }
  
}
