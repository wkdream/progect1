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

		request.setCharacterEncoding("UTF-8");// ʹ��UTF-8����
		response.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");// ������ҳno��ֵ
		String password = request.getParameter("password");// ������ҳpassword��ֵ
		String check = request.getParameter("check");
		Cookie mycook=null;
		if(check.equals("xz")){//ѡ���ס����
			mycook=new Cookie("mrcookinfo",no+"#"+password);//����cookie������ֵ
	    	mycook.setMaxAge(60); //����cookie����ʱ��
	    	response.addCookie(mycook);//���cookie
		}else{ //��ѡ���ס����
			mycook=new Cookie("mrcookinfo",null); //��֮ǰ���õ�cookie��ֵ�滻Ϊnull
			mycook.setMaxAge(0); //��cookie����ʧЧ
			response.addCookie(mycook); //���cookie
		}
		PrintWriter PrintWriter = response.getWriter();// ��������printWriter
		String a = login(no, password, request);// ����login()����
		PrintWriter.print(a);// �ɹ�����success
	}
	public String login(String no, String password, HttpServletRequest request) {
		connectmysql conn = new connectmysql();// ʵ����connectmysql
		try {
			Statement sta = conn.connectmysql().createStatement();// ����statement���󣬻�ȡ���ݿ�����
			String sql = "SELECT * FROM user_info WHERE no='" + no + "'";// ��ѯ���ݿ�
			ResultSet rs = sta.executeQuery(sql);// ����ѯ�������ݷŵ�rs����
			while (rs.next()) {
				String name = rs.getString("name");// �������ݿ��е�name
				String shouji = rs.getString("shouji");
				String weizhi = rs.getString("weizhi");
				MDUtil m = new MDUtil();
				String s = m.MD5(password);
				if (s.equals(rs.getString("password"))) {// �Ƚ����ݿ��е������Ƿ��������passwordһ��
					
					
					user user = new user();// ʵ����user
					user.setNo(no);
					user.setName(name);
					user.setDizhi(weizhi);
					user.setPassword(password);
					user.setShouji(shouji);
					HttpSession se = request.getSession();
					se.setAttribute("user", user); 
					return "��ȷ";
				}
				return "�������";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ѧ�Ų�����";

	}

}
