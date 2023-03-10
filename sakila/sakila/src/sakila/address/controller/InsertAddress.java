package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;

@WebServlet("/address/InsertCountry")
public class InsertAddress extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String country = request.getParameter("country");
		System.out.println("country : " + country);
		
		countryDao = new CountryDao();
		Country c = new Country();
		c.setCountry(country);
		countryDao.insertCountry(c);
	}
}
