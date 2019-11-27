<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@include file="../../header/util.jsp" %>

<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>付款页</title>
    

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	
	.payment_box{
		position:absolute;
		width: 100%;
		height: 100%;
		/*渐变*/
		background: -webkit-linear-gradient(#0585E0, white); /* Safari 5.1 - 6.0 */
		background: -o-linear-gradient(#0585E0, white); /* Opera 11.1 - 12.0 */
		background: -moz-linear-gradient(#0585E0, white); /* Firefox 3.6 - 15 */
		background: linear-gradient(#0585E0, white); /* 标准的语法 */
	}
	
	.payment_order{
		position:absolute;
		width: 100%;
		height: 100%;
		top:0px;
	}

	.payment_order_tablenumber{
		position:absolute;
		width: 100%;
		height: 180px;
		top:5px;
		display: none;
	}

	.payment_order_tablenumber_number{
		width:98%;
		height:auto;
	/*	border-left: 10px white solid;
		border-right: 10px white solid;
		border-radius: 50px; */
		color:white;
		font-size:100px;
		line-height: 170px;
		text-align: center;
		font-weight: 900;
		margin: 0 auto;
	/*	background: rgba(255,255,255,0.2);*/
	}

	.payment_order_details{
		width: 96%;
		height: auto;
		top:10px;
		left: 0px;
		right: 0px;
		margin:auto;
		margin-top:10px;
		margin-bottom:280px;
		background: white;
	}
	
	.payment_order_details li{
		display:block;
		width: 90%;
		height: 200px;
		border-bottom: 1px gray solid;
		margin: 0 auto;
	}
	
	.payment_order_details_lowbox{
		width: 100%;
		height: 35px;
	}
	
	.payment_order_details_box{
		width: 100%;
		height: 130px;
	}
	
	.payment_order_details_box div{
		line-height: 130px;
		text-align: center;
		font-size:32px;
		color:black;
	}
	
	.payment_order_details_img{
		float:left;
		width: 130px;
		height: 130px;
	}
	
	.payment_order_details_img img{
		width: 130px;
		height: 130px;
	}
	
	.payment_order_details_name{
		float:left;
		width: 39%;
		height: 100%;
	}
	
	.payment_order_details_number{
		float:left;
		width: 15%;
		height: 100%;
	}
	
	.payment_order_details_originalprice{
		float:left;
		width: 15%;
		height: 100%; 
	}
	
	.payment_order_details_unitprice{
		float:left;
		width: 15%;
		height: 100%;
	}
	
	.payment_confirm{
		position:fixed;
		width: 100%;
		height: 130px;
		bottom:0px;
		color:white;
		background: rgba(0,0,0,1);
	}
	
	.payment_confirm_price{
		position:absolute;
		left:0;
		width: 50%;
		height: 100%;
	}
	
	.payment_confirm_totalprice{
		float:left;
		height: 100%;
		font-size:55px;
		line-height:130px;
		margin:auto;
	}
	
	.payment_confirm_discount{
		float:left;
		height: 100%;
		font-size:30px;
		line-height:130px;
		color:gray;
		margin:auto;
	}
	
	.payment_confirm_pay{
		position: absolute;
		right: 0;
		width: 250px;
		height: 100%;
		font-size:55px;
		line-height:130px;	
		border:none;
		background: rgb(20,255,66);
	}
		
</style>
</head>

  <body>
	<div class="payment_box">
		<div class="payment_order">
			<div class="payment_order_tablenumber">
				<div class="payment_order_tablenumber_number">12号桌</div>
			</div>
			<div id="orderlist" class="payment_order_details"></div>
		</div>
		<div class="payment_confirm">
			<div class="payment_confirm_price">
				<div class="payment_confirm_totalprice">￥${ sessionScope.orders.total}</div>
				<div class="payment_confirm_discount"></div>
			</div>
			<button id="paybut" class="payment_confirm_pay" style="color:white;">支付</button>
		</div>
	</div>
  </body>
  
  <script type="text/javascript">
  	//加载判定
 	var loadinit = false;
 	//移动设备宽高
	 var MW = 0;
	 var MH = 0;
 
	function uload(){
	
		shoppingLoad();
		
		$("#paybut").click(function (){
			$.post("<%=path%>/order/add.do",{},function(data){
	  			if(data.code == "1"){
	  				alert("订单已提交,请耐心等待!");
	  				window.location.href ="<%=path%>/views/showpage.do";
	  			}else{
	  				alert(data.message);
	  				window.location.href ="<%=path%>/views/showpage.do";
	  			}
  			}, "JSON");
		});
	}
 	
  	function shoppingLoad(){
  			$.post("<%=path%>/shoppingcrat/find.do",{}, function(data) {
  				var basePath = "<%=path%>/";
  				if(data[0].number == 0){
											
  				}else{
					var str = "<ul>";
					var oldprice = 0;
					var price =0;
					
					for(var i=0;i<data.length;i++){
						str+="<li>"
						
						+'<div class="payment_order_details_lowbox"></div>'
						+'<div class="payment_order_details_box">'
						+'<div class="payment_order_details_img"><img src="'+basePath+data[i].photo+'?${Math.random()}"/></div>'
						+'<div class="payment_order_details_name" style="text-align: left;">&nbsp;&nbsp;&nbsp;'+data[i].name+'</div>'
						+'<div class="payment_order_details_number">X'+data[i].number+'</div>'
						+'<div class="payment_order_details_originalprice" style="color:gray;"><s>￥'+data[i].oldprice*data[i].number+'</s></div>'
						+'<div class="payment_order_details_unitprice">￥'+data[i].nowprice*data[i].number+'</div>'
						+'</div>'
  		
  						+"</li>";
  						
  						price+=data[i].price*data[i].number;
  						oldprice+=data[i].oldprice*data[i].number;
					}
					str+="</ul>";
					
					$("#orderlist").html(str);
					
					if(oldprice > price)
						$(".payment_confirm_discount").html("&nbsp;&nbsp;|&nbsp;已优惠￥"+(oldprice-price));
						
  				}
  
  				windowauto();
  			}, "JSON");
  	
  		}
  		
  /******************************* 移动设备判断 ********************************************/
function isMobile(){ 
	  if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i))
	  	return true; 
	  else
  		return false; 
  }
  	
  	function windowauto(){
  	
  		if(isMobile() && loadinit){
        		windowWidth = MW;
        		windowHeight = MH;
        	}else{
				MW = windowWidth;
				MH = windowHeight;
        	}
  		
  		$(".payment_order_details li").css({
  			height: windowHeight*0.13+"px",
  			lineHeight: windowHeight*0.13+"px",
  			fontSize: windowHeight*0.04+"px"
  		});
  		
  		$(".payment_order_details_lowbox").css({
  			height: windowHeight*0.0228+"px"
  		});
  		
  		$(".payment_order_details_box").css({
  			height: windowHeight*0.0845+"px"
  		});
  		
  		$(".payment_order_details_box div").css({
  			lineHeight: windowHeight*0.0845+"px"
  		});
  	
  		$(".payment_order_details_img").css({
  			width:windowHeight*0.0845+"px",
  			height: windowHeight*0.0845+"px"
  		});
  		
  		$(".payment_order_details_img img").css({
  			width:windowHeight*0.0845+"px",
  			height: windowHeight*0.0845+"px"
  		});
  		
  		$(".payment_order_details_name").css({
  			fontSize: windowHeight*0.03+"px"
  		});
  		
  		$(".payment_order_details_number").css({
  			fontSize: windowHeight*0.03+"px"
  		});
  		
  		$(".payment_order_details_originalprice").css({
  			fontSize: windowHeight*0.03+"px"
  		});
  		
  		$(".payment_order_details_unitprice").css({
  			fontSize: windowHeight*0.03+"px"
  		});
  		
  		$(".payment_confirm").css({
  			height: windowHeight*0.13+"px"
  		});
  		
  		$(".payment_confirm_totalprice").css({
  			lineHeight: windowHeight*0.13+"px",
  			fontSize: windowHeight*0.05+"px"
  		});
  		
  		$(".payment_confirm_discount").css({
  			lineHeight: windowHeight*0.13+"px",
  			fontSize: windowHeight*0.03+"px"
  		});
  		
  		$(".payment_confirm_pay").css({
  			width:windowWidth*0.2+"px",
  			lineHeight: windowHeight*0.13+"px",
  			fontSize: windowHeight*0.05+"px"
  		});
  		
  		loadinit = true;
  	}
 

  </script>
  
</html>		