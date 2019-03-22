<%@page import="huifu.huifuDao"%>
<%@page import="huifu.huifu"%>
<%@page import="yingxiongcla.yingxiongcla"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.user"%>
<%@ page import="yingxiong.yingxiong"%>
<%
	user user = (user) session.getAttribute("user");
	String no = user.getNo();
	String weizhi = user.getDizhi();
	yingxiong ying = new yingxiong();
	ArrayList list = ying.xsxx();
	huifuDao hui = new huifuDao();
	ArrayList huilist = hui.xsxx(no);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<script src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function chakan(pyname) {
		location.href = "chakan.jsp?pyname=" + pyname;
	}
	function shoucang(no) {
		location.href = "shoucang.jsp?no=" + no;
	}
	function tianjia(pyname) {
		var s = pyname;
		var str = new Array();
		var flag = "tianjia";
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
				"flag" : flag,
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
	function sousuo(no) {
		$("#errorname").html("");
		$("#errorname").show();
		var flag = "sousuo";
		var pyname = $("#pyname").val();
		$.ajax({
			type : "post",
			url : "main",
			data : {
				"pyname" : pyname,
				"flag" : flag,
				"no" : no
			},
			success : function(msg) {
				if (msg == "success") {
					location.href = "sousuo.jsp";
				} else {
					$("#errorname").html("该英雄不存在，请重新输入！");
					$("#errorname").show();
				}
			}
		});
	}
	function fabiao(no) {
		$("#errorname").html("");
		$("#errorname").show();
		var fabiao = $("#fabiao").val();
		var flag = "fabiao";
		var mydate = new Date();
		var shijian = (1900 + mydate.getYear()) + "年" + (1 + mydate.getMonth())
				+ "月" + mydate.getDate() + "日" + mydate.getHours() + "时"
				+ mydate.getMinutes() + "分" + mydate.getSeconds() + "秒";
		if (fabiao == "") {
			alert("不能发表空意见！");
		} else {
			$.ajax({
				type : "post",
				url : "main",
				data : {
					"fabiao" : fabiao,
					"no" : no,
					"flag" : flag,
					"shijian" : shijian
				},
				success : function(msg) {
					if (msg == "success") {
						alert("发表成功！");
					} else {
						alert("发表失败！请稍后再试！");
					}
				}
			});
		}
	}
	function luntan(no) {
		location.href = "luntan.jsp?no=" + no;
	}
	function zhuxiao() {
		var flag = "zhuxiao";
		if (confirm("退出该系统？")) {
			$.ajax({
				type : "post",
				url : "main",
				data : {
					"flag" : flag
				},
				success : function(msg) {
					if (msg == "success") {
						location.href = "denglu.jsp";
					} else {
						alert("注销失败！请稍后再试！");
					}
				}
			});
		}
	}
	function xiugai(no) {
		location.href = "xiugai.jsp?no=" + no;
	}
	function quanbuxinxi(no) {
		location.href = "quanbuxinxi.jsp?no=" + no;
	}
	function weiduxinxi(no) {
		location.href = "weiduxinxi.jsp?no=" + no;
	}
	function wode() {
		$("#wode").show();
	}
	function wode() {
		$("#wode").toggle();//让指定的div区域隐藏
	}
	function shoujianxiang() {
		$("#shou").show();
	}
	function shoujianxiang() {
		$("#shou").toggle();//让指定的div区域隐藏
	}
</script>
</head>
<body>
	您最擅长的位置是<%=weizhi%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<li style="display: inline"><input type="button" value="我的"
		onmouseover="wode()"> &nbsp;&nbsp;&nbsp;&nbsp;<input
		type="button" value="注销" onclick="zhuxiao()">
		<div id="wode" style="display: none">
			<ul>
				<input style="margin-left: 170px; margin-top: -20px" type="button"
					value="论坛入口" onclick="luntan('<%=no%>')">
			</ul>
			<ul>
				<input style="margin-left: 170px; margin-top: -20px" type="button"
					value="修改密码" onclick="xiugai('<%=no%>')">
			</ul>
			<ul>
				<input style="margin-left: 170px; margin-top: -20px" type="button"
					value="我的收藏" onclick="shoucang('<%=no%>')">
			</ul>
			<ul>
				<input style="margin-left: 170px; margin-top: -20px" type="button"
					value="收件箱" onmouseover="shoujianxiang()">
				<div id="shou" style="display: none">
					<ul>
						<input style="margin-left: 170px; margin-top: 0px" type="button"
							value="全部信息" onclick="quanbuxinxi('<%=no%>')"> (<%=huilist.get(0)%>)
					</ul>
					<ul>
						<input style="margin-left: 170px; margin-top: 0px" type="button"
							value="未读信息" onclick="weiduxinxi('<%=no%>')"> (<%=huilist.get(1)%>)
					</ul>
				</div>
			</ul>
		</div></li> &nbsp;&nbsp;&nbsp;&nbsp;
	<table border=1>
		<tr>
			<th style="width: 80px; height: 40px">位置</th>
			<th style="width: 80px; height: 40px">英雄</th>
			<th style="width: 80px; height: 40px">大神详解</th>
			<th style="width: 80px; height: 40px">添加到我喜欢</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
				String name = "";
				String pyname = "";
				yingxiongcla yingc = (yingxiongcla) list.get(i);
				if (weizhi.equals(yingc.getWeizhi())) {
					name = yingc.getName();
					pyname = yingc.getPyname();
				} else {
					continue;
				}
		%>
		<tr>
			<td style="width: 80px; height: 30px" align=center><%=weizhi%></td>
			<td style="width: 80px; height: 30px" align=center><%=name%></td>
			<td style="width: 80px; height: 30px" align=center><input
				type="button" value="点击查看" style="width: 80px; height: 30px"
				onclick="chakan('<%=pyname%>')"></td>
			<td><input style="width: 80px; height: 30px" type="button" style="width: 130px" value="+"
				onclick="tianjia('<%=pyname + "+" + no + "+" + name + "+" + weizhi%>')"></td>
		</tr>
		<%
			}
		%>
	</table>
	查看其他英雄
	<br>
	<input id="pyname" type="text" placeholder="输入英雄名字的拼音">
	<input type="button" value="点击搜索" onclick="sousuo('<%=no%>')">
	<br>
	<div style="display: none" id="errorname"></div>
	<div>发表你的意见：</div>
	<textarea rows="6" cols="30" id="fabiao"></textarea>
	<br>
	<input type="button" value="发表" onclick="fabiao('<%=no%>')">
</body>
</html>