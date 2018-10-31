/**
 * $Id: URL4AppTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.net.URL;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class URL4AppTest extends TestCase {

  /**
   * @param name
   */
  public URL4AppTest(String name) {
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

  /**
   * Test method for {@link maven.java.URL4App#getURL(java.lang.String)}.
   */
  public final void testGetURL() throws Exception{
    URL4App ua = new URL4App();
    String name = "";
    URL url = ua.getURL(name);
    
    assertNotNull("url is null",url);
    
    System.out.println("autority:     " + url.getAuthority());
    System.out.println("default port: " + url.getDefaultPort());
    System.out.println("file:         " + url.getFile());
    System.out.println("host:         " + url.getHost());
    System.out.println("path:         " + url.getPath());
    System.out.println("port:         " + url.getPort());
    System.out.println("protocol:     " + url.getProtocol());
    System.out.println("query:        " + url.getQuery());
    System.out.println("ref:          " + url.getRef());
    System.out.println("userinfo:     " + url.getUserInfo());
    System.out.println("content:      " + url.getContent().toString());
    System.out.println("class name:   " + url.getClass().getName());
  }

}
