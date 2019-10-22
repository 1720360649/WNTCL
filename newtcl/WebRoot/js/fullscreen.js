/*
ʹ��ʾ��:
<body onmousemove="isFullscreenForNoScroll()">
*isFullscreenForNoScroll() Ϊȫ����������body.onmousemove()�����е���


<button id="full" onclick="fullScreen()">����ȫ��ģʽ(F11)</button>
*/

//�ж�������Ƿ�֧��ȫ��
function isFullscreenEnabled(){
	return document.fullscreenEnabled       ||
		   document.mozFullScreenEnabled    ||
		   document.webkitFullscreenEnabled ||
		   document.msFullscreenEnabled || false;
 }
//ȫ����ť��ʾ
if(isFullscreenEnabled()){
	document.getElementById("full").style.display = "block";
}

//����ȫ��
		   function fullScreen() {
			    var el = document.documentElement,
			        rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen,
			        wscript;
			 
			    if(typeof rfs != "undefined" && rfs) {
			        rfs.call(el);
			        return;
			    }
			 
			    if(typeof window.ActiveXObject != "undefined") {
			        wscript = new ActiveXObject("WScript.Shell");
			        if(wscript) {
			            wscript.SendKeys("{F11}");
			        }
			    }
			}
			
		//全屏监听及按钮状态适应
		 function isFullscreenForNoScroll(){
            var explorer = window.navigator.userAgent.toLowerCase();
            if(explorer.indexOf('chrome')>0){//webkit
                if (document.body.scrollHeight === window.screen.height && document.body.scrollWidth === window.screen.width) {
                    document.getElementById("full").style.display = "none";
                } else {
                    document.getElementById("full").style.display = "block";
                }
            }else{//IE 9+  fireFox
                if (window.outerHeight === window.screen.height && window.outerWidth === window.screen.width) {
                   document.getElementById("full").style.display = "none";
                } else {
                   document.getElementById("full").style.display = "block";
                }
            }
        }
