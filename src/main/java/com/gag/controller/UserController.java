package com.gag.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gag.controller.manager.UserManager;
import com.gag.model.User;
import com.gag.model.dao.UserDAO;
import com.gag.service.NotificationService;
import com.gag.util.exceptions.RegisterException;


@Controller
public class UserController {

	@Autowired
	private NotificationService notificationService;
	
	private static final String FILE_PATH = "C:\\Users\\HP\\Desktop\\uploads\\";
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String login(Model model, HttpServletRequest request, HttpSession session) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = UserManager.USER_MANAGER.loginUser(username, password);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(10000);
			return "index";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String register(HttpServletRequest request, Model model, HttpSession session) {
		try {
			String pass = request.getParameter("password");
			String pass2 = request.getParameter("password2");
			if (!pass.equals(pass2)) {
				throw new RegisterException("passwords should match");
			}
			
			User user = new User(	request.getParameter("firstName"), request.getParameter("lastName"), 
									request.getParameter("username"), request.getParameter("password"), 
									request.getParameter("email"),
									Integer.parseInt(request.getParameter("gender")));
			synchronized (UserController.class) {
				UserManager.USER_MANAGER.registerUser(user);
				notificationService.sendNotification(user);
			}
			model.addAttribute("success", "Activation code has been sent to your email.");
			return "index";
		} catch (RegisterException | SQLException e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String changeUserData( Model model, HttpSession session,
								  @RequestParam("avatar") MultipartFile file, 
								  HttpServletRequest request) {
		try {
			if (session.getAttribute("user") == null) {
				model.addAttribute("error", "We don't know who you are. Please, login first.");
				return "login";
			}
			User user = (User) session.getAttribute("user");
			String fileName = user.getPhoto();
			if (file != null && !file.isEmpty()) {
				fileName = "avatar-" + user.getUsername() + "-" + file.getOriginalFilename();
				System.out.println(fileName);
				File serverFile = new File(FILE_PATH + fileName);
				serverFile.createNewFile();
				Files.copy(file.getInputStream(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String biography = request.getParameter("biography");
			int country =  Integer.parseInt(request.getParameter("country"));
			int genderId = Integer.parseInt(request.getParameter("gender"));
			
			UserManager.USER_MANAGER.changeProfile(user, firstName, lastName, fileName, biography, genderId, country);
			model.addAttribute("success", "Data changed successfuly.");
			return "settings";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "settings";
		}
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(HttpSession session, Model model) {
		try {
			if (session.getAttribute("user") == null) {
				model.addAttribute("error", "We don't know who you are. Please, login first.");
				return "login";
			}
			User user = (User) session.getAttribute("user");
			UserManager.USER_MANAGER.deleteAccount(user);
			session.invalidate();
			return "index";
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping(value= "/activate", method= RequestMethod.POST)
	public String activateAccount(HttpServletRequest request, Model model) {
		try {
			String username = (String) request.getParameter("username");
			String code = (String) request.getParameter("code");
			UserManager.USER_MANAGER.activateUser(username, code);
			model.addAttribute("success", "Activation succeeded!");
		} catch (Exception e) {
			model.addAttribute("error", "Sorry, there was a problem: " + e.getMessage());
			return "verify";
		}
		return "login";
	}
}
