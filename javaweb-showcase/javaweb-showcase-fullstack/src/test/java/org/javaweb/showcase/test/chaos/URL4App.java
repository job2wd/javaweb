/**
 * $Id: URL4App.java 80 2017-01-20 04:21:11Z job2wd $
 */
package org.javaweb.showcase.test.chaos;

import java.net.URL;

/**
 * @author weidong_wang
 * @version $Revision: 80 $ $Date: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 */
public class URL4App {

  /**
   * 
   */
  public URL4App() {
    super();
  }

  public URL getURL(String name){
    return URL4App.class.getResource(name);
  }
}
