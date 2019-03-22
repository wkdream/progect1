<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String[] info = new String[]{"", ""};//定义两个字符串变量
	Cookie[] cook = request.getCookies();//得到所有的cookie
	if (cook != null) {
		for (int i = 0; i < cook.length; i++) {
			if (cook[i].getName().equals("mrcookinfo")) {//for循环查找所需cookie
				info = cook[i].getValue().split("#"); //拆分cookie里面的字符串
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#beijing {
	background-image: URL(img/beijing1.gif);
	background-size: 100%;
	background-repeat: no-repeat;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function login() {
		var check = "";
		if ($("#checkbox").is(":checked")) {
			check = "xz";
		}else{
			check="not";
		}
		$("#errorno").html("");
		$("#errorpassword").html("");
		$("#errorno").show();
		$("#errorpassword").show();
		var no = $("#no").val();
		var password = $("#password").val();
		if (no == "") {
			$("#errorno").html("呢称不能为空！ ");
			$("#errorno").show();
		} else if (password == "") {
			$("#errorpassword").html("密码不能为空！ ");
			$("#errorpassword").show();
		} else {
			$.ajax({
				type : "post",
				url : "login",
				data : {
					"no" : no,
					"check" : check,
					"password" : password
				},
				success : function(msg) {
					if (msg == "正确") {
						location.href = "main.jsp";
					} else if (msg == "密码错误") {
						$("#errorpassword").html("密码错误！");
					} else {
						$("#errorno").html("该呢称不存在！");
					}
				}

			});
		}
	}
	function cz() {
		location.reload();
	}
</script>
<meta charset="UTF-8">
<title>登陆界面</title>
</head>
<body id="beijing">
	<div align="center" style="margin-top: 50px;" id="beijing">
		<h1>王者荣耀</h1>
		<h4>新手辅助系统</h4>
		<a href="http://pvp.qq.com/web201605/introduce.shtml">入门须知</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="http://pvp.qq.com/web201605/wallpaper.shtml">英雄壁纸</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="http://pvp.qq.com/cp/a20170829bbgxsm/index.html">版本介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="http://pvp.qq.com/v/">视频中心</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="http://pvp.qq.com/coming/">爆料站</a>
		<div>
			<br> <font color="green" size="4em">昵&nbsp;称:</font> <input
				value="<%=info[0]%>" name="no" id="no" type="text"
				style="width: 350px; height: 30px">
		</div>
		<br>
		<div class="am-alert am-alert-warning" style="display: none"
			id="errorno"></div>
		<div>
			<font color="green" size="4em">密&nbsp;码:</font><input
				value="<%=info[1]%>" name="password" type="password" id="password"
				style="width: 350px; height: 30px">
		</div>
		<br>
		<div class="am-alert am-alert-warning" style="display: none"
			id="errorpassword"></div>
		<input type="checkbox" id="checkbox" style="margin-left: -210px">记住密码<br>
		<br>
		<button type="button" onclick="login()"
			style="width: 80px; height: 40px; background: #00BFFF">&nbsp;登&nbsp;陆&nbsp;</button>
		<button type="button" onclick="cz()"
			style="width: 80px; height: 40px; background: white">重置</button>
		<h6>
			还没有账号?<a href="zhuce.html">点击注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			密码忘记了?<a href="zhuce.html">点击找回</a>
		</h6>
	</div>
</body>
</html>