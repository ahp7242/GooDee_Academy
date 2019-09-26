package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesList")
public class GetEmployeesListServlet extends HttpServlet {
	// 사원 목록 출력할 페이지  model 추가
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// employeesDao 객체 생성(model)
		employeesDao = new EmployeesDao();
		
		// 목록 출력할 행의 개수를 저장하는 변수 생성
		int limit = 10;
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		//System.out.println("GetEmployeesListServlet param limit : " + limit);
		
		// List<Employees> 타입의 list 변수 생성후 selectEmployeesList()메소드 실행한 데이터를 list에 저장
		List<Employees> list = employeesDao.selectEmployeesListByLimit(limit);
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp").forward(request, response);		
	}
}
