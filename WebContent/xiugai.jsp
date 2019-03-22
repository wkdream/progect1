<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>
    <%
    String no = request.getParameter("no");
    
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<title>修改密码</title>
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function login() {
		var flag="xiugai";
		$("#errorno").html("");
		$("#erroryuanpassword").html("");
		$("#errorxinpassword").html("");
		$("#errorxinpasswordagain").html("");
		$("#errorno").show();
		$("#erroryuanpassword").show();
		$("#errorxinpassword").show();
		$("#errorxinpasswordagain").show();
		var no = $("#no").val();
		var yuanpassword = $("#yuanpassword").val();
		var xinpassword = $("#xinpassword").val();
		var xinpasswordagain = $("#xinpasswordagain").val();
		if (no == "") {
			$("#errorno").html("账号不能为空！");
			$("#errorno").show();
		} else if (yuanpassword == "") {
			$("#erroryuanpassword").html("原密码不能为空！");
			$("#erroryuanpassword").show();
		} else if (xinpassword == "") {
			$("#errorxinpassword").html("新密码不能为空！");
			$("#errorxinpassword").show();
		} else if (xinpasswordagain != xinpassword) {
			$("#errorxinpasswordagain").html("两次输入的新密码不一致！");
			$("#errorxinpasswordagain").show();
		} else {
			$.ajax({
				type : "post",
				url : "main",
				data : {
					"no" : no,
					"flag":flag,
					"yuanpassword" : yuanpassword,
					"xinpassword" : xinpassword
				},
				success : function(msg) {
					if (msg == "success") {
						alert("恭喜你！密码修改成功！");
						location.href = "denglu.jsp";
					} else {
						$("#erroryuanpassword").html("原密码错误！请重新输入！");
						$("#erroryuanpassword").show();
					}
				}

			});
		}
	}
</script>
</head>
<body>
	<div class="login_box">
		<div align="center">
			<strong><h1>修改密码</h1></strong>
		</div>
		<br> <br>
		<form>
			<div class="form-group" align="center">
				<label class="t">你的账号：</label> <input value="<%=no %>"type="text"
					class="form-control x319 in"  id="no">
			</div>
			<div align="center" class="am-alert am-alert-warning" data-am-alert
				style="display: none" id="errorno"></div>
			<div class="form-group" align="center">
				<label class="t">请输入原密码：</label> <input type="password" id="yuanpassword"
					placeholder="你的原密码" class="password form-control x319 in">
			</div>
			<div align="center" class="am-alert am-alert-warning" data-am-alert
				style="display: none" id="erroryuanpassword"></div>
			<div align="center" class="form-group">
				<label class="t">请输入新密码：</label> <input type="password"
					id="xinpassword" placeholder="您的新密码"
					class="password form-control x319 in">
			</div>
			<div align="center" class="am-alert am-alert-warning" data-am-alert
				style="display: none" id="errorxinpassword"></div>
			<div align="center" class="form-group">
				<label class="t">请再次输入新密码：</label> <input id="xinpasswordagain" type="password"
					placeholder="确认您的新密码" class="form-control x319 in">
			</div>
			<div align="center" class="am-alert am-alert-warning" data-am-alert
				style="display: none" id="errorxinpasswordagain"></div>
			<div align="center" class="form-group space">
				<button type="button" class="btn btn-primary btn-lg"
					onclick="login()">&nbsp;提&nbsp;交&nbsp;</button>
				<input type="reset" value="&nbsp;重&nbsp;置&nbsp;"
					class="btn btn-default btn-lg">
			</div>
		</form>
	</div>
</body>
</html>