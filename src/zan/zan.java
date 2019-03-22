package zan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connectsql.connectmysql;

@WebServlet("/zan")
public class zan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public zan() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");// 使用UTF-8编码
		response.setCharacterEncoding("UTF-8");
		String yijian = request.getParameter("yijian");
		String zantong = request.getParameter("zantong");
		int zan=Integer.parseInt(zantong)+1;
		PrintWriter PrintWriter = response.getWriter();// 创建对象printWriter
		String a = zan(yijian,zan);
		PrintWriter.print(a);
	}
	private String zan(String yijian,int zantong) {
		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql =  "UPDATE yijian SET zantong="+zantong+" where fabiao='"+yijian+"'";// 修改数据库数据
			sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}
		return "success";
	}

}
