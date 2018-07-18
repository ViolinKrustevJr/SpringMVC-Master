package com.gag.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gag.model.Gender;
import com.gag.model.db.DBManager;

public enum GenderDAO implements IGenderDAO{

	GENDER_DAO;
	private Connection con;
	
	private GenderDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}
	
	@Override
	public List<Gender> getAllGenders() throws Exception {
		String sql = "SELECT id, type FROM genders";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<Gender> genders = new ArrayList<>();
		while(rs.next()) {
			genders.add(new Gender(rs.getInt("id"), rs.getString("type")));
		}
		return genders;
	}

}
