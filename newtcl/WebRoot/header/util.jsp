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
    
    <link type="text/css" rel="stylesheet" href="<%=path %>/utils/font_awesome/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="<%=path %>/utils/toastr/toastr.min.css">
    <link type="text/css" rel="stylesheet" href="<%=path %>/utils/layui/css/layui.css">
    
    <script type="text/javascript" src="<%=path %>/utils/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=path %>/utils/toastr/toastr.min.js"></script>
 	<script type="text/javascript" src="<%=path %>/utils/layui/layui.js"></script>
 	<script type="text/javascript" src="<%=path %>/utils/flot-2.1.3/source/jquery.flot.js"></script>
 	
	
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
   
        
 /*******************************toastr弹窗初始化**************************  */
            toastr.options = {
                "showDuration": "300",//显示的动画时间
                "hideDuration": "500",//消失的动画时间
                "timeOut": "2000", //展现时间
                "extendedTimeOut": "1000",//加长展示时间
                "positionClass": "toast-top-center",//展示位置
                "showEasing": "swing",//显示时的动画缓冲方式
                "hideEasing": "linear",//消失时的动画缓冲方式
                "showMethod": "fadeIn",//显示时的动画方式
                "hideMethod": "fadeOut" //消失时的动画方式
            };
        });
        
 /**********************************全屏监听********************************  */
 	//isFullscreenForNoScroll();
 	
 	
    </script>
</head>
<body>

</body>

</html>
