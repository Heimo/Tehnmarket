package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;

@Controller
public class ProductController {

	@RequestMapping(value="/products/{catId}",method=RequestMethod.GET)
	public String goToProducts(@PathVariable("catId") Integer catId,Model m) {
		
		System.out.println(catId);
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			 products = (ArrayList<Product>) ProductDao.getInstance().getProductByCat(catId);
			 System.out.println("no problem from db");
		} catch (SQLException e) {
			e.printStackTrace();
			return "products_error_page";
		}
		
		m.addAttribute("products", products);
		if(products.isEmpty()) {
			System.out.println("nqma produkti");
			return "products_error_page";
		}
		return "products";
	}
	
	@RequestMapping(value="searchProduct",method=RequestMethod.POST)
	public String searchProducts(@ModelAttribute("search") String search,Model m) {
		System.out.println(search);
		ArrayList<Product> products = new ArrayList<>();
		
		try {
			products = (ArrayList<Product>) ProductDao.getInstance().search(search);
			m.addAttribute("products",products);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "products_error_page";
		}
		
		
		return "index";
	}
	
	
	// The cart is saved in a session with a HashSet
	@RequestMapping(value="products/add_to_cart/{id}",method=RequestMethod.GET)
	public String addToCart(HttpSession session,@PathVariable("id") Integer id) {
		// add to session 
		// check if basket exists in session
		// basket should be a collection of products and their quantity
		// quantity by default will be 1 when added
		System.out.println("Adding "+id+"to the cart");
		
		//get the cart from session
		HashSet<Integer> theCart = new HashSet<Integer>();
		
		HashSet<Integer> cart = (HashSet<Integer>) session.getAttribute("cart");
		
		// do this if cart does not exist already
		if(cart!=null) {
			theCart.addAll(cart);
		}
		
		//add product id to set
		theCart.add(id);
		
		//save new cart to session	
		session.setAttribute("cart", theCart);
		
		return "index";
	}
	
	@RequestMapping(value="product/{id}",method=RequestMethod.GET)
	public String goToProduct(HttpServletRequest request,Model m,@PathVariable("id") Integer id) {
		
		int productId = Integer.parseInt(request.getParameter("id"));
		// D change 
		productId = id;
		
		
		try {
			Product product = ProductDao.getInstance().getProductById(productId);
			m.addAttribute("product",product);
			System.out.println("tuka "+ productId + " sum "+product );
			if(product!=null){
				return "product";
			}
			else {
				return "products_error_page";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "products_error_page";
		}
	}
}
