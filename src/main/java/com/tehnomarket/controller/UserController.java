package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;
import com.tehnomarket.model.dao.UserDao;
import com.tehnomarket.util.HashPassword;
import com.tehnomarket.util.SendMailSSL;
import java.util.UUID;

@Controller
//@RequestMapping(value="/try")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerUser(Model m) {
		User u = new User();
		m.addAttribute("new_user", u);
		return "newUser";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String newUser(@ModelAttribute User u,Model m) throws SQLException {
		
		if(u.getPassword().equals(u.getPasswordCheck())) {
			u.setPassword(HashPassword.hashPassword(u.getPassword()));
			userDao.saveUser(u);
		}
		else {
			m.addAttribute("error", "Could not register");
			return "error";
		}
		return "index";
		
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String sendLogin(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "login";
			}else {
				session.invalidate();
				return "index";
			}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLogin(HttpServletRequest request,HttpSession session) throws SQLException, IllegalArgumentException {
		String pass = request.getParameter("pass");
		String email = request.getParameter("email"); 
		String hashpass = userDao.getHashPass(email);
		User u = null;
		if(HashPassword.checkPassword(pass, hashpass)) {
			u = userDao.getUser(email,hashpass);
		}
		if(u != null) {
			session.setAttribute("user", u);
			return "index";
		}
		else {
			request.setAttribute("error","Incorrect Login");
			return "login";
		}
	}
	
	@RequestMapping(value="/favourites",method=RequestMethod.GET)
	public String changeProduct(Model m,HttpSession session) throws Exception {
		
		User u = (User)session.getAttribute("user");
		ArrayList<Product> products = (ArrayList<Product>) productDao.getFavouritesByUserId(u.getId());
		m.addAttribute("products",products);
		return "favouriteProducts";
	}
	
	@RequestMapping(value="/removeFavourite/{id}",method=RequestMethod.GET)
	public String removeFavourite(Model m,HttpSession session,@PathVariable("id") int productId) throws Exception {
		
		User u = (User)session.getAttribute("user");
		productDao.removeFromFavourites(u.getId(), productId);
		return "index";
	}
	
	// FORGOT PASS
	@RequestMapping(value="/forgotPass",method=RequestMethod.GET)
	public String forgotPass(Model m) {
		
		User u = new User();
		m.addAttribute("new_pass", u);
		return "NewPassword";
	}
	
	@RequestMapping(value="/forgotPass",method=RequestMethod.POST)
	public String restorePass(@ModelAttribute User u,Model m) throws SQLException {
		
		User check = userDao.checkEmail(u.getEmail());
		
		if(check!=null) {
			//new password
			String password = UUID.randomUUID().toString().replace("-", "");
			String hashPass= HashPassword.hashPassword(password);
			check.setPassword(hashPass);
			userDao.editUser(check);
			//send password
			String receiver = check.getEmail();
			String subject = "Tehnomarket New Password !";
			String text = "Your new password for Tehnomarket is: "+password;
			SendMailSSL send = new SendMailSSL(receiver,subject,text);
			send.sendMail();
		}
		else {
			m.addAttribute("error","No such user,try again m8");
			return "error";
		}
		
		
		return "index";
	}
}
