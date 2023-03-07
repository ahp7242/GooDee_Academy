package model;

import java.sql.*;
import java.util.*;

import db.DBHelper;
import vo.Departments;

public class DepartmentsDao {
	
	// Departments 테이블의 부서별 사원수를 알려주는 메소드
	public List<Map<String, Object>> selectDepartmentsCountByDeptNo(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); // 다형성 - ArrayList대신 다른 것을 사용할 수 도 있기 때문에
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final String sql = "SELECT d.dept_no, d.dept_name, COUNT(d.dept_no) "
							+ "From dept_emp de INNER JOIN departments d ON de.dept_no = d.dept_no "
							+ "WHERE de.to_date = '9999-01-01' GROUP BY de.dept_no ORDER BY COUNT(d.dept_no) desc";
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 map타입으로 list에 저장
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptNo", rs.getString("d.dept_no"));
				map.put("deptName",rs.getString("d.dept_name"));
				map.put("deptCount", rs.getString("COUNT(d.dept_no)"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	// Departments 테이블의 리스트의 전체 행의 수를 알려주는 메소드
	public int selectDepartmentsRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM departments";
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
	
	
	// Departments 테이블의 리스트(부서목록)를 알려주는 메소드
	public List<Departments> selectDepartmentsList(){
		List<Departments> list = new ArrayList<Departments>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT dept_no, dept_name FROM departments ORDER BY dept_no ASC";
		try {
			// 드라이버 로딩, DB 연결
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 departments타입으로 list에 저장
			while(rs.next()) {
				Departments departments = new Departments();
				departments.setDeptNo(rs.getString("dept_no"));
				departments.setDeptName(rs.getString("dept_name"));
				list.add(departments);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 반납
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {   // 자바의 변수 생명주기는 {} 밑에 Exception e와 다른 값이다.
				e.printStackTrace(); // 예외 발생시 콘솔에 예외코드 출력.
			}
		}
		return list;
	}
}
