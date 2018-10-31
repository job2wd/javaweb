/**
 * $Id: LoggingInfo.java 80 2017-01-20 04:21:11Z job2wd $
 * 
 * @mail job2wd@gmail.com
 */
package org.javaweb.showcase.test.serialization;

import java.util.Date;

/**
 * @author David
 * @version 1.0
 * @date 2012-4-24 下午03:58:09
 */
public class LoggingInfo implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7714221168963339948L;

  private Date loggingDate = new Date();
  private String uid;
  private String pwd;

  public LoggingInfo(String user, String password) {
    uid = user;
    pwd = password;
  }

  public String toString() {
    return "logon info: \n   " + "user: " + uid + "\n   logging date : " + loggingDate.toString() + "\n   password: " + pwd;
  }

}
