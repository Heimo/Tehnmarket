package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Order;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.CategoryDao;
import com.tehnomarket.model.dao.ProductDao;


@Controller
public class NavigationController {

	@Autowired
	ServletContext context;
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value= {"/index.html","/homepage"},method=RequestMethod.GET)
	public String sendIndex(Model m) {
		return "index";
	}
	
	/*
	@RequestMapping(value= {"/index.html"},method=RequestMethod.GET)
	public String sendIndexFromOtherPages() {
		return "redirect:/";
	}*/

	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String goToCart(HttpSession session,Model m) {
		Order o = new Order();
		m.addAttribute("new_order", o);
		
		HashMap<Product,Integer> mapProduct = (HashMap<Product,Integer>) session.getAttribute("cart");
		if(mapProduct == null) {
			mapProduct= new HashMap<Product,Integer>();
		}
		
		//get all products from cart as objects
		Set<Product> productSet = mapProduct.keySet();
		ArrayList<Product> productArr = new ArrayList<Product>(productSet);
		
		
		// Get all id's from the products into an array
		ArrayList<Integer> productId = new ArrayList<Integer>();
		for(int i=0;i<productArr.size();i++) {
			productId.add(productArr.get(i).getId());
		}
		
		
		// get quantities FROM STORE for the products 
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		try {
			 quantities = productDao.getProductQuantities(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("PROBLEM WITH QUANTITY ARRAY");
			m.addAttribute("error","Could not get products");
			return "database_error_page";
		}
		
		HashMap<Product,Integer> maxQuantity = new HashMap<Product,Integer>();
		
		for(int i=0;i<quantities.size();i++) {
			maxQuantity.put(productArr.get(i), quantities.get(i));
		}
		
		
		
		for(int i=0;i<productArr.size();i++) {
			Product p = productArr.get(i);
			int amount = mapProduct.get(p);
			p.setAmount(amount);
			productArr.set(i, p);
		}
		
		// total price
		int totalPrice = 0;
		for(Product key: mapProduct.keySet()) {
			System.out.println(key.getPrice());
			System.out.println(mapProduct.get(key));
			
			totalPrice += (key.getPrice()*mapProduct.get(key));
		}
		
		
		
		
		
		/* mapProduct which is the cart
		 * productArr which is an Array with the products from the cart
		 * quantities which is information from db for available quantitiy of products
		 * maxQuantitiy which is a map with the product as a key and its max quantity from db
		 */
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("productArr", productArr);
		session.setAttribute("mapProduct",mapProduct);
		session.setAttribute("productQuantity", maxQuantity);
		
		return "cart";
	}
	
	
}
