<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<% 
  request.setCharacterEncoding("UTF-8");

  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  response.setHeader("Pragma","no-cache");
  response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
  response.setDateHeader("Expires", 0);
%>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="timeStamp" value="<%=new java.util.Date().getTime()%>"/>
