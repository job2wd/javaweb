package org.javaweb.showcase.test.chaos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

  /**
   * Create the test case
   * 
   * @param testName
   *          name of the test case
   */
  public AppTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {
    assertTrue(true);
  }

  public void testRetrieveJavaSystemProperties() {
    App app = new App();
    int num = app.retrieveJavaSystemProperties();

    assertEquals(true, num > 0 ? true : false);
  }

  public void testRetrieveJavaSystemEnv() {
    App app = new App();
    int num = app.retrieveJavaSystemEnv();

    assertEquals(true, num > 0 ? true : false);
  }

}
