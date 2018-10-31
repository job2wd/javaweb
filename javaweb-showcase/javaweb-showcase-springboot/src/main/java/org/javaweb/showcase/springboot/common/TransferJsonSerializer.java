/*
 * $Id: TransferJsonSerializer.java 3818 2018-05-29 04:03:26Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/TransferJsonSerializer.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * 
 * @author wangwd
 * @version $Revision: 3818 $, $Date: 2018年5月29日 上午10:25:10$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-29 12:03:26#$
 */
public class TransferJsonSerializer extends JsonSerializer<Object> {

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString("");
  }

}
