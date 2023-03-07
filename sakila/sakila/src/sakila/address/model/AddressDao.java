package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class AddressDao {
	
	// index 페이지에 테이블 행의수 출력시 사용
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM address";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
	
	public List<Address> selectAddressList(int currentPage){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;
		
		
		String sql = "SELECT a.address_id, a.address, a.district, ci.city_id, a.postal_code, a.phone, a.last_update FROM address a INNER JOIN city ci on a.city_id = ci.city_id ORDER BY address_id desc limit ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Address a = new Address();
				a.setCity(new City());
				a.getCity().setCityId(rs.getInt("ci.city_id"));
				a.setAddressId(rs.getInt("a.address_id"));
				a.setAddress(rs.getString("a.address"));
				a.setDistrict(rs.getString("a.district"));
				a.setPostalCode(rs.getString("a.postal_code"));
				a.setPhone(rs.getString("a.phone"));
				a.setLastUpdate(rs.getString("a.last_update"));
				list.add(a);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	public void insertAddress(Address address) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO address(city_id, address_id, address, address2, district, postal_code, phone) VALUES(?,?,?,?,?,?,?,NOW())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, address.getCity().getCityId());
			stmt.setInt(2, address.getAddressId());
			stmt.setString(3, address.getAddress());
			stmt.setString(4, address.getAddress2());
			stmt.setString(5, address.getDistrict());
			stmt.setString(6, address.getPostalCode());
			stmt.setString(7, address.getPhone());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, stmt, conn);
		}
	}
	/*
	public int insertAddress(Connection conn, Address address) throws Exception{
		int addressId = 0;
		//Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//String sql = "INSERT INTO address(address, address2, district, city_id, postal_code, phone, last_update) VALUES(?,?,?,?,?,?,NOW())";
		String sql = "INSERT INTO address(city_id, address, district, postal_code, phone) VALUES(?,?,?,?,?,NOW())";
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, address.getCity().getCityId());
			stmt.setString(2, address.getAddress());
			stmt.setString(3, address.getDistrict());
			stmt.setString(4, address.getPostalCode());
			stmt.setString(5, address.getPhone());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
		return addressId;
	}
	*/
}
