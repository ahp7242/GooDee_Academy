package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CityDao {
	
	// 페이징
	public List<City> selectCityList(int currentPage){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;
		
		String sql = "SELECT ci.city_id, ci.city, co.country, co.country_id, ci.last_update FROM city ci INNER JOIN country co ON ci.country_id = co.country_id ORDER BY city_id DESC limit ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City ci = new City();
				ci.setCityId(rs.getInt("ci.city_id"));
				ci.setCity(rs.getString("ci.city"));
				ci.setCountry(new Country());
				ci.getCountry().setCountry(rs.getString("co.country"));
				ci.getCountry().setCountryId(rs.getInt("co.country_id"));
				ci.setLastUpdate(rs.getString("ci.last_update"));
				list.add(ci);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	// index 페이지에 테이블 행의수 출력시 사용
	public int selectCityCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM city";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");	
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
	
	// 추가
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO city(city_id, city, country_id, last_update) VALUES(?,?,?,NOW())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, city.getCityId());
			stmt.setString(2, city.getCity());
			stmt.setInt(3, city.getCountry().getCountryId());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, stmt, conn);
		}
	}
}
