<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
	List<Message> messages = (List) request.getAttribute("message");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/show.css" rel="stylesheet" type="text/css">
<link href="css/person.css" rel="stylesheet" type="text/css">
<title>在线涂鸦网</title>
<style type="text/css">
.button{
	padding:5px 15px;
	background:#3399FF;
	border:none;
	color:#fff;
	margin-top:180px;
	cursor:pointer;
	}
</style>
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
 <script language="javascript">
			function setImage(obj) {
				image.src = obj.value;
				//alert(image.src);
			}
		</script>
		<style>
		   .wen{
		   padding:5px 10px;
		   }
		   .shang{
		   padding:5px 10px;
		   }
		   .biao{
		   margin:20px 30px;
		   }
		</style>
</header>
<body>
<form class="biao" action="changeTou" method="post" enctype="multipart/form-data">
<center>
  <div class="content">
      <div class="con_top">
         <h3>修改头像</h3>
         <div class="line"></div>
         <div class="zuo">
           <a href="#"><img src="image/people/250669.jpg!face.large.jpg" width="100px" height="100px"></a> 
           <a href="#" class="a_name"><span class="name">abi小怪</span></a>
         </div>
      </div>
      
      <div class="con_center">
         <div class="tu">
           <img id="image" name="image" src="image/people/250669.jpg!face.large.jpg" width="200px" height="200px"/>
           <input class="wen" type="file" name="file" value="浏览……" /> 
           <input type="submit" value="上传" class="button" />
         </div>
      </div>
      
  </div>
  
</center>
</form>
</body>
<footer id="foot">
  <div class="wrapper">
		<ul class="clearfix">
            <li><a href="about.html">关于涂鸦</a>|</li>
			<li><a href="join.html">加入我们</a>|</li>
			<li><a href="bussiness.html">商务合作</a>|</li>
			<li><a href="help.html">帮助中心</a></li>		</ul>
		</ul>
		<p>&nbsp; </p>
	</div>
</div>
</footer>
</html>