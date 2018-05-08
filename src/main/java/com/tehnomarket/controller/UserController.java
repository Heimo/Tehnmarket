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

import com.tehnomarket.model.Order;
import com.tehnomarket.model.Product;
import com.tehnomarket.model.User;
import com.tehnomarket.model.dao.ProductDao;
import com.tehnomarket.model.dao.UserDao;
import com.tehnomarket.util.HashPassword;
import com.tehnomarket.util.SendMailSSL;
import java.util.UUID;

@Controller
// @RequestMapping(value="/try")
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model m) {
		User u = new User();
		m.addAttribute("new_user", u);
		return "newUser";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String newUser(@ModelAttribute User u, Model m) throws SQLException {
		
		//check that names are not spaces
		boolean firstName = !u.getFirstName().contains(" ");
		boolean secondName =! u.getLastName().contains(" ");
		boolean email = u.getEmail().contains("@");
		String g = u.getGender();
		boolean gender = (g.equals("M") || g.equals("F") || g.equals("G"));
		boolean password = u.getPassword().equals(u.getPasswordCheck());
		// regex check for password
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		boolean password2 = u.getPassword().matches(regex);
		

		if (firstName && secondName && email && gender && password && password2) {
			u.setPassword(HashPassword.hashPassword(u.getPassword()));

			userDao.saveUser(u);
		} else {
			m.addAttribute("errorMessage", "The input you've entered did not match our standards, password must be at least 8 chars long and etc.");
			return "login";
		}
		return "index";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String sendLogin(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "login";
		} else {
			session.invalidate();
			return "index";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request, HttpSession session, Model m)  {
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		try {
			String hashpass = userDao.getHashPass(email);
			User u = null;
			if (HashPassword.checkPassword(pass, hashpass)) {
				u = userDao.getUser(email, hashpass);
			}
			if (u != null) {
				session.setAttribute("user", u);
				return "index";
			} else {
				request.setAttribute("error", "Incorrect Login");
				return "login";
			}
		} catch (Exception e) {
			m.addAttribute("errorMessage", "The entered parameters don't match any in our database!");
			return "login";
		}
	}

	@RequestMapping(value = "/favourites", method = RequestMethod.GET)
	public String changeProduct(Model m, HttpSession session) throws Exception {

		User u = (User) session.getAttribute("user");
		ArrayList<Product> products = (ArrayList<Product>) productDao.getFavouritesByUserId(u.getId());
		m.addAttribute("products", products);
		return "favouriteProducts";
	}

	@RequestMapping(value = "/removeFavourite/{id}", method = RequestMethod.GET)
	public String removeFavourite(Model m, HttpSession session, @PathVariable("id") int productId) throws Exception {

		User u = (User) session.getAttribute("user");
		productDao.removeFromFavourites(u.getId(), productId);
		return "redirect:/favourites";
	}

	// FORGOT PASS
	@RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
	public String forgotPass(Model m) {

		User u = new User();
		m.addAttribute("new_pass", u);
		return "NewPassword";
	}

	@RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
	public String restorePass(@ModelAttribute User u, Model m) throws SQLException {

		User check = userDao.checkEmail(u.getEmail());

		if (check != null) {
			// new password
			String password = UUID.randomUUID().toString().replace("-", "");
			String hashPass = HashPassword.hashPassword(password);
			check.setPassword(hashPass);
			userDao.editUser(check);
			// send password
			String receiver = check.getEmail();
			String subject = "Tehnomarket New Password !";
			String text = "Your new password for Tehnomarket is: " + password;
			SendMailSSL send = new SendMailSSL(receiver, subject, text);
			send.sendMail();
		} else {
			m.addAttribute("errorMessage", "No such user email,try again!");
			return "login";
		}

		return "login";
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String accountPage(Model m, HttpSession session) throws SQLException {

		// orders
		User u = (User) session.getAttribute("user");
		int userId = (int) u.getId();

		ArrayList<Order> orders = productDao.getOrders(userId);

		m.addAttribute("Orders", orders);
		//
		return "account";
	}
}
