 package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBHelper;
import vo.Employees; 

public class EmployeesDao {
	
	// employees 테이블의 gender를 이용하여 전체 사원의 남/여 인원수를 출력(group by)
	public List<Map<String, Object>> selectEmployeesCountGroupByGender(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT gender, COUNT(gender) cnt FROM employees GROUP BY gender";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	// employees 테이블의 사원번호 범위를 입력(값~값)하여 지정한 범위 리스트를 출력(between)
	public List<Employees> selectEmployeesListBetween(int begin, int end){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		/*
		 * SELECT emp_no, birth_date, first_name, last_name, gender, hire_date
		 * FROM employees
		 * WHERE emp_no BETWEEN ? AND ? ORDER BY emp_no ASC
		 */
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE emp_no BETWEEN ? AND ? ORDER BY emp_no ASC";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, begin);
			stmt.setInt(2, end);
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
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	// between max min값 출력
	public int selectEmpNo(String str) {
		int empNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		if(str.equals("max")) {
			sql = "SELECT MAX(emp_no) FROM employees";
		} else if(str.equals("min")) {
			sql = "SELECT MIN(emp_no) FROM employees ";
		}
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				empNo = rs.getInt(1);
			}
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			try {
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return empNo;
	}
	
	
	// Employees 테이블의 리스트를 오름차순/내림차순으로 50줄을 출력
	public List<Employees> selectEmployeesListOrderBy(String order){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		if(order.equals("asc")) {
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name ASC LIMIT 50";
		} else if(order.equals("desc")) {
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name DESC LIMIT 50";
		}
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
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
		return list;
	}
	
	
	// Employees 테이블의 리스트의 전체 행의 수
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
	
	
	// Employees 테이블의 리스트(사원목록)
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
