<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��¼ע��</title>
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/dengzhu.css" rel="stylesheet" type="text/css">
</head>

<body>
<header id="tou">
 <div id="nav">
    <div class="nav_left">
      <a href="index.jsp"><img src="image/logos.png" id="logo"></a>
      <ul class="menu">
         <li><a href="index.jsp">��ҳ</a></li>
         <li><a href="people.jsp">����</a></li>
         <li><a href="active.jsp">�</a></li>
         <li><a href="about.html">����</a>
            <ul>
                <li><a></a></li>
            </ul>
         </li>
      </ul> 
    </div>
    <div class="nav_right">
      <ul class="menu" id="menu1">
         <li><a href="denglu.jsp">��¼</a></li>
         <li><a href="zhuce.jsp">ע�����ʺ�</a></li>
      </ul>
    </div>
 </div> 
</header>
<div class="dengzhu">
    <div class="dengzhu_left"><img src="image/deng/466ee1cf1df0560cf8975b53e4eb5d04.jpg" /></div>
    <div class="dengzhu_right">
    <p class="login"><b>��¼Ϳѻ</b><a href="zhuce.jsp">ע�����ʺ�</a></p>
    <form action="helplogin" method="post">"
      <table class="tabla1" width="300" height="96" border="0" >
         <tr>
            <td id="name"colspan="2">�ʺ�(E-mail) </td>
			       <td ><input name="email" type="text" class="input-xlarge" id="input01" value="" />
		           <input type="hidden" name="token_sha" value="" /></td>
         </tr>
         <tr>
           <td id="mi" colspan="2">����</td>
	       <td><input name="password" type="password" class="input-xlarge" id="pwd" /></td>
         </tr>
         <tr>
			       <td>&nbsp;</td>
			       <td>&nbsp;</td>
        </tr>
			     <tr>
			       <td>&nbsp;</td>
                   <td>&nbsp;</td>
			       <td width="200"><input name="button" type="submit" class="btn btn-info btn-large" id="button" value="  ��¼  "    /></td>
      </table> 
      </form>  
    </div> 
</div>
<footer id="foot">
  <div class="wrapper">
		<ul class="clearfix">		
			<li><a href="about.html">����Ϳѻ</a>|</li>
			<li><a href="join.html">��������</a>|</li>
			<li><a href="bussiness.html">�������</a>|</li>
			<li><a href="help.html">��������</a></li>
		</ul>
		<p>&nbsp; </p>
  </div>
</footer>
</body>
</html>
