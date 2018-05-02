package com.tehnomarket.controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.dao.ProductDao;

@Controller
public class OrderController {

	
	@RequestMapping(value="/cart",method=RequestMethod.POST)
	public String goToOrder(Model m,HttpSession session) {
		
		HashMap<Product,Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		if(cart.isEmpty()) {
			// make a jsp that just says "Your cart is empty and a sad face"
			return "emptyCart";
		}
		
		HashMap<Product,Integer> cartProducts = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		m.addAttribute("theCart", cartProducts);
			
		return "cart";
	}
	
	@RequestMapping(value="/quantity/{productId}/{option}",method=RequestMethod.GET)
	public String quantityControl(HttpSession session,Model m,@PathVariable("productId") int productId,@PathVariable("option") String change) throws SQLException{
		
		//this is the amount we want to increase
		int amount;
		if(change.equals("increase")) {
			amount=1;
		}
		else {
			amount=-1;
		}
		
		
		Product product = (Product) ProductDao.getProductById(productId);
		System.out.println("THIS IS OUR KEY FOR THE KART "+product);
		
		HashMap<Product,Integer> theCart = (HashMap<Product, Integer>) session.getAttribute("cart");
		System.out.println("THESE ARE ALL THE KEYS FOR THE CART" + theCart.keySet());
		
		HashMap<Product,Integer> maxQuantity = (HashMap<Product, Integer>) session.getAttribute("productQuantity");
		
		
		
		
		int currentQuant = theCart.get(product);
		int maxQuant = maxQuantity.get(product);
		
		// checks if user is trying to go to negative or above possible amount
		// changes nothing if either limit is breached 
		if(currentQuant==1 && amount==-1) {
			return "cart";
		}
		else if(currentQuant==maxQuant && amount==1) {
			return "cart";
		}
		else {
			currentQuant+=amount;
			theCart.put(product,currentQuant);
			session.setAttribute("cart", theCart);
		}
		
		return "redirect:/cart";
	}
	
}
