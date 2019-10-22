<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>万念技术</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="favicons.ico" type="image/x-icon">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>
	
	<style type="text/css">
		
		*{
			margin: 0;
			padding: 0;
		}
		
		li{
			list-style: none;
		}
		
		a{
			cursor: pointer;
			color: gray;
			text-decoration: none;
		}
		
		body{
			position: relative;
		}
		
		#viewtop{
			overflow:hidden;
			width: 100%;
			height: 80px;
			background: #333333;
		}
		
		#trademark{
			float:left;
			width: 240px;;
			height: 80px;
		}
		
		#trademark_img{
			float:left;
			display:block;
			width: 64px;
			height: 64px;
			margin-top: 8px;
			margin-left: 8px;
		}
		
		#enterprise{
			float:left;
			height: 60px;
			color: white;
			font-size:28px;
			font-weight: 700;
			line-height:60px;
			margin-left:7px;
                        margin-top: 10px;
                        padding-right:5px;
                        border:none;
                        border-right:1px solid white;
		}
		
		#nav{
			float:left;
			width:auto;
			height: 100%;
			margin-left:0px;
		}
		
		#nav li{
			float:left;
			height:80px;
			width: 150px;
		}
		
		#nav li:HOVER{
			background: rgba(255,255,255,0.2);
		}

		#nav a{
			color:white;
		}
		
		#nav .nav_text{
			width:100%;
			height:80px;
			line-height:100px;
			font-size:20px;
			text-align:center;
		}
		
		#viewbody{
			width: 941px;
			height: 100%;
			margin: 0px auto;
		}
		
		#showbox{
			width: 100%;
			height: 300px;
			border:none;
			background: gray;
		}

		#showbox_img{
			float:left;
			height: 100%;
			width: 700px;
			background: green;
		}
		
		#showbox_img img{
			width: 700px;
			height: 100%;
		}
		
		#showbox_text{
			float:left;
			height: 100%;
			width: 240px;
			border:none;
			background: rgb(184,197,204);
		}
		
		#showbox_text_title{
			width: 100%;
			height: 60px;
			line-height:60px;
			text-align:center;
			margin-top:5px;
			margin-bottom:5px;
		}
		
		#showbox_text_title_img{
			display:block;
			float:left;
			width: 60px;
			height: 60px;
			margin-left:52px;
		}
		
		#showbox_text_title_text{
			float:left;		
			color:white;
			font-size:19px;
			font-weight: 600;
			margin-left:10px;
		}
		
		#showbox_text_urlbox{
			width: 100%;
			height: 227px;
			background: white;
		}
		
		
		
		#showbox_text_urlbox li{
			width: 100%;
			height: 25%;
			line-height:57px;
			text-align:center;
			color:black;
		}
		
		#showbox_text_urlbox li a{
			color:black;
		}
		
		#showbox_text_urlbox li:HOVER{
			background: gray;
		}

		#viewbottom{
			position: absolute;
			overflow:hidden;
			bottom:0px;
			width: 100%;
			height: 35px;
			line-height: 35px;
			text-align: center;
			font-size:12px;
			color:gray;
			background: #333333;
		}

	</style>

  </head>
  
  <body>
  	<div id="viewtop">
  		<a href="">
	  		<div id="trademark">
	  			<img id="trademark_img" alt="万念技术" src="wn.png" />
	  			<div id="enterprise">万念技术</div><div style="font-size:18px;margin-left:201px;height:80px;line-height:39px;color:white;">个人空间</div>	
	  		</div>
	  	</a>
  		<div id="nav">
  			<ul>
  				<li>
  					<a href="">
  						<div class="nav_text">首页</div>
  					</a>
  				</li>
  				<li>
  					<a href="">
  						<div class="nav_text">技术交流</div>
  					</a>
  				</li>
<li>
  					<a href="">
  						<div class="nav_text" style="width:400px;">以下页面为个人开发系统（供交流，学习）</div>
  					</a>
  				</li>
  			</ul>
  		</div>
  	</div>
  	
  	<div id="viewbody">
  		<div id="showbox">
  			<a><div id="showbox_img"><img alt="new餐厅系统" src="<%=path%>/newtcl.jpg"></div></a>
			<div id="showbox_text">
				<div id="showbox_text_title">
						<img id="showbox_text_title_img" alt="newtcl" src="<%=path%>/new.jpg">
						<div id="showbox_text_title_text">new餐厅</div>
				</div>
				<hr>
				<div id="showbox_text_urlbox">
					<ul>
						<a href="/newtcl/views/userlogin.do?id=1"><li>点餐页</li></a>
						<a href="/newtcl/inside/cooklogin.do?staff=1"><li>厨师页</li></a>
						<a href="/newtcl/inside/waiterlogin.do"><li>跑堂页</li></a>
						<a href="/newtcl/inside/supporter.do"><li>后台管理</li></a>
					</ul>
				</div>
			</div>  			
  		</div>
  	</div>
  	
  	<div id="viewbottom"><a href="http://www.beian.gov.cn/apply/selectBeiAn?id=638464&token=f8888201-f138-4a5d-8ae7-916f8358e7b9">公网安备-36010902000229号</a> &nbsp;&nbsp;&nbsp; Copyright©2019-2019 万念技术 &nbsp;&nbsp;&nbsp; <a href="http://www.beian.miit.gov.cn/">赣ICP备19011277号-1</a></div>
  </body>
  
 <script type="text/javascript">
	
</script>

</html>
