<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="../../header/util.jsp" %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>厨房大厅</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
	
	.kitchen_box{
		position:absolute;
		width: 100%;
		height: 100%;
		/*渐变*/
		background: -webkit-linear-gradient(#0585E0, white); /* Safari 5.1 - 6.0 */
		background: -o-linear-gradient(#0585E0, white); /* Opera 11.1 - 12.0 */
		background: -moz-linear-gradient(#0585E0, white); /* Firefox 3.6 - 15 */
		background: linear-gradient(#0585E0, white); /* 标准的语法 */
	}

	.kitchen_overview{
		position:fixed;
		width: 100%;
		height: 160px;
		top:0px;
		font-size:60px;
		line-height:140px;
		text-align:center;
		background: black;
	}
	
	.kitchen_overview_left{
		float:left;
		width: 30%;
		height: 160px;
		color:white;
	}
	
	.kitchen_overview_right{
		float:left;
		width: 70%;
		font-size:70px;
		font-weight:900;
		color:red;
		height: 160px;
	}

	.kitchen_current{
		width: 96%;
		height: auto;
		top:200px;
		left: 0px;
		right: 0px;
		margin:auto;
		margin-top:180px;
		margin-bottom:300px;
		background: white;
		
	}
	
	.kitchen_current li{
		display:block;
		width: 90%;
		height: 180px;
		border-bottom: 1px gray solid;
		margin: 0 auto;
	}
	
	.kitchen_current_left{
		float:left;
		height: 100%;
		width: 80%;
		font-size:60px;
		line-height:180px;
	}
	
	.kitchen_current_right{
		float:left;
		height: 100%;
		width: 20%;
		font-size:80px;
		text-align:center;
		line-height:180px;
		 
	}
	
	.kitchen_operation{
		position:fixed;
		width: 100%;
		height: 130px;
		bottom:0px;
		color:white;
		background: black;
	}
	
	.kitchen_operation_current{
		position:absolute;
		left:0ox;
		width: 80%;
		height:100%;
		margin: auto;
	}
	
	.kitchen_operation_current_left{
		float:left;
		height: 100%;
		line-height:60px;
		font-size:42px;  
	}
	
	.kitchen_operation_current_right{
		float:left;
		height: 100%;
		width:75%;
		line-height: 130px;
		font-size:48px;
		text-align: center;
	}

	.kitchen_operation_button{
		position: absolute;
		width: 200px;
		height: 130px;
		right: 0px;
		top:0px;
		bottom:0px;
		color:white;
		line-height:130px;
		font-size:58px;
		font-weight:900;
		border:none;
		margin: auto;
		background: rgb(20,255,66);
	}
		
</style>

  </head>
  
  <body>
	<div class="kitchen_box">
		<div class="kitchen_overview">
				<div class="kitchen_overview_left">大厅菜品:</div>
				<div class="kitchen_overview_right"></div>
		</div>
		<div class="kitchen_current"></div>
		<div class="kitchen_operation">
			<div class="kitchen_operation_current">
				<div class="kitchen_operation_current_left">当前制作:</div>
				<div class="kitchen_operation_current_right"></div>
			</div>
			<button class="kitchen_operation_button" >完成</button>
		</div>
	</div>
  </body>
  
  <script type="text/javascript">
  
  	//刷新时间
  	var t = 60000;
  	var timer;
		
		//初始化函数
		function uload(){
			listload();
			timer = setInterval(function() {
				listload();
			}, t);
		}
		//窗口自适应
		function windowauto(){
		
		}
		
		//列表加载器
		function listload(){
			$.post("<%=path%>/kitchenhall/getlist.do",{},function(data){
				var basePath = "<%=path%>/";
				
				if(data[0].number == 0){
					var one = "";
  					one+='<div class="kitchen_operation_current">'
					+'<div class="kitchen_operation_current_left">当前制作:</div>'
					+'<div class="kitchen_operation_current_right">当前无菜品需要制作</div>'
					+'</div>'
					+'<button class="kitchen_operation_button" style="background:gray;">已完成</button>'
					
  					$(".kitchen_operation").html(one);
  				}else{
  					//设置当前制作
  					var one = "";
  					one+='<div class="kitchen_operation_current">'
					+'<div class="kitchen_operation_current_left">当前制作:</div>'
					+'<div class="kitchen_operation_current_right">'+data[0].name+'x'+data[0].number+'</div>'
					+'</div>'
					+'<button class="kitchen_operation_button" onclick="remove()">已完成</button>'
					
  					$(".kitchen_operation").html(one);
  					
  					//设置预览
  					var str = "<ul>";
  					for(var i=1;i<data.length;i++){
  						str+="<li>"
  						+'<div class="kitchen_current_left">'+data[i].name+'</div>'
						+'<div class="kitchen_current_right">x'+data[i].number+'</div>'
  						+"</li>";
  					}
  					str+="</ul>";
  					
  					$(".kitchen_current").html(str);
  				}
  			
  				//顺便加载大厅菜总数
  				numberload();
  				
  				if(data.length == 4){
					t = 60000;
					clearInterval(timer);
					timer = setInterval(function() {
						listload();
					}, t);
				}else{
					t = 3000;
					clearInterval(timer);
					timer = setInterval(function() {
						listload();
					}, t);
				}
  			}, "JSON");
		}
		
		//完成/移除当前菜
		function remove(){
			$.post("<%=path%>/kitchenhall/remove.do",{},function(data){
				if(data.code == "1"){
					listload();
				}else{
					alert(data.message);
					listload();
				}			
  			}, "JSON");
		}
		
		//获取大厅总数
		function numberload(){
			$.post("<%=path%>/kitchenhall/getkitchenhallnumber.do",{},function(data){
			
				if(data == null || data<1){
					$(".kitchen_overview_right").html(0);
				}else{
					$(".kitchen_overview_right").html(data);
				}
			
  			}, "JSON");
		}
		  
  </script>
  
</html>
