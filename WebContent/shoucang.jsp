<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="shoucangdao.shoucang"%>
<%@page import="yingxiongcla.yingxiongcla"%>
<%
	String no = request.getParameter("no");
	shoucang ying = new shoucang();
	ArrayList list = ying.xsxx(no);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function chakan(pyname) {
		location.href = "chakan.jsp?pyname=" + pyname;
	}
	function yichu(pyname) {
		var str = new Array();
		var flag="yichu";
		str = pyname.split("+");
		for (var i = 0; i < str.length; i++) {
			var no = str[0];
			pyname = str[1];
		}
		if (confirm("删除该英雄")) {
			$.ajax({
				type : "post",
				url : "main",
				data : {
					"pyname" : pyname,
					"flag":flag,
					"no" : no
				},
				success : function(msg) {
					if (msg == "成功删除") {
						location.href = "shoucang.jsp?no=" + no;
					} else {
						alert("删除失败！请重试！");
					}
				}
			});
		}
	}
</script>
<title>我的收藏</title>
</head>
<body>
	<table border=1>
		<tr>
			<th>位置</th>
			<th>英雄</th>
			<th>大神介绍</th>
			<th>移除该英雄</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				yingxiongcla yingc = (yingxiongcla) list.get(i);
				String name = yingc.getName();
				String weizhi = yingc.getWeizhi();
				String pyname = yingc.getPyname();
		%>
		<tr>
			<td><%=weizhi%></td>
			<td><%=name%></td>
			<td><input type="button" value="点击查看"
				onclick="chakan('<%=pyname%>')"></td>
			<td><input type="button" style="width: 80px" value="移除"
				onclick="yichu('<%=no + "+" + pyname%>')"></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="main.jsp">添加其他英雄</a>
</body>
</html>