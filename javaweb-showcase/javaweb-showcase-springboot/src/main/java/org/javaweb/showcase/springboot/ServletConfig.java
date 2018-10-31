/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot;

import java.nio.charset.Charset;
import java.time.Duration;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Session;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年9月5日 上午9:26:50$
 * @LastChanged $Author:$, $Date::                    #$
 */
//@Configuration
public class ServletConfig {
  
  //@Bean
  public TomcatServletWebServerFactory tomcatConfig() {
    TomcatServletWebServerFactory tomcatConfig = new TomcatServletWebServerFactory();
    
    tomcatConfig.setPort(80);
    tomcatConfig.setPort(443);
    tomcatConfig.setProtocol("http");
    tomcatConfig.setUriEncoding(Charset.forName("UTF-8"));
    
    Session session = new Session();
    session.setPersistent(false);
    session.setTimeout(Duration.ofMinutes(60));
    
    tomcatConfig.setSession(session);
    
    return tomcatConfig;
  }
}
