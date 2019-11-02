<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统一登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="icon" href="favicons.ico" type="image/x-icon">
	<link href="<%= basePath %>/jquery_toast/css/toast.style.css" rel="stylesheet">
	
	<script type="text/javascript" src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="<%=path %>/jquery_toast/js/toast.script.js"></script>
	
	<!-- QQ登录脚本包 -->
 	<script type="text/javascript"  charset="utf-8"
    src="http://connect.qq.com/qc_jssdk.js"></script>	
	
	<style type="text/css">
	
	*{
		margin: 0;
		padding: 0;
	}
	
	li{
		list-style: none;
	}
	
	body{
		position: relative;
	}
	
	
	#tit{
		width:400px;
		height:100px;
		margin: 10px auto;
	}
	
	.tittxt{
		width:400px;
		height:100px;
		line-height:100px;
		color:white;
		text-align: center;
	}
	

	#loginbox{
		position:absolute;
		width: 450px;
		height:700px;
		left:0px;
		right:0px;
		margin:auto;
		background: rgba(30,12,12,0.4);
	}

	#login{
		position:absolute;
		width:400px;
		height:600px;
		top:0px;
		bottom:0px;
		left:0px;
		right:0px;
		margin: auto;
	}
	
	#loginbox input{
		color:white;
		border-radius: 10px;
		text-align:center;
		border-top:none;
		border-left:none;
		border-right:none;
		margin:40px auto;
		background: rgba(255, 255, 255,0.1);
	}
	
	#codebox{
		margin:40px auto;
	}
	
	#codebox .code{
		float:left;
		margin: 0px 0px;
	}	
	
	#codeimg{
		float:left;
		width: 110px;
		left:270px;
		bottom:285px;
		margin-left:35px;
	}
	
	#codes{
		float:left;
		height:50px;
	}
	
	.but{
		width:400px;
		height:45px;
		bottom:180px;
		color:white;
		border-top:none;
		border-left:none;
		border-right:none;
		border-bottom:1px solid white;
		border-radius: 10px;
		background: rgba(8, 94, 163, 0.9);
	}
	
	.but:HOVER{
		cursor:pointer;
		background: rgba(9, 119, 208, 0.8);
	}
	
	#reg{
		display:block;
		position:absolute;
		width:250px;
		height:40px;
		right:10px;
		bottom:10px;
		color: #ffcc00;
	}
	
	 input::-webkit-input-placeholder {
        /* placeholder颜色  */       
         color: white;
        /* placeholder字体大小  */        
    }
    
	
	#otherlogin{
		width: auto;
		height: 50px;
		margin: 0px auto;
	}

	#otherlogin li{
		float: left;
		width: 50px;
		height: 50px;
		margin:0px a;
		margin-left: 30px;
	}
	
	#otherlogin img{
		width: 50px;
		height: 50px;
	}
	
	#qqLoginBtn{
		width: 50px;
		height: 50px;
	}
		
	</style>

  </head>
  
  <body>
  	<div id="loginbox">
			<div id="login">
				<div id="tit">
	  			<div class="tittxt">用户登陆</div>
	  			</div>
				<form action="" name="loginFrom" method="post">
					<table>
						<tr>
							<td><input class="phone" type="text" required="required"
								placeholder="手机号" name="username" value=""></td>
						</tr>
	
						<tr>
							<td><input class="password" type="password"
								required="required" placeholder="密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码" name="userpassword" value="">
							</td>
						</tr>
	
						<tr>
							<td><div id="codebox">
									<input class="code" type="text" required="required" placeholder="验证码" name="code" value="">
									<div id="codeimg">
										<img id="codes" alt="验证码" src="<%=path %>/newtcl/images/do.do"
										onclick="refresh(this)" />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<button class="but" type="button" onclick="tolongin()">进
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 入</button>
							</td>
						</tr>
					</table>
				</form>
				<div id="otherlogin">
  					<ul>
  						<li style="color:white;font-weight:600;font-size: 20px;margin-left:0px;">快捷登陆</li>
			  			<li><div id="qqLoginBtn" onclick="qlogin()"><img alt="QQ" src="<%=path%>/qq.png"></div></li>
			  			<li><div id="alipayLoginBtn">支付宝</div></li>
  					</ul>
  				</div>
  	
				<a id="reg" href="<%=path %>/login/reg.jsp">&gt;&gt;注册 </a>
			</div>
		</div>
  </body>
  
