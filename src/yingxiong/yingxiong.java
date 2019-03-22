package yingxiong;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectsql.connectmysql;
import user.user;
import yingxiongcla.yingxiongcla;


public class yingxiong {
	public ArrayList xsxx() {
		ArrayList list = new ArrayList();

		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM yingxiong";// 查询数据库
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");// 调用数据库中的name
				String weizhi = rs.getString("weizhi");// 调用数据库中的weizhi
				String jieshao=rs.getString("jieshao");
				String pyname=rs.getString("pyname");
				
				yingxiongcla ying=new yingxiongcla();
				user use=new user();
				ying.setName(name);
				ying.setWeizhi(weizhi);
				ying.setJieshao(jieshao);
				ying.setPyname(pyname);
				list.add(ying);
			}
			
		} catch (Exception e) {

		}
		return list;
	}

	public yingxiongcla getying(String pyname) {
		ArrayList list = new ArrayList();
		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		yingxiongcla ying=new yingxiongcla();
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM yingxiong where pyname='" + pyname + "'";// 查询数据库
			rs = sta.executeQuery(sql);// 将查询到的内容放到list里面
			while (rs.next()) {
				String weizhi = rs.getString("weizhi");// 调用数据库中的weizhi
				String jieshao=rs.getString("jieshao");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String fanwei=rs.getString("yuanjin");
				String mingwen=rs.getString("mingwen");
				String chuzhuang=rs.getString("jycz");
				String touxiang=rs.getString("touxiang");
				
				ying.setName(name);
				ying.setPyname(pyname);
				ying.setWeizhi(weizhi);
				ying.setJieshao(jieshao);
				ying.setFanwei(fanwei);
				ying.setMingwen(mingwen);
				ying.setSex(sex);
				ying.setTouxiang(touxiang);
				ying.setZb(chuzhuang);
			}
			
		} catch (Exception e) {

		}
		return ying;
	}

}
