package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	public String goToProducts(@PathVariable("catId") Integer catId,Model m,HttpSession session) {
		
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
		
		
		m.addAttribute("categoryId", catId);
		session.setAttribute("position", catId);
		return "/products";
	}
	
	@RequestMapping(value="sort/{sort}",method=RequestMethod.GET)
	public String goToProductsSorted(@PathVariable("sort") String sortType,Model m,HttpSession session) {
		
		
		int position = (int) session.getAttribute("position");
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			 products = (ArrayList<Product>) ProductDao.getInstance().getProductByCat(position);
			 System.out.println("no problem from db");
		} catch (SQLException e) {
			e.printStackTrace();
			return "products_error_page";
		}
		
		if(sortType.equals("min")) {
			Collections.sort(products, (p1,p2)-> Float.compare(p1.getPrice(), p2.getPrice()));
		}
		else {
			Collections.sort(products, (p1,p2)-> Float.compare(p2.getPrice(), p1.getPrice()));
		}
		m.addAttribute("products", products);
		
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
	
	
	// The cart is saved in a session with an ArrayList 
	@RequestMapping(value="*/add_to_cart",method=RequestMethod.GET)
	public String addToCart(HttpSession session,HttpServletRequest request) {
		// add to session 
		// check if basket exists in session
		// basket should be a collection of products and their quantity
		// quantity by default will be 1 when added
		int id =Integer.parseInt(request.getParameter("id"));
		System.out.println("Adding "+id+"to the cart");
		
		//get the cart from session
		ArrayList<Integer> theCart = new ArrayList<Integer>();
		
		ArrayList<Integer> cart1 = (ArrayList<Integer>) session.getAttribute("cart");
		
		// do this if cart does not exist already
		if(cart1!=null) {
			theCart.addAll(cart1);
		}
		
		//add product id to set
		theCart.add(id);
		
		//put them in a set to remove possible duplicates
		HashSet<Integer> temp = new HashSet<Integer>(theCart);
		theCart.clear();
		theCart.addAll(temp);
		
		//save new cart to session	
		session.setAttribute("cart", theCart);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="product/{id}",method=RequestMethod.GET)
	public String goToProduct(HttpServletRequest request,Model m,@PathVariable("id") Integer id,HttpSession session) {
		
		
		session.setAttribute("position", id);
		System.out.println("SESSION ID IS"+id);
		int productId;
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
