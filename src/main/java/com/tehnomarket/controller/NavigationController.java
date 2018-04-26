package com.tehnomarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NavigationController {


	@RequestMapping(value="/index.html",method=RequestMethod.GET)
	public String sendIndex(Model m) {
		return "index";
	}
}
