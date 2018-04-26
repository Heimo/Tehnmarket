package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;

@Controller
public class ProductController {

	@RequestMapping(value="products/{categoryId}",method=RequestMethod.GET)
	public String goToProducts(@RequestParam("categoryId") int catId,Model m) {
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			 products = (ArrayList<Product>) ProductDao.getInstance().getProductByCat(catId);
		} catch (SQLException e) {
			e.printStackTrace();
			return "products_error_page";
		}
		
		m.addAttribute("products", products);
		if(products.isEmpty()) {
			return "products_error_page";
		}
		return "products";
	}
	
	@RequestMapping(value="searchProduct",method=RequestMethod.POST)
	public String searchProducts(HttpServletRequest request) {
		String search=(String) request.getAttribute("search");
		
		ArrayList<Product> product = new ArrayList<>();
		
		try {
			product = (ArrayList<Product>) ProductDao.getInstance().search(search);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "products_error_page";
		}
		
		
		return "products";
	}
	
	
	@RequestMapping(value="add_to_cart/{productId}",method=RequestMethod.GET)
	public void addToCart(HttpSession session,@RequestParam("productId") int productId) {
		// add to session 
		// check if basket exists in session
		// basket should be a collection of products and their quantity
		// quantity by default will be 1 when added 
		
		final int QUANTITY = 1; 
		
		Map<Product,Integer> cart = new HashMap();
		
		
		if(session.getAttribute("cart")!=null) {
			cart = (HashMap<Product,Integer>) session.getAttribute("basket");
		}
		
		try {
			cart.put(ProductDao.getInstance().getProductById(productId),QUANTITY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("cart", cart);
		
	}
	
	
}
