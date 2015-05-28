<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>登录注册</title>
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/dengzhu.css" rel="stylesheet" type="text/css">
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
<div class="dengzhu">
    <div class="dengzhu_left"><img src="image/deng/466ee1cf1df0560cf8975b53e4eb5d04.jpg" /></div>
    <div class="dengzhu_right">
    <p class="login"><b>登录涂鸦</b><a href="zhuce.jsp">注册新帐号</a></p>
    <form action="helplogin" method="post">"
      <table class="tabla1" width="300" height="96" border="0" >
         <tr>
            <td id="name"colspan="2">帐号(E-mail) </td>
			       <td ><input name="email" type="text" class="input-xlarge" id="input01" value="" />
		           <input type="hidden" name="token_sha" value="" /></td>
         </tr>
         <tr>
           <td id="mi" colspan="2">密码</td>
	       <td><input name="password" type="password" class="input-xlarge" id="pwd" /></td>
         </tr>
         <tr>
			       <td>&nbsp;</td>
			       <td>&nbsp;</td>
        </tr>
			     <tr>
			       <td>&nbsp;</td>
                   <td>&nbsp;</td>
			       <td width="200"><input name="button" type="submit" class="btn btn-info btn-large" id="button" value="  登录  "    /></td>
      </table> 
      </form>  
    </div> 
</div>
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
