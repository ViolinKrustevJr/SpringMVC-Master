package com.gag.model.dao;

import com.gag.model.User;

public interface IUserDAO {

	void saveUser(User u) throws Exception;
	void deleteUser(User u) throws Exception;
	void deleteInactiveUsers() throws Exception;
	void updateUserData(User u) throws Exception;
	User getUserById(int id) throws Exception;
	User getUserByUsername(String username)throws Exception;
	String checkForEmail(String email)throws Exception;
	String checkForUsername(String username) throws Exception;
	
}
