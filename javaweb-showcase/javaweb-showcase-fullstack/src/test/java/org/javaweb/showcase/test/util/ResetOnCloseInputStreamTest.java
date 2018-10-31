/**
 * $Id: ResetOnCloseInputStreamTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class ResetOnCloseInputStreamTest extends TestCase {

  /**
   * @param name
   */
  public ResetOnCloseInputStreamTest(String name) {
    super(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  private void closeAfterInputStreamIsConsumed(InputStream is) throws IOException {
    int len = -1;
    byte[] buf = new byte[512];
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    while ((len = is.read(buf)) != -1) {
      out.write(buf, 0, len);
      out.flush();
      System.out.println(len + " " + new String(out.toByteArray()));
    }
    is.close();
    if (is instanceof ResetOnCloseInputStream) {
      ((ResetOnCloseInputStream) is).close(true);
    }
    
    out.close();
    System.out.println("=========");
  }

  public void testResetOnCloseInputStream() throws Exception {
    InputStream is = new ByteArrayInputStream("sample".getBytes());
    ResetOnCloseInputStream decoratedIs = new ResetOnCloseInputStream(is);
    closeAfterInputStreamIsConsumed(decoratedIs);
    closeAfterInputStreamIsConsumed(decoratedIs);
    closeAfterInputStreamIsConsumed(is);
    closeAfterInputStreamIsConsumed(is);
  }

}
