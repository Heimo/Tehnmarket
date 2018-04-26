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
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@RequestMapping(value="add_to_cart",method=RequestMethod.GET)
	public String addToCart(HttpSession session,HttpServletRequest request) {
		// add to session 
		// check if basket exists in session
		// basket should be a collection of products and their quantity
		// quantity by default will be 1 when added 
		
		int productId = Integer.parseInt(request.getParameter("id"));
		Product product = null;
		try {
		 product = ProductDao.getInstance().getProductById(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<Product,Integer> cart = (HashMap<Product,Integer>) session.getAttribute("cart");
		
		System.out.println("cart "+cart);
		if(cart==null) {
			cart = new HashMap();
		}
		System.out.println("product "+ product);
		
		if(cart.get(product)!=null) {
				cart.put(product, cart.get(product)+1);
		}
		else {
			cart.put(product, 1);
		}
			
		session.setAttribute("cart", cart);
		return "index";
	}
	
	@RequestMapping(value="product",method=RequestMethod.GET)
	public String goToProduct(HttpServletRequest request,Model m) {
		
		int productId = Integer.parseInt(request.getParameter("id"));
		try {
			Product product = ProductDao.getInstance().getProductById(productId);
			m.addAttribute("product",product);
			System.out.println("tuak "+ productId + " sum "+product );
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
