package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeesDao {
	public int selectEmployeesCount() {
		int count = 0;
		final String sql = "SELECT COUNT(*) FROM employees";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) { // iterator
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 밑에 Exception e와 다른 값이다.
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace(); // 예외 발생시 콘솔에 예외코드 출력.
			}
		}
		
		return count;
	}
}
