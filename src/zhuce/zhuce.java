package zhuce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connectsql.connectmysql;
import jiami.MDUtil;

@WebServlet("/zhuce")
public class zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public zhuce() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// ʹ��UTF-8����
		response.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		MDUtil m = new MDUtil();   //ʵ��������
		String s = m.MD5(password);//��������м���
		String name = request.getParameter("name"); //��ȡ��ҳ�������ĸ���ֵ
		String phone = request.getParameter("phone");
		String dizhi = request.getParameter("weizhi");
		PrintWriter PrintWriter = response.getWriter();// ��������printWriter
		String a = addstudent(no, s, name,phone,dizhi);
		PrintWriter.print(a);
	}

	public String addstudent(String no, String s, String name,String shouji,String dizhi) {

		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql = "INSERT INTO user_info VALUES('" + no + "','" + s + "','" + name + "','" + shouji + "','" + dizhi + "')";// �������ݿ�����
			sta.executeUpdate(sql);
		} catch (Exception e) {
			return "���ʧ��";
		}
		return "success";
	}
}
