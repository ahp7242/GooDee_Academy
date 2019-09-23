package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepartmentsDao;
import model.DeptEmpDao;
import model.DeptManagerDao;
import model.EmployeesDao;
import model.SalariesDao;
import model.TitlesDao;

@WebServlet({"/", "/index"})
public class IndexServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;
	private DeptEmpDao deptEmpDao;
	private DeptManagerDao deptManagerDao;
	private EmployeesDao employeesDao;
	private SalariesDao salariesDao;
	private TitlesDao titlesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/index URL 요청");
		
		departmentsDao = new DepartmentsDao();
		int departmentsRowCount = departmentsDao.selectDepartmentsRowCount();
		
		deptEmpDao = new DeptEmpDao();
		int deptEmpRowCount = deptEmpDao.selectDeptEmpRowCount();
		
		deptManagerDao = new DeptManagerDao();
		int deptManagerRowCount = deptManagerDao.selectDeptManagerRowCount();
		
		// EmployeesDao employeesDao = new EmployeesDao();
		this.employeesDao = new EmployeesDao();
		int employeesRowCount = employeesDao.selectEmployeesRowCount();
		
		salariesDao = new SalariesDao();
		int salariesRowCount = salariesDao.selectSalariesRowCount();
		
		titlesDao = new TitlesDao();
		int titlesRowCount = titlesDao.selectTitlesRowCount();
		
		request.setAttribute("departmentsRowCount", departmentsRowCount);
		request.setAttribute("deptEmpRowCount", deptEmpRowCount);
		request.setAttribute("deptManagerRowCount", deptManagerRowCount);
		request.setAttribute("employeesRowCount", employeesRowCount); // 오토박싱
		request.setAttribute("salariesRowCount", salariesRowCount);
		request.setAttribute("titlesRowCount", titlesRowCount);
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}
}
