 package model;

import java.sql.*;
import java.util.*;

import vo.Employees; 

public class EmployeesDao {
	
	// Employees의 리스트의 전체 행의 수
	public int selectEmployeesRowCount() {
		int count = 0;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM employees";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) { 
			e.printStackTrace();
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
	
	
	// Employees의 리스트(사원목록)
	public List<Employees> selectEmployeesListByLimit(int limit){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees LIMIT ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
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
		return list;
	}
}
