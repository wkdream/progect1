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

		connectmysql conn = new connectmysql();// ʵ����connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// ����statement���󣬻�ȡ���ݿ�����
			String sql = "SELECT * FROM yingxiong";// ��ѯ���ݿ�
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");// �������ݿ��е�name
				String weizhi = rs.getString("weizhi");// �������ݿ��е�weizhi
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
		connectmysql conn = new connectmysql();// ʵ����connectmysql
		ResultSet rs = null;
		yingxiongcla ying=new yingxiongcla();
		try {
			Statement sta = conn.connectmysql().createStatement();// ����statement���󣬻�ȡ���ݿ�����
			String sql = "SELECT * FROM yingxiong where pyname='" + pyname + "'";// ��ѯ���ݿ�
			rs = sta.executeQuery(sql);// ����ѯ�������ݷŵ�list����
			while (rs.next()) {
				String weizhi = rs.getString("weizhi");// �������ݿ��е�weizhi
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
