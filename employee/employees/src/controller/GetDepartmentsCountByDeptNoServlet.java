package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DepartmentsDao;

@WebServlet("/departments/getDepartmentsCountByDeptNo")
public class GetDepartmentsCountByDeptNoServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = request.getSession(); //처음으로 요청할때 받아온다
		System.out.println("indexServlet :"+session.getAttribute("sessionEmpNo"));
		
		if(session.getAttribute("sessionEmpNo") == null) { //처음으로 접속이거나 로그인을 하지않으면 반응한다.
			response.sendRedirect(request.getContextPath()+"/login");
			return; //밑에있는 메서드를 실행시키지않기위해서 return으로 실행을 종료
		}
		
		// model 객체 생성
		departmentsDao = new DepartmentsDao();
		
		// 출력할 데이터 저장
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = departmentsDao.selectDepartmentsCountByDeptNo();
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("list", list);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsCountByDeptNo.jsp").forward(request, response);;
	}
}
