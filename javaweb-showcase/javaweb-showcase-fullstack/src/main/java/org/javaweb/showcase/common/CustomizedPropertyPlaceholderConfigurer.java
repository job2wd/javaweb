/*
 * $Id: CustomizedPropertyPlaceholderConfigurer.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/common/CustomizedPropertyPlaceholderConfigurer.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.common;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;


/**
 * 自定义 PropertyPlaceholderConfigurer，将 properties 文件中的内容存入 {@link DiConfig} Map 中，供其他地方直接使用配置信息。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-12-24 下午6:26:15$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

  @Override
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
    super.processProperties(beanFactoryToProcess, props);
    
    Set<Object> keySet = props.keySet();
    for (Object key : keySet) {
      String keyStr = (String)key;
      PropertiesMapHelper.put((String)key, props.getProperty(keyStr));
    }
  }

  @Override
  public void setLocations(Resource... locations) {
    super.setLocations(locations);
    try {
      for (Resource r : locations) {
        PropertiesMapHelper.putPropertiesFile(r.getFilename(), r.getFile());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
}
