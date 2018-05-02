package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.dao.ProductDao;


@Controller
public class NavigationController {


	@RequestMapping(value= {"/index.html","homepage"},method=RequestMethod.GET)
	public String sendIndex() {
		return "index";
	}
	
	@RequestMapping(value= {"*/index.html"},method=RequestMethod.GET)
	public String sendIndexFromOtherPages() {
		return "redirect:/";
	}

	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String goToCart(HttpSession session,Model m) {
		
		//check if cart is empty
		if(session.getAttribute("cart")==null) {
			return "cart";
		}
		
		HashMap<Product,Integer> mapProduct = (HashMap<Product,Integer>) session.getAttribute("cart");
		
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
			 quantities = ProductDao.getProductQuantities(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("PROBLEM WITH QUANTITY ARRAY");
			m.addAttribute("error","Could not get products");
			return "database_error_page";
		}
		
		/* mapProduct which is the cart
		 * productArr which is an Array with the products from the cart
		 * quantities which is information from db for available quantitiy of products
		 * 
		 */
		
		m.addAttribute("productArr", productArr);
		m.addAttribute("quantities", quantities); // might have to be saved in session
		
		
		return "cart";
	}
}
