package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SalariesDao;

@WebServlet("/salaries/getSalariesStatistics")
public class GetSalariesStatisticsServlet extends HttpServlet {
	private SalariesDao salariesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
