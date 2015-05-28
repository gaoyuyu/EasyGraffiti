<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="gb2312">
<link href="css/1.css" rel="stylesheet" type="text/css">
<title>在线涂鸦网</title>
</head>

<body>
<header id="tou">
 <div id="nav">
    <div class="nav_left">
      <a href="index.jsp"><img src="image/logos.png" id="logo"></a>
      <ul class="menu">
         <li><a href="index.jsp">首页</a></li>
         <li><a href="people.jsp">达人</a></li>
         <li><a href="active.jsp">活动</a></li>
         <li><a href="about.html">更多</a>
            <ul>
                <li><a></a></li>
            </ul>
         </li>
      </ul> 
    </div>
    <div class="nav_right">
      <ul class="menu" id="menu1">
      <%
      if((userinfo != null)&&(userinfo.getSuccess()==true))
    	  {
    	  	out.print("<a href='helpGerenzhuye'>"+userinfo.getEmail()+ "</a>&nbsp;<span style='color:#c72b40;'></span>&nbsp;&nbsp;【<a href='helpExit'>安全退出</a>】");
    	  }
      else if(userinfo!=null)
    	  {
    	  	out.print("用户名或密码错误！【<a href='denglu.jsp'>请登录</a>】【<a href='zhuce.jsp'>免费注册</a>】");
    	  }
      else if(userinfo==null)
    	  {
    	 	out.print("【<a href='denglu.jsp'>请登录</a>】【<a href='zhuce.jsp'>免费注册</a>】");
    	  }
		%>
      </ul>
    </div>
 </div> 
</header>
<center>
  <div id="biao">
   <div id="biao_left">
    <a href="#"><img src="image/24.jpg" height="361px"></a>
   </div>
   <script type="text/javascript">//alert(-90/10)    
var Speed =0;    
var timer = null;   
 var timers = null;    
 var num = 0;    
 function topMove(iTarget){       
  /*if(timer){            clearInterval(timer)        }*/        colorMove(iTarget)    }  
    function colorMove(n){       
	 var color = document.getElementById("color");        
	 if(color.offsetTop != n){          
	   Speed += (n -  color.offsetTop)/7;      
	     }       
		  color.style.top = Speed + "px";       
		   if(timer){            clearTimeout(timer)        }       
		    timer = setTimeout("colorMove("+n+")",10)    }    
			function autoMove(){        if(0 == num){            topMove(0);            num = -360;        }        
			else if(-360 == num){            topMove(-360);            num = -720;        }        
			else{            topMove(num);            num =0;        }    }   
			 setInterval("autoMove()",1000)   
			  </script>
              <div id="wrap">   
               <ul id="colList">        
               <li><a href="" onmouseover="topMove(0)">1</a></li>        
               <li><a href="" onmouseover="topMove(-130)">2</a></li>       
                <li><a href="" onmouseover="topMove(-260)">3</a></li>    
                </ul>    
                <div id="color">        
                <div class="red box"> 
                  <img src="image/26.jpg" />
                 </div>        
                <div class="yellow box">
                <img src="image/35.jpg" />        </div>       
                 <div class="orange box">
                 <img src="image/41.jpg" />        </div>    
                 </div>
                 </div>
   <div id="biao_right">
   <a href="#"><img src="image/11.jpg"></a>
   <a href="#"><img src="image/17.jpg"></a>
   <a href="#"><img src="image/18.jpg"></a>
   </div>
  </div>
  <div id="huo">
    		<ul>
		<div class="newMessage_tit clearfix pic_wallh3" style="font-weight:700;"><a class="c-0099cc f-r f-normal" style="font-weight:700;" href="#">更多</a><h5 style="float:left; padding-left:65px;font-size:12px;font-weight:bold;">活跃插画师</h5></div>
	 
		
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/13.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=13">夜之渡鸦</a>
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/14.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=14">执壶人</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/15.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=15">mowenxh</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/16.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=16">点灯虫</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/17.jpg"width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=17">妹子杰</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/18.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=18">芈嫚</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/19.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=19">just R</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/13774384524272.jpg!face.middle.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=20">好好姑娘小可</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/13775247825036.jpg!face.middle.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=22">Sora54</a>
  
				
				</div>
				</div>
			<div class="lileft" style="width:64px; overflow:hidden; float:left;position:relative;"><a href="#"><img src="huo/user_100.jpg" width="64" height="64"></a>
