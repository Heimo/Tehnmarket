<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tehnomarket</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>

</head>	
<body>

	<div class="header">
		<div class="container">
			<div class="row">
			  <div class="col-md-12">
				 <div class="header-left">
					 <div class="logo" style:"display:inline-block; width: 150px;">
						<a href="${pageContext.request.contextPath}/index.html"><img src="https://i.imgur.com/7tnAVEG.png" alt=""/></a>
					 </div>

					 
	    		    <div class="clear"></div>
	    	    </div>
	            <div class="header_right">
	    		  <!-- start search-->
				      <div class="search-box">
							<div id="sb-search" class="sb-search">
								<form action="${pageContext.request.contextPath}/searchProduct">
									<input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search" id="search">
									<input class="sb-search-submit" type="submit" value="">
									<span class="sb-icon-search"> </span>
								</form>
							</div>
						</div>
						<!----search-scripts---->
						<script src="js/classie.js"></script>
						<script src="js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
						<!----//search-scripts---->
				    <ul class="icon1 sub-icon1 profile_img">
					 <li><a class="active-icon c1" href="#"> </a>
						<ul class="sub-icon1 list">
						  <div class="product_control_buttons">
						  	<a href="#"><img src="images/edit.png" alt=""/></a>
						  		<a href="#"><img src="images/close_edit.png" alt=""/></a>
						  </div>
						   <div class="clear"></div>
						  <li class="list_img"><img src="images/1.jpg" alt=""/></li>
						  <li class="list_desc"><h4><a href="#">velit esse molestie</a></h4><span class="actual">1 x
                          $12.00</span></li>
						  <div class="login_buttons">
							 <div class="check_button"><a href="checkout.html">Check out</a></div>
							 <div class="login_button"><a href="login.html">Login</a></div>
							 <div class="clear"></div>
						  </div>
						  <div class="clear"></div>
						</ul>
					 </li>
				   </ul>
		           <div class="clear"></div>
	       </div>
	      </div>
		 </div>		
		 
		 <div class="menu" style:"display:inline-block;">
			  <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
			    <ul class="nav" id="nav">

					<c:forEach var="category" items="${ categories }"  varStatus="loop">

						<c:if test="${(category.parentCategoryId == 0 && !loop.first) || loop.last}">
							 </div>
                    		</div>
                  		  </li>
						</c:if>
						
						<c:if test="${category.parentCategoryId == 0}">
						<li>
                   		 <div class="dropdown">
                     	 <button class="dropbtn">${category.categoryName}</button>
                      	 <div class="dropdown-content">
							
						</c:if>
						
						<c:if test="${category.parentCategoryId != 0}">
						<a href="${pageContext.request.contextPath}/products/${category.categoryId}">${category.categoryName }</a>
						</c:if>
						
					</c:forEach>
              
					<div class="clear"></div>
				</ul>
			</div>
				    
	    </div>	
	</div>
		 
		 <a href="${pageContext.request.contextPath}/cart">
			Cart:
			<c:if test="${empty cart}">
				0
			</c:if>
			<c:if test="${!empty cart}">
				${fn:length(cart)}
			</c:if>
		</a>
		<br>
		
		<c:if test="${empty user}">
			<a href="${pageContext.request.contextPath}/login">Click here to login</a>
		</c:if>
		<c:if test="${!empty user}">
			Hello ${user.firstName} ${user.lastName}
			<a href="${pageContext.request.contextPath}/account">Account page</a>
			<a href="${pageContext.request.contextPath}/login">Click here to logout</a>
		</c:if>
		<br>
		<br>
</body>
</html>