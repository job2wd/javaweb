<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<title>错误页面 - 500</title>
</head>

<h1> 
  <img alt="出错啦" src="${ctxPath}/static/img/404-1.gif">
  <span>出错啦！</span>
</h1>

<div style="color:#FF0000; font-weight: bold; max-width: 80%; margin-top: 10px; border-width: 1px; border-color: #FF0000;">
  ${exception.getMessage()}
</div>
