package com.gag.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gag.model.Country;
import com.gag.model.Gender;
import com.gag.model.Post;
import com.gag.model.User;
import com.gag.model.dao.CountryDAO;
import com.gag.model.dao.GenderDAO;
import com.gag.model.dao.PostDAO;
import com.gag.model.dao.SectionDAO;
import com.gag.model.dao.UserDAO;

@Controller
public class ApplicationController {
	
	@Autowired
	private ServletContext context;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showMain(Model m, HttpSession session) {
		try {
			context.setAttribute("sections", SectionDAO.SECTION_DAO.getAll());
			context.setAttribute("posts",  PostDAO.POST_DAO.getFreshPosts());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping(value = "/showlogin", method = RequestMethod.GET)
	public String showLoginPage() {	
		//Return the login page
		return "login";
	}
	
	@RequestMapping(value="/showSettings", method = RequestMethod.GET)
	public String showSettings(Model model, HttpSession session) {
		try {
			if (session.getAttribute("user") == null) {
				model.addAttribute("error", "We don't know who you are. Please, login first.");
				return "login";
			}
			List<Gender> genders = GenderDAO.GENDER_DAO.getAllGenders();
			List<Country> countries = CountryDAO.COUNTRY_DAO.getAllCountries();
			session.setAttribute("genders", genders);
			session.setAttribute("countries", countries);
			// return settings page
			return "settings";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "settings";
		}
	}
	
	@RequestMapping(value = "/showRegister", method = RequestMethod.GET)
	public String showRegister(Model model) {
		try {
			List<Gender> genders = GenderDAO.GENDER_DAO.getAllGenders();
			context.setAttribute("genders", genders);
			// return the register page
			return "register";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}	
	}
	
	@RequestMapping(value="/verify", method= RequestMethod.GET)
	public String showVerificationPage() {
		// shows verification page
		return "verify";
	}
	
	@RequestMapping(value="/profile", method= RequestMethod.GET)
	public String showProfile(HttpSession session) {
		// show profile page
		User user = (User)session.getAttribute("user");
				try {
					session.setAttribute("posts", PostDAO.POST_DAO.getPostsByOwner(user.getId()));
					session.setAttribute("voted", PostDAO.POST_DAO.getVotedPosts(user.getId()));
					session.setAttribute("commented", PostDAO.POST_DAO.getCommentedPosts(user.getId()));
				} catch (SQLException e) {
                   System.out.println(e.getMessage());
				}
		return "profile";
	}
	
	@RequestMapping(value="/videos", method=RequestMethod.GET)
	public String showVideos(Model model) {
		try {
			List<Post> videos = PostDAO.POST_DAO.getVideos();
			model.addAttribute("videos", videos);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "videos";
	}
}
