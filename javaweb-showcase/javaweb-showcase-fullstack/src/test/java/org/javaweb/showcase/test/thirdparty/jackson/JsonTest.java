/*
 * $Id: JsonTest.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/thirdparty/jackson/JsonTest.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.test.thirdparty.jackson;

import org.javaweb.showcase.test.BaseJunitTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * https://github.com/FasterXML/jackson-databind/
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-1-14 下午7:34:44$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class JsonTest extends BaseJunitTest {

  private ObjectMapper mapper;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    //PRINT_STATISTICS_LOG = false;
  }
  
  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    
    mapper = new ObjectMapper(); // create once, reuse
    
    // SerializationFeature for changing how JSON is written

    // to enable standard indentation ("pretty-printing"):
    //mapper.enable(SerializationFeature.INDENT_OUTPUT);
 
   // to allow serialization of "empty" POJOs (no properties to serialize)
   // (without this setting, an exception is thrown in those cases)
   mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
   // to write java.util.Date, Calendar as number (timestamp):
   mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 
   // DeserializationFeature for changing how JSON is read as POJOs:

   // to prevent exception when encountering unknown property:
   mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
   // to allow coercion of JSON empty String ("") to null Object value:
   mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }
  
  @Test(timeout=10 * 1000L)
  public void testJacksonReadValue() throws Exception {
    MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        
    System.out.println("name:" + value.name + " age:" + value.age);
    
    org.junit.Assert.assertEquals("name is error", "Bob", value.name);
    org.junit.Assert.assertEquals("age is error", 13, value.age);
  }
  
  @Test(timeout=10 * 1000L)
  public void testJacksonWriteValue() throws Exception {
    MyValue value = new MyValue();
    value.age = 13;
    value.name = "Bob";
    
   String json = mapper.writeValueAsString(value);
   
   System.out.println("json:\n" + json);
   
   org.junit.Assert.assertEquals("json is error", "{\"name\":\"Bob\",\"age\":13}", json);
  }
  
  @Test(timeout=10 * 1000L)
  public void testJacksonAnnotaionWriteValue() throws Exception {
    MyAnnotationBean bean = new MyAnnotationBean();
    bean.setAge(13);
    bean.setName("Bob");
    bean.internal = "ignore";
    
    String json = mapper.writeValueAsString(bean);
    
    System.out.println("json:\n" + json);
    
    org.junit.Assert.assertEquals("json is error", "{\"myName\":\"Bob\",\"myAge\":13}", json);
  }
  
  @Test(timeout=10 * 1000L)
  public void testJacksonAnnotaionReadValue() throws Exception {
    MyAnnotationBean bean = mapper.readValue("{\"myName\":\"Bob\",\"myAge\":13,\"internal\":\"ingore property 'internal'\"}",
        MyAnnotationBean.class);
    
    System.out.println("name:" + bean.getName() + " age:" + bean.getAge() + " internal:" + bean.internal);
    
    org.junit.Assert.assertEquals("name is error", "Bob", bean.getName());
    org.junit.Assert.assertEquals("age is error", 13, bean.getAge());
    org.junit.Assert.assertEquals("internal is error", null, bean.internal);
  }

}
