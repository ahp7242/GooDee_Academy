package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TitlesDao {
	public int selectTitlesRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM titles";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 밑에 Exception e와 다른 값이다.
			e.printStackTrace(); // 예외 발생시 콘솔에 예외코드 출력.
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return count;
	}
}
