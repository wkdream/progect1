package guangchang;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectsql.connectmysql;
import guangchangc.guangchangc;

public class guangchang {
	
	public List<guangchangc> xsxx() {
		List<guangchangc> list = new ArrayList<>();

		connectmysql conn = new connectmysql();
		ResultSet rs = null;
		try {
			Statement sta = conn.connectmysql().createStatement();
			String sql = "SELECT * FROM yijian";
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				String no = rs.getString("no");
				String yijian = rs.getString("fabiao");
				String shijian=rs.getString("shijian");
				int zantong=rs.getInt("zantong");
				int buzantong=rs.getInt("buzantong"); 
				
				guangchangc guang=new guangchangc();
				guang.setNo(no);
				guang.setYijian(yijian);
				guang.setShijian(shijian);
				guang.setZantong(zantong);
				guang.setBuzantong(buzantong);
				list.add(guang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
