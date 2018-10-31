/**
 * $Id: PropertiesTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class PropertiesTest extends TestCase {

  /**
   * @param name
   */
  public PropertiesTest(String name) {
    super(name);
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testValue2Upercase() throws Exception {
    String sourceFile = "D:/workspace/maven-test/src/test/resources/temp/lowercase.properties";
    String targetFile = "D:/workspace/maven-test/src/test/resources/temp/upercase.properties";

    Properties sp = new Properties();
    sp.load(new FileInputStream(sourceFile));
    
    Properties tp = new Properties();
    tp.load(new FileInputStream(targetFile));
    
    Enumeration<Object> sp_keys = sp.keys();
    String reg = " ";
    while (sp_keys.hasMoreElements()) {
      String key = (String)sp_keys.nextElement();
      String value = ((String)sp.get(key)).trim();
      
      String[] v_reg = value.split(reg);
      value = "";
      for (int i=0;i<v_reg.length;i++) {
        v_reg[i] = v_reg[i].substring(0, 1).toUpperCase() + v_reg[i].substring(1);
        value +=v_reg[i] + reg;
      }
      value = value.trim();
      tp.setProperty(key, value);
    }
    tp.store(new FileOutputStream(targetFile), "Upercase Result");
  }
  
  public void testPropertiesKeySort() throws Exception {
    String sourceFile = "D:/workspace/maven-test/src/test/resources/temp/upercase.properties";

    Properties sp = new Properties();
    sp.load(new FileInputStream(sourceFile));
    
    Object[] keys = sp.keySet().toArray();
    java.util.Arrays.sort(keys);
    
    for (Object key:keys) {
      System.out.println(key);
    }
    
  }
}
