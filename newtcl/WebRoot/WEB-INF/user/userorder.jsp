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
	}
	
	.orderstatus{
		text-align: right;
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
	
</style>

  </head>
  
  <body>
 	<div id="orderbox">
 		<ul>
 			<li></li>
 			<li></li>
 		</ul>
 	</div>
  </body>
 
  <script type="text/javascript">
  	
  	function uload(){
  		$.post("<%=path%>/order/findtouser.do",{},function(data){
  			var str = "<ul>";
  			for(var i=data.length-1;i>=0;i--){
  				if(1 == data[i].makestatus)
  					str+="<li class='complete'";
  				else
  					str+="<li class='uncomplete'";
				  				
  				str+="id=\"o"+data[i].id+"\" onclick=\"toorderdetails("+data[i].id+")\">"
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
  			windowauto();
  		});
  	}
  	
  	
  	/******************************************订单点击事件****************************************/
  	function toorderdetails(){
  	
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
