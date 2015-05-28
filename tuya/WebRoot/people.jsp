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
<link href="css/people.css" rel="stylesheet" type="text/css">
<title>在线涂鸦网</title>
</head>

<body>
<header id="tou">
 <div id="nav">
    <div class="nav_left">
      <a href="index.html"><img src="image/logos.png" id="logo"></a>
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
<div class="author">
  <div class="author_top black">
     <strong style="float:left;">
       <img style="padding:0px;" src="image/people/strst.png">认证作者
     </strong>
  </div>
  <div class="author_bottom">
     <ul style="padding:10px;">
       <li class="tic">
          <a href="person.jsp"><img src="image/people/257463.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.jsp">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/286348.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/14.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/156.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/1079.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/11912.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/41035.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/115962.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/125505.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/156051.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/262491.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/250669.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/235933.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13677369442929.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13676850514300.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13724734155900.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13692292972969.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/mvp.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
     </ul> 
  </div>
</div>

<div class="author">
  <div class="author_top red">
    <strong style="float:left;">
       <img style="padding:0px;" src="image/people/strst.png">推荐作者
     </strong>
  </div>
  <div class="author_bottom">
     <ul style="padding:10px;">
       <li class="tic">
          <a href="person.html"><img src="image/people/257463.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/286348.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/14.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/156.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/1079.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/11912.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/41035.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/115962.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/125505.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/156051.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/262491.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/250669.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/235933.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13677369442929.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13676850514300.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13724734155900.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
       <li class="tic">
          <a href="person.html"><img src="image/people/13692292972969.jpg!face.large.jpg" width="100px" height="100"></a>
          <div class="tic-b">
            <p class="text-c" style="margin:5px;">
              <a  class="name" href="person.html">
                <span style="float:left;padding:0px 5px;"><img src="image/people/star.png"></span>old先生
              </a>
            </p>
            <div class="clear"></div>
            <p style="color:#999;"> old先，漫画家，代表作品《小丑丹尼》 </p>
          </div>
       </li>
     </ul>  
  </div> 
</div>


</center>
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