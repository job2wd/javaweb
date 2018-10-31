/**
 * $Id: ReaderConvertByteArrayTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import java.io.StringReader;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class ReaderConvertByteArrayTest extends TestCase {

  /**
   * @param name
   */
  public ReaderConvertByteArrayTest(String name) {
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
  
  public void testToByteArray() throws Exception {
    ReaderConvertByteArray converter = new ReaderConvertByteArray();
    byte[] b = converter.toByteArray(new StringReader("aaa"));
    
    assertNotNull("Reader convert to byte array", b);
    
    assertEquals("Reader convert to byte array", "aaa", new String(b));
  }

  public void testToString() throws Exception {
    ReaderConvertByteArray converter = new ReaderConvertByteArray();
    
    String res = converter.toString(new StringReader("aaa"));
    
    assertNotNull("Reader convert to String", res);
    
    assertEquals("Reader convert to String", "aaa", res);
  }
  
  public void testCommonsIOUtils() throws Exception {
    byte[] b = IOUtils.toByteArray(new StringReader("aaa"), "UTF-8");
    
    assertNotNull("Reader convert to byte array", b);
    
    assertEquals("Reader convert to byte array", "aaa", new String(b));
    
  }
}
