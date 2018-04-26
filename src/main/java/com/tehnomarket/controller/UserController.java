package com.tehnomarket.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.UserDao;

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
			UserDao.saveUser(u);
		} catch (SQLException e) {
			return "registrationError";
		}
		return "index";
	}
}
