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
	
	public String login(Employees employees) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sessionEmpNo = null;
		String sql = "SELECT emp_no,first_name,last_name FROM employees WHERE emp_no = ? AND (first_name = ? AND last_name = ?)";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,employees.getEmpNo());
			stmt.setString(2, employees.getFirstName());
			stmt.setString(3,employees.getLastName());
			rs = stmt.executeQuery();
			if(rs.next()) {
				sessionEmpNo = rs.getString("emp_no");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return sessionEmpNo;
	}

	
	
	// 사원목록 마지막 페이지를 리턴하는 메소드(페이징)
		public int selectLastPage(int rowPerPage) {
			//System.out.println("selectLastPage param rowPerPage : " + rowPerPage);
			// employees테이블의 전체행의 수를 구하는 메소드 호출
			int totalCount = this.selectEmployeesRowCount();
			int lastPage = totalCount/rowPerPage;
			if(lastPage % rowPerPage != 0) {
				lastPage++;
			}
			return lastPage;
		}
		
		
		// 사원목록을 원하는 행의 개수만큼 리턴하는 메소드(페이징)
		public List<Employees> selectEmployeesListByPage(int currentPage, int rowPerPage){
			//System.out.println("selectEmployeesListByPage param currentPage : " + currentPage);
			//System.out.println("selectEmployeesListByPage param rowPerPage : " + rowPerPage);
			// 리턴할 리스트 생성
			List<Employees> list = new ArrayList<Employees>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			final String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees limit ?, ?"; // ?몇행부터 ?몇개
			try {
				// 드라이버 로딩, DB 연결
				conn = DBHelper.getConnection();
				// 쿼리문 저장
				stmt = conn.prepareStatement(sql);
				int startRow = (currentPage-1)*rowPerPage; // currnetPage, rowPerPage -> startRow? (currentPage-1)*rowPerPage;
				stmt.setInt(1, startRow);
				stmt.setInt(2, rowPerPage);
				// 쿼리문 실행
				rs = stmt.executeQuery();
				// 결과값 저장하여 리턴
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
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// 자원 반납
					DBHelper.close(rs, stmt, conn);
				} catch(Exception e) {
					e.printStackTrace(); 
				}
			}
			return list;
		}
	
	
	// employees 테이블의 gender를 이용하여 전체 사원의 남/여 인원수를 알려주는 메소드(group by)
	public List<Map<String, Object>> selectEmployeesCountGroupByGender(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT gender, COUNT(gender) cnt FROM employees GROUP BY gender";
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
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
		return list;
	}
	
	
	// employees 테이블의 사원번호 범위를 입력(값~값)하여 지정한 범위 리스트를 알려주는 메소드(between)
	public List<Employees> selectEmployeesListBetween(int begin, int end){
		//System.out.println("selectEmployeesListBetween param begin : " + begin);
		//System.out.println("selectEmployeesListBetween param end : " + end);
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
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, begin);
			stmt.setInt(2, end);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
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
				// 자원 반납
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	// between max min값을 알려주는 메소드
	public int selectEmpNo(String str) {
		//System.out.println("selectEmpNo param str : " + str);
		int empNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		// 동적 쿼리 구현
		if(str.equals("max")) {
			sql = "SELECT MAX(emp_no) FROM employees";
		} else if(str.equals("min")) {
			sql = "SELECT MIN(emp_no) FROM employees ";
		}
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
			if(rs.next()) {
				empNo = rs.getInt(1);
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
		return empNo;
	}
	
	
	// Employees 테이블의 리스트를 오름차순/내림차순으로 50줄을 알려주는 메소드
	public List<Employees> selectEmployeesListOrderBy(String order){
		//System.out.println("selectEmployeesListOrderBy param order : " + order);
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		// 동적 쿼리 구현
		if(order.equals("asc")) {
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name ASC LIMIT 50";
		} else if(order.equals("desc")) {
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name DESC LIMIT 50";
		}
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
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
				// 자원 반납
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return list;
	}
		
	
	// Employees 테이블의 리스트(사원목록)를 알려주는 메소드
	public List<Employees> selectEmployeesListByLimit(int limit){
		//System.out.println("selectEmployeesListByLimit param limit : " + limit);
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees LIMIT ?";
		try {
			// 드라이버 로딩, DB 연결
			conn = DBHelper.getConnection();
			// 쿼리문 저장
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			// 쿼리문 실행
			rs = stmt.executeQuery();
			// 결과값 저장하여 리턴
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
				// 자원 반납
				DBHelper.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// Employees 테이블의 리스트의 전체 행의 수를 알려주는 메소드
	public int selectEmployeesRowCount() {
		int count = 0;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM employees";
		try {
			// 드라이버 로딩, DB 연결
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
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
