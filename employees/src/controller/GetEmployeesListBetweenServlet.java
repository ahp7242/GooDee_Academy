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

@WebServlet("/employees/getEmployeesListBetween")
public class GetEmployeesListBetweenServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// model 객체 생성
		employeesDao = new EmployeesDao();
		
		//System.out.println("GetEmployeesListBetweenServlet param begin : " + begin);
		//System.out.println("GetEmployeesListBetweenServlet param end : " + end);
		// parameter 저장
		int begin = Integer.parseInt(request.getParameter("begin"));
		int end = Integer.parseInt(request.getParameter("end"));
		
		// 출력할 데이터 저장
		List<Employees> list = employeesDao.selectEmployeesListBetween(begin, end);
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListBetween.jsp").forward(request, response);
	}
}

