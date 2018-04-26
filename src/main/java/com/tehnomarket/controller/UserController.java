package com.tehnomarket.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.UserDao;
import com.tehnomarket.util.HashPassword;

@Controller
//@RequestMapping(value="/try")
public class UserController {

	
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerUser(Model m) {
		User u = new User();
		m.addAttribute("new_user", u);
		return "newUser";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String newUser(@ModelAttribute User u) {
		
		try {
			if(u.getPassword().equals(u.getPasswordCheck())) {
				u.setPassword(HashPassword.hashPassword(u.getPassword()));
				UserDao.saveUser(u);
			}
			else {
				return "registrationError";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "registrationError";
		}
		return "index";
		
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String sendLogin(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "login";
			}else {
				session.invalidate();
				return "index";
			}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLogin(HttpServletRequest request,HttpSession session) {
		try {
			String pass = request.getParameter("pass");
			String email = request.getParameter("email"); 
			String hashpass = UserDao.getHashPass(email);
			User u = null;
			if(HashPassword.checkPassword(pass, hashpass)) {
				u = UserDao.getUser(email,hashpass);
			}
			if(u != null) {
				session.setAttribute("user", u);
				return "index";
			}
			else {
				request.setAttribute("error","Incorrect Login");
				return "login";
			}

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to connect to db");
			request.setAttribute("error"," System error");
			return "login";
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error",e.getMessage());
			return "login";
		}
	}
}
