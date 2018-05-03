package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tehnomarket.model.Category;
import com.tehnomarket.model.Characteristics;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.CategoryDao;
import com.tehnomarket.model.dao.CharacteristicsDao;
import com.tehnomarket.model.dao.ProductDao;
import com.tehnomarket.model.dao.UserDao;
import com.tehnomarket.util.HashPassword;

@RequestMapping(produces = "text/plain;charset=UTF-8")
@Controller
public class AdminController {
	
	@RequestMapping(value="/account",method=RequestMethod.GET)
	public String accountPage(){
		return "account";
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public String addProduct(Model m,HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}
		
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
	public String saveProduct(@ModelAttribute("new_product") Product p,Model m,HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}	
		
		try {
			System.out.println(p.getName() + " "+p.getBrand());
			ProductDao.getInstance().saveProduct(p);
		} catch (Exception e) {
			m.addAttribute("error","Could not save product");
			return "error";
		}
		return "index";
		
	}
	
	@RequestMapping(value="/listProducts",method=RequestMethod.GET)
	public String listProducts(Model m,HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}
		
		try {
			ArrayList<Product> products = (ArrayList<Product>) ProductDao.getInstance().getAllProducts();
			m.addAttribute("products",products);
		} catch (SQLException e) {
			m.addAttribute("error","Could not get products");
			return "error";
		}
		return "listProducts";
	}
	
	@RequestMapping(value="/editProduct",method=RequestMethod.GET)
	public String editProduct(Model m,HttpServletRequest request,HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}
		
		ArrayList<Category> categories;
		ArrayList<Characteristics> characts = new ArrayList<Characteristics>();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = ProductDao.getInstance().getProductById(id);
			m.addAttribute("edit_product",product);
			categories = (ArrayList<Category>) CategoryDao.getInstance().getAllCategories();
			m.addAttribute("categories",categories);
			characts = CharacteristicsDao.getInstance().getAllProductChar(id);
			m.addAttribute("characts", characts);
		} catch (SQLException e) {
			m.addAttribute("error","Could not get product");
			return "error";
		}
		return "editProduct";
	}
	
	@RequestMapping(value="/editProduct",method=RequestMethod.POST)
	@ResponseBody
	public String saveEditedProduct(@RequestBody Product p,Model m,HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}
		
		try {
			System.out.println(p.getName());
			ProductDao.getInstance().editProduct(p);
		} catch (SQLException e) {
			m.addAttribute("error","Could not get product " + e.getMessage());
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping(value="/deleteProduct",method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request,Model m,HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(!u.isAdmin()) {
			return "redirect:/";
		}
		
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
	
	@RequestMapping(value="/getCharacteristics/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String getCharacteristics(HttpServletRequest request,@PathVariable("id") int catId){
		
		ArrayList<Characteristics> characts = new ArrayList<Characteristics>();
		try {
			characts = CharacteristicsDao.getInstance().getCategoryCharacteristics(catId);
		} catch (SQLException e) {
			
			return "error";
		}
		Gson gson = new GsonBuilder().create();
		
		return gson.toJson(characts);
	}
	
	@RequestMapping(value="/saveCharacteristics",method=RequestMethod.POST)
	@ResponseBody
	public String saveCharacteristics(HttpServletRequest request,@RequestBody ArrayList<Characteristics> characts){
		try {
			ProductDao.getInstance().replaceProductCharacteristics(characts);
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "ok";
	}
}
