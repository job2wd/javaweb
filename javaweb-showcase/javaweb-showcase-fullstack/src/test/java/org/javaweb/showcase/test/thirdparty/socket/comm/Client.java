package org.javaweb.showcase.test.thirdparty.socket.comm;

import java.io.InputStream;


/**
 * Socket client interface.
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-9-14 下午4:07:55$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public interface Client {

  void connect();
  
  void send(byte[] data);
  
  void send(InputStream is);
  
}
