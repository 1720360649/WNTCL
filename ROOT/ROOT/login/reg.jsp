<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
%>

<%
	//表单提交编码格式
	request.setCharacterEncoding("utf-8");
%>

<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="icon" href="favicons.ico" type="image/x-icon">
<script type="text/javascript" src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>

<title>注册页</title>

<style>

*{
	padding: 0;
	margin: 0;
}

li{
	list-style: none;
}

#indexbox{
	overflow:hidden;
	
	/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
}

#regChoice{
	background: rgba(0, 0, 0, 0.1);
	font-weight: 700;
}

#regChoice li{
	float:left;
	color:black;
	text-align: center;
}

#regChoice .onRegChoice{
	background: rgba(0, 0, 0, 0.4);
	color: white;
}

#regbox {
	height: 91%;
	width: 100%;
	margin:auto;
	background: rgba(0, 0, 0, 0.4);
}
	 
#reg {
	width: 300px;
	height: 400px;
	left: 0px;
	right: 0px;
	top: 40px;
	margin: auto;
}

.usernumber {
	display: block;
	height: 20px;
	width: 100px;
	border: none;
	font-size:16px;
	margin:0px auto;
}

input {
	height: 30px;
	width: 320px;
	border-radius:8px;
	border:none;
	border-bottom:2px solid white;
	color: white;
	font-size:20px;
	text-align:center;
	background: rgba(255, 255, 255, 0.1);
}

 input::-webkit-input-placeholder {
        /* placeholder颜色  */       
         color: white;
    }

label {
	display: block;
	color: #FFFFFF;
}

#but {
	width: 320px;
	border: 1px solid #3762bc;
	color: #000;
	padding: 9px 14px;
	font-size: 15px;
	line-height: normal;
	border-radius: 5px;
	margin: 0px auto;
}

#phone_prompt {
	width:310px;
	color: red;
	font-size: 10px;
}

#pwd_prompt {
	width:310px;
	color: aqua;
	font-size: 10px;
}

#repwd_prompt {
	width:310px;
	color: red;
}

#preview {
	width: 80px;
	height: 80px;
	border: 1px solid gray;
	overflow: hidden;
	margin-left: 100px;
}

#preview{
	overflow:hidden;
	width:100px;
	height:100px;
	border-radius:50px;
	margin: auto;
}
#preview img{
	width:100px;
	height:100px;
}

#image{
	display: none;
}

.image{
}

#imghead {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
}

#Yreg{
	position: absolute;
	width:500px;
	height:30px;
	top:350px;
	left: 0px;
	right:0px;
	color:white;
	font-size:24px;
	margin: auto;
	display: none;
}

</style>

</head>
	
<body>
	<div id="indexbox">
	<div id="regChoice">
		<ul>
			<li class="onRegChoice" onclick="onuser()">用户注册</li>
			<li onclick="onmanage()">商家注册</li>
		</ul>
	</div>
		<div id="regbox">
				<form action="" name="regform" method="post">
					<table>
						<tr>
							<td style="top:60px;">
							<input
								id="phone" type="text" name="phone" required="required" placeholder="手机号"
								onkeyup="checkPhone()" />
							<div id="phone_prompt"></div>
							</td>
						</tr>
						<tr>
							<td style="top:60px;">
							<input
								id="user" type="text" name="username" required="required" placeholder="用户名"
								onkeyup="checkName()" />
							</td>
						</tr>
						<tr>
							<td style="top:155px;">
							<input
								id="pwd" type="password" name="userpassword" required="required" placeholder="密码"
								onkeyup="checkPwd()" />
							<div id="pwd_prompt">密码包含 数字,英文,字符中的两种以上，长度在6-20之间</div>
							</td>
						</tr>
		
						<tr>
							<td style="top:250px;">
								<input
								id="repwd" type="password" name="" required="required" placeholder="确认密码"
								onkeyup="checkRepwd()" />
							<div id="repwd_prompt"></div></td>
		
						</tr>
		
						<tr>
							<td>
								<div id="preview" onclick="onpreview()">
									<img id="imghead" border=0 src="<%=path %>/onavatar.jpg"/>
								</div> <input type="file" id="image" onchange="readFile(this)"
								class="image" name="image" value=""/></td>
						</tr>
	
						<tr>
							<td style="bottom:40px;">
								<button id="but" type="button" onclick="startreg()">注
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 册</button>
							</td>
						</tr>
						
					</table>
				</form>
		</div>
	<div id="Yreg"><a href="Login.jsp">前往登陆</a></div>
</div>

</body>

