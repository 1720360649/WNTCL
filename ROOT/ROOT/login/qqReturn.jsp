<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>message</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="favicons.ico" type="image/x-icon">
	<script type="text/javascript" src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>
	 <script type="text/javascript" src="http://connect.qq.com/qc_jssdk.js" charset="utf-8"></script>

  </head>
  
  <body>
  	<div id ="tips"></div>
  </body>
  <script type="text/javascript">
  
  //登陆验证,避免重复操作
  $.post("<%=path%>/newtcl/views/checklogin.do",{}, function(data) {
		if(data.code == "1"){
			window.location.href = "<%=path%>/index.jsp";
		}else{
			$("#tips").html('登陆成功,页面将自动跳转,如页面长时间未跳转请点击按钮以刷新 <button onclick="tipsgo()">刷新</button>');
		}
	});
  
function tipsgo(){
	window.location.reload();
}
 
  
	if(QC.Login.check()){
 			var qname = null;
 			var qavatar = null;
	 		QC.Login.getMe(function(openId, accessToken){
	 			$.post("https://graph.qq.com/user/get_user_info",{
					"access_token" : accessToken,
					"oauth_consumer_key" : 101788312,
					"openid" : openId
				}, function(data) {
					qname = data.nickname;
					qavatar = data.figureurl_qq_1;
				})	
	 		
	 			var other = {"qid":openId,"qtoken":accessToken,"qname":qname,"qavatar":qavatar};
	 			$.post("<%=path%>/newtcl/otherlogin/qqlogin.do",{
	 				"qid":openId,
	 				"qtoken":accessToken,
	 				"qname":qname,
	 				"qavatar":qavatar
	 			}, function(data) {
					if(data.code == "1"){
						CloseWebPage();
					}else{
						alert("登陆失败,请重新登陆或联系管理员:"+data.message);
					}	 			
	 			});
	        });
 		}else{
 			alert("登陆失败,请重试!");
 		}
 		
 		
 		  
 function CloseWebPage() {     
    if (navigator.userAgent.indexOf("MSIE") > 0) {     
        if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {     
            window.opener = null; window.close();     
        }     
        else {     
            window.open('', '_top'); window.top.close();     
        }     
    }     
    else if (navigator.userAgent.indexOf("Firefox") > 0) {     
        window.location.href = 'about:blank '; //火狐默认状态非window.open的页面window.close是无效的    
        //window.history.go(-2);     
        window.top.close();
    }     
    else {     
        window.opener = null;      
        window.open('', '_self', '');     
        window.close();     
    }     
}
 		
 		
 		
  </script>
  
</html>
  