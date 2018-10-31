package org.javaweb.showcase.test.thirdparty.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyAnnotationBean {

  private String name;
  
  @JsonProperty("myAge")
  private int age;
  
  @JsonIgnore
  public String internal;

  // without annotation, we'd get "theName", but we want "name":
  @JsonProperty("myName")
  public String getName() {
    return name;
  }

  // note: it is enough to add annotation on just getter OR setter;
  // so we can omit it here
  public void setName(String n) {
    name = n;
  }

  
  public int getAge() {
    return age;
  }

  
  public void setAge(int age) {
    this.age = age;
  }

}
