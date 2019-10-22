<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="../../header/util.jsp" %>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>待上桌</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
	
	.waiter_box{
		position:absolute;
		width: 100%;
		height: 100%;
		/*渐变*/
		background: -webkit-linear-gradient(#0585E0, white); /* Safari 5.1 - 6.0 */
		background: -o-linear-gradient(#0585E0, white); /* Opera 11.1 - 12.0 */
		background: -moz-linear-gradient(#0585E0, white); /* Firefox 3.6 - 15 */
		background: linear-gradient(#0585E0, white); /* 标准的语法 */
	}
	
	.waiter_current{
		width: 100%;
		height: auto;
		left: 0px;
		right: 0px;
	
	}
	
	.waiter_current ul{
		background: white;
	}

	.waiter_current_table{
		font-size: 70px;
		font-weight:900;
		color: white;
		text-align:center;
		background: #00FF7F;
	}
	
	.waiter_current_remove{
		float:right;
		font-size: 50px;
		font-weight:500;
		color: black;
		text-align:center;
		margin: 0px;
		background: #FFD700;
	}
	
	.waiter_current_li{
		display:block;
		width: 90%;
		border:none;
		border-bottom: 1px gray solid;
		margin-left:10%;
		background: white;
	}
	.waiter_current_left{
		overflow:hidden;
		float:left;
		height: 100%;
		width: 80%;
		font-size:60px;
		text-align: center;
	}
	
	.waiter_current_middle{
		float:left;
		height: 100%;
		width: 20%;
		color:red;
		font-size:80px;
		text-align:center;
		 
	}
	
	.waiter_current_right{
		float:left;
		height: 100%;
		width: 20%;
		color:blue;
		font-size:85px;
		text-align:center;
		 
	}
	
</style>

  </head>
  
  <body>
  	<div class="waiter_box">
	</div>
  </body>
  
  <script type="text/javascript">
  
  	//刷新时间
  	var t = 3000;
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

  function listload(){
			$.post("<%=path%>/waiter/getlist.do",{},function(data){
				
				if(JSON.stringify(data) == '{}'){
  					$(".waiter_box").html("<h2 style='color:white;backgroun:rgba(0,0,0,0);text-align: center;font-size:58px;margin-top:200px;'>当前无菜品需要上桌!</h2>");
  				}else{
  					//设置预览
  					var str = "<div class='waiter_current'><ul>";
  					//遍历桌号
  					for(var key in data){
  						var list = data[key];
  						
  						str+="<li>"
  						+"<div class='waiter_current_table' style='height:"+windowHeight*0.08+"px;line-height:"+windowHeight*0.08+"px;'>"+key+"号桌"
  						+"<button class='waiter_current_remove' onclick='remove("+key+")' style='height:"+windowHeight*0.08+"px;line-height:"+windowHeight*0.08+"px;'>完成传菜</button></div>";
  				
  						//遍历菜品
  						for(var i = 0;i<list.length; i++){
	  						str+="<div class='waiter_current_li' style='height:"+windowHeight*0.1+"px;line-height:"+windowHeight*0.1+"px;'>"
	  						+'<div class="waiter_current_left">'+list[i].name+'</div>'
							+'<div class="waiter_current_middle">x'+list[i].number+'</div>'
						//	+'<div class="waiter_current_right">'+list[i].table+'</div>'
	  						+"</div>";
  						}
  						
  						str+="</li>";
  					}
  					str+="</ul></div>";
  					
  					$(".waiter_box").html(str);
  				}
  	
  
  			}, "JSON");
		}
		
	function remove(id){
		$.post("<%=path%>/waiter/remove.do",{'table':id},function(data){
				
  				if(data.code == "1"){
  					//重新加载列表
  					listload();
  				}else{
  					//提示
  					alert(data.message);
  				}
  				
  			}, "JSON");
	}
	
  </script>
  
</html>
