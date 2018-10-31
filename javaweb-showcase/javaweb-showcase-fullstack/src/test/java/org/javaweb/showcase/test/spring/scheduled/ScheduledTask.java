/*
 * $Id: ScheduledTask.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/spring/scheduled/ScheduledTask.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.showcase.test.spring.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


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
@EnableScheduling
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
