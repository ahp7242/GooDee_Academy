package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepartmentsDao;
import vo.Departments;

@WebServlet("/departments/getDepartmentsList")
public class GetDepartmentsListServlet extends HttpServlet {
	// 부서 목록 출력할 페이지 model 추가
	private DepartmentsDao departmentsDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// departmentsDao 객체 생성(model)
		departmentsDao = new DepartmentsDao();
		
		// List<Departments> 타입의 list 변수 생성후 selectDepartmentsList()메소드 실행한 데이터를 list에 저장
		List<Departments> list = departmentsDao.selectDepartmentsList();
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsList.jsp").forward(request, response);	
	}
}
