<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo = (Userinfo) session.getAttribute("userinfo");
	List<Message> messages = (List) request.getAttribute("message");
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="gb2312">
		<link href="css/1.css" rel="stylesheet" type="text/css">
		<link href="css/person.css" rel="stylesheet" type="text/css">
		<title>����Ϳѻ��</title>
		<style>
.shuru {
	width: 560px;
	height: 200px;
}

.button {
	padding: 5px 15px;
	background: #3399FF;
	border: none;
	color: #fff;
	margin-top: 10px;
	margin-left: 500px;
	margin-bottom: 30px;
	cursor: pointer;
}

.button1 {
	padding: 5px 10px;
	background: #FAFAFA;
	border: 1px solid #CCC;
	float: right;
	margin-right: 80px;
}
</style>
	</head>

	<body>
		<header id="tou">
		<div id="nav">
			<div class="nav_left">
				<a href="index.jsp"><img src="image/logos.png" id="logo">
				</a>
				<ul class="menu">
					<li>
						<a href="index.jsp">��ҳ</a>
					</li>
					<li>
						<a href="people.jsp">����</a>
					</li>
					<li>
						<a href="active.jsp">�</a>
					</li>
					<li>
						<a href="about.html">����</a>
						<ul>
							<li>
								<a></a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="nav_right">
				<ul class="menu" id="menu1">
					<%
						if ((userinfo != null) && (userinfo.getSuccess() == true))
						{
							out
									.print("<a href='helpGerenzhuye'>"
											+ userinfo.getEmail()
											+ "</a>&nbsp;<span style='color:#c72b40;'></span>&nbsp;&nbsp;��<a href='helpExit'>��ȫ�˳�</a>��");
						}
						else if (userinfo != null)
						{
							out
									.print("�û�����������󣡡�<a href='denglu.jsp'>���¼</a>����<a href='zhuce.jsp'>���ע��</a>��");
						}
						else if (userinfo == null)
						{
							out
									.print("��<a href='denglu.jsp'>���¼</a>����<a href='zhuce.jsp'>���ע��</a>��");
						}
					%>
				</ul>
			</div>
		</div>
		</header>
		<center>
			<div id="person" style="height: 1200px;">
				<div class="per-left">
					<div class="p-l-t">
						<div class="name">
							<div class="name-img">
								<img src="images/<%=userinfo.getPhoto()%>" />
							</div>
							<p class="name1">
								<b style="float: left; font-weight: bold; font-size: 14px;"><%=userinfo.getUsername()%></b>
								<span class="newmanx3"> <img src="image/people/mvp.png">
								</span>
								<div class="clear"></div>
								<br>
								<br>
								<div style="padding-top: 40px; float: left;">
									<br>
									<img src="image/person/sex0.png">
									&nbsp;&nbsp;<%=userinfo.getPlace()%><br />
									<br><%=userinfo.getMark()%>
								</div>
						</div>
					</div>
				</div>
				<div class="per-right">
					<div class="nav">
						<ul class="per-nav">
							<li>
								<a href="person.jsp">������ҳ</a>
							</li>
							<li>
								<a href="zuopin.jsp">�ҵ���Ʒ</a>
							</li>
							<li>
								<a href="gerenziliao.jsp">��������</a>
							</li>
							<li>
								<a href="showMessage?mark=showMessage&userId2=<%=userinfo.getUserid2()%>">���԰�</a>
							</li>
						</ul>
					</div>
					<div class="liuyanban">
						<form action="addMessage" method="post">
							<div class="message">
								<input type="hidden" name="mark" value="addMessage" />
								<input name="mesContent" class="shuru" type="text" width="560px"
									height="200px" />
								<br />
								<input type="submit" value="�ύ" class="button" />
							</div>
						</form>
						<div style="width: 650px;">
							<h3 style="float: left; color: #666;">
								��������
							</h3>
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
				<li>
					<a href="about.html">����Ϳѻ</a>|
				</li>
				<li>
					<a href="join.html">��������</a>|
				</li>
				<li>
					<a href="bussiness.html">�������</a>|
				</li>
				<li>
					<a href="help.html">��������</a>
				</li>
			</ul>
			<p>
				&nbsp;
			</p>
		</div>
		</footer>
	</body>
</html>