/**
 * Project Name: spring-mail-showcase
 * Created Package Name: org.spring.mail.test
 * Created File Name: SimpleTextMailTest.java
 * $Id: MailSendTest.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/common/mail/MailSendTest.java $
 * Copyright (c) 2013 Company, nationsky. All Rights Reserved.
 */
package org.javaweb.showcase.test.common.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.javaweb.showcase.mail.MailSender;
import org.javaweb.showcase.mail.SimpleHtmlMultipartMailMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;


/**
 * TODO
 *
 * @Author wangweidong
 * @CreatedDate 2013-11-26 下午4:23:29
 * @Version $Revision: 80 $
 * @LastChangedBy $Author: job2wd $
 * @LastChangedDate $LastChangedDate: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 * @LastChangedRevision $LastChangedRevision: 80 $
 * 
 */
//@Configuration
//@EnableAutoConfiguration
//@SpringApplicationConfiguration(locations={"/conf/applicationContext-mail.xml"})
//@ComponentScan(basePackages={"org.javaweb.showcase.common.mail", "org.javaweb.showcase.test.common.mail"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cfg/applicationContext-base.xml", "/cfg/applicationContext-mail.xml"})
public class MailSendTest {//extends BaseSpringJunitTest {
   
	//@Resource(name="mailSender")
  @Autowired
  @Qualifier("mailSender")
	private MailSender<SimpleHtmlMultipartMailMessage> mailSender;
  
  @Autowired
  private VelocityEngine velocityEngine;
		
	@Test
	public void testSimpleTextMailSend() {
		System.out.println("mail send start....");
		
		try {
			SimpleHtmlMultipartMailMessage mailMessage = new SimpleHtmlMultipartMailMessage();
			mailMessage.setTo(new String[]{"wangweidong@guoyatech.com", "joy2wd@163.com"});
			mailMessage.setFrom("java_spring_mail@163.com");
			mailMessage.setFromAlias("Reply");
			mailMessage.setSubject("Java 邮件发送测试！");
			mailMessage.setText("Hello, Spring Java mail! Java 邮件发送测试！");
			
			//mailSender = applicationContext.getBean(SimpleHtmlMultipartMailSender.class);
			
			mailSender.sendMail(new SimpleHtmlMultipartMailMessage[]{mailMessage});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("mail sent!");
	}

	@Test
	public void testSimpleHtmlMailSend() {
		System.out.println("mail send start....");
		
		try {
			SimpleHtmlMultipartMailMessage mailMessage = new SimpleHtmlMultipartMailMessage();
			mailMessage.setTo(new String[]{"wangweidong@nationsky.com", "joy2wd@163.com"});
			mailMessage.setFrom("java_spring_mail@163.com");
			mailMessage.setFromAlias("Reply");
			mailMessage.setSubject("Java 邮件发送测试！");
			
			mailMessage.setText("<html>" +
	        "<body>" +
	        "<BR>" +
	        "<div align='center'>" +
	          "<img src='cid:imageid'/>" +
	          "<BR>" +
	          "<h4>" +
	            "返回 fancydeepin 的Blogjava：" +
	            "<a href='http://www.blogjava.net/fancydeepin/'>http://www.blogjava.net/fancydeepin/</a>" +
	          "</h4>" +
	        "</div>" +
	      "</body>" +
	    "</html>");
			ClassPathResource image = new ClassPathResource("mail/clock.png");
			mailMessage.addResource("imageid", image.getFile());
			
			mailSender.sendMail(new SimpleHtmlMultipartMailMessage[]{mailMessage});
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		System.out.println("mail sent!");
	}

	@Test
	public void testSimpleHtmlMultipartMailSend() {
		System.out.println("mail send start....");
		
		try {
			SimpleHtmlMultipartMailMessage mailMessage = new SimpleHtmlMultipartMailMessage();
			mailMessage.setTo(new String[]{"wangweidong@nationsky.com", "joy2wd@163.com"});
			mailMessage.setFrom("java_spring_mail@163.com");
			mailMessage.setFromAlias("Reply");
			mailMessage.setSubject("Java 邮件发送测试！");
			
			mailMessage.setText("<html>" +
	        "<body>" +
	        "<BR>" +
	        "<div align='center'>" +
	          "<img src='cid:imageid'/>" +
	          "<BR>" +
	          "<h4>" +
	            "返回 fancydeepin 的Blogjava：" +
	            "<a href='http://www.blogjava.net/fancydeepin/'>http://www.blogjava.net/fancydeepin/</a>" +
	          "</h4>" +
	        "</div>" +
	      "</body>" +
	    "</html>");
			ClassPathResource image = new ClassPathResource("mail/clock.png");
			mailMessage.addResource("imageid", image.getFile());
			
			mailMessage.addAttachment("my clock.png", image.getFile());
			
			mailSender.sendMail(new SimpleHtmlMultipartMailMessage[]{mailMessage});
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		System.out.println("mail sent!");
	}
	
  @Test
  public void testSimpleHtmlMultipartMailSendByVelocityEngine() {
    System.out.println("mail send start....");
    
    try {
      SimpleHtmlMultipartMailMessage mailMessage = new SimpleHtmlMultipartMailMessage();
      mailMessage.setTo(new String[]{"wangweidong@nationsky.com", "joy2wd@163.com"});
      mailMessage.setFrom("java_spring_mail@163.com");
      mailMessage.setFromAlias("Reply");
      mailMessage.setSubject("Java 邮件发送测试！");
      
      EmailUser user = new EmailUser();
      user.setEmailAddress("joy2wd@163.com");
      user.setUserName("David");
      
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", user);
      String text = VelocityEngineUtils.mergeTemplateIntoString(
         velocityEngine, "mail/user-mail.vm", "UTF-8", model);
      
      mailMessage.setText(text);
      
      ClassPathResource image = new ClassPathResource("mail/clock.png");
      mailMessage.addResource("imageid", image.getFile());
      
      mailMessage.addAttachment("my clock.png", image.getFile());
      
      mailSender.sendMail(new SimpleHtmlMultipartMailMessage[]{mailMessage});
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
    
    System.out.println("mail sent!");
  }
	
}
