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
<title>����Ϳѻ��</title>
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
  <div class="content">
      <div class="con_top">
         <h3>��Ʒչʾ</h3>
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
         <h2>��<%=worksInfo.getTitle() %>��</h2>
         <p><%=worksInfo.getIntroduce() %></p>
         <img src="remen/<%=worksInfo.getWorksnum() %>" width="450px" height="500px" />
         </div>
      </div>
      <div class="con_bottom">
      <h3>��������Ʒ˵��ʲô��</h3>
      <form action="addComment" method="post">
      <input type="hidden" name="mark" value="addComment" />
      <input type="hidden" name="userId2" value="<%=worksInfo.getUserId() %>" />
      <input type="text"  class="ping" name="mesContent"/>
      <br />
      <input type="submit" value="�ύ����" class="ti" />
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
					out.print(" </div><input type='submit' value='�ظ�' class='button1'/></div></div></div></td>");
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
