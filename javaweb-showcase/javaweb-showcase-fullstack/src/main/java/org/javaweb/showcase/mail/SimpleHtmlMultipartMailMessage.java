/**
 * Project Name: spring-mail-showcase
 * Created Package Name: org.spring.mail
 * Created File Name: HtmlMultipartSimpleMailMessage.java
 * $Id: SimpleHtmlMultipartMailMessage.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/mail/SimpleHtmlMultipartMailMessage.java $
 * Copyright (c) 2013 Company, nationsky. All Rights Reserved.
 */
package org.javaweb.showcase.mail;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * TODO
 * 
 * @Author wangweidong
 * @CreatedDate 2013-11-26 下午5:47:53
 * @Version $Revision: 80 $
 * @LastChangedBy $Author: job2wd $
 * @LastChangedDate $LastChangedDate: 2017-01-20 12:21:11 +0800 (星期五, 20 一月 2017) $
 * @LastChangedRevision $LastChangedRevision: 80 $
 * 
 */
public class SimpleHtmlMultipartMailMessage extends SimpleMailMessage {

	private static final long serialVersionUID = 5930676590593332848L;

	/**
	 * 邮件发送者显示别名
	 */
	private String fromAlias;

	/**
	 * HTML 中引用资源映射：key - 资源 ID；value - file。<br>
	 * 引用方式：cid：resourceId，如：<code>&lt;img src="cid:log_img"/&gt</code>，则 key=log_img。
	 */
	private Map<String, File> resources = new LinkedHashMap<String, File>();

	/**
	 * 附件：key - 附件显示文件名；value - file。
	 */
	private Map<String, File> attachments = new LinkedHashMap<String, File>();

	public void addResource(String resourceId, File file) {
		resources.put(resourceId, file);
	}

	public void addAttachment(String attachmentFilename, File file) {
		attachments.put(attachmentFilename, file);
	}

	public String getFromAlias() {
		return fromAlias;
	}

	public void setFromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
	}

	public Map<String, File> getResources() {
		return resources;
	}

	public Map<String, File> getAttachments() {
		return attachments;
	}

}
