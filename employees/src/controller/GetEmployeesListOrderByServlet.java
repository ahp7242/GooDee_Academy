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

@WebServlet("/employees/getEmployeesListOrderBy")
public class GetEmployeesListOrderByServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// model 객체 생성
		employeesDao = new EmployeesDao();
		
		//System.out.println("GetEmployeesListOrderByServlet param order : " + order);
		String order = request.getParameter("order");
		
		// 출력할 데이터 저장
		List<Employees> list = employeesDao.selectEmployeesListOrderBy(order); // order : asc, desc
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListOrderBy.jsp").forward(request, response);
	}
}
