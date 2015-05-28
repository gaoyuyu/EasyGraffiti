<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>
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
<div id="person" style="height:550px;">
  <div class="per-left">
    <div class="p-l-t">
       <div class="name">
         <div class="name-img">
         	<img src="images/<%=userinfo.getPhoto()%>"/>
         </div>
         <p class="name1">
         <b style="float:left;font-weight:bold;font-size:14px;"><%=userinfo.getUsername() %></b>
         <span class="newmanx3">
          <img src="image/people/mvp.png">
         </span>
         <div class="clear"></div>
         <br><br>
         <div style="padding-top:40px;float:left;"><br>
         <img src="image/person/sex0.png">&nbsp;&nbsp;<%=userinfo.getPlace() %><br/>
         <br><%=userinfo.getMark() %>
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
        <h2>您的信息已修改成功！</h2>
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
