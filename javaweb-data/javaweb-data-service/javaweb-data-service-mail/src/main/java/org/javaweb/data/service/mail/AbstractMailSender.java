/**
 * Project Name: spring-mail-showcase
 * Created Package Name: org.spring.mail
 * Created File Name: AbstractMailSendService.java
 * $Id: AbstractMailSender.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/mail/AbstractMailSender.java $
 * Copyright (c) 2013 Company, nationsky. All Rights Reserved.
 */
package org.javaweb.data.service.mail;

import org.springframework.mail.MailMessage;



/**
 * TODO
 *
 * @Author wangweidong
 * @CreatedDate 2013-11-25 下午3:59:18
 * @Version $Revision: 80 $
 * @LastChangedBy $Author: job2wd $
 * @LastChangedDate $LastChangedDate: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 * @LastChangedRevision $LastChangedRevision: 80 $
 * 
 */
public abstract class AbstractMailSender<T extends MailMessage> implements MailSender<T> {
	
}
