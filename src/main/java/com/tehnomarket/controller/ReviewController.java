package com.tehnomarket.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tehnomarket.model.Review;
import com.tehnomarket.model.dao.ReviewDao;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@RequestMapping(value="/product/rateProduct",method=RequestMethod.POST)
	public String rateProduct(HttpServletRequest request,Model m) {
		Review r = new Review(Integer.parseInt(request.getParameter("user_id")),
							  Integer.parseInt(request.getParameter("product_id")),
							  Integer.parseInt(request.getParameter("rate")),
							  null,
							  request.getParameter("comment"));
		try {
			reviewDao.saveReview(r);
		} catch (SQLException e) {
			m.addAttribute("error", "Could not save review " + e.getMessage());
			return "error";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/product/deleteRating",method=RequestMethod.POST)
	public String deleteRating(HttpServletRequest request,Model m) {
		Review r = new Review( Integer.parseInt(request.getParameter("review_id")),
								Integer.parseInt(request.getParameter("user_id")),
							  Integer.parseInt(request.getParameter("product_id")));
		try {
			reviewDao.deleteReview(r);
		} catch (SQLException e) {
			m.addAttribute("error", "Could not save review " + e.getMessage());
			return "error";
		}
		
		return "redirect:/";
	}
}
