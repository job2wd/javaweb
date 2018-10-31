<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="/common/meta.jsp"%>

  <title>guoyatech FBA - <sitemesh:title /></title>

  <%@ include file="/common/script.jsp"%>
 
  <sitemesh:head />
  
  <style type="text/css">
    .header-div {
      height:20%;
    }
    
    .body-div1 { 
      font-weight: bold; 
      min-width: 98%; 
      margin-top: 5px;
      padding: 5px;
      margin: 5px;
      border-style:dotted solid double dashed;
    }
    
    .left-div {
      width:20%; 
      float: left; 
      min-height: 98%;
    }
    
    .center-div1 {
      float: right; 
      min-width: 98%;
    }
    
    .footer-div {
      height:20%;
    }
  </style>
</head>

<body>
  <div class="header-div">
    <%@ include file="/WEB-INF/layouts/header.jsp"%>
  </div>
  
  <div class="body-div">
    <div class="left-div">
      <%@ include file="/WEB-INF/layouts/left.jsp"%>
    </div>
    
    <div class="center-div">
      <sitemesh:body />
    </div>    
  </div>
  
  <div class="footer-div">
    <%@ include file="/WEB-INF/layouts/footer.jsp"%>
  </div>
</body>
</html>
