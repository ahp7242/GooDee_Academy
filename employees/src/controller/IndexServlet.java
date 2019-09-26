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
		// URL 요청 확인
		//System.out.println("/index URL 요청");
		
		// DAO 객체 생성
		// DepartmentsDao departmentsDao = new DepartmentsDao();
		this.departmentsDao = new DepartmentsDao();
		deptEmpDao = new DeptEmpDao();
		deptManagerDao = new DeptManagerDao();
		employeesDao = new EmployeesDao();
		salariesDao = new SalariesDao();
		titlesDao = new TitlesDao();
		
		// 각각의 RowCount값 저장
		int departmentsRowCount = departmentsDao.selectDepartmentsRowCount();
		int deptEmpRowCount = deptEmpDao.selectDeptEmpRowCount();
		int deptManagerRowCount = deptManagerDao.selectDeptManagerRowCount();
		int employeesRowCount = employeesDao.selectEmployeesRowCount();
		int salariesRowCount = salariesDao.selectSalariesRowCount();
		int titlesRowCount = titlesDao.selectTitlesRowCount();
		
		int maxEmpNo = employeesDao.selectEmpNo("max");
		int minEmpNo = employeesDao.selectEmpNo("min");
		
		// request에 전송될 내용 포함
		request.setAttribute("departmentsRowCount", departmentsRowCount);
		request.setAttribute("deptEmpRowCount", deptEmpRowCount);
		request.setAttribute("deptManagerRowCount", deptManagerRowCount);
		request.setAttribute("employeesRowCount", employeesRowCount); // 오토박싱
		request.setAttribute("salariesRowCount", salariesRowCount);
		request.setAttribute("titlesRowCount", titlesRowCount);
		request.setAttribute("maxEmpNo", maxEmpNo);
		request.setAttribute("minEmpNo", minEmpNo);
		
		// /WEB-INF/views/index.jsp
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		// RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		// rd.forward(request, response);
	}
}
