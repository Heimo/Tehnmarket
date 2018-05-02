package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Category;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.CategoryDao;
import com.tehnomarket.model.dao.ProductDao;
import com.tehnomarket.model.dao.UserDao;
import com.tehnomarket.util.HashPassword;

@Controller
public class AdminController {
	
	@RequestMapping(value="/Account",method=RequestMethod.GET)
	public String accountPage(){
		return "account";
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public String addProduct(Model m) {
		Product p = new Product();
		ArrayList<Category> categories;
		try {
			categories = (ArrayList<Category>) CategoryDao.getInstance().getAllCategories();
		} catch (SQLException e) {
			m.addAttribute("error","SQL error");
			return "error";
		}
		m.addAttribute("new_product", p);
		m.addAttribute("categories",categories);
		return "addProduct";
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("new_product") Product p,Model m) {
		try {
			System.out.println(p.getName() + " "+p.getBrand());
			ProductDao.getInstance().saveProduct(p);
		} catch (Exception e) {
			m.addAttribute("error","Could not save product");
			return "error";
		}
		return "index";
		
	}
	
	@RequestMapping(value="/changeProduct",method=RequestMethod.GET)
	public String changeProduct(Model m) {
		
		try {
			ArrayList<Product> products = (ArrayList<Product>) ProductDao.getInstance().getAllProducts();
			m.addAttribute("products",products);
		} catch (SQLException e) {
			m.addAttribute("error","Could not get products");
			return "error";
		}
		return "changeProduct";
	}
	
	@RequestMapping(value="/deleteProduct",method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request,Model m) {
		
		int id=Integer.parseInt(request.getParameter("id"));
		try {

			ProductDao.getInstance().deleteProductById(id);
		} catch (SQLException e) {
			m.addAttribute("error","Could not delete product");
			return "error";
		}
		return "index";
	}
	
	// CONTROLERS FOR EDITING USER 
	
	@RequestMapping(value="/editUser",method=RequestMethod.GET)
	public String editUser(HttpSession session,Model m) {
		
		
		
		User oldUser = (User) session.getAttribute("user");
		User u = new User();
		
		// set the data as the old data !
		System.out.println("TESTING USER ID IS "+oldUser.getId());
		u.setId(oldUser.getId());
		u.setFirstName(oldUser.getFirstName());
		u.setLastName(oldUser.getLastName());
		u.setDateOfBirth(oldUser.getDateOfBirth());
		u.setGender(oldUser.getGender());
		
		m.addAttribute("edit_user", u);
		
		return "editUser";
	}
	
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	public String newEditUser(@ModelAttribute User u,Model m,HttpSession session) {
		
		try {
			User oldUser = (User) session.getAttribute("user");
			u.setId(oldUser.getId());
			if(u.getPassword().equals(u.getPasswordCheck())) {
				u.setPassword(HashPassword.hashPassword(u.getPassword()));
				UserDao.editUser(u);
			}
			else {
				System.out.println("PASSWORDS DIDN' MATCH OR SOMETHING");
				m.addAttribute("error","Registration error");
				return "error";
			}
		} catch (SQLException e) {
			System.out.println("SOMETHING WENT WRONG WITH THE SQL ");
			m.addAttribute("error","REgistration eror");
			return "registrationError";
		}
		
		
		return "redirect:/";
	}
	
}
