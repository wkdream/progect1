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

@WebServlet("/cai")
public class cai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cai() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// ʹ��UTF-8����
		response.setCharacterEncoding("UTF-8");
		String yijian = request.getParameter("yijian");
		String buzantong = request.getParameter("buzantong");
		int zan=Integer.parseInt(buzantong)+1;
		PrintWriter PrintWriter = response.getWriter();// ��������printWriter
		String a = zan(yijian,zan);
		PrintWriter.print(a);
	}
	private String zan(String yijian,int buzantong) {
		connectmysql conn = new connectmysql();
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql =  "UPDATE yijian SET buzantong="+buzantong+" where fabiao='"+yijian+"'";// �޸����ݿ�����
			sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return "���ʧ��";
		}
		return "success";
	}

}