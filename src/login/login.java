package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connectsql.connectmysql;
import jiami.MDUtil;
import user.user;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");// 使用UTF-8编码
		response.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");// 接收网页no的值
		String password = request.getParameter("password");// 接收网页password的值
		String check = request.getParameter("check");
		Cookie mycook=null;
		if(check.equals("xz")){//选择记住密码
			mycook=new Cookie("mrcookinfo",no+"#"+password);//设置cookie，并赋值
	    	mycook.setMaxAge(60); //设置cookie生存时间
	    	response.addCookie(mycook);//添加cookie
		}else{ //不选择记住密码
			mycook=new Cookie("mrcookinfo",null); //将之前设置的cookie，值替换为null
			mycook.setMaxAge(0); //让cookie立即失效
			response.addCookie(mycook); //添加cookie
		}
		PrintWriter PrintWriter = response.getWriter();// 创建对象printWriter
		String a = login(no, password, request);// 调用login()方法
		PrintWriter.print(a);// 成功返回success
	}
	public String login(String no, String password, HttpServletRequest request) {
		connectmysql conn = new connectmysql();// 实例化connectmysql
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM user_info WHERE no='" + no + "'";// 查询数据库
			ResultSet rs = sta.executeQuery(sql);// 将查询到的内容放到rs里面
			while (rs.next()) {
				String name = rs.getString("name");// 调用数据库中的name
				String shouji = rs.getString("shouji");
				String weizhi = rs.getString("weizhi");
				MDUtil m = new MDUtil();
				String s = m.MD5(password);
				if (s.equals(rs.getString("password"))) {// 比较数据库中的密码是否与输入的password一致
					
					
					user user = new user();// 实例化user
					user.setNo(no);
					user.setName(name);
					user.setDizhi(weizhi);
					user.setPassword(password);
					user.setShouji(shouji);
					HttpSession se = request.getSession();
					se.setAttribute("user", user); 
					return "正确";
				}
				return "密码错误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "学号不存在";

	}

}
