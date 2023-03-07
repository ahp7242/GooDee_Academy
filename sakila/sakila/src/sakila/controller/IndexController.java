package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.ActorDao;
import sakila.address.model.AddressDao;
import sakila.address.model.CategoryDao;
import sakila.address.model.CityDao;
import sakila.address.model.CountryDao;
import sakila.address.model.CustomerDao;
import sakila.address.model.FilmActorDao;
import sakila.address.model.FilmDao;
import sakila.address.model.FilmTextDao;
import sakila.address.model.FilmCategoryDao;
import sakila.address.model.InventoryDao;
import sakila.address.model.LanguageDao;
import sakila.address.model.PaymentDao;
import sakila.address.model.RentalDao;
import sakila.address.model.StaffDao;
import sakila.address.model.StoreDao;

@WebServlet("/indexController")
public class IndexController extends HttpServlet {
	private CountryDao countryDao;
	private CityDao cityDao;
	private AddressDao addressDao;
	private CustomerDao customerDao;
	private StaffDao staffDao;
	private StoreDao storeDao;
	private PaymentDao paymentDao;
	private RentalDao rentalDao;
	private CategoryDao categoryDao;
	private FilmCategoryDao filmCategoryDao;
	private FilmDao filmDao;
	private LanguageDao languageDao;
	private ActorDao actorDao;
	private FilmActorDao filmActorDao;
	private InventoryDao inventoryDao;
	private FilmTextDao filmTextDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		//List<Integer> list = new ArrayList<Integer>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		countryDao = new CountryDao();
		int countryRow = countryDao.selectCountryCount();
		//list.add(countryRow); // autoboxing
		map.put("countryRow", countryRow);
		
		cityDao = new CityDao();
		int cityRow = cityDao.selectCityCount();
		map.put("cityRow", cityRow);
		
		addressDao = new AddressDao();
		int addressRow = addressDao.selectAddressCount();
		map.put("addressRow", addressRow);

		customerDao = new CustomerDao();
		int customerRow = customerDao.selectCustomerCount();
		map.put("customerRow", customerRow);
		
		staffDao = new StaffDao();
		int staffRow = staffDao.selectStaffCount();
		map.put("staffRow", staffRow);
		
		storeDao = new StoreDao();
		int storeRow = storeDao.selectStoreCount();
		map.put("storeRow", storeRow);
		
		paymentDao = new PaymentDao();
		int paymentRow = paymentDao.selectPaymentCount();
		map.put("paymentRow", paymentRow);
		
		rentalDao = new RentalDao();
		int rentalRow = rentalDao.selectRentalCount();
		map.put("rentalRow", rentalRow);
		
		categoryDao = new CategoryDao();
		int categoryRow = categoryDao.selectCategoryCount();
		map.put("categoryRow", categoryRow);
		
		filmCategoryDao = new FilmCategoryDao();
		int filmCategoryRow = filmCategoryDao.selectFilmCategoryCount();
		map.put("filmCategoryRow", filmCategoryRow);
		
		filmDao = new FilmDao();
		int filmRow = filmDao.selectFilmCount();
		map.put("filmRow", filmRow);
		
		languageDao = new LanguageDao();
		int languageRow = languageDao.selectLanguageCount();
		map.put("languageRow", languageRow);
		
		actorDao = new ActorDao();
		int actorRow = actorDao.selectActorCount();
		map.put("actorRow", actorRow);
		
		filmActorDao = new FilmActorDao();
		int filmActorRow = filmActorDao.selectFilmActorCount();
		map.put("filmActorRow", filmActorRow);
		
		inventoryDao = new InventoryDao();
		int inventoryRow = inventoryDao.selectInventoryCount();
		map.put("inventoryRow", inventoryRow);
		
		filmTextDao = new FilmTextDao();
		int filmTextRow = filmTextDao.selectFilmTextCount();
		map.put("filmTextRow", filmTextRow);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}
}
