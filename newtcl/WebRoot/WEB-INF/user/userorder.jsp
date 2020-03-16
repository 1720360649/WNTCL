<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../header/util.jsp" %>
<%
 path = request.getContextPath();
 basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的订单</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<style type="text/css">
	
	#orderbox li{
		overflow:hidden;
		margin: 0 auto;
		margin-top:25px;
		border: 1px solid black;
		border-radius: 5px;
		cursor: pointer;
	
		/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */
		-webkit-user-select: none; /* Chrome/Safari/Opera */
		-khtml-user-select: none; /* Konqueror */
		-moz-user-select: none; /* Firefox */
		-ms-user-select: none; /* Internet Explorer/Edge */
		user-select: none;
		unselectable
		="on"
		
		/*阴影*/
		
	   -moz-box-shadow: 2px 2px 10px #909090;

	   -webkit-box-shadow: 2px 2px 10px #909090;
	
	   box-shadow:2px 2px 10px #909090;
	   
	   /*动画过渡*/
	   transition: 0.4s;
	   	-moz-transition: 0.4s;	/* Firefox 4 */
		-webkit-transition: 0.4s;	/* Safari 和 Chrome */
		-o-transition: 0.4s;	/* Opera */
	}

	#orderbox li div {
		float: left;
	}
	
	.ordertime{
		background: #37A6EB;
	}
	
	.orderid{
		text-align: right;
		background: #37A6EB;
	}
	
	.ordertotal{
		text-align: center;
		border-bottom: 1px solid black;
	}
	
	.orderstatus{
		text-align: right;
		border-bottom: 1px solid black;
	}
	
	.complete{
		color:gray;
	}
	
	.complete .ordertime{
		background: rgba(0,0,0,0.1);
	}
	
	.complete .orderid{
		background: rgba(0,0,0,0.1);
	}
	
	.orderDetailed{
		width:500px;
		height: 500px;
	}
	
