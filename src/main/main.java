package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connectsql.connectmysql;
import jiami.MDUtil;
import user.user;
import yingxiongcla.yingxiongcla;

@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public main() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		String no = request.getParameter("no");
		PrintWriter PrintWriter = response.getWriter();
		
		if (flag.equals("tianjia")) {//收藏功能
			String name = request.getParameter("name");
			String pyname = request.getParameter("pyname");
			String weizhi = request.getParameter("weizhi");
			String a = main1(no, name, pyname, weizhi);
			PrintWriter.print(a);
		} else if (flag.equals("sousuo")) {//搜索功能
			String pyname = request.getParameter("pyname");
			String a = sou(no, pyname, request);
			PrintWriter.print(a);
		} else if (flag.equals("fabiao")) {//发表话题
			String fabiao = request.getParameter("fabiao");
			String shijian = request.getParameter("shijian");
			String a = yijian(no, fabiao, shijian);
			PrintWriter.print(a);
		}else if (flag.equals("yichu")){//从收藏中删除
			String pyname = request.getParameter("pyname");
			String a = yichu(no,pyname);
			PrintWriter.print(a);
		}else if(flag.equals("zhuxiao")){//注销账号
			HttpSession se1 = request.getSession();
			se1.invalidate();
			PrintWriter.print("success");
		}else if (flag.equals("hf")){//回复发布话题人信息
			String fano = request.getParameter("fano");
			String pingno = request.getParameter("pingno");
			String yijian= request.getParameter("yijian");
			String pinglun = request.getParameter("huifu");
			String HF = HF(fano,pingno,pinglun,yijian);
			PrintWriter.print(HF);
		}else if (flag.equals("xiugai")) {//修改密码
			String yuanpassword = request.getParameter("yuanpassword");
			String xinpassword = request.getParameter("xinpassword");
			String a = xiugai(no, yuanpassword, xinpassword);
			PrintWriter.print(a);
		}else if (flag.equals("chayue")) {//论坛
			String pingno = request.getParameter("pingno");
			String huati = request.getParameter("huati");
			String neirong = request.getParameter("neirong");
			String a = chayue(pingno, huati, neirong);
			PrintWriter.print(a);
		}
	}
private String chayue(String pingno, String huati, String neirong) {
		connectmysql conn = new connectmysql();
		Statement sta = null;
		String sql = "UPDATE pinglun SET chakan='1' where pingno='" + pingno + "'and yijian='" + huati + "'and pinglun='" + neirong + "'";
		try {
			sta = conn.connectmysql().createStatement();
			sta.executeUpdate(sql);
			return "success";
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return "shibai";
	}

	private String xiugai(String no, String yuanpassword, String xinpassword) {
		connectmysql conn = new connectmysql();
		MDUtil m = new MDUtil();
		String sql2 = "SELECT * FROM user_info";
		String sql1="UPDATE user_info SET password='"+m.MD5(xinpassword)+"'where no='"+no+"'";
		Statement sta = null;
		try {
			sta = conn.connectmysql().createStatement();
			ResultSet rs = sta.executeQuery(sql2);
			while (rs.next()) {
				String no1 = rs.getString("no");
				if (no1.equals(no)) {
					String password=rs.getString("password");
					String s = m.MD5(yuanpassword);
					if(s.equals(password)){
						sta.executeUpdate(sql1);
						return "success";
					}else{
						return "cuowu";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shibai";
	}

	private String HF(String fano, String pingno, String pinglun,String yijian) {
		connectmysql conn = new connectmysql();
		Statement sta = null;
		String sql = "insert pinglun SET fano='" + fano + "',pingno='" + pingno + "',pinglun='" + pinglun + "',yijian='" + yijian + "',chakan='0'";
		try {
			sta = conn.connectmysql().createStatement();
			sta.executeUpdate(sql);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "shibai";
	}

	private String main1(String no, String name, String pyname, String weizhi) {
		connectmysql conn = new connectmysql();
		String sql = "CREATE TABLE " + no
				+ " (name  varchar(255) NULL ,pyname  varchar(255) NULL ,weizhi  varchar(255) NULL );";
		String sql1 = "insert " + no + " SET name='" + name + "',pyname='" + pyname + "',weizhi='" + weizhi + "'";
		String sql2 = "SELECT * FROM user_info";
		Statement sta = null;
		try {
			sta = conn.connectmysql().createStatement();
			ResultSet rs = sta.executeQuery(sql2);
			while (rs.next()) {
				String no1 = rs.getString("no");
				if (no1.equals(no)) {
					sta.executeUpdate(sql1);
					return "success";
				}
			}
		} catch (Exception e) {
			try {
				sta.executeUpdate(sql);
				sta.executeUpdate(sql1);
				return "success";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return "shibai";
	}

	private String sou(String no, String pyname, HttpServletRequest request) {
		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql = "SELECT * FROM yingxiong WHERE pyname='" + pyname + "'";
			String sql1 = "SELECT * FROM user_info WHERE no='" + no + "'";
			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {

				String name = rs.getString("name");
				String weizhi = rs.getString("weizhi");
				rs.close();

				ResultSet rs1 = sta.executeQuery(sql1);
				while (rs1.next()) {
					String name1 = rs1.getString("name");
					String weizhi1 = rs1.getString("weizhi");
					user user = new user();
					user.setNo(no);
					user.setName(name1);
					user.setDizhi(weizhi1);
					HttpSession se1 = request.getSession();
					se1.setAttribute("user", user);
				}
				yingxiongcla ying = new yingxiongcla();
				ying.setName(name);
				ying.setWeizhi(weizhi);
				ying.setPyname(pyname);
				HttpSession se = request.getSession();
				se.setAttribute("ying", ying);
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "未找到";
	}

	private String yijian(String no, String fabiao, String shijian) {
		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql = "INSERT INTO yijian (no,fabiao,shijian) VALUES('" + no + "','" + fabiao + "','" + shijian
					+ "')";
			sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}
		return "success";
	}
	public String yichu(String no,String pyname) {

		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql = "DELETE from "+no+" WHERE pyname='"+pyname+"'";
			sta.executeUpdate(sql);
			return "成功删除";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "删除失败";

}
}
