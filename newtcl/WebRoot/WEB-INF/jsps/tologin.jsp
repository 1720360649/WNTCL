<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.newtcl.entity.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String str = "http://wntcl.top/login/login.jsp";
	if(session.getAttribute("wntcluser") !=null){
		User user = (User)session.getAttribute("wntcluser");
		if("u".equals(user.getJurisdiction()))
			str = "http://wntcl.top/newtcl/views/userlogin.do?id=1";
	}
 %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>跳转页</title>
    
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
    <h2>页面跳转中.................. </h2>
  </body>
  
  <script type="text/javascript">
  
  	window.location.href = "<%=str %>";
  	
  </script>
  
</html>
