<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../header/util.jsp" %>
<%
 path = request.getContextPath();
 basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>餐厅后台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	#aside{
       	background: #DFDEE3;
       	position: fixed;
       	left:0;
       	height: 100%;
       	width: 15%;
       	border-right: #cccccc 1px solid;
    }
   
    .menuType{
    	height: 10%;
    	text-align: center;
    	cursor:pointer;
    	
    	/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
    }
    
    .menuActive{
       	color: #5bc0de;
       	background: white;
    }
    
    .showbox{
    	display: none;
    }
    
    .Activebox{
    	display:block;
    }
    
    #essential{
    	height: 100%;
    	width: 85%;
    	margin-left: 15%;
    }
    
    #essential_top{
    	overflow:hidden;
    	height: 25%;
    	width: 100%;
    	border:none;
    	border-bottom:1px solid gray;
    }
    
    #essential_top_on{
    	height: 65%;
    	width: 100%;
    	border:none;
    	margin-top:0px;
    }
   
    #essential_top_on_img{
    	float:left;
    	margin-left:20px;
    	display: block;
    }
   
    #essential_top_on_txt{
    	float:left;
    	margin-left: 20px;
    }
    
    #essential_top_on_meassage{
    	float:left;
    	margin-left: 20px;
    }
    
    #essential_top_on_meassage li{
    	float: left;
    	width:50%;
    }

	#essential_bottom{
		overflow:hidden;
		height: 72%;
    	width: 100%;
	}
	
	#flotbox{
		text-align:center;
		color:rgba(0,0,0,0.5);
		background: rgba(0,0,0,0.05);
		
		/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
	
	}

	</style>

  </head>
  
  <body>
 
  	<aside id="aside">
	        <div class="menuType menuActive">基本信息</div>
	        <div class="menuType">我的店铺</div>
	        <div class="menuType">我的员工</div>
	        <div class="menuType">用户订单</div>
    </aside>
    
    <div id='essential' class="showbox Activebox">
    	<div id="essential_top">
    		<div id="essential_top_on">
    			<img id="essential_top_on_img" alt="" src="<%=path %>/img/wechat.png">
    			<div id="essential_top_on_txt">店铺名</div>
    			<div id="essential_top_on_meassage">
    				<ul>
    					<li>&nbsp;电话:</li>
    					<li>邮箱:</li>
    					<li>QQ:</li>
    					<li>支付宝:</li>
    				</ul>
    			</div>
    		</div>
    	</div>
    	<div id="essential_bottom"><div id="flotbox"></div></div>
    </div>
    <div id='shop' class="showbox">
    	
    </div>
    <div id='staff' class="showbox">
    	
    </div>
    <div id='orders' class="showbox">
    	
    </div>
  
  </body>
  
  <script type="text/javascript">
  //定义flot数据
  var flotdata = null;
  
  	function uload(){
  	
	  	//加载店铺基本信息!
	  	$.post("<%=path%>/supporter/getme.do",{},function(data){
	  		if(data == null){
	  			alert("获取登陆信息失败!请重新登陆!");
	  			window.location.href="http://wntcl.top/login/login.jsp";
	  		}else{
	  			$("#essential_top_on_txt").html(data.name);
	  			
	  			var str = "<ul>";
	  			str+="<li>电话:&nbsp;&nbsp;"+data.phone+"</li>";
	  			
	  			if(data.qqOpenId == null || data.qqOpenId == ""){
	  				str+="<li>QQ:&nbsp;&nbsp;未绑定</li>";
	  			}else{
	  				str+="<li>QQ:&nbsp;&nbsp;已绑定</li>";
	  			}
	  			
	  			if(data.alipayOpenid == null || data.alipayOpenid == ""){
	  				str+="<li>邮箱:&nbsp;&nbsp;未绑定</li>";
	  			}else{
	  				str+="<li>邮箱:&nbsp;&nbsp;"+data.email+"</li>";
	  			}
	  			
	  			if(data.alipayOpenid == null || data.alipayOpenid == ""){
	  				str+="<li>支付宝:&nbsp;&nbsp;未绑定</li>";
	  			}else{
	  				str+="<li>支付宝:&nbsp;&nbsp;已绑定</li>";
	  			}
	  			str += "</ul>";
	  			
	  			$("#essential_top_on_meassage").html(str);
	  			
	  			windowauto();
	  		}
	  	});
  		
  		//加载flot绘图!
  		$.post("<%=path%>/supporter/businessanalysis.do",{},function(data){
			flotdata = data;
  		});
  		
  	}
  	
  	/*************************************float绘图********************************/
	function flotplay(){
		if(flotdata == null){
			$("#flotbox").html("暂无数据分析结果!");
		}else{
			alert(flotdata);
		}
	}	
	
  	//*************************************自适应***********************************/
  	function windowauto(){
  		$(".menuType").css({
  			"line-height":windowHeight*0.1+"px",
  			"font-size":windowHeight*0.03+"px"
  		});
  
  		$("#essential_top_on").css({
  			"margin-top":(windowHeight*0.25)*0.175+"px"
  		});
  		
  		$("#essential_top_on_img").css({
  			"Height": (windowHeight*0.25)*0.65+"px",
  			"width":(windowHeight*0.25)*0.65+"px"
  		});
  		
  		$("#essential_top_on_txt").css({
  			"height":((windowHeight*0.25)*0.65)*0.3+"px",
  			"width":(windowWidth*0.85-((windowHeight*0.25)*0.65+40))+"px",
  			"line-height":((windowHeight*0.25)*0.65)*0.3+"px",
  			"font-size":windowHeight*0.04+"px"
  		});
  		
  		$("#essential_top_on_meassage").css({
  			"height":((windowHeight*0.25)*0.65)*0.6+"px",
  			"width":(windowWidth*0.85-((windowHeight*0.25)*0.65+40))+"px",
  			"margin-top":((windowHeight*0.25)*0.65)*0.1+"px",
  			"font-size":windowHeight*0.025+"px"
  		});
  		
  		$("#essential_top_on_meassage li").css({
  			"height":((windowHeight*0.25)*0.65)*0.3+"px",
  			"width":((windowWidth*0.85-((windowHeight*0.25)*0.65+40))/2)+"px",
  			"line-height":((windowHeight*0.25)*0.65)*0.3+"px"
  		});
  	
  		$("#flotbox").css({
  			"height":windowHeight*0.6+"px",
  			"width":windowHeight*0.7+"px",
  			"line-height":windowHeight*0.6+"px",
  			"font-size":windowHeight*0.05+"px",
  			"margin": windowHeight*0.075+"px auto"
  		});
  		
  		//将float函数置于自适应函数内以适应宽高
  		flotplay();
}

</script>
  
</html>
