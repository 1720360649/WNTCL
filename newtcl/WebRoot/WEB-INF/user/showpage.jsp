<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@include file="../../header/util.jsp" %>
<html>
<head>
    <title>首页</title>
    
    <style>
    a{ 
	    text-decoration:none !important;
	}
    
    #goodsbox{
    	height: 100%;
    	overflow-x: hidden;
    	/*overflow-y: auto;*/
        /*overflow: auto;*/
    	
    }
    
    	#aside{
            background: #DFDEE3;
            position: fixed;
            left:0;
            height: 100%;
            width: 20%;
            border-right: #cccccc 1px solid;
    	}
        #main{
        	position:absolute;
        	left:20%;
        	height: 100%;
            width: 80%;
            background: white;
        }
        
      .menuType{
            width: 100%;
            height: 10%;
           	background: #DFDEE3;
            text-align: center;
            line-height: 70px;
            border-bottom:1px solid gray;
            cursor: pointer;
        }
        
        .menuActive{
            color: #5bc0de;
            background: white;
        }
        
        .goodsshow_good{
        	float:left;
        	border: 2px solid gray;
         	border-radius: 5px;
        }
        
      	.goodsshow_good_img{
      		width: 100%;
      		height: 64%;
      	}
        
        .goodsshow_good_name{
        	width: 90%;
        	height: 16%;
        	font-size: 34px;
        	margin-left: 5%;
        	margin-top:2%;
        }
       
        .goodsshow_good_price{
        	width: 90%;
        	height: 18%;
        	font-size:40px;
        	margin-left:5%;
        	color:red;
        }
      
        .goodsshow_good_add{
        	float: right;
        }
       
         .goodsshow_good_add img{
        	width: 100%;
        	height: 100%;
        }
        
    #shopping_cart{
		position:fixed;
		width:20px;
		height:10px;
		bottom:0px;
		background:rgba(0,0,0,0.7);
	}
	
	.shopping_icon{
		position:absolute;
		color:gray;
		border-radius:1000px;
		background: rgba(0,0,0,0.5);
		z-index: 1000;
	}
	
	.shopping_icon_after{
		position:absolute;
		top:0px;
		right:0px;
		bottom:0px;
		left:0px;
		margin:auto;
		border-radius:1000px;
		background: rgba(0,0,0,1);
	}

	.shopping_icon i{
		position: absolute;
		width:40px;
		height: 40px;
		top: 0px;
		right:0px;
		bottom:0px;
		left:0px;
		margin: auto;
	}	
	
	.shopping_txt{
		position: absolute;
		height:100%;
		width:300px;
		color:gray;
		font-size: 32px;
	}
	
	.shopping_orders{
		position: absolute;
		width:100%;
		height:auto;
		background: white;
		z-index: 999;
		display: none;
	}
	
	.shopping_order_txt{
		width: 100%;
		font-size: 32px;
		font-weight: 900;
		background : rgba(216,222,245);	
	}
	
	.shopping_order_list li{
		border:none;
		border-bottom: 1px solid;
	}

	.shopping_order_list_name{
		float:left;
		width:55%;
		height:100%;
		font-size: 40px;
		color:black;
	}
	
	.shopping_order_list_price{
		float:left;
		width:15%;
		height:100%;
		font-size: 38px;
		font-weight:600;
		color:red;
	}
	
	.shopping_order_list_number{
		float:right;
		width:30%;
		height:100%;
		font-size: 40px;
		color:black;
	}
	
	.shopping_order_list_number_remove{
		float:left;
	}
	
	.shopping_order_list_number_add{
		float:left;
	}
	
	.shopping_order_list_number_number{
		float:left;
		text-align: center;
	}
	
	.shopping_gopayment{
		position:absolute;
		right:0px;
		bottom:0px;
		color:white;
		font-size:42px;
		text-align:center;
		background: #47EB60;
		z-index: 1000;
		display:none;
	}
	
	
    </style>
</head>
<body>

	<div id="goodsbox">
		<aside id="aside">
	        <div class="menuType menuActive">加载中</div>
    	</aside>
    
    	<div id="main">
    		暂无商品
    	</div>
	</div>
   
    <div id="shopping_cart" onclick="on_shopping_cart()"> 
  		<div class="shopping_icon">
  			<div class="shopping_icon_after"></div>
  			<i class="fa fa-shopping-cart fa-3x"></i>
  		</div>
  		<div class="shopping_txt">您还没有选择商品</div>
  		<div class="shopping_orders">
  			<div class="shopping_order_txt">&nbsp;&nbsp;已选商品</div>
  			<div class="shopping_order_list">
  				<ul>
  					
  				</ul>
  			</div>
  		</div>
  	</div>
  	<a href="<%=path%>/views/payment.do"><div class="shopping_gopayment">去付款</div></a>
</body>

