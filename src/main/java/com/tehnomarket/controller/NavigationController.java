package com.tehnomarket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NavigationController {


	@RequestMapping(value= {"/index.html","homepage"},method=RequestMethod.GET)
	public String sendIndex() {
		return "index";
	}
	
	@RequestMapping(value="cart",method=RequestMethod.GET)
	public String goToCart() {
		return "cart";
	}
}
