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

		connectmysql conn = new connectmysql();// ʵ����connectmysql
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();// ����statement���󣬻�ȡ���ݿ�����
			String sql = "SELECT * FROM "+no;// ��ѯ���ݿ�
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");// �������ݿ��е�name
				String weizhi = rs.getString("weizhi");// �������ݿ��е�weizhi
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