<script type="text/javascript">
    
    //有无商品
  var pd_shopping = false;
  //购物车开启状态
  var pd_shoppingcart = false;
  
  var basePath= "<%=basePath%>";
  
  var shopping_txt_status = true;
  
/***********************************************用户初始化***************************************************/
    function uload(){
 
  			$.post("<%=path%>/goods/showgoodstotype.do",{manager_id:1},function (data) {
              $("#goodsbox").html(''+data.html+'');
              windowauto();
              $('html,body').animate({scrollTop:0}, 500);
            });
            
            shoppingLoad();
            
  	}
  		
  		
/***********************************************购物车加载*************************************************/
  		function shoppingLoad(){
  			$.post("<%=path%>/shoppingcrat/find.do",{}, function(data) {
	  			if(data[0].number == 0){
	  					pd_shopping = false;
	  					pd_shoppingcart = false;
	  					shopping_txt_status = true;
	  					$(".shopping_orders").css({
							"display" : "none"  					
	  					});
	  					$(".shopping_txt").html("您还没有选择商品");
	  					$(".shopping_txt").css({
							"color" : "gray",
							"margin-left" : "0px"
						});
						
						$(".shopping_icon_after").css({
							"background" : "black"
						});
						
						$(".shopping_icon i").css({
							"color" : "gray" 
						});
						
						$(".shopping_gopayment").css({
							"display" : "none"
						});
						
						windowauto();
  				}else{
					var str = "<ul>";
					var price = 0;
					
					for(var i=0;i<data.length;i++){
						str+="<li>"
						+"<div class='shopping_order_list_name'>"
						+data[i].name+"</div>"
						+"<div class='shopping_order_list_price'>¥"
						+data[i].price+"</div>"
						+"<div class='shopping_order_list_number'>"
						+"<div class='shopping_order_list_number_remove' onclick='goodsshow_good_remove("+data[i].id+")'><img src='<%=path%>/img/remove.png' /></div>"
						+"<div class='shopping_order_list_number_number'>"
						+data[i].number+"</div>"
						+"<div class='shopping_order_list_number_add' onclick='goodsshow_good_add("+data[i].id+")'><img src='<%=path%>/img/add.png' /></div></div>"
  						+"</li>";
  						price+=data[i].price*data[i].number;
					}
					str+="</ul>";
					
					$(".shopping_order_list").html(str);
					$(".shopping_txt").html("¥"+price);
					$(".shopping_txt").css({
						"color" : "red",
						"font-size" : "48px",
						"margin-left" : "20px"
					});
					
					$(".shopping_icon_after").css({
						"background" : "#2979FF"
					});
					
					$(".shopping_icon i").css({
						"color" : "white" 
					});
					
					$(".shopping_gopayment").css({
						"display" : "block"
					});
					
					pd_shopping = true;
					shopping_txt_status = false;
				
					windowauto();
  				}
  
  			}, "JSON");
  			
  		}
  
  
/*************************************************分类跳转***************************************************/      
      function gottype(obj,line){
      	$('html,body').animate({scrollTop:(((windowHeight*0.43)*line)+(windowHeight*0.03))}, 500);
      	$(".menuType").removeClass("menuActive");	
      	$(obj).addClass("menuActive");
      }
   
/**************************************************购物车按钮事件********************************************/
      //购物车点击事件
      function on_shopping_cart(){
  			if(pd_shopping){
  				if(pd_shoppingcart){
  					$(".shopping_orders").css({
  						display : "none"
  					});
  					pd_shoppingcart = false;
  				}else{
  					$(".shopping_orders").css({
  						display : "block"
  					});
  					pd_shoppingcart = true;
  				}
  			}
  		}
      	//购物车  商品 增加
        function goodsshow_good_add(id){
        	$.post("<%=path%>/shoppingcrat/add.do",{"id":id}, function(data) {
        		shoppingLoad();
  			});
  			
  			var e=arguments.callee.caller.arguments[0] || window.event; // 兼容火狐
        	e.stopPropagation(); //防止事件冒泡
        }
        //购物车 商品 减少
        function goodsshow_good_remove(id){
        	$.post("<%=path%>/shoppingcrat/delete.do",{"id":id}, function(data) {
        		shoppingLoad();
  			});
        	
        	var e=arguments.callee.caller.arguments[0] || window.event; // 兼容火狐
        	e.stopPropagation(); //防止事件冒泡
        }
        
     
