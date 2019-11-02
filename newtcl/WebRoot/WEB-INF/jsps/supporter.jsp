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
       	position: fixed;
       	left:0;
       	height: 100%;
       	width: 15%;
       	color:white;
       	border-right: #cccccc 1px solid;
       	background: rgba(0,0,0,0.8);
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
    
    #essential{
    	height: 100%;
    	width: 85%;
    	margin-left: 15%;
    	display:none;
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
	
	#viewbox{
		text-align:center;
		color:rgba(0,0,0,0.5);
	
		/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
	}  
	
	#shop{
		height: 100%;
    	width: 85%;
    	margin-left: 15%;
	}  
	
	#shop_left{
		position:fixed;
		overflow-x:hidden;
       	left:15%;
       	height: 100%;
       	width: 13%;
       	background:gray;
	}

	#shop_aside{
		float: left;
	}
	
	#shop_aside div{
		height: 6%;
    	text-align: center;
    	cursor:pointer;
    	color:white;
    	border:none;
    	margin-top:5px;
    	border-top:0.5px solid white;
    	border-bottom:0.5px solid white;
    	
    	/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
	}
	
	#shop_aside .typeActive{
		background: white;
		color: black;
	}
	
	#shop_aside div:HOVER{
		background: white;
		color: black;
	}
	
	#shop_aside input{
		height: 6%;
		text-align: center;
	}
	
	#shop_aside .deltype{
		position: absolute;
		right:1px;
		margin-top:-20px;
		background: #56A4EB;
		color: red;
		border:none;
		display: none;
		
		/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
	}
	#shop_aside .deltype:HOVER{
		background: red;
		color: white;
	}
		
	#shop_right{
		position:fixed;
		overflow-x:hidden;
		right:0;
		height: 100%;
		width:72%;
	}
	
	.typebox{
		display: none;
	}
	
	#shop_right div li{
		float:left;
		border:1px solid green;
	}
	
	#shop_right div li img{
		display: block;
	}
	
	#shop_right div li div{
		display: block;
		margin-left: 2%;
		margin-bottom:5px;
	}

	.goodsname{
		border: none;
		margin-left: 2%;
	}
	
	#shop_right .addgood{
		text-align: center;
		cursor: pointer;
		color: white;
		background: gray;
		border: 1px solid white;
		
		/*		文字不可选中*/
		-webkit-touch-callout: none; /* iOS Safari */

		-webkit-user-select: none; /* Chrome/Safari/Opera */

		-khtml-user-select: none; /* Konqueror */

		-moz-user-select: none; /* Firefox */

		-ms-user-select: none; /* Internet Explorer/Edge */

		user-select: none; 
		
		unselectable="on"
	}
	
	#shop_right .addgood:HOVER{
		color: green;
		background: white;
		border: 1px solid gray;
	}
	
	.addimginput{
		display: none;
	}
	
	#shop_right .goodseditbut{
		text-align: center;
		border-radius:40px 40px 0px 0px;
		color:white;
		background: green;
	}

	#shop_right div li div input{
		border: none;
		text-align: center;
		background: rgba(0,0,0,0.05);
	}

	#staff{
		display: none;
	}
	
	#orders{
		display:none;
	}

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
	        <div class="menuType menuActive" onclick="gottype(this)">基本信息</div>
	        <div class="menuType" onclick="gottype(this)">我的店铺</div>
	        <div class="menuType" onclick="gottype(this)">我的员工</div>
	        <div class="menuType" onclick="gottype(this)">用户订单</div>
    </aside>
    
    <div id='essential'>
    	<div id="essential_top">
    		<div id="essential_top_on">
    			<img id="essential_top_on_img" alt="" src="<%=path %>/img/avtar.jpg">
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
    	<div id="essential_bottom"><div id="viewbox"></div></div>
    </div>
    <div id="shop">
    	<div id="shop_left">
    		<aside id="shop_aside">
		        <div class="type typeActive" onclick="ontype(this,'#t1')" oncontextmenu="typesmenu(this)">加载中</div>
		        <div class="addtypes" style="border:2px dashed white;" onclick="onaddtypes()">+</div>
	   		</aside>
    	</div>	
    	<div id="shop_right">
    		<div id="t1">
    			<ul>
	    			<li>
						<div style="text-align: center;line-height: 25%;">加载中</div>
					</li>	
    			</ul>
    		</div>
    	</div>
    </div>
    
    <div id='staff'>
    	
    </div>
    <div id='orders'>
    	
    </div>
  
  </body>
  
  <script type="text/javascript">
  
	//数据
	var chartdata = null;
	//日期
  	var minday = 0;
  	var nowday = 0;
  	var nowmonth = 0;
  	//数据
  	var oldname=null;
  	var nowname=null;
  	//商品添加所需数据
  	var addstaus = true;
  	var edittypeid = 0;
  	var addavatar = null;
  	var addname = null;
  	var addnowpice = -1;
  	var addoldpice = -1;
  	var addstock = -1;
  
  	function uload(){
 
  		//初始化时间
  		var temp = new Date();
  		nowday = temp.getDate();
  		nowmonth = temp.getMonth()+1;
  		minday = nowday-6;
 
  		//阻止默认右键菜单
		document.body.addEventListener("contextmenu",function(e){
           	e.returnValue = false;//false的时候禁用，要恢复的时候改成true
		})
 
	  	//加载店铺基本信息!
	  	$.post("<%=path%>/supporter/getme.do",{},function(data){
	  		//if(data == null || data.id =="undefined" || data.id == "" || data.id == null){
	  		if(false){
	  			alert("获取登陆信息失败!请重新登陆!");
	  			window.location.href="http://wntcl.top/login/login.jsp";
	  		}else{
	  			document.getElementById("essential_top_on_img").src = data.avatar+"";
	  	
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
  
		//经营信息加载
  		$.post("<%=path%>/supporter/businessanalysis.do",{},function(data){
  			//数据存储数组
  			var arr = new Array();
  			
			if(data == null || data.length < 1){
				chartdata = [0,0,0,0,0,0,0];
			}else{
				//初始化数组
				for(var i=0;i<32;i++){
					arr[i] = 0;
				}
				//数据覆盖
				for(var i=0;i<data.length;i++){
					var str = data[i].time;
					var day = parseInt(str.substring(str.length,str.length-2));
					arr[day] = data[i].total;
				}
				chartdata = [arr[minday],arr[minday+1],arr[minday+2],
				arr[minday+3],arr[minday+4],arr[minday+5],arr[minday+6]];
			}
			viewplay();
  		});
  		
  		//商品页加载
  		shopload();
		
  	}
  	
  	/*************************************商品信息页加载************************************/
  	function shopload(){
  		//加载标签
		$.post("<%=path%>/supporter/findtype.do",{},function(data){
		
			$("#shop_left").html(data.html);
			
			//店铺商品加载!
			$.post("<%=path%>/supporter/getmeshop.do",{},function(data){
		  		if("0" == data.code){
		  			alert("未查询到信息!");
		  			//window.location.href="http://wntcl.top/login/login.jsp";
		  		}else {
		  			$("#shop_right").html(data.html);
		  			$(".typebox").css({
						"display":"none"
					});	
		  			$("#t1").css({
						"display":"block"
					});	
					
					windowauto();
		  		}
	  		});
		});
  	}
	
  	/*************************************主标签切换**************************************/
 	 function gottype(obj){
      	$(".menuType").removeClass("menuActive");	
      	$(obj).addClass("menuActive");
      }
   	/***************************************类型切换***************************************/
   	function ontype(obj,str){
   		//页面位置还原
   		$("#shop_right").animate({scrollTop:0}, 10);
   		//标签样式切换
   		$(".type").removeClass("typeActive");	
      	$(obj).addClass("typeActive");
      	//类型详情切换
      	$(".typebox").css({
      		"display":"none"
      	});
      	$(str).css({
			"display":"block"
		});	
   	}
   	/*************************************标签信息修改*************************************/
   	//获得焦点
  	function goodsedit(obj){
  		oldname = obj.value;
  		
  		$(obj).removeAttr("readonly");
  		$(obj).css({
  			"border":"1px solid"
  		});
  	}
  	//失去焦点
	function goodseditlast(obj,keyword){
		nowname = obj.value;
		if(nowname == null || nowname == ""){
			obj.value = oldname;
			$(obj).css({
				"border":"none"
			});
		}else{
			$(obj).addClass("readonly");
			$(obj).css({
				"border":"none"
			});
			if(oldname == nowname){
				
			}else{
				
			}
		}
		
	}
	/*************************************类别添加***************************************/
	//点击
	function onaddtypes(){
		$(".addtypes").before("<input class=\"addtypesinput\" type=\"text\" onblur=\"addtypesonblur(this)\">");
		$(".addtypesinput").focus();
        $(".addtypesinput").select();
		windowauto();
	}
	//输入失焦
	function addtypesonblur(obj){
		var val = obj.value;
		$(".addtypesinput").remove();
		if(val == null || val == ""){
			return ;
		}
		$(".addtypes").before("<div class=\"type newtype\">"+val+"</div>");
		//开始添加标签
		$.post("<%=path%>/supporter/addtype.do",{"name":val},function(data){
			if(data.code == "1"){
				shopload();
			}else{
				alert("添加失败!请重试!");
			}
  		});

		//自适应
		windowauto();
	}
	
	/**************************************标签右键菜单************************************/
	function typesmenu(obj,id){
		
		$(obj).find("div").css({
			"display":"block"
		});
		$(obj).mouseleave(function(){
  			$(obj).find("div").css({
				"display":"none"
			});
		});
		$(obj).find("div").mouseenter(function(){
  			$(obj).find("div").css({
				"display":"block"
			});
		});
	}
	
	/**************************************类别删除***********************************/
	function deltype(obj,id){
	
		var e=arguments.callee.caller.arguments[0] || window.event; // 兼容火狐
       e.stopPropagation(); //防止事件冒泡
	
		$(obj).css({
			"display":"none"
		});
		
		if(confirm("确定要删除该分类吗？删除后不可恢复!")){
			$.post("<%=path%>/supporter/deltype.do",{"id":id},function(data){
				if(data.code == "1"){
						shopload();
				}else{
					alert(data.message);
					$(obj).css({
						"display":"block"
					});
				}
	  		});
  		}else{
  			$(obj).css({
				"display":"block"
			});
  		}
  		
	}
	
	/**************************************菜品添加***********************************/
	function addgoods(obj,id){
		if(!addstaus)
			return ;
		
		c = false;
		edittypeid = id;
		$(obj).removeAttr("onclick");
		$(".addgood").off('mouseenter').unbind('mouseleave');

		$(obj).addClass("openaddgoodsbox");

		$(".addgood").css({
			"display":"none"
		});
	
		$(".openaddgoodsbox").css({
			"display":"block"
		});
		
		$(obj).css({
			"background":"white",
			"color":"green",
			"border":"1px solid green"
		});	       
		$(obj).html("<img id=\"imghend\" src=\"/newtcl/img/supporteraddgood.jpg\" onclick=\"onaddimg()\"><input class=\"addimginput\" type=\"file\" "
			+"id=\"image\" onchange=\"readFile(this)\" name=\"image\">"
			+"<input class=\"goodsname addname\" type=\"text\" readonly=\"readonly\" value=\"\""
			+"onclick=\"onaddname()\" onblur=\"addnameonblur(this,"+id+")\">"
			+"<div class=\"nowpice\">现价:<input class=\"addnowpice\" type=\"number\" value=''"
			+"onclick=\"onaddnowpice(this)\" onblur=\"addnowpiceonblur(this)\"></div>"
			+"<div class=\"oldpice addoldpice\">原价:<input type=\"text\" value=\"无\""
			+"onclick=\"onaddoldpice()\" onblur=\"addoldpiceonblur(this)\" style=\"color:gray;\"></div>"
			+"<div class=\"goodstock addgoodstock\">库存:<input type=\"text\" value='无'"
			+"onclick=\"onaddgoodstock(this)\" onblur=\"addgoodstockonblur(this)\" style=\"color:gray;\"></div>"
			+"<div class=\"goodseditbut\" onclick=\"addsubmit()\">上架</div>"
		);

		windowauto();
		onaddname();
	}

	//商品名
	function onaddname(){
			
		$(".addname").focus();
        $(".addname").select();
        
		$(".addname").css({
			"border":"1px solid black"
		});
		$(".addname").removeAttr("readonly");
	}
	function addnameonblur(obj,id){
		var val = obj.value;
		if(val == null || val == ""){
			if(confirm("您未输入商品名称,是否取消商品添加!")){
				addstaus = true;
				$(".addgood").css({
						"display":"block",
						"color":"white",
						"border":"1px solid white",
						"background":"gray"
				});
				$(".addgood").hover(function(){
					$(".addgood").css({
						"display":"block",
						"color":"green",
						"border":"1px solid black",
						"background":"white"
					});
				},function(){
					$(".addgood").css({
						"display":"block",
						"color":"white",
						"border":"1px solid white",
						"background":"gray"
					});
				});
				
				$(".openaddgoodsbox").remove();
				$("#t"+id).prepend("<li class=\"addgood\" onclick=\"addgoods(this,"+id+")\">+</li>");

				windowauto();
				return ;
	  		}else{
	  			$(".addname").val("填写您的商品名称");
	  			$(".addname").focus();
        		$(".addname").select();
	  		}
		}else{
			if(val == "填写您的商品名称"){
				$(".addname").css({
					"border":"block"
				});
				$(".addname").focus();
        		$(".addname").select();
			}else{
				$(".addname").css({
					"border":"none"
				});
			}
			addname = val;
		}
	}

	//现价
	function onaddnowpice(obj){
		var val = obj.value;
		
		$(".addnowpice").focus();
        $(".addnowpice").select();
	}
	function addnowpiceonblur(obj){
		var val = obj.value;
		if(val <= 0 || val == null){
			$(obj).val(0);
			addnowpice = 0;
		}
		addnowpice = val;
	}
  
  	//原价
  	function onaddoldpice(obj){
  		$(".addoldpice").html("原价:<input type=\"number\" value=\"无\""
		+"onblur=\"addoldpiceonblur(this)\" style=\"color:gray;\">");
		
		$(".addoldpice input").focus();
        $(".addoldpice input").select();
        
        //自适应改变的布局
        windowauto();
  	}
  	function addoldpiceonblur(obj){
  		var val = obj.value;
  		if(val == null || val == 0){
  			$(".addoldpice").html("原价:<input type=\"text\" value=\"无\""
			+"onclick=\"onaddoldpice()\" onblur=\"addoldpiceonblur(this)\" style=\"color:gray;\">");
			
			$(".addoldpice input").css({
				"color":"gray"  			
  			});
  			addoldpice = -1;
  		}else{
  			$(".addoldpice input").css({
				"color":"black" 			
  			});
  	
  			addoldpice = val;
  		}
  		
  		//自适应改变的布局
  		windowauto();
  	}
  	
  	//库存
  	function onaddgoodstock(){
  		$(".addgoodstock").html("库存:<input type=\"number\" value=\"无\""
		+"onblur=\"addgoodstockonblur(this)\" style=\"color:gray;\">");
		
		$(".addgoodstock input").focus();
        $(".addgoodstock input").select();
        
        //自适应改变的布局
        windowauto();
  	}
  	function addgoodstockonblur(obj){
  		var val = obj.value;
  		if(val == null || val == 0){
  			$(".addgoodstock").html("库存:<input type=\"text\" value=\"无\""
			+"onclick=\"onaddgoodstock()\" onblur=\"addgoodstockonblur(this)\" style=\"color:gray;\">");
			
			$(".addgoodstock input").css({
				"color":"gray"  			
  			});
  			addstock = -1;
  		}else{
  			$(".addgoodstock input").css({
				"color":"black"  			
  			});
  	
  			addstock = val;
  		}
  		
  		//自适应改变的布局
  		windowauto();
  	}
  	
  	//图片
  	function onaddimg(){
  		$("#image").click();
  	}
  	
  	//图片转码为base64
	function readFile(file) {
		var filen = file.files[0];
		if (!/image\/\w+/.test(filen.type)) {
			alert("请确保文件为图像类型");
			return false;
		}
		var reader = new FileReader();
		reader.readAsDataURL(filen);
		reader.onload = function(e) {
			var ImgFileSize = this.result;
			var index = ImgFileSize.lastIndexOf("\,");
			ImgFileSize = ImgFileSize.substring(index + 1, ImgFileSize.length);
			previewImage(file);
		}
	}

	//	将图片显示在网页中
	function previewImage(file) {
	
		if (file.files && file.files[0]) {
			var img = document.getElementById('imghend');
			img.onload = function() {
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
				addavatar = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		} else { //兼容IE
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			var img = document.getElementById('imghend');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			addavatar = evt.target.result;
		}

		//图片改变后设置大小
		windowauto();
	}

	//添加提交
	function addsubmit(){
		if(addname == null || addname == "填写您的商品名称"){
			jquerytoast("body","上架失败","请填写商品名称",windowWidth*0.5);
		}else if(addnowpice == null || addnowpice < 0){
			jquerytoast("body","上架失败","请填写商品现价",windowWidth*0.5);
		}else if(addavatar == null || addavatar == ""){
			jquerytoast("body","上架失败","请上传商品照片",windowWidth*0.5);
		}else{
			$.post("<%=path%>/supporter/",{"id":id},function(data){
				if(data.code == "1"){
						shopload();
				}else{	
					alert(data.message);
					$(obj).css({
						"display":"block"
					});
				}
	  		});
		}
	}
  	  	
  	/*************************************Echarts绘图********************************/
	function viewplay(){
		//判断数据是否存在
		if(chartdata == null)
			return 0;

		//初始化Echarts
		var myChart = echarts.init(document.getElementById('viewbox'));
		
		var month = nowmonth+"/";
		// 指定图表的配置项和数据
        var option = {
            title: {
                text: '七日营业额'
            },
            tooltip: {},
            legend: {
                data:['营业额']
            },
            xAxis: {
                data: [month+(minday),month+(minday+1),month+(minday+2),
                month+(minday+3),month+(minday+4),month+(minday+5),
                month+(minday+6)]
            },
            yAxis: {},
            series: [{
                name: '营业额',
                type: 'line',
                data: chartdata
            }]
        };
       // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
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
  
  		$("#viewbox").css({
  			"height":windowHeight*0.6+"px",
  			"width":windowHeight*0.7+"px",
  			"line-height":windowHeight*0.6+"px",
  			"font-size":windowHeight*0.05+"px",
  			"margin": windowHeight*0.075+"px auto"
  		});
  		
  		$("#shop_aside div").css({
  			"width":windowWidth*0.13+"px",
  			"line-height": windowHeight*0.06+"px",
  			"font-size" : windowHeight*0.025+"px"
  		});
  		
  		$("#shop_aside .addtypesinput").css({
  			"width":windowWidth*0.13+"px",
  			"line-height": windowHeight*0.06+"px",
  			"font-size" : windowHeight*0.025+"px"
  		});
  		
  		$("#shop_aside .deltype").css({
  			"width":windowWidth*0.1+"px",
  			"height":windowHeight*0.04+"px",
  			"line-height": windowHeight*0.04+"px",
  			"font-size" : windowHeight*0.02+"px"
  		});
  	
  		$("#shop_right div li").css({
  			"height":windowHeight*0.42+"px",
  			"width":(windowHeight*0.35)*0.8+"px",
  			"margin-left":windowHeight*0.05+"px",
  			"margin-top":windowHeight*0.03+"px"
  		});
  		
  		$("#shop_right div li img").css({
  			"height":windowHeight*0.2+"px",
  			"width":(windowHeight*0.35)*0.8+"px"
  		});
  		
  		$(".goodsname").css({
  			"height":windowHeight*0.045+"px",
  			"line-height":windowHeight*0.045+"px",
  			"width":(windowHeight*0.35)*0.78+"px",
  			"font-size":windowHeight*0.03+"px"
  		});
  		
  		$("#shop_right .addgood").css({
  			"height":windowHeight*0.42+"px",
  			"width":(windowHeight*0.35)*0.8+"px",
  			"line-height":windowHeight*0.38+"px",
  			"font-size":windowHeight*0.3+"px"
  		});
  		
  		$("#shop_right div li div").css({
  			"height":windowHeight*0.04+"px",
  			"line-height":windowHeight*0.04+"px",
  			"width":(windowHeight*0.35)*0.78+"px",
  			"font-size":windowHeight*0.024+"px"
  		});
  		
  		$("#shop_right div li div input").css({
  			"height":windowHeight*0.04+"px",
  			"line-height":windowHeight*0.04+"px",
  			"width":(windowHeight*0.35)*0.5+"px"
  		});
  		
  		$("#shop_right .goodseditbut").css({
  			"height":windowHeight*0.035+"px",
  			"line-height":windowHeight*0.035+"px",
  			"width":(windowHeight*0.35)*0.45+"px",
  			"margin-top":windowHeight*0.01+"px",
  			"margin-left":(windowHeight*0.35)*0.175+"px",
  			"font-size":windowHeight*0.024+"px"
  		});
  
	}
	
	/**********************************sleep函数*********************************/
	function sleep(delay) {
	  var start = (new Date()).getTime();
	  while ((new Date()).getTime() - start < delay) {
	    continue;
	  }
	}

</script>
  
</html>
