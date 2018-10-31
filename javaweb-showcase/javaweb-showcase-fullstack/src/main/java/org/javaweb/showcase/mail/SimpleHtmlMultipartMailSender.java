/**
 * Project Name: spring-mail-showcase
 * Created Package Name: org.spring.mail
 * Created File Name: DefaultMailSendService.java
 * $Id: SimpleHtmlMultipartMailSender.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/mail/SimpleHtmlMultipartMailSender.java $
 * Copyright (c) 2013 Company, nationsky. All Rights Reserved.
 */
package org.javaweb.showcase.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * TODO
 * 
 * @Author wangweidong
 * @CreatedDate 2013-11-25 下午3:59:45
 * @Version $Revision: 80 $
 * @LastChangedBy $Author: job2wd $
 * @LastChangedDate $LastChangedDate: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 * @LastChangedRevision $LastChangedRevision: 80 $
 * 
 */
@Component("mailSender")
public class SimpleHtmlMultipartMailSender extends AbstractMailSender<SimpleHtmlMultipartMailMessage> {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static Logger log = LoggerFactory.getLogger(SimpleHtmlMultipartMailSender.class);

	@Autowired
	@Qualifier("defaultJavaMailSender")
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(SimpleHtmlMultipartMailMessage[] mailMessages) throws MailException {
		log.debug("Send simple Html Multipart mail....");
		try {
			MimeMessage[] mimeMessages = this.makeMimeMessages(mailMessages);

			javaMailSender.send(mimeMessages);
		} catch (Exception e) {
			throw new MailSendException(e.getMessage(), e.getCause());
		}
		log.debug("Sent simple Html Multipart mail");
	}

	private MimeMessage[] makeMimeMessages(SimpleHtmlMultipartMailMessage[] mailMessages) throws MessagingException,
	UnsupportedEncodingException {
		org.springframework.util.Assert.notEmpty(mailMessages, "mail messages must not be null and it must contain at least 1 element");
		org.springframework.util.Assert.noNullElements(mailMessages, "mail messages must not contain any null elements");

		int length = mailMessages.length;
		MimeMessage[] mimeMessages = new MimeMessage[length];

		for (int i = 0; i < length; i++) {
			SimpleHtmlMultipartMailMessage mailMessage = mailMessages[i];

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper messageHelper = this.smartNewMimeMessageHelper(mailMessage, mimeMessage);

			messageHelper.setFrom(mailMessage.getFrom(), mailMessage.getFromAlias());
			messageHelper.setTo(mailMessage.getTo());

			messageHelper.setSubject(mailMessage.getSubject());
			messageHelper.setText(mailMessage.getText(), true);

			this.addResources(mailMessage, messageHelper);

			this.addAttachments(mailMessage, messageHelper);

			mimeMessages[i] = mimeMessage;
		}

		return mimeMessages;
	}

	private MimeMessageHelper smartNewMimeMessageHelper(SimpleHtmlMultipartMailMessage mailMessage, MimeMessage mimeMessage)
			throws MessagingException {
		// 若没有添加附件，则默认使用非 Multipart 模式发送邮件。
		if(mailMessage.getAttachments().isEmpty() && mailMessage.getResources().isEmpty()) {
			return new MimeMessageHelper(mimeMessage, DEFAULT_ENCODING);
		} else {
			return new MimeMessageHelper(mimeMessage, true, DEFAULT_ENCODING);
		}
	}

	private void addResources(SimpleHtmlMultipartMailMessage mailMessage, MimeMessageHelper messageHelper) throws MessagingException {
		Map<String, File> resources = mailMessage.getResources();
		Set<String> keys = resources.keySet();

		for (String cid : keys) {
			messageHelper.addInline(cid, resources.get(cid));
		}
	}

	private void addAttachments(SimpleHtmlMultipartMailMessage mailMessage, MimeMessageHelper messageHelper) throws MessagingException {
		Map<String, File> attachments = mailMessage.getAttachments();
		Set<String> keys = attachments.keySet();

		for (String attachmentFilename : keys) {
			messageHelper.addAttachment(attachmentFilename, attachments.get(attachmentFilename));
		}
	}

}
