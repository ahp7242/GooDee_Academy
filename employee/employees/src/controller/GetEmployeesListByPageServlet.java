package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesListByPage")
public class GetEmployeesListByPageServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = request.getSession(); //처음으로 요청할때 받아온다
		System.out.println("indexServlet :"+session.getAttribute("sessionEmpNo"));
		
		if(session.getAttribute("sessionEmpNo") == null) { //처음으로 접속이거나 로그인을 하지않으면 반응한다.
			response.sendRedirect(request.getContextPath()+"/login");
			return; //밑에있는 메서드를 실행시키지않기위해서 return으로 실행을 종료
		}
		
		// 출력할 행 개수
		int rowPerPage = 10;
		
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//System.out.println("rowPerPage : " + rowPerPage);
		//System.out.println("currentPage : " + currentPage);
		
		// model 객체 생성
		employeesDao = new EmployeesDao();
		
		// list 생성 후 출력할 데이터 저장
		List<Employees> list = employeesDao.selectEmployeesListByPage(currentPage, rowPerPage);
		
		int lastPage = employeesDao.selectLastPage(rowPerPage);
		//System.out.println("lastPage : " + lastPage);
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListByPage.jsp").forward(request, response);
		
	}
}