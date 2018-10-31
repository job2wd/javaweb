/*
 * $Id: CacheComplexKey4HelloWord.java 134 2017-02-06 06:14:28Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/simple/impl/CacheComplexKey4HelloWord.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple.impl;

import org.apache.commons.lang3.StringUtils;
import org.javaweb.data.service.cache.simple.CacheComplexKey;


/**
 * 
 * @author wangwd
 * @version $Revision: 134 $, $Date: 2016-4-21 下午3:56:33$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:14:28#$
 */
public class CacheComplexKey4HelloWord extends CacheComplexKey {

  public static final String KEY_NAME = "src_ip-dst_ip:dst_port";
  
  @Override
  public String getKeyName() {
    return KEY_NAME;
  }

  @Override
  public Object getKeyValue(Object pojo) {
    HelloWord p = (HelloWord)pojo;
    
    String key = p.getSrcIp() + "-" + p.getDesIp();
    
    if(StringUtils.isNotBlank(p.getDesPort())) {
      key += ":" + p.getDesPort();
    }
    
    return key;
  }

  public class HelloWord {
    private Integer id;
    private String name;
    private String srcIp;
    private String desIp;
    private String desPort;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSrcIp() {
      return srcIp;
    }
    
    public void setSrcIp(String srcIp) {
      this.srcIp = srcIp;
    }
    
    public String getDesIp() {
      return desIp;
    }
    
    public void setDesIp(String desIp) {
      this.desIp = desIp;
    }
    
    public String getDesPort() {
      return desPort;
    }
    
    public void setDesPort(String desPort) {
      this.desPort = desPort;
    }
    
  }
  
}
