<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>
<html>
<head>
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/send.css" rel="stylesheet" type="text/css">
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
         <li><a href="about.html">����</a></li>
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
<form action="helpUpload" method="post">
<center>
  <div class="content">
    <table>
      <tr>
        <td>����</td>
        <td class="ip"><input type="text" name="title"></td>
      </tr>
      <tr>
        <td>����</td>
        <td class="ip"><textarea name="introduce" rows="" cols="" style="width:400px;height:150px;"></textarea></td>
        <td>
          <p>����Ϳѻ����Ʒ������ʾ��</p>
          <ol>
            <li>����ת��������Ʒ </li>
            <li>���𷢲������Ϣ</li>
            <li>���𷢲�Υ������ұ��������</li>
            <li>���ذ�Ȩ������ԭ�� </li>
          </ol>
        </td>
      </tr>
      <tr>
        <td>��Ʒ����</td>
        <td style="text-align:left;margin-left:10px;">
          <select name="sort">
            <option value="����">����</option>
            <option value="����">����</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>��Ʒ��ǩ</td>
        <td class="ip"><input type="text" name="label"></td>
        <td>ʹ�ö��Ÿ��� ���磺è,�� </td>
      </tr>
    </table>
    <hr>
    <div class="btm">
      <a href="send11.jsp" class="send">SELECTFILES</a>
      <p>����ļ��ϴ�����ʽjpg,png,gif   �ļ���С��3MB����</p>
      <input type="submit" name="button2" id="button2" class="btn btn-info btn-large" value="�ύ" />
    </div>
  </div>
</center>
</form>
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