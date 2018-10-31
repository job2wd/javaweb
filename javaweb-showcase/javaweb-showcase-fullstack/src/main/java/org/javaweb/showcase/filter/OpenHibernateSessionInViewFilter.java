/*
 * $Id: OpenHibernateSessionInViewFilter.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/filter/OpenHibernateSessionInViewFilter.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.orm.hibernate4.support.AsyncRequestInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 绑定所有数据源 Hibernate session 到请求处理线程。详细描述参见：{@link org.springframework.orm.hibernate4.support.OpenSessionInViewFilter}。
 * <p>
 * 需要在 web.xml 中配置 filter，并指定 init-param，param-name 为 sessionFactoryBeanNames，param-value 为以 “,” 分隔的 Hibernate SessionFactory bean 名称。
 * </p>
 * 
 * @author wangwd
 * @version $Revision: 137 $, $Date: 2015-8-19 下午3:49:18$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 * @see org.springframework.orm.hibernate4.support.OpenSessionInViewFilter
 */
public class OpenHibernateSessionInViewFilter extends OncePerRequestFilter {

  public static final String DEFAULT_SESSION_FACTORY_BEAN_NAME = "sessionFactory";

  private String sessionFactoryBeanNames = DEFAULT_SESSION_FACTORY_BEAN_NAME;
  
  private SessionFactory[] sessionFactorys;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (sessionFactorys == null) {
      sessionFactorys = lookupSessionFactorys(request);
    }

    for (SessionFactory sf : sessionFactorys) {
      if (!TransactionSynchronizationManager.hasResource(sf)) {
        this.bindSessionFactory(sf, request);
      }
    }

    try {
      filterChain.doFilter(request, response);
    } finally {
      for (SessionFactory sf : sessionFactorys) {
        this.closeSession(sf, request);
      }
    }
  }
  
  private void bindSessionFactory(SessionFactory sessionFactory, HttpServletRequest request) {
    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    String key = getAlreadyFilteredAttributeName();

    boolean isFirstRequest = !isAsyncDispatch(request);
    if (isFirstRequest || !applySessionBindingInterceptor(asyncManager, key)) {
      logger.debug("Opening Hibernate Session in OpenHibernateSessionInViewFilter");
      
      Session session = openSession(sessionFactory);
      SessionHolder sessionHolder = new SessionHolder(session);
      TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);

      AsyncRequestInterceptor interceptor = new AsyncRequestInterceptor(sessionFactory, sessionHolder);
      asyncManager.registerCallableInterceptor(key, interceptor);
      asyncManager.registerDeferredResultInterceptor(key, interceptor);
    }
  }

  private void closeSession(SessionFactory sessionFactory, HttpServletRequest request) {
    SessionHolder sessionHolder =
        (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
    if (!isAsyncStarted(request)) {
      logger.debug("Closing Hibernate Session in OpenHibernateSessionInViewFilter");
      SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
  }
  
  protected SessionFactory[] lookupSessionFactorys(HttpServletRequest request) {
    return lookupSessionFactorys();
  }
  
  protected SessionFactory[] lookupSessionFactorys() {
    String sessionFactoryBeanNames = this.getSessionFactoryBeanNames();
        
    if(StringUtils.isNotBlank(sessionFactoryBeanNames)) {
      String[] beanNames = sessionFactoryBeanNames.split("[,]");
      int length = beanNames.length;
      
      SessionFactory[] sessionFactorys = new SessionFactory[length];
      
      for(int i = 0; i < length; i++) {
        String name = beanNames[i];
        
        if (logger.isDebugEnabled()) {
          logger.debug("Using SessionFactory '" + name + "' for OpenHibernateSessionInViewFilter");
        }
        
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        SessionFactory sf = wac.getBean(name, SessionFactory.class);
        
        sessionFactorys[i] = sf;
      }
      
      return sessionFactorys;
    }
    
    return null;
  }
  
  private boolean applySessionBindingInterceptor(WebAsyncManager asyncManager, String key) {
    if (asyncManager.getCallableInterceptor(key) == null) {
      return false;
    }
    ((AsyncRequestInterceptor) asyncManager.getCallableInterceptor(key)).bindSession();
    return true;
  }
  
  protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
    try {
      Session session = null;//sessionFactory.getCurrentSession();
      
      if(session == null) {
        session = sessionFactory.openSession();
      }
      
      session.setFlushMode(FlushMode.MANUAL);
      return session;
    }
    catch (HibernateException ex) {
      throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
    }
  }
  
  @Override
  protected boolean shouldNotFilterAsyncDispatch() {
    return false;
  }

  @Override
  protected boolean shouldNotFilterErrorDispatch() {
    return false;
  }

  public String getSessionFactoryBeanNames() {
    return sessionFactoryBeanNames;
  }

  public void setSessionFactoryBeanNames(String sessionFactoryBeanNames) {
    this.sessionFactoryBeanNames = sessionFactoryBeanNames;
  }

}
