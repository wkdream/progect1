<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="utf-8"%>
<%@ page import="yingxiongcla.yingxiongcla"%>
<%@ page import="user.user"%>
<%
	user user = (user) session.getAttribute("user");
	String no = user.getNo();
	yingxiongcla ying = (yingxiongcla) session.getAttribute("ying");
	String name = ying.getName();
	String pyname = ying.getPyname();
	String weizhi = ying.getWeizhi();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function chakan(pyname) {
		location.href = "chakan.jsp?pyname=" + pyname;
	}
	function tianjia(pyname) {
		var s = pyname;
		var flag="tianjia";
		var str = new Array();
		str = s.split("+");
		for (var i = 0; i < str.length; i++) {
			var pyname = str[0];
			var no = str[1];
			var name = str[2];
			var weizhi = str[3];
		}
		$.ajax({
			type : "post",
			url : "main",
			data : {
				"no" : no,
				"name" : name,
				"pyname" : pyname,
				"flag":flag,
				"weizhi" : weizhi
			},
			success : function(msg) {
				if (msg == "success") {
					alert("添加成功！");
				} else {
					alert("很遗憾！添加失败！请稍后再试！");
				}
			}
		});
	}
</script>
</head>
<body>
	根据你输入的内容，我们找到：
	<table border=1>
		<tr>
			<th>位置</th>
			<th>英雄</th>
			<th>大神详解</th>
			<th>添加到我喜欢</th>
		</tr>
		<tr>
			<td><%=weizhi%></td>
			<td><%=name%></td>
			<td><input type="button" value="点击查看"
				onclick="chakan('<%=pyname%>')"></td>
			<td><input type="button" style="width: 130px" value="+"
				onclick="tianjia('<%=pyname + "+" + no + "+" + name + "+" + weizhi%>')"></td>
		</tr>
	</table>
	<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>