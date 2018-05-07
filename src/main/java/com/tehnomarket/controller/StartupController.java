package com.tehnomarket.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.tehnomarket.model.dao.CategoryDao;

@Component
public class StartupController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	CategoryDao categoryDao;

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() throws SQLException {
    context.setAttribute("categories", categoryDao.getAllCategories());
  }
}