<script type="text/javascript">

	//窗口宽度
 	var wW = window.innerWidth;
 	//窗口高度
 	var wH = window.innerHeight;
 	
 	var avatar="null";//用户上传头像
 	var pn = false; //用户名合法判断
	var pp = false; //密码合法判断
	var rp = false; //确认密码判断
	var type = "user";
	var but = document.getElementById("but");
	document.getElementById("but").disabled = true;
 	
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
        	wW = window.innerWidth;
 			wH = window.innerHeight;
 			
 			windowauto();
        }
   
   });	
   
  function uload(){
	   	
  }
  
  
  /**************************************注册选择************************************/
  function onuser(){
  	type = "user";
  
 	$("#regChoice").html('<ul><li class="onRegChoice" onclick="onuser()">用户注册</li><li onclick="onmanage()">商家注册</li></ul>');
  	
  	$("#regbox").html('<form action="" name="regform" method="post"><table><tr><td style="top:60px;"><input id="phone" type="text" name="phone" required="required" placeholder="手机号"onkeyup="checkPhone()" />'
  	+'<div id="phone_prompt"></div></td></tr><tr><td style="top:60px;"><input id="user" type="text" name="username" required="required" placeholder="用户名"onkeyup="checkName()" /></td></tr><tr><td style="top:155px;">'
  	+'<input id="pwd" type="password" name="userpassword" required="required" placeholder="密码"onkeyup="checkPwd()" /><div id="pwd_prompt">密码包含 数字,英文,字符中的两种以上，长度在6-20之间</div></td></tr><tr>'
  	+'<td style="top:250px;"><input id="repwd" type="password" name="" required="required" placeholder="确认密码"onkeyup="checkRepwd()" /><div id="repwd_prompt"></div></td></tr><tr><td>'
  	+'<div id="preview" onclick="onpreview()"><img id="imghead" border=0 src="<%=path %>/onavatar.jpg"/></div> <input type="file" id="image" onchange="readFile(this)" class="image" name="image" value=""/></td></tr>'
  	+'<tr><td style="bottom:40px;"><button id="but" type="button" onclick="startreg()">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 册</button></td></tr></table></form>');
  	
  	windowauto();
  }
 		
  function onmanage(){
  	type = "mc";
  
  	$("#regChoice").html('<ul><li onclick="onuser()">用户注册</li><li class="onRegChoice" onclick="onmanage()">商家注册</li></ul>');
  	
  	$("#regbox").html('<form action="" name="regform" method="post"><table><tr><td style="top:60px;"><input id="phone" type="text" name="phone" required="required" placeholder="手机号"onkeyup="checkPhone()" />'
  	+'<div id="phone_prompt"></div></td></tr><tr><td style="top:60px;"><input id="user" type="text" name="username" required="required" placeholder="店铺名"onkeyup="checkName()" /></td></tr><tr><td style="top:155px;">'
  	+'<input id="pwd" type="password" name="userpassword" required="required" placeholder="密码"onkeyup="checkPwd()" /><div id="pwd_prompt">密码包含 数字,英文,字符中的两种以上，长度在6-20之间</div></td></tr><tr>'
  	+'<td style="top:250px;"><input id="repwd" type="password" name="" required="required" placeholder="确认密码"onkeyup="checkRepwd()" /><div id="repwd_prompt"></div></td></tr><tr><td>'
  	+'<input id="mccode" type="text" name="mccode" required="required" placeholder="邀请码(填写运营方提供的商户邀请码)" onkeyup="" /><div id="mccode_prompt"></div></td></tr>'
  	+'<tr><td style="bottom:40px;"><button id="but" type="button" onclick="startreg()">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 册</button></td></tr></table></form>');
  
  	windowauto();
  } 
	
	/*************************************注册提交按钮****************************/
	function startreg(){
		
		var phone = $("#phone").val();
		var name = $("#user").val();
		var pwd = $("#pwd").val();
		
		var str;
		
		if("user" == type){
			str ={avatar: avatar,phone: phone,name: name,password: pwd,mccode: "null",regType : type};
		}else{
			var mccode = $("#mccode").val();
			str ={avatar: avatar,phone: phone,name: name,password: pwd,mccode: mccode,regType : type};
		}
		
		$.post("<%=path%>/newtcl/user/reg.do",str,function(data){
			if("1" == data.code){
			
			window.location.href = "<%=path%>/index.jsp";
				
			}else{
				alert("注册失败!"+data.message);
			}
		});
	}

	
	/*****************************************电话验证***************************************/
	function checkPhone() {
	
		var phone = document.getElementById("phone").value;
		var userId = document.getElementById("phone_prompt");

		userId.innerHTML = "";
		var reg = /^1[3456789]\d{9}$/;
		if (reg.test(phone) == false) {
			pn = false;
			userId.innerHTML = "手机号有误,请重新填写!";
			userId.style.color = "red";
			ckeckYN();
			return false;
		}else{
			userId.innerHTML = "";
			$.post("<%=path%>/newtcl/user/checkphone.do", {
			phone : $("#phone").val()
			}, function(data) {
				if ("1" == data.code) {
					userId.innerHTML = "";
					pn = true;
					ckeckYN();
					return true;
				} else {
					pn = false;
					userId.innerHTML = "该手机号已被注册";
					userId.style.color = "red";
				}
			});
		}
		
		ckeckYN();
	}
	
	//用户名输入框状态改变
	function checkName(){
		ckeckYN();
	}
	
	/***************************************密码验证*******************************************/
	function checkPwd() {
		var pwd = document.getElementById("pwd").value;
		var pwdId = document.getElementById("pwd_prompt");
		pwdId.innerHTML = "";
		var reg = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$/;
		if (reg.test(pwd) == false) {
			pp = false;
			pwdId.innerHTML = "密码必须包含 数字,英文,字符中的两种以上，长度在6-20之间";
			pwdId.style.color = "red";
			ckeckYN();
			return false;
		}

		pp = true;
		ckeckYN();
	
		return true;
	}
	
	/*********************************************确认密码********************************************/
	function checkRepwd() {
		
		var repwd = document.getElementById("repwd").value;
		var pwd = document.getElementById("pwd").value;
		var repwdId = document.getElementById("repwd_prompt");
		repwdId.innerHTML = "";
		if (pwd != repwd) {
			repwdId.innerHTML = "两次输入的密码不一致";
			rp = false;
			ckeckYN();
			return false;
		}
		rp = true;
		ckeckYN();
		return true;
	}

	/************************************************提交条件检查***************************************/
	function ckeckYN(){
		if (pn && pp && rp) {
			document.getElementById("but").style.backgroundColor = "#F08120";
			document.getElementById("but").style.color = "#fff";
			document.getElementById("but").disabled = false;
		}
	}
	
	/*************************************************头像选择************************************************/

	//图片按钮
	function onpreview(){
		$("#image").click();
	}

	//图片转码为base64
	function readFile(file) {
		var filen = file.files[0];
		if (!/image\/\w+/.test(filen.type)) {
			alert("请确保文件为图像类型");
			return false;
		}
		var reader = new FileReader();
		reader.readAsDataURL(filen);
		reader.onload = function(e) {
			var ImgFileSize = this.result;
			var index = ImgFileSize.lastIndexOf("\,");
			ImgFileSize = ImgFileSize.substring(index + 1, ImgFileSize.length);
			previewImage(file);
		}
	}


	//	将图片显示在网页中
	function previewImage(file) {
		var MAXWIDTH = wH*0.2;
		var MAXHEIGHT = wH*0.2;
		var div = document.getElementById('preview');
		if (file.files && file.files[0]) {
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.onload = function() {
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
				avatar = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		} else { //兼容IE
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			avatar = evt.target.result;
		}
		
		//图片改变后设置大小
		$("#imghead").css({
			"width":(wH*0.20)+"px",
			"height":(wH*0.20)+"px"
		});
	}
	
	//显示图片的等比例缩放
	
	/***************************************响应式************************************/
	function windowauto(){
	
		$("#regChoice").css({
			"width":wW+"px",
			"height":(wH*0.09)+"px"
		});
		
		$("#regChoice li").css({
			"width":(wW*0.5)+"px",
			"line-height":(wH*0.09)+"px",
			"font-size":(wH*0.04)+"px"
		});
	
		$("input").css({
			"width":(wW*0.9)+"px",
			"height":(wH*0.09)+"px",
			"margin":(wH*0.02)+"px auto",
			"margin-left":(wW*0.05)+"px",
			"font-size":(wH*0.04)+"px"
		});

		$("#phone_prompt").css({
			"width":(wW*0.9)+"px",
			"margin-left":(wW*0.05)+"px",
			"font-size":(wH*0.03)+"px"
		});
		
		$("#pwd_prompt").css({
			"width":(wW*0.9)+"px",
			"margin-left":(wW*0.05)+"px",
			"font-size":(wH*0.03)+"px"
		});
		
		$("#repwd_prompt").css({
			"width":(wW*0.9)+"px",
			"margin-left":(wW*0.05)+"px",
			"font-size":(wH*0.03)+"px"
		});
		
		$("#preview").css({
			"width":(wH*0.20)+"px",
			"height":(wH*0.20)+"px",
			"border-radius":(wH*0.20)+"px",
			"margin":"0px auto"
		});
		
		$("#imghead").css({
			"width":(wH*0.20)+"px",
			"height":(wH*0.20)+"px"
		});

		$("#but").css({
			"width":(wW*0.9)+"px",
			"height":(wH*0.09)+"px",
			"margin-left":(wW*0.05)+"px",
			"margin-top":(wH*0.02)+"px",
			"font-size":(wH*0.05)+"px"
		});
		
	}

</script>
</html>