<script type="text/javascript">

	var timer = null;
	//窗口宽度
 	var windowWidth = window.innerWidth;
 	//窗口高度
 	var windowHeight = window.innerHeight;
 	//个人初始化函数
 	function uload(){}
 	//个人自适应函数
 	function windowauto(){}
        
  $(function () {
  
  		uload();
  		windowauto();
  		
 /**********************************窗口变化监听(全局自适应)****************  */
        window.onresize = function(){
        	//获取变化后
        	windowWidth = window.innerWidth;
 			windowHeight = window.innerHeight;
 			
 			windowauto();
        }
   
   });
   
   /**********************************初始化***************************************/
   function uload(){
		/******************************** 登陆判断  *****************************************************/
		$.post("<%=path%>/newtcl/views/checklogin.do",{}, function(data) {
						if(data.code == "1"){
							window.location.href = "<%=path%>/index.jsp";
						}else{
							if(isMobile()){
								$("#alipayLoginBtn").html('<a href="alipays://platformapi/startapp?appId=20000067&url=https%3a%2f%2fopenauth.alipay.com%2foauth2%2fpublicAppAuthorize.htm%3fapp_id%3d2019090666987149%26scope%3dauth_user%26state%3d'
								+data.html
								+'%26redirect_uri%3dhttp%3a%2f%2fwww.wntcl.top%2flogin%2falipayReturn.jsp"><img style="width:'+(windowHeight*0.1)+';height:'+(windowHeight*0.1)+';" src="<%=path%>/alipay.png"></a>');
							}else{
								$("#alipayLoginBtn").html('<a href="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2019090666987149&scope=auth_user&state='
								+data.html
								+'&redirect_uri=http%3a%2f%2fwww.wntcl.top%2flogin%2falipayReturn.jsp"><img style="width:'+(windowHeight*0.1)+';height:'+(windowHeight*0.1)+';" src="<%=path%>/alipay.png"></a>');
							}
						}		
		});   
   }
	
	/******************************* 移动设备判断 ********************************************/
	function isMobile(){ 
	  if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i) 
	  )
	  	return true; 
	  else
  		return false; 
  }

	/******************************** QQ按钮  *****************************************************/
	function qlogin(){
		QC.Login.showPopup({appId:"101788312",redirectURI:"http://wntcl.top/login/qqReturn.jsp"});
		timer = setInterval(function() {
			$.post("<%=path%>/newtcl/views/checklogin.do",{}, function(data) {
				if(data.code == "1")
					window.location.href = "<%=path%>/index.jsp";
			});
		}, 800);
		
		timer();
	}
	
	/*************************************验证码刷新*************************************************/
 	function refresh(obj){
		obj.src="<%=path %>/newtcl/images/do.do?id="+Math.random();
	}
	
	/**************************************账号密码登陆****************************************************/
	function tolongin(){
		var phone = $(".phone").val();
		var pwd = $(".password").val();
		var code = $(".code").val();
		
		if(phone == "" || phone == undefined || phone == null){
			jquerytoast("错误提示","请填写用户名",windowWidth*0.5);
			return ;
		}else if(pwd == "" || pwd == undefined || pwd == null){
			jquerytoast("错误提示","请填写密码",windowWidth*0.44);
			return ;
		}else if(code == "" || code == undefined || code == null){
			jquerytoast("错误提示","请填写验证码",windowWidth*0.5);
			return ;
		}

	  	$.post("<%=path %>/newtcl/user/login.do",{
	  		phone:phone,
	  		password:pwd,
	  		code:code
	  	},function(data){
	  		if(data.code == "1"){
	  			window.history.go(-1);
	  			window.location.href = "<%=path%>/index.jsp";
	  		}else{
	  			$("#codes").click();
	  			jquerytoast("登陆失败",""+data.message,windowWidth*0.5);
	  		}
	  	});
  			
  	
  		}
  		