<div style="position:absolute;top:48px;left:46px;cursor:pointer">
</div>	
				<div class="text-c" style="float:left;width:70px;overflow:hidden;">
 
				<a class="c-333 f-blod"  href="helpUserinfo?num=23">牙箍-心率焦急</a>
  
				
				</div>
				</div>
			
		</ul>
  </div>
  <div id="guang">
    <a href="#"><img src="image/22.jpg" width="485px"></a>
    <a href="#"><img src="image/23.gif" width="485px"></a>
  </div>
  <div id="zui">
    <div class="more"><h3 style="float:left; font-size:16px; padding-top:20px; padding-left:20px;">热门作品</h3>
		<a style="float:right;   padding-top:20px; font-size:15px; font-weight:700; margin-right:20px;" href="#"></a></div>
    <div id="zui_list">
				<ul class="clearfix" id="homexwork" style="margin-left:3px;">
				
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=18" target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/01.jpg); background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=18" class="title_name" style="color:#000;">小系列</a></center>
						<span class="spanxxx1"></span>						
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=19"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/02.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=19" class="title_name" style="color:#000;">音乐</a></center>
												<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=10"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/03.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=10" class="title_name" style="color:#000;">钥匙</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=11"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/04.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=11" class="title_name" style="color:#000;">3p小飞娥</a></center>
												<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=12"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/05.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=12" class="title_name" style="color:#000;">皇飞虹</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=13"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/06.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=13" class="title_name" style="color:#000;">飞蛾仔木片书签（6p)</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=14"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/07.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=14" class="title_name" style="color:#000;">母亲节快乐</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=15"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/08.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=15" class="title_name" style="color:#000;">lobster</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=16"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/09.jpg); background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=16" class="title_name" style="color:#000;">手与梯田</a></center>
												<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
  <li class="picdiv" style="float:left; width:160px; height:220px; margin-bottom:10px; margin-left:25px;">

 
					<!-- 	<div class="att"><a class="hot">热</a><a>组</a></div> -->
					<a href="helpShow?num=17"  target="_blank"><div style="height:200px; text-align:center;  background-image:url(zui/10.jpg) ; background-repeat:no-repeat; background-position:center ">
</div></a>
<center><a href="helpShow?num=17" class="title_name" style="color:#000;">新疆维吾尔自治区·沙雅</a></center>
						<span class="spanxxx1"></span>						<center><span class="love c-777"><span class="love_btn love_btn_on"></span></span></center>
				 
					</li>
                    
 
    <br style=" clear:both"/>
  
   
 
				</ul>
    </div>
  </div>
  <div id="hao">
  <div class="more">
     		<h3 style="float:left; font-size:16px; padding-top:20px; padding-left:20px;">最新作品</h3>
		<a style="float:right;   padding-top:20px; font-size:15px; font-weight:700; margin-right:20px;"></a></div>
		<div class="clear"></div>
		<div class="works_list_con" style="display:block">
			<div class="works_list_onebox" style="display:block">
				<ul class="clearfix" id="homexwork" style="display:block">
				
				
				
				<li style="margin-left:3px;"><a href="helpShow?num=20" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h01.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a class="title_name"  style="color:#333;line-height:20px; margin-left:2px;">小龙女</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="helpShow?num=20"><img src="hao/01.jpg" />
							<span style="float:left;">好好姑娘小可</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=21" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h02.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=21" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>20140511</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/02.jpg" />
							<span style="float:left;">Sora54</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=22" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h03.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=22" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>鬼白~2(●′ω`●)</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/03.jpg" />
							<span style="float:left;">Sora54</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=23" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h04.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=23" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>崔撕膜</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/04.jpg" />
							<span style="float:left;">林田</span></a>
 <span   class="newmanx21" style="margin:  3px;"></span>
						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=24" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h05.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=24" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>事实证明我内心是很阳光的</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/05.jpg" />
							<span style="float:left;">Sora54</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=25" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h06.jpg) ; background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=25" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>HOPE</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">3</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/06.jpg" />
							<span style="float:left;">阿霂</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=26" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h07.jpg) ; background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=26" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>untitle</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">1</a>
							<a class="W_love"style="display:none">1</a>
							<a href="#"><img src="hao/08.jpg" />
							<span style="float:left;">artistic青尘</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=27" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h08.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=27" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>dark world</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href"#"><img src="hao/09.jpg" />
							<span style="float:left;">tsunami</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=28" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h09.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=28" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>金樱子水彩步骤动态图</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/10.jpg" />
							<span style="float:left;">南瓜五毛</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=29" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h10.jpg) ; background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=29" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>24节气之立夏</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/027.jpg" />
							<span style="float:left;">快格</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=30" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h11.jpg) ; background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=30" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>练习二则</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/01.jpg" />
							<span style="float:left;">朽木的森林</span></a>
 						</div>
					</li>
<li style="margin-left:3px;"><a href="helpShow?num=31" target="_blank"><div style="height:220px; text-align:center;  background-image:url(hao/h12.jpg); background-repeat:no-repeat; background-position:center ">
						</div></a>
						
						 	
						<a href="helpShow?num=31" class="title_name"  style='color:#333;line-height:20px; margin-left:2px;'>涂涂。。。。</a>
						
						
						<div class="W_info clearfix">
							<a class="W_pl" style="display:none">0</a>
							<a class="W_love"style="display:none">0</a>
							<a href="#"><img src="hao/02.jpg" />
							<span style="float:left;">（凹君</span></a>
 						</div>
					</li>
				</ul>
			</div> 
  </div>
</center>
<footer id="foot">
  <div class="wrapper">
		<ul class="clearfix">		
			<li><a href="about.html">关于涂鸦</a>|</li>
			<li><a href="join.html">加入我们</a>|</li>
			<li><a href="bussiness.html">商务合作</a>|</li>
			<li><a href="help.html">帮助中心</a></li>
		</ul>
		<p>&nbsp; </p>
  </div>
</footer>
</body>
</html>