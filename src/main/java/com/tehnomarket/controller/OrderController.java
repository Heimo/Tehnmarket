package com.tehnomarket.controller;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Order;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;

@Controller
public class OrderController {

	
	@RequestMapping(value="/cart",method=RequestMethod.POST)
	public String goToOrder(Model m,HttpSession session,@ModelAttribute Order o) {
		
		// tehnically nobody should be able to see the make order button without
		// having anything in the cart , but just in case 
		HashMap<Product,Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		if(cart.isEmpty()) {
			// make a jsp that just says "Your cart is empty and a sad face"
			return "emptyCart";
		}
		
		//now we have product o with input for delivery and number
		//first set the date of the order
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		sdf.format(now);
		o.setDateOfOrder(now);
		
		//set totalCost
		double combinedCost=0;
		for(Product key: cart.keySet()) {
			int amount = cart.get(key);
			combinedCost+=combinedCost + ( amount*(key.getPrice()-key.getDiscount()) );
		}
		o.setTotalCost(combinedCost);
		
		//set status -1 for canceled 0 for in process and etc. 
		o.setStatus(0);
		
		//set user Id
		User user = (User) session.getAttribute("user");
		o.setUserId(user.getId());
		
		//set the products in the order
		o.setOrder(cart);
		
		System.out.println("Testing ORDER !! : "+o);
		
		ProductDao.makeOrder(o);
		
		// empty old cart 
		cart = new HashMap<Product,Integer>();
		session.setAttribute("cart", cart);
			
		return "index";
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
		
		
		HashMap<Product,Integer> theCart = (HashMap<Product, Integer>) session.getAttribute("cart");
		
		
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
			System.out.println("CHANGING AMOUNT: "+currentQuant);
			theCart.put(product,currentQuant);
			session.setAttribute("cart", theCart);
		}
		
		return "redirect:/cart";
	}
	
}