/**************************************提示弹窗********************************************/
function jquerytoast(title,message,width){
	$.Toast(title, message , "error", {
  					appendTo: "#loginbox",
	                stack: true,
	                position_class: "toast-top-center",
	                has_icon:true,
	                has_close_btn:true,
	                fullscreen:false,
	                width: width,
	                timeout:3000,
	                sticky:true,
	                has_progress:true,
	                rtl:false,
           });
           
     $(".toast-item-wrapper .toast-title").css({
         "font-size":windowHeight*0.03+"px"
     });
     $(".toast-item-wrapper .toast-message").css({
         "font-size":windowHeight*0.05+"px",
         "width":"auto"
     });
     $(".toast-item-wrapper i.toast-icon").css({
         "font-size":windowHeight*0.04+"px",
         "line-height":windowHeight*0.08+"px"
     });
}

/****************************************响应式界面****************************************************/
function windowauto(){
	$("#loginbox").css({
		width:windowWidth,
		height:windowHeight
	});
	
	$(".tittxt").css({
		"font-size":(windowHeight*0.07)+"px"
	});
	
	$("#loginbox input").css({
		"font-size":(windowHeight*0.05)+"px",
		"margin":(windowHeight*0.035)+"px auto"
	});
	
	$("#login").css({
		"width":(windowWidth*0.9)+"px",
		"height":(windowHeight*0.93)+"px"
	});
	
	$("#loginbox input").css({
		"width":(windowWidth*0.9)+"px",
		"height":(windowHeight*0.09)+"px"
	});
	
	$("#codebox").css({
		"width":(windowWidth*0.9)+"px",
		"height":(windowHeight*0.09)+"px"
	});
	
	$("#codebox .code").css({
		"width":(windowWidth*0.54)+"px",
		"margin":"0px 0px"
	});
	
	$("#codeimg").css({
		"height":(windowHeight*0.09)+"px"
	});
	
	$("#codes").css({
		"height":(windowHeight*0.09)+"px"
	});

	$(".but").css({
		"width":(windowWidth*0.9)+"px",
		"height":(windowHeight*0.09)+"px",
		"font-size":(windowHeight*0.05)+"px",
		"margin":(windowHeight*0.035)+"px auto"
	});
	
	$("#otherlogin").css({
		"width":(windowHeight*0.3+90)+"px",
		"height":(windowHeight*0.1)+"px"
	});
	
	$("#otherlogin li").css({
		"width":(windowHeight*0.1)+"px",
		"height":(windowHeight*0.1)+"px",
		"font-size":(windowHeight*0.05)+"px"
	});
	
	$("#qqLoginBtn").css({
		"width":(windowHeight*0.1)+"px",
		"height":(windowHeight*0.1)+"px"
	});
	
	$("#otherlogin img").css({
		"width":(windowHeight*0.1)+"px",
		"height":(windowHeight*0.1)+"px"
	});
	
	$("#reg").css({
		"font-size":(windowHeight*0.05)+"px"
	});
	
	$("#login input::-webkit-input-placeholder").css({
		"font-size":(windowHeight*0.05)+"px"
	});
	
	//弹窗
	 $(".toast-item-wrapper .toast-title").css({
          "font-size":windowHeight*0.03+"px"
     });
     $(".toast-item-wrapper .toast-message").css({
         "font-size":windowHeight*0.05+"px",
         "width":"auto"
     });
     $(".toast-item-wrapper i.toast-icon").css({
         "font-size":windowHeight*0.04+"px",
         "line-height":windowHeight*0.08+"px"
     });
	
}
</script>

</html>
