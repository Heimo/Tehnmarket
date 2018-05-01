package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Category;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.dao.CategoryDao;
import com.tehnomarket.model.dao.ProductDao;

@Controller
public class AdminController {
	
	@RequestMapping(value="Account",method=RequestMethod.GET)
	public String accountPage(){
		return "account";
	}
	
	@RequestMapping(value="addProduct",method=RequestMethod.GET)
	public String addProduct(Model m) {
		Product p = new Product();
		ArrayList<Category> categories;
		try {
			categories = (ArrayList<Category>) CategoryDao.getInstance().getAllCategories();
		} catch (SQLException e) {
			return "error";
		}
		m.addAttribute("new_product", p);
		m.addAttribute("categories",categories);
		return "addProduct";
	}
	
	@RequestMapping(value="addProduct",method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("new_product") Product p) {
		try {
			System.out.println(p.getName() + " "+p.getBrand());
			ProductDao.getInstance().saveProduct(p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "error";
		}
		return "index";
		
	}
	
	@RequestMapping(value="changeProduct",method=RequestMethod.GET)
	public String changeProduct(Model m) {
		
		try {
			ArrayList<Product> products = (ArrayList<Product>) ProductDao.getInstance().getAllProducts();
			m.addAttribute("products",products);
		} catch (SQLException e) {
			return "error";
		}
		return "changeProduct";
	}
	
	@RequestMapping(value="deleteProduct",method=RequestMethod.GET)
	public String deleteProduct(HttpServletRequest request) {
		
		int id=Integer.parseInt(request.getParameter("id"));
		try {

			ProductDao.getInstance().deleteProductById(id);
		} catch (SQLException e) {
			return "error";
		}
		return "index";
	}
}
