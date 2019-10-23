<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String authcode = request.getParameter("auth_code");
	String sessionid = request.getParameter("state");
 %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支付宝授权接入页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="icon" href="favicons.ico" type="image/x-icon">
	<script type="text/javascript" src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>

  </head>
  
  <body>
  
  </body>
 
 <script type="text/javascript">
 
 	$.post("<%=path%>/newtcl/views/checklogin.do",{}, function(data) {
		if(data.code == "1"){
			window.location.href = "<%=path%>/index.jsp";
		}else{
			var code = "<%=authcode%>";
		 	var sessionid = "<%=sessionid%>";
		 	//接入后台
		 	$.post("<%=path%>/newtcl/otherlogin/alipaylogin.do",{"authcode":code,"sessionid":sessionid},function(data) {
		 		if(data.code == "1"){
					window.location.href = "<%=path%>/index.jsp";
				}else{
					alert(data.message);
				}	
		 	});
		}
	});
 	
 </script> 
 
</html>
