/**
 * $Id: CharsetCovertTest.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.util;

import junit.framework.TestCase;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class CharsetCovertTest extends TestCase {

  private CharsetConvert covert;
  /**
   * @param name
   */
  public CharsetCovertTest(String name) {
    super(name);
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    covert = new CharsetConvert();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test method for {@link maven.java.util.CharsetConvert#utf8CovertUnicode(java.lang.String)}.
   */
  public final void testUtf8CovertUnicode() {
    String utf8Str = "UTF-8字符";
    String unicodeStr = covert.utf8CovertUnicode(utf8Str);
    
    super.assertEquals("\\u4EA7\\u751F\\u7ED3\\u679C".toLowerCase(), unicodeStr.toLowerCase());
  }

  /**
   * Test method for {@link maven.java.util.CharsetConvert#unicodeCovertUtf8(java.lang.String)}.
   */
  public final void testUnicodeCovertUtf8() {
    String unicodeStr = "\u5BFC\u5165\u7AD9\u70B9\u8BC1\u4E66\u4E0D\u5141\u8BB8\u4E3A\u7A7A\\!";
    String utf8Str = covert.unicodeCovertUtf8(unicodeStr);
    
    super.assertEquals("Unicode to covert UTF-8!", utf8Str);
    //System.out.println("Unicode \n " + unicodeStr + " \n to \n UTF-8 \n is : \n" + utf8Str);
    
    //System.out.println("Unicode '\u5BFC\u5165\u7AD9\u70B9' new String() is : \n" + new String("\u5BFC\u5165\u7AD9\u70B9"));
  }

  public final void testUnicode2Utf8BaseString() {
    String bs = "\u786E\u5B9A\u6D4B\u8BD5\u8FDE\u63A5?\\n\u6CE8\u610F\uFF1A\u6D4B\u8BD5\u8FDE\u63A5\u53EF\u80FD\u8981\u82B1\u8D39\u4E00\u5B9A\u65F6\u95F4\uFF0C\u60A8\u9700\u8981\u8010\u5FC3\u7B49\u5F85";
    System.out.println(bs);
  }
  
}
