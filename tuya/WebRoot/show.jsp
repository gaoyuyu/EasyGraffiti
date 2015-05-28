<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
	Userinfo userInfo=(Userinfo)session.getAttribute("userInfo");
	WorksInfo worksInfo=(WorksInfo)session.getAttribute("worksInfo");
	List<Message> messages = (List) request.getAttribute("message");
%>
<!DOCTYPE HTML>
<html>

<head>
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/show.css" rel="stylesheet" type="text/css">
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
  <div class="content">
      <div class="con_top">
         <h3>作品展示</h3>
         <div class="line"></div>
         <div class="zuo">
           <a href="helpUserinfo?num=<%=worksInfo.getUserId() %>"><img src="images/<%=userInfo.getPhoto() %>" width="100px" height="100px"></a> 
           <a href="helpUserinfo?num=<%=worksInfo.getUserId() %>" class="a_name"><span class="name"><%=userInfo.getUsername() %></span></a>
         </div>
      </div>
      <div class="con_center">
         <div class="shang">
         <p><%=worksInfo.getWorksUploadTime() %></p>
         </div>
         <div class="tu">
         <h2>【<%=worksInfo.getTitle() %>】</h2>
         <p><%=worksInfo.getIntroduce() %></p>
         <img src="remen/<%=worksInfo.getWorksnum() %>" width="450px" height="500px" />
         </div>
      </div>
      <div class="con_bottom">
      <h3>想对这个作品说点什么？</h3>
      <form action="addComment" method="post">
      <input type="hidden" name="mark" value="addComment" />
      <input type="hidden" name="userId2" value="<%=worksInfo.getUserId() %>" />
      <input type="text"  class="ping" name="mesContent"/>
      <br />
      <input type="submit" value="提交评论" class="ti" />
      </form>
      <div width="700">
      
	  
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
					out.print(" </div><input type='submit' value='回复' class='button1'/></div></div></div></td>");
				out.print("</tr>");
			    out.print("<tr>");  
			    	out.print("<td  colspan='2'>");
			    	out.print("</td>");
			    out.print("</tr>");
 			out.print("</table>");
 		}
 	}
 %>
</div></div></center>
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
