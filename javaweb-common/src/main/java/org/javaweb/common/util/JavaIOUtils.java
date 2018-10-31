/*
 * $Id: JavaIOUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaIOUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;


/**
 * 
 * @author wangwd
 * @version $Revision: 5096 $, $Date: 2018年7月6日 上午9:46:56$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaIOUtils extends IOUtils {

  public static void close(InputStream input) {
    if(input != null) {
      try {
        input.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  
  public static void close(OutputStream output) {
    if(output != null) {
      try {
        output.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
  
}
