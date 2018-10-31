/*
 * $Id: SystemInitializingProcessor.java 5169 2018-08-14 01:52:26Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/common/SystemInitializingProcessor.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.data.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/**
 *
 * @author wangwd
 * @version $Revision: 5169 $, $Date: 2018年5月2日 上午9:32:01$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-14 09:52:26#$
 */
@Component
public class SystemInitializingProcessor implements InitializingBean {//BeanPostProcessor

  private static final Logger log = LoggerFactory.getLogger(SystemInitializingProcessor.class);

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("System initializing start....");

    log.debug("System initializing end.");
  }

}
