<%@page import="huifu.huifu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="huifu.huifuDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
    	String no=request.getParameter("no");
    	huifuDao huidu=new huifuDao();
    	ArrayList list=huidu.xsxx1(no);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>全部信息</title>
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function chayue(a){
	var flag="chayue";
	var str = new Array();
	str = a.split("+");
	var neirong = str[0];
	var pingno = str[1];
	var huati = str[2];
	$.ajax({
		type : "post",
		url : "main",
		data : {
			"pingno" : pingno,
			"flag":flag,
			"huati":huati,
			"neirong":neirong
		},
		success : function(msg) {
			if (msg == "success") {
				//encodeURI(encodeURI("刘德华"));
				location.href="xinjian.jsp?neirong="+encodeURI(encodeURI(neirong));//传值解决中文乱码问题
			} else {
				alert("系统繁忙！请稍后再试！");
			}
		}
	});
}
</script>
</head>
<body>
	<table border=1>
		<tr>
			<th>回复人</th>
			<th>回复话题</th>
			<th>读信息</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				huifu h = (huifu) list.get(i);
		%>
		<tr>
			<td><%=h.getPingno()%></td>
			<td><%=h.getYijian()%></td>
			<td><input type="button" value="点击查看"
				onclick="chayue('<%=h.getPinglun() + "+" + h.getPingno() + "+" + h.getYijian()%>')"></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>