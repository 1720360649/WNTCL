<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    
	<link type="text/css" rel="stylesheet" href="<%=path %>/utils/font-awesome/css/fontawesome.css"> 
    <link type="text/css" rel="stylesheet" href="<%=path %>/utils/jquery_toast/css/toast.style.css">
    <link type="text/css" rel="stylesheet" href="<%=path %>/utils/layui/css/layui.css">

    <script type="text/javascript" src="<%=path %>/utils/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%=path %>/utils/jquery_toast/js/toast.script.js"></script>
 	<!-- <script type="text/javascript" src="<%=path %>/utils/layui/layui.js"></script>  -->
 	<script type="text/javascript" src="<%=path %>/utils/Echarts/echarts.min.js"></script>
 
 	
	
 	<link rel="icon" href="<%=path %>/img/new.ico" type="image/x-icon">
 
 	<!-- <script type="text/javascript" src="<%=path %>/js/fullscreen.js"></script>  -->
 
 <style type="text/css">
 
 	*{
 		margin: 0px;
 		padding: 0px;
 	}
 
 	li{
 		list-style: none;
 	}
 	
 	body{
 		position: relative;
 	}
 	
 	::-webkit-scrollbar {
            display: none;
        }
 	 	
 </style>
 
 <script>
 /*********************************全局变量******************************  */
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
 
 /**************************************提示弹窗********************************************/
 function jquerytoast(id,title,message,width){
 		$.Toast(title, message , "error", {
		  					appendTo: id,
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
 
 /**********************************全屏监听********************************  */
 	//isFullscreenForNoScroll();
 	
 	
    </script>
</head>
<body>

</body>

</html>
