package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CityDao;

@WebServlet("/selectCityCount")
public class SelectCityCount extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application.json");
		
		cityDao = new CityDao();
		int count = cityDao.selectCityCount();
		
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		System.out.println(jsonCount);
		response.getWriter().write(jsonCount);
	}
}
