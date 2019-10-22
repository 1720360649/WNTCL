<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="tips"><h2>当前用户权限不足,请前往相应页面,或跟换用户 </h2><button onclick="toback()">返回上一页</button><button onclick="goindex()">返回万念首页</button></div>
  </body>
  
  <script type="text/javascript">
  
  	function toback(){
  		window.history.go(-1);
  	}
  	
  	function goindex(){
 		window.location.href = "<%=path%>/index.jsp";
  	}
  	
  </script>
  
</html>
