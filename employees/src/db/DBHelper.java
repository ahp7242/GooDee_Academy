package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				// 자원 반납
				rs.close();
			} catch(Exception e) {
				// 반납이 안될시 예외 메세지 출력
				e.printStackTrace(); 
			}
		}
		
		if(stmt != null) {
			try {
				// 자원 반납
				stmt.close();
			} catch(Exception e) {
				// 반납이 안될시 예외 메세지 출력
				e.printStackTrace(); 
			}
		}
		
		if(conn != null) {
			try {
				// 자원 반납
				conn.close();
			} catch(Exception e) {
				// 반납이 안될시 예외 메세지 출력
				e.printStackTrace(); 
			}
		}
	}
}

