<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>
<html>
<head>
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/send.css" rel="stylesheet" type="text/css">
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
         <li><a href="about.html">更多</a></li>
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
<form action="helpUpload" method="post">
<center>
  <div class="content">
    <table>
      <tr>
        <td>标题</td>
        <td class="ip"><input type="text" name="title"></td>
      </tr>
      <tr>
        <td>介绍</td>
        <td class="ip"><textarea name="introduce" rows="" cols="" style="width:400px;height:150px;"></textarea></td>
        <td>
          <p>在线涂鸦网作品分享提示：</p>
          <ol>
            <li>请勿转载他人作品 </li>
            <li>请勿发布广告信息</li>
            <li>请勿发布违法和政冶敏感内容</li>
            <li>尊重版权，鼓励原创 </li>
          </ol>
        </td>
      </tr>
      <tr>
        <td>作品分类</td>
        <td style="text-align:left;margin-left:10px;">
          <select name="sort">
            <option value="试手">试手</option>
            <option value="新作">新作</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>作品标签</td>
        <td class="ip"><input type="text" name="label"></td>
        <td>使用逗号隔开 比如：猫,狗 </td>
      </tr>
    </table>
    <hr>
    <div class="btm">
      <a href="send11.jsp" class="send">SELECTFILES</a>
      <p>浏览文件上传，格式jpg,png,gif   文件大小：3MB以内</p>
      <input type="submit" name="button2" id="button2" class="btn btn-info btn-large" value="提交" />
    </div>
  </div>
</center>
</form>
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