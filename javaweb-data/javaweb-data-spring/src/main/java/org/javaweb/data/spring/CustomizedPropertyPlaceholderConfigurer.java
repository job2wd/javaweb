/*
 * $Id: CustomizedPropertyPlaceholderConfigurer.java 3596 2018-05-18 08:10:27Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/CustomizedPropertyPlaceholderConfigurer.jav $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.data.spring;

import java.util.Properties;
import java.util.Set;

import org.javaweb.common.PropertiesMapHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;


/**
 * 自定义 PropertyPlaceholderConfigurer，将 properties 文件中的内容存入 {@link DiConfig} Map 中，供其他地方直接使用配置信息。
 * 
 * @author wangwd
 * @version $Revision: 3596 $, $Date: 2014-12-24 下午6:26:15$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-05-18 16:10:27#$
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
