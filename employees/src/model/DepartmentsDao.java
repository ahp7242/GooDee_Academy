package model;

import java.sql.*;
import java.util.*;

import vo.Departments;

public class DepartmentsDao {
	
	// Departments의 리스트의 전체 행의 수
	public int selectDepartmentsRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM departments";
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
	
	
	// Departments의 리스트(부서목록)
	public List<Departments> selectDepartmentsList(){
		List<Departments> list = new ArrayList<Departments>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT dept_no, dept_name FROM departments";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
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