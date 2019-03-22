<%@page import="yingxiongcla.yingxiongcla"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="yingxiong.yingxiong"%>
<%
	String pyname = request.getParameter("pyname");
	yingxiong ying = new yingxiong();
	yingxiongcla yc = ying.getying(pyname);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
img{
	position:absolute;
	left:150px;
	top:0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>英雄介绍</title>
</head>
<body >
	<img src="<%=yc.getTouxiang() %>" height="95px">
	英雄名称：<%=yc.getName()%><br> 性别：<%=yc.getSex()%><br>远近程：<%=yc.getFanwei()%><br>定位：<%=yc.getWeizhi()%><br>
	大神见解：<%=yc.getJieshao()%><br>推荐装备:<%=yc.getZb()%><br>推荐铭文：<%=yc.getMingwen()%><br>
	<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>