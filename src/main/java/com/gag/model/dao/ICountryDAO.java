package com.gag.model.dao;

import java.util.List;

import com.gag.model.Country;

public interface ICountryDAO {

	List<Country> getAllCountries() throws Exception;
}