/*************************************************自适应****************************************************/
        
        function windowauto(){
  			
  			$("body").css({
	  			"width" : windowWidth+"px",
	  			"height" : windowHeight+"px"
  			});	
  			
  			$("#aside div").css({
  				"width":windowWidth*0.2+"px",
  				"height":windowHeight*0.1+"px",
  				"line-height":windowHeight*0.1+"px",
  				"line-height":windowHeight*0.1+"px",
  				"font-size":windowHeight*0.05+"px"
  			});
  			
  			$(".goodsshow_good").css({
  				"height":windowHeight*0.4+"px",
  				"width":windowWidth*0.35+"px",
  				"margin-left":(windowWidth*0.033)-4+"px",
  				"margin-top":windowHeight*0.03+"px"
  			});
  			
  			$(".goodsshow_good_img").css({
  				"height":windowHeight*0.3+"px",
  				"width":windowWidth*0.35+"px"
  			});
  			
  			$(".goodsshow_good_img img").css({
  				"height":windowHeight*0.3+"px",
  				"width":windowWidth*0.35+"px"
  			});
  			
  			$(".goodsshow_good_name").css({
  				"height":windowHeight*0.04+"px",
  				"line-height" : windowHeight*0.04+"px",
  				"font-size":windowHeight*0.03+"px"
  			});
  			
  			$(".goodsshow_good_price").css({
  				"height":windowHeight*0.05+"px",
  				"line-height" : windowHeight*0.05+"px",
  				"font-size":windowHeight*0.04+"px",
  			});
  			
  			$(".goodsshow_good_add").css({
  				"width" : windowHeight*0.45*0.18+'px',
  				"height" : windowHeight*0.45*0.18+'px',
  				"margin-top":-(windowHeight*0.035)+"px"
  			});
  			
  			$(".shopping_gopayment").css({
  				"width" :windowHeight*0.2+"px",
  				"height" :windowHeight*0.1+"px",
  				"line-height" : windowHeight*0.1+"px",
  				"font-size":windowHeight*0.05+"px"
  			});
  		
  			$("#shopping_cart").css({
  				"width":windowWidth+"px",
  				"height":windowHeight*0.1+"px",
  				"bottom": "0px"
  			});
  			
  			$(".shopping_icon").css({
  				"width":windowHeight*0.1+"px",
  				"height":windowHeight*0.1+"px",
  				"left":(windowHeight*0.1)*0.5+"px",
  				"top":-(windowHeight*0.1)*0.2+"px"
  			});
  			
  			$(".shopping_icon_after").css({
  				"width":windowHeight*0.08+"px",
  				"height":windowHeight*0.08+"px"
  			});
  			
  			if(shopping_txt_status){
  				$(".shopping_txt").css({
  				"width":(windowWidth-((windowHeight*0.1)*1.5+10))+"px",
  				"left" : (windowHeight*0.1)*1.5+10+"px",
  				"line-height": windowHeight*0.1+"px",
  				"font-size":windowHeight*0.06+"px"
  				});
  			}else{
  				$(".shopping_txt").css({
  				"width":(windowWidth*0.4)+"px",
  				"left" : (windowHeight*0.1)*1.5+10+"px",
  				"line-height": windowHeight*0.1+"px",
  				"font-size":windowHeight*0.06+"px"
  				});
  			}
  		
  			$(".shopping_orders").css({
  				"bottom" : (windowHeight*0.1-1)+"px"
  			});
  			
  			$(".shopping_order_txt").css({
  				"height": windowHeight*0.06+"px",
  				"line-height": windowHeight*0.06+"px",
  				"font-size":windowHeight*0.04+"px"
  			});
  	
  			$(".shopping_order_list li").css({
				"height" : windowHeight*0.1+"px",
				"width"  : windowWidth*0.97+"px",
				"line-height" : windowHeight*0.1+"px",
				"margin-left" : windowWidth*0.03+"px"
  			});
  			
  			$(".shopping_order_list_number").css({
  				"height" : windowHeight*0.1+"px",
				"width"  : windowHeight*0.3+"px"
  			});
  		
  			$(".shopping_order_list_price").css({
  				"height":windowHeight*0.1+"px",
				"width":((windowWidth*0.97)-(windowHeight*0.3))*0.25+"px",
  				"line-height": windowHeight*0.1+"px",
  				"font-size":windowHeight*0.05+"px"
  			});
  
  			$(".shopping_order_list_name").css({
  				"line-height": windowHeight*0.1+"px",
  				"width":((windowWidth*0.97)-(windowHeight*0.3))*0.7+"px",
  				"font-size":windowHeight*0.045+"px"
  			});
  	
  			$(".shopping_order_list_number_remove").css({
  				"height" : windowHeight*0.1+"px",
				"width"  :windowHeight*0.1+"px"
  			});
  			
  			$(".shopping_order_list_number_number").css({
  				"height" : windowHeight*0.1+"px",
				"width"  : windowHeight*0.1+"px"
  			});
  			
  			$(".shopping_order_list_number_add").css({
  				"height" : windowHeight*0.1+"px",
				"width"  : windowHeight*0.1+"px"
  			});
  			
  			$(".shopping_order_list_number img").css({
  				"height" : windowHeight*0.1+"px",
				"width"  : windowHeight*0.1+"px"
  			});
  		}
     
        
    </script>
</html>
