<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="mybean.data.*"%>
<%
	Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
%>
<!DOCTYPE HTML>
<html>

<head>
<meta charset="gb2312">
<link href="css/1.css" rel="stylesheet" type="text/css">
<link href="css/active.css" rel="stylesheet" type="text/css" />
<title>在线涂鸦网</title>
</head>

<body>
<header id="tou">

<%
    
     out.println(" {\"activity\":[{\"name\"：\"咔咔有奖涂鸦比赛\",\"info\":\"咔咔联合涂鸦王国举办插画作品设计活动，征集符合咔咔风格定位的作品，入选作品做为实际产品进行生产出售,通过举办插画活动和插画师们建立沟通，使咔咔关联涂鸦文化，丰富咔咔，使咔咔特征更鲜明，更具有活力。\",\"start\":\"2013年5月10日\",\"end\":\"2013年8月10日\",\"image\":\"http://172.25.161.22:8080/scrawl/image/active/5.png\"},{\"name\":\"冰与火之歌A Song Of Ice and Fire\",\"info\":\"\",\"start\":\"2013年5月10日\",\"end\":\"2013年8月10日\",\"image\":\"http://172.25.161.22:8080/scrawl/image/active/2.jpg\"},{\"name\":\"拿起画笔 为雅安祈福！\",\"info\":\"\",\"start\":\"2013年5月10日\",\"end\":\"2013年8月10日\",\"image\":\"http://172.25.161.22:8080/scrawl/image/active/1.jpg"+"}]}");

 %>
</body>
</html>