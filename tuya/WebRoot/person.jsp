<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
	List<Message> messages = (List) request.getAttribute("message");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="gb2312">
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/person.css" rel="stylesheet" type="text/css">
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
<div id="person">
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
  <div class="per-right">
    <div class="nav">
       <ul class="per-nav">
         <li><a href="person.jsp">个人首页</a></li>
         <li><a href="zuopin.jsp">我的作品</a></li>
         <li><a href="gerenziliao.jsp">个人资料</a></li>
         <li><a href="showMessage?mark=showMessage&userId2=<%=userinfo.getUserid2()%>">留言板</a></li>
       </ul>
        <a href="send.jsp" class="send">快速上传</a>
    </div>
    <div class="zuopin">
        <div style="width:650px;">
          <h3 style="float:left;color:#666;">最新作品</h3>
          <a style="float:right;color:#03F;" href="zuopin.jsp">更多作品</a>
        </div>
        <div class="clear"></div>
        <div class="z-bottom" style="overflow:hidden;">
           <ul>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/454201.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/454204.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/454205.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/546132.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/570581.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
             <li class="picdiv">
                <a href="#" target="_blank">
                   <div class="tupian"  style="height:220px;text-align:center;">
                     <img src="image/person/574296.jpg!photo.middle.jpg" height="220px">
                   </div>
                </a>
                <span class="spanxxx1"></span>
                <a class="title_name">宇宙人来袭</a>
             </li>
           </ul>
        </div>
    </div>
    
    
    
    <div class="liuyanban">
      <div style="width:650px;">
          <h3 style="float:left;color:#666;">最新留言</h3>
          <a style="float:right;color:#03F;" href="showMessage?mark=showMessage&userId2=<%=userinfo.getUserid2()%>">更多留言</a>
        </div>
        <%
 	if (messages == null)
 	{
 		out.print(" ");
 	}
 	else
 	{
 		for (Message message : messages)
 		{
 			String mesContent=message.getMesContent();
 			String mesTime=message.getMesTime();
 			String username=message.getUsername();
 			String photo=message.getPhoto();
 			out.print("<div class='clear'></div><div id='load_user_gbook'><table width='100%' border='0' cellspacing='3' cellpadding='3' style='border-bottom:dotted #CCC 1px; margin-bottom:10px'>");
 				out.print("<tr>");
 					out.print("<td width='58'><img src='images/"+photo+"'width=48 border=0/>");
 					out.print("</td>");
					out.print("<td width=700><div  style=' width:600px; word-break:break-all; color:#555; padding:5px'><b style='float:left;'>"+username+"</b>");
					out.print("<div class='clear'></div><div style='padding-top:5px'>"+mesContent+"</div>");
					out.print(" </div></div></div></div></td>");
				out.print("</tr>");
			    out.print("<tr>");  
			    	out.print("<td  colspan='2'>");
			    	out.print("</td>");
			    out.print("</tr>");
 			out.print("</table>");
 		}
 	}
 %>
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