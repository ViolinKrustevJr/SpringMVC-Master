package com.gag.controller.manager;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gag.model.BCrypt;
import com.gag.model.User;
import com.gag.model.dao.UserDAO;
import com.gag.service.NotificationService;
import com.gag.util.exceptions.RegisterException;

public enum UserManager {

	USER_MANAGER;
	private static final String[] EMAIL_DOMAINS = {"abv.bg", "gmail.com", "yahoo.com", "hotmail.com", "aol.com", "msn.com"};

	public void registerUser(User u) throws RegisterException, SQLException {

		if (u.getUsername() == null || u.getUsername().length() < 5) {
			throw new RegisterException("Username must be at least 5 chars long");
		}
		if (UserDAO.USER_DAO.checkForUsername(u.getUsername()) != null &&
			UserDAO.USER_DAO.checkForUsername(u.getUsername()).length() > 0) {
			throw new RegisterException("Username already exists");
		}
		if (u.getPassword() == null || u.getPassword().length() < 5) {
			throw new RegisterException("password should be at least 5 chars long");
		}
		if (u.getFirstName() == null) {
			throw new RegisterException("enter first name");
		}
		if (u.getLastName() == null) {
			throw new RegisterException("enter last name");
		}
		if (u.getGenderId() < 1) {
			throw new RegisterException("gender not added");
		}
		if (!emailValidator(u.getEmail())) {
			throw new RegisterException("inalid email");
		}
		if (UserDAO.USER_DAO.checkForEmail(u.getEmail()) != null &&
			UserDAO.USER_DAO.checkForEmail(u.getEmail()).length() > 0) {
			throw new RegisterException("email already exists");
		}

		UserDAO.USER_DAO.saveUser(u);
	}

	public User loginUser(String username, String password) throws Exception {
		User u = UserDAO.USER_DAO.getUserByUsername(username);
		if (username == null || password == null || u==null || !(u.getUsername().equals(username))
				|| !(BCrypt.checkpw(password, u.getPassword())) || u.getActivationCode() != null) {
			throw new Exception("Invalid username/password");
		}
		return u;
	}

	public void changeProfile(	User user, String firstName, String lastName, String photo, String biography,
								int genderId, int country) throws Exception {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new Exception("The field for first name can not be empty.");
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new Exception("The field for last name can not be empty.");
		}
		if (user.getFirstName().equals(firstName) && 
			user.getLastName().equals(lastName) && 
			(user.getGenderId() == genderId) && 
			photo.equals(user.getPhoto()) && 
			biography.equals(user.getBiography()) && 
			(user.getCountryId() == country)) {
			throw new Exception("Nothing was changed.");
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhoto(photo);
		user.setGenderId(genderId);
		user.setCountryId(country);
		user.setBiography(biography);
		UserDAO.USER_DAO.updateUserData(user);
	}
	
	public void deleteAccount(User user) throws Exception {
		if (user == null) {
			throw new Exception("Not logged in.");
		}
		UserDAO.USER_DAO.deleteUser(user);
	}
	
	public void activateUser(String username, String code) throws Exception {
		User user = UserDAO.USER_DAO.getUserByUsername(username);
		System.out.println(user.getActivationCode());
		if (!user.getActivationCode().equals(code)) {
			throw new Exception("Invalid activation code");
		}
		user.setActivationCode(null);
		UserDAO.USER_DAO.updateUserData(user);
	}
	
	private boolean emailValidator(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		if (matcher.find()) {
			String emailDomain = email.substring(email.indexOf("@")+1);
			System.out.println(emailDomain);
			for (String domain : EMAIL_DOMAINS) {
				if (emailDomain.equals(domain)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
}
