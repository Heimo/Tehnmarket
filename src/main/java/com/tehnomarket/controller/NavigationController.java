package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
		return "/index";
	}
	
	@RequestMapping(value="cart",method=RequestMethod.GET)
	public String goToCart(HttpSession session,Model m) {
		
		//check if cart is empty
		if(session.getAttribute("cart")==null) {
			return "empty_cart_page";
		}
		
		ArrayList<Integer> itemIds = (ArrayList<Integer>) session.getAttribute("cart");
		
		//get all products from cart as objects
		ArrayList<Product> productArr = new ArrayList<Product>();
		for(int i=0;i<productArr.size();i++) {
			try {
				productArr.add(ProductDao.getProductById(itemIds.get(i)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("PROBLEM WITH GETTING PRODUCTS ARRAY");
				return "database_error_page";
			}
		}
		// get quantities for the products 
		
		ArrayList<Integer> quantites = new ArrayList<Integer>();
		try {
			ArrayList<Integer> quantities = ProductDao.getProductQuantities(itemIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("PROBLEM WITH QUANTITY ARRAY");
			return "database_error_page";
		}
		
		m.addAttribute("quantities",quantites);
		m.addAttribute("cart", productArr);
		
		return "cart";
	}
}
