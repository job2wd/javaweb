/*
 * $Id: SpringBeanFactoryUtils.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/SpringBeanFactoryUtils.java $
 * Copyright (c) 2014 Company, www.guoyatech.com. All Rights Reserved.
 */
package org.javaweb.data.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类，用于获取 Spring 容器创建的 Bean 对象。
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014/03/04 14:04:21$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
@Component
public class SpringBeanFactoryUtils implements BeanFactoryAware {

	public static BeanFactory beanFactory;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanFactoryUtils.beanFactory = beanFactory;
	}
	
	public static Object getBean(String name) {
		return SpringBeanFactoryUtils.beanFactory.getBean(name);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return SpringBeanFactoryUtils.beanFactory.getBean(clazz);
	}

}
