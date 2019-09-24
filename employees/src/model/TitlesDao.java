package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBHelper;

public class TitlesDao {
	
	// titles 테이블의 리스트의 전체 행의 수
	public int selectTitlesRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM titles";
		try {
			conn = DBHelper.getConnection(); // DB 메소드화
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				DBHelper.close(rs, stmt, conn); // close 메소드화
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return count;
	}
}
