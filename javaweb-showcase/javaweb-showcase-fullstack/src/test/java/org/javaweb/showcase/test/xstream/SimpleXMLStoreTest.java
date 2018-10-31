/**
 * $Id: SimpleXMLStoreTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.xstream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Wang WD
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class SimpleXMLStoreTest {

  private String file;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    file = this.getClass().getResource("/store/simpleBeanXStream.xml").getFile();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testStore() throws Exception {
    SimpleJavaBean bean = new SimpleJavaBean();
    bean.setId("0");
    bean.setName("Root");
    bean.setParentNode(null);

    SimpleJavaBean child0001 = new SimpleJavaBean();
    child0001.setId("0001");
    child0001.setName("Node001");
    child0001.setParentNode("0");

    SimpleJavaBean child0002 = new SimpleJavaBean();
    child0002.setId("0002");
    child0002.setName("Node002");
    child0002.setParentNode("0");

    SimpleJavaBean child00010001 = new SimpleJavaBean();
    child00010001.setId("00010001");
    child00010001.setName("Node00010001");
    child00010001.setParentNode("0001");
    child0001.addChild(child00010001);

    SimpleJavaBean child0003 = new SimpleJavaBean();
    child0003.setId("0003");
    child0003.setName("Node0003");
    child0003.setParentNode("0");

    bean.addChild(child0001);
    bean.addChild(child0002);
    bean.addChild(child0003);

    SimpleXMLStore store = new SimpleXMLStore(file, bean);
    store.store();
  }

  @Test
  public void testLoad() throws Exception {
    //    SimpleJavaBean bean = new SimpleJavaBean();
    //    SimpleXMLStore store = new SimpleXMLStore(file, bean);
    //    store.store();
    SimpleXMLStore store = new SimpleXMLStore(file);
    store.load();
    SimpleJavaBean fetch_bean = store.getBean();

    Assert.assertNotNull(fetch_bean);
    //Assert.assertEquals(bean, fetch_bean);
  }
}
