<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/person.css" rel="stylesheet" type="text/css">
<link href="css/gerenxinxi.css" rel="stylesheet" type="text/css">
<title>��������</title>
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
         <%
      if((userinfo != null)&&(userinfo.getSuccess()==true))
    	  {
    	  	out.print("<a href='helpGerenzhuye'>"+userinfo.getEmail()+ "</a>&nbsp;<span style='color:#c72b40;'></span>&nbsp;&nbsp;��<a href='helpExit'>��ȫ�˳�</a>��");
    	  }
      else if(userinfo!=null)
    	  {
    	  	out.print("�û�����������󣡡�<a href='denglu.jsp'>���¼</a>����<a href='zhuce.jsp'>���ע��</a>��");
    	  }
      else if(userinfo==null)
    	  {
    	 	out.print("��<a href='denglu.jsp'>���¼</a>����<a href='zhuce.jsp'>���ע��</a>��");
    	  }
		%>
      </ul>
    </div>
 </div> 
</header>
<center>
<div id="person" style="height:600px;">
  <div class="per-left">
    <div class="p-l-t">
       <div class="name">
         <div class="name-img">
           <img src="images/<%=userinfo.getPhoto() %>"/>
           <a href="changeT.jsp">�޸�ͷ��</a>
         </div>
         <p class="name1">
         <b style="float:left;font-weight:bold;font-size:14px;"><%=userinfo.getUsername() %></b>
         <span class="newmanx3">
          <img src="image/people/mvp.png">
         </span>
         <div class="clear"></div>
         <br/><br/>
         <div style="padding-top:40px;float:left;">
         <img src="image/person/sex0.png"><%=userinfo.getPlace() %>
         <br><%=userinfo.getMark() %>
         </div>
       </div>
    </div>
  </div>
  <div class="per-right" >
    <div class="nav">
       <ul class="per-nav">
         <li><a href="person.jsp">������ҳ</a></li>
         <li><a href="#">�ҵ���Ʒ</a></li>
         <li><a href="gerenziliao.jsp">��������</a></li>
         <li><a href="message.jsp">���԰�</a></li>
       </ul>
    </div>
    <form action="helpChangeMessage" method="post">
    <div class="content">
       <div class="xinxi">������Ϣ</div>
       <div class="about_xinxi">
          <dl>
             <dt>�ǳƣ�</dt>
             <dd><input name="username" type="text" /></dd>
             <dt>�Ա�</dt>
             <dd><input name="sex" type="radio" checked="checked" value="��"/>�� <input name="sex" type="radio" value="Ů" />Ů</dd>
          </dl>
          <dl>
             <dt>���ӵأ�</dt>
             <dd><input name="place" type="text" /></dd>
          </dl>
       </div>
    </div>
    <div class="content">
       <div class="xinxi">������Ϣ</div>
       <div class="about_xinxi">
          <dl>
             <dt>���������</dt>
             <dd><input name="information" type="text" /></dd>
          </dl>
       </div>
    </div>
    <div class="content">
       <div class="xinxi">���˱�ǩ</div>
       <div class="about_xinxi">
          <dl>
             <dt>���������</dt>
             <dd><input name="mark" type="text" /></dd>
          </dl>
       </div>
    </div>
    <div class="content">
       <div class="xinxi">����˵��</div>
       <div class="about_xinxi">
          <dl>
             <dt>���������</dt>
             <dd><input name="introduction" type="text" /></dd>
          </dl>
       </div>
    </div>
    <input type="submit" class="button"  value="����" />
  </div>
  </form>
</center>
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
