package huifu;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectsql.connectmysql;
import user.user;
import yingxiongcla.yingxiongcla;

public class huifuDao {
	public ArrayList xsxx(String no) {
		ArrayList list = new ArrayList();
		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM pinglun";// 查询数据库
			rs = sta.executeQuery(sql);
			int i=0;
			int g=0;
			while (rs.next()) {
				String fano = rs.getString("fano");
				String chakan= rs.getString("chakan");
				if(no.equals(fano)){
					i++;
					if(chakan.equals("0")){
						g++;
					}
				}
			}
			list.add(i);
			list.add(g);
		} catch (Exception e) {
			
		}
		return list;
	}
	public ArrayList xsxx1(String no) {
		ArrayList list=new ArrayList();
		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM pinglun";// 查询数据库
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String fano = rs.getString("fano");// 调用数据库中的fano
				String pingno=rs.getString("pingno");
				String yijian=rs.getString("yijian");
				String pinglun=rs.getString("pinglun");
				
				huifu hui=new huifu();
				hui.setFano(fano);
				hui.setPinglun(pinglun);
				hui.setYijian(yijian);
				hui.setPingno(pingno);
				list.add(hui);
			}
		} catch (Exception e) {
			
		}
		return list;
	}
	public ArrayList xsxx2(String no) {
		ArrayList list=new ArrayList();
		connectmysql conn = new connectmysql();// 实例化connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// 创建statement对象，获取数据库连接
			String sql = "SELECT * FROM pinglun";// 查询数据库
			rs = sta.executeQuery(sql);// 将查询到的内容放到list里面
			while (rs.next()) {
				String chakan=rs.getString("chakan");
				if(chakan.equals("0")){
					String fano = rs.getString("fano");// 调用数据库中的name
					String pingno=rs.getString("pingno");
					String yijian=rs.getString("yijian");
					String pinglun=rs.getString("pinglun");
					huifu hui=new huifu();
					hui.setFano(fano);
					hui.setPinglun(pinglun);
					hui.setYijian(yijian);
					hui.setPingno(pingno);
					list.add(hui);
				}
			}
		} catch (Exception e) {
			
		}
		return list;
	}
}
