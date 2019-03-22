<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    
    String a = 
    java.net.URLDecoder.decode(request.getParameter("neirong"),"UTF-8");//解决中文乱码的接收方式
    
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>读信息</title>
</head>
<body>
	你收到的回复：<br><%=a %><br>
	<a href="javascript:history.go(-1)">返回上一页</a>
</body>
</html>