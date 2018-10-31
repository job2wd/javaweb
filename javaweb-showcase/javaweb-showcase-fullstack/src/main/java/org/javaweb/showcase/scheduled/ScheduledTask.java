/*
 * $Id: ScheduledTask.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/scheduled/ScheduledTask.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * spring scheduling test<br>
 * 
 * https://spring.io/guides<br>
 * https://spring.io/guides/gs/scheduling-tasks/
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2015-1-30 下午4:01:27$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
//@EnableScheduling
@Component
public class ScheduledTask {

  private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
  
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
      log.debug("Now time: {}", dateFormat.format(new Date()));
  }
  
}
