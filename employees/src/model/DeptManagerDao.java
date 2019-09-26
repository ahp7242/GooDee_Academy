package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBHelper;

public class DeptManagerDao {
	
	// Dept_manager 테이블의 리스트의 전체 행의 수를 알려주는 메소드
	public int selectDeptManagerRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM dept_manager";
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				// 자원 반납
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return count;
	}
}
