<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/person.css" rel="stylesheet" type="text/css">
<link href="css/change.css" rel="stylesheet" type="text/css">
<title>个人资料</title>
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
         <li><a href="denglu.jsp">登录</a></li>
         <li><a href="zhuce.jsp">注册新帐号</a></li>
      </ul>
    </div>
 </div> 
</header>
<center>
<div id="person" style="height:550px;">
  <div class="per-left">
    <div class="p-l-t">
       <div class="name">
         <div class="name-img">
           <img src="image/people/14.jpg!face.large.jpg">
           <a href="changeTou.jsp">修改头像</a>
         </div>
         <p class="name1">
         <b style="float:left;font-weight:bold;font-size:14px;">old先</b>
         <span class="newmanx3">
          <img src="image/people/mvp.png">
         </span>
         <div class="clear"></div>
         <p style="padding:5px 20px;">认证：old先，漫画家，代表作品《小丑丹尼》</p>
         </p>
         <div style="padding-top:40px;float:left;">
         <img src="image/person/sex0.png">其他地区
         </div>
       </div>
    </div>
  </div>
  <div class="per-right" >
    <div class="nav">
       <ul class="per-nav">
         <li><a href="person.jsp">个人首页</a></li>
         <li><a href="#">我的作品</a></li>
         <li><a href="gerenziliao.jsp">个人资料</a></li>
         <li><a href="message.jsp">留言板</a></li>
       </ul>
    </div>
    <div class="content">
      <div class="dui"><img src="image/dui.jpg" /></div>
      <div class="txt">
        <h2>您的头像已修改成功！</h2>
      </div>
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
