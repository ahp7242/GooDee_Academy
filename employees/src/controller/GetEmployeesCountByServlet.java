package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;

@WebServlet("/employees/getEmployeesCountBy")
public class GetEmployeesCountByServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employeesDao = new EmployeesDao();
		List<Map<String, Object>> list = employeesDao.selectEmployeesCountGroupByGender();
		System.out.println("GetEmployeesListOrderByServlet param order : " + list);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListCountBy.jsp").forward(request, response);
	}
}

