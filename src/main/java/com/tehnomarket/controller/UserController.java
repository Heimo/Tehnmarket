package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;
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
	public String newUser(@ModelAttribute User u,Model m) {
		
		try {
			if(u.getPassword().equals(u.getPasswordCheck())) {
				u.setPassword(HashPassword.hashPassword(u.getPassword()));
				UserDao.saveUser(u);
			}
			else {
				m.addAttribute("error", "Could not register");
				return "error";
			}
		} catch (SQLException e) {
			m.addAttribute("error", "Could not register");
			return "error";
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
	
	@RequestMapping(value="/favourites",method=RequestMethod.GET)
	public String changeProduct(Model m,HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		try {
			ArrayList<Product> products = (ArrayList<Product>) ProductDao.getInstance().getFavouritesByUserId(u.getId());
			m.addAttribute("products",products);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			m.addAttribute("error","Could not get products");
			return "error";
		}catch (Exception e) {
			m.addAttribute("error","Not logged");
			return "error";
		}
		return "favouriteProducts";
	}
	
	@RequestMapping(value="/removeFavourite/{id}",method=RequestMethod.GET)
	public String removeFavourite(Model m,HttpSession session,@PathVariable("id") int productId) {
		
		User u = (User)session.getAttribute("user");
		try {
			ProductDao.removeFromFavourites(u.getId(), productId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			m.addAttribute("error","Could find product");
			return "error";
		}catch (Exception e) {
			m.addAttribute("error","Not logged");
			return "error";
		}
		return "index";
	}
}
