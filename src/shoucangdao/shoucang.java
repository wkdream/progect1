package shoucangdao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectsql.connectmysql;
import user.user;
import yingxiongcla.yingxiongcla;

public class shoucang {
	public ArrayList xsxx(String no) {
		ArrayList list = new ArrayList();

		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM "+no;// 查询数据库
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");// 调用数据库中的name
				String weizhi = rs.getString("weizhi");// 调用数据库中的weizhi
				//String jieshao=rs.getString("jieshao");
				String pyname=rs.getString("pyname");
				
				yingxiongcla ying=new yingxiongcla();
				ying.setName(name);
				ying.setWeizhi(weizhi);
				ying.setPyname(pyname);
				list.add(ying);
			}
			
		} catch (Exception e) {

		}
		return list;
	}

}
