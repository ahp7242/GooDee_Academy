package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SalariesDao;

@WebServlet("/salaries/getSalariesStatistics")
public class GetSalariesStatisticsServlet extends HttpServlet {
	private SalariesDao salariesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = request.getSession(); //처음으로 요청할때 받아온다
		System.out.println("indexServlet :"+session.getAttribute("sessionEmpNo"));
		
		if(session.getAttribute("sessionEmpNo") == null) { //처음으로 접속이거나 로그인을 하지않으면 반응한다.
			response.sendRedirect(request.getContextPath()+"/login");
			return; //밑에있는 메서드를 실행시키지않기위해서 return으로 실행을 종료
		}
		
		// model 객체 생성
		salariesDao = new SalariesDao();
		
		// 출력할 데이터 저장
		Map<String, Long> map = salariesDao.selectSalariesStatistics();
		//System.out.println(map);
		
		// request에 view로 넘길 list값 저장
		request.setAttribute("map", map);
		// view로 forward
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesStatistics.jsp").forward(request, response);
	}
}
