/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.common.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;


/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年5月2日 下午2:34:48$
 * @LastChanged $Author:$, $Date::                    #$
 */
public class MD5Test extends BaseJunitTest {

  @Test
  public void testMD5Password() {
    String md5Password = DigestUtils.md5Hex("123456");
    log("123456 MD5:" + md5Password);
  }
  
}