</style>

  </head>
  
  <body>
 	<div id="orderbox">
 		<ul>
 		</ul>
 	</div>
 	
  </body>
 
  <script type="text/javascript">
  //标记点击事件状态
  var click = 0;
  //标记上次点击对象
  var lastObj = null;
  	
  	function uload(){
  		$.post("<%=path%>/order/findtouser.do",{},function(data){
  			if(data != null){
  				var str = "<ul>";
	  			for(var i=data.length-1;i>=0;i--){
	  				if(1 == data[i].makestatus)
	  					str+="<li class='complete'";
	  				else
	  					str+="<li class='uncomplete'";
					  				
	  				str+="id=\"o"+data[i].id+"\" onclick=\"toorderdetails(this,"+data[i].id+")\">"
	  				+"<div class='ordertime'>&nbsp;"+data[i].time+"</div>"
	  				+"<div class='orderid'>订单号:"+data[i].id+"&nbsp;&nbsp;&nbsp;</div>"	
	  				+"<div class='ordertotal'>"+data[i].total+"</div>"
	  				
	  				if(1 == data[i].makestatus)
	  					str+="<div class='orderstatus'>已完成&nbsp;</div>";
	  				else
	  					str+="<div class='orderstatus'>订单内有商品未完成配送&nbsp;</div>";
	  				 
	  				str+="</li>";
	  			}
	  			
	  			str+="</ul>";
	  			$("#orderbox").html(str);
  			}else{
  				$("#orderbox").html("<div>未查询到用户信息,请<a href='https://wntcl.top/login/login.jsp'>登陆</a>或重试!</div>");
  			}
  			
  			windowauto();
  		});
  	}
  	
  	
  	/******************************************订单点击事件****************************************/
  	function toorderdetails(obj,id){
  		$("li").css({height: windowHeight*0.15+"px"});
  	
  		if($(".orderDetailed").siblings().length < 1){
  			select(obj,id);
  			click = 1;
  			lastObj = obj;
  		}else{
  			$(".orderDetailed").remove();
  			if(click == 1){
  				if(lastObj == obj){
  					$(obj).css({height: windowHeight*0.15+"px"});
  					click = 0;
  					lastObj = null;
  				}else{
  					select(obj,id);
		  			lastObj = obj;
  				}
  			}else{	
  				click = 0;
  				lastObj = null;
  			}
  		}
  	}
  
  	//订单详情查询及渲染
  	function select(obj,id){
  		
  		//请求后台获取数据
  		$.post("<%=path%>/orderline/getOrderListforid.do",{id:id},function(data){
  			var str="<div class='orderDetailed' style='height:"+windowHeight*0.09*data.length+"px;"
  			+"width:"+windowWidth*0.95+"px;'><ul>";
  		
  			for(var i=0;i<data.length;i++){
  				str+="<li style='border:none;border-bottom:1px solid green; box-shadow:none;margin:0px;margin-left:"
  				+windowWidth*0.01+"px;height:"+windowHeight*0.09+"px;border-radius: 0px;"
  				+"width:"+windowWidth*0.94+"px;'><div class='photo' style='width:"+windowHeight*0.09*1.5+"px;"
  				+"height:"+windowHeight*0.09+"px;margin-left:"+windowHeight*0.01+"px;margin-top:"+windowHeight*0.01+"px;'><img src='<%=path%>/"
  				+data[i].photo+"' style='height:"+windowHeight*0.07+"px;width:"+windowHeight*0.09*1.5+"px;'/></div>"
  				+"<div class='name' style='width:"+windowHeight*0.09*1.5+"px;"
  				+"height:"+windowHeight*0.09+"px;line-height:"+windowHeight*0.09+"px;margin-left:"+windowHeight*0.01+"px;font-size:"+windowHeight*0.03+"px;color:black'>"
  				+data[i].name+"</div><div class='price' style='width:"+windowHeight*0.09*1.5+"px;"
  				+"height:"+windowHeight*0.09+"px;line-height:"+windowHeight*0.09+"px;margin-left:"+windowHeight*0.01+"px;font-size:"+windowHeight*0.03+"px;color:black'>"
  				+data[i].price+"</div><div class='number' style='width:"+windowHeight*0.09*1.5+"px;"
  				+"height:"+windowHeight*0.09+"px;line-height:"+windowHeight*0.09+"px;margin-left:"+windowHeight*0.01+"px;font-size:"+windowHeight*0.03+"px;color:black'>"
  				+data[i].number+"</div>"
  				+"</li>";
  			}
  			str+="</ul></div>";
  			$(obj).css({height: windowHeight*(0.09*data.length+0.15)+10+"px"});
			$(obj).append(str);
			$(".orderDetailed").css({
				width: windowWidth*0.95+"px",
				height: windowHeight*0.09*data.length+"px"
			});
  		});
  		
  	}
 
	
	/******************************************动态CSS**********************************************/
  	function windowauto(){
  		$("#orderbox ul li").css({
  			width: windowWidth*0.95+"px",
  			height: windowHeight*0.15+"px"
  		});
  		
  		$(".ordertime").css({
  			width: windowWidth*0.95*0.5+"px",
  			height: windowHeight*0.15*0.3+"px",
  			lineHeight: windowHeight*0.15*0.3+"px",
  			fontSize: windowHeight*0.03+"px",
  		});
  		
  		$(".orderid").css({
  			width: windowWidth*0.95*0.5+"px",
  			height: windowHeight*0.15*0.3+"px",
  			lineHeight: windowHeight*0.15*0.3+"px",
  			fontSize: windowHeight*0.02+"px",
  		});
  		
  		$(".ordertotal").css({	
  			width: windowWidth*0.95*0.5+"px",
  			height: windowHeight*0.15*0.7+"px",
  			lineHeight: windowHeight*0.15*0.7+"px",
  			fontSize: windowHeight*0.05+"px",
  			fontWeight: "600",
  		});
  	
  		$(".orderstatus").css({
  			width: windowWidth*0.95*0.5+"px",
  			height: windowHeight*0.15*0.7+"px",
  			lineHeight: windowHeight*0.15*0.7+"px",
  			fontSize: windowHeight*0.03+"px",
  		});
  	}
 
  </script>
  
</html>
