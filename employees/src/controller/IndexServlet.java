package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;

@WebServlet({"/", "/index"})
public class IndexServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/index URL 요청");
		// EmployeesDao employeesDao = new EmployeesDao();
		this.employeesDao = new EmployeesDao();
		int employeesRowCount = employeesDao.selectEmployeesRowCount();
		
		request.setAttribute("employeesRowCount", employeesRowCount); // 오토박싱
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}
}
