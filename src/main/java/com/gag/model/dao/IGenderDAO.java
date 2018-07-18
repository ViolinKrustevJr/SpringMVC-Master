package com.gag.model.dao;

import java.util.List;
import com.gag.model.Gender;

public interface IGenderDAO {

	List<Gender> getAllGenders() throws Exception;
}
