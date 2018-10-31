/**
 * $Id: TestEgex.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class TestEgex {

  /**
   * 
   */
  public TestEgex() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    String str = "For my money, the important thing ";
    String regEx = "an++";
    boolean result = Pattern.compile(regEx).matcher(str).find();
    System.out.println(result);

    loclaLanage();
    
  }

  public static void loclaLanage() {
    Locale[] locale = Locale.getAvailableLocales();
    for (Locale loc : locale) {
      // 国家名：国家的代码
      System.out.println(loc.getDisplayCountry() + "：" + loc.getCountry());
    }
    System.out.println("------------");
    for (Locale loc : locale) {
      // 语言名：语言代码
      System.out.println(loc.getDisplayLanguage() + "：" + loc.getLanguage());
    }
  }
  
 
}
