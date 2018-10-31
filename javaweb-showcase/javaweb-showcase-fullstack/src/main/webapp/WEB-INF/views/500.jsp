<%@ page language="java" import="java.util.*, java.io.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
  <title>错误页面 - 500</title>
  <style type="text/css">
    .error-div {
      color:#FF0000; 
      font-weight: bold; 
      max-width: 80%; 
      margin-top: 10px; 
      border-width: 1px; 
      border-color: #FF0000;
      padding: 5px;
      margin: 10px;
      border-style:dotted solid double dashed;
    }
  </style>
</head>

<h1> 
  <img alt="出错啦" src="${ctxPath}/static/img/500-1.jpg">
  <img alt="出错啦" src="${ctxPath}/static/img/500-3.jpg">
  <span>出错啦！</span>
</h1>

<div class="error-div">
  <!-- ${exception.getMessage()} -->
  
  <%
    StringWriter priter = new StringWriter();
    exception.printStackTrace(new PrintWriter(priter));
    
    out.print(priter.toString());
  %>
</div>
