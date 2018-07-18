package com.gag.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gag.model.Country;
import com.gag.model.Gender;
import com.gag.model.db.DBManager;

public enum CountryDAO implements ICountryDAO{
	
	COUNTRY_DAO;
	private Connection con;
	
	private CountryDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}
	
	@Override
	public List<Country> getAllCountries() throws Exception {
		String sql = "SELECT id, name FROM countries";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<Country> countries = new ArrayList<>();
		while(rs.next()) {
			countries.add(new Country(rs.getInt("id"), rs.getString("name")));
		}
		return countries;
	}

}
