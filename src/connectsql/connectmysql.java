package connectsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connectmysql {
	public Connection connectmysql() {
		String driver = "com.mysql.jdbc.Driver";               
		String url = "jdbc:mysql://localhost:3306/users";   
		String usename = "root";
		String pwd = "123";									  
		Connection con=null;
		try {
			Class.forName(driver);
			con= DriverManager.getConnection(url, usename, pwd); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;                                             
	}
}
