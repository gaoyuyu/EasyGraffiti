<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link href="css/1.css" rel="stylesheet" type="text/css">
		<link href="css/dengzhu.css" rel="stylesheet" type="text/css">
		<title>ע��</title>
		
	<script type="text/javascript">
			function reImg(){
				var img = document.getElementById("img");
				img.src="/keshe/validate?rnd=" + Math.random();
			}
		</script>
		
		<script type="text/javascript">
// JavaScript Document
var mouseover_tid = [];
var mouseout_tid = [];
$(document).ready(function() {
	$('.menulist').each(function(index) {
		$(this).hover(function() {
			var _self = this;
			clearTimeout(mouseout_tid[index]);
			mouseover_tid[index] = setTimeout(function() {
				$(_self).find('.i-list').show();
				$(_self).find('.list_tit').addClass('on');
			}, 1);
		}, function() {
			var _self = this;
			clearTimeout(mouseover_tid[index]);
			mouseout_tid[index] = setTimeout(function() {
				$(_self).find('.i-list').hide();
				$(_self).find('.list_tit').removeClass('on');
			}, 1);
		});
	});
});
</script>
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
						<a href="#">����</a>
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
					<li>
						<a href="denglu.jsp">��¼</a>
					</li>
					<li>
						<a href="zhuce.jsp">ע�����ʺ�</a>
					</li>
				</ul>
			</div>
		</div>
		</header>
		<div class="dengzhu" id="zhuce">
			<div class="dengzhu_left">
				<img src="image/deng/90c83a1513cc64a8dbc91d2535c72add.jpg"
					width="500px" height="500px" />
			</div>
			<div class="dengzhu_right">
				
				<form action="helpRegister" method="post">
					<div class="account_main">
						<p class="login_tip">
							<b>ע���»�Ա</b>,�����ʺ�
							<a href="denglu.jsp">���¼</a>
						</p>
						<div class="control-group">
							<table width="460px" border="0" cellspacing="2" cellpadding="2">
								<tbody>
									
									<tr>
										<td width="20%" align="right">
											<span style="margin-right: 20px"> �ʺ�(E-mail)</span>
										</td>
										<td width="80%">
											<input name="email" type="text" class="input-xlarge"
												id="email" value="" maxlength="70"
												onblur="checkreg('email')" />
											<span id=chkemail></span>
										</td>
									</tr>
									
									<tr>
										<td align="right">
											<span style="margin-right: 20px">�ǳ�</span>
										</td>
										<td>
											<input name="username" type="text" class="input-xlarge"
												id="username" value="" maxlength="40"
												onblur="checkreg('username')" />
											<span id=chkusername></span>
										</td>
									</tr>
									
									<tr>
										<td align="right">
											<span style="margin-right: 20px">����</span>
										</td>
										<td>
											<input name="password" type="password" class="input-xlarge"
												id="pwd" value="" maxlength="30" />
										</td>
									</tr>
									
									<tr>
										<td align="right">
											<span style="margin-right: 20px">�����ظ�</span>
										</td>
										<td>
											<input name="password2" type="password" class="input-xlarge"
												id="pwd2" value="" maxlength="30" />
										</td>
									</tr>
									
									<tr>
										<td align="right" valign="top">
											<span style="margin-right: 20px">�Ա�</span>
										</td>
										<td height="33" valign="top">
											<input name="sex" type="radio" id="radio" value="��"
												 />
											��
											<input name="sex" type="radio" id="radio2" value="Ů" />
											Ů
										</td>
									</tr>
									
									<tr>
										<td align="right">
											<span style="margin-right: 20px">��֤��</span>
										</td>
										<td>
											<input name="yzm" type="text" class="input-xlarge" id="yzm"
												style="width: 80px;"
												onkeyup="if (this.value != this.value.toUpperCase()) this.value=this.value.toUpperCase();" />

											<img style="cursor: pointer"
												onclick="document.getElementById('captcha').src='code'+Math.random();"
												src="check" id="img" />
										</td>
									</tr>

									<tr>
										<td align="right">
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									
									<tr>
										<td align="right">
											&nbsp;
										</td>
										<td>
											<input type="submit" name="button2" id="button2"
												class="btn btn-info btn-large" value="�ύ" />
											<input type="hidden" name="save" id="save" />
										</td>
									</tr>
									
								</tbody>
							</table>

						</div>
					</div>
			</div>
		</div>
		<script type="text/javascript">
function checkreg(ck) {

	//��� 
	if (ck == "username") {
		var txt = $("#username").val();
		$.post(SITE_ROOT + "index.php?app=ajax&c=checkreg&callajax=username", {
			txt : txt
		}, function(result) {

			if (result == "err1") {
				$("#chkusername").html("����ȷ�����ǳ�")
			}

			if (result == "err2") {
				$("#chkusername").html("������ǳ�̫��")
			}

			if (result == "no") {
				$("#chkusername").html("�ǳ��Ѿ�����!")
			}

			if (result == "yes") {
				$("#chkusername").html("��ȷ")
			}

		});
	}

	//��� 
	if (ck == "email") {
		var txt = $("#email").val();
		$.post(SITE_ROOT + "index.php?app=ajax&c=checkreg&callajax=email", {
			txt : txt
		}, function(result) {
			if (result == "err1") {
				$("#chkemail").html("����ȷ��дEmail")
			}
			if (result == "err2") {
				$("#chkemail").html("Email��ʽ����ȷ")
			}

			if (result == "no") {
				$("#chkemail").html("Email�Ѿ�����!")
			}

			if (result == "yes") {
				$("#chkemail").html("��ȷ")
			}

		});
	}

}
</script>
		</form>

		</div>
		</div>
		<footer id="foot">
		<div class="wrapper">
			<ul class="clearfix">

				<li>
					<a href="#">����Ϳѻ</a>|
				</li>
				<li>
					<a href="#">��������</a>|
				</li>
				<li>
					<a href="#">�������</a>|
				</li>
				<li>
					<a href="#">��������</a>|
				</li>
				<li>
					<a href="#">Ͷ�߽���</a>|
				</li>
			</ul>
			<p>
				&nbsp;
			</p>
		</div>
	</body>
</html>

