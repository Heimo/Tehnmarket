<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tehnomarket</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel='stylesheet' type='text/css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
</head>
<body>

	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">


					<div class="header_right" style="display: inline-block;">
						<!-- start search-->
						<div class="logo"style:"display:inline-block; width: 150px;">
							<a href="${pageContext.request.contextPath}/index.html"><img
								src="https://i.imgur.com/7tnAVEG.png" alt="" /></a>
						</div>

						<div class="search-box"style:"width:50%;display:inline-block;">
							<div id="sb-search" class="sb-search sb-search-open">
								<form action="${pageContext.request.contextPath}/searchProduct"
									method="post">
									<input class="sb-search-input"
										placeholder="Enter your search term..." type="search"
										name="search" id="search"> <input
										class="sb-search-submit" type="submit" value=""> <span
										class="sb-icon-search"> </span>
								</form>
							</div>
						</div>

						<div align="right"
							style="display: inline-block; color: #ffffff; float: right;">



							<c:if test="${empty user}">
								<div class="login_button">
									<a href="${pageContext.request.contextPath}/login">login</a>
								</div>
							</c:if>
							<c:if test="${!empty user}">
								Hello, ${user.firstName} ${user.lastName} <br>
								<div class="login_button">
									<a href="${pageContext.request.contextPath}/login">logout</a>
								</div>
								<div class="login_button">
									<a href="${pageContext.request.contextPath}/account">account</a>
								</div>
								<c:if test="${user.admin}">
									<div class="login_button">
										<a href="#">admin</a>
									</div>
								</c:if>

							</c:if>
						</div>

						<!----search-scripts---->
						<script src="${pageContext.request.contextPath}/js/classie.js"></script>
						<script src="${pageContext.request.contextPath}/js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
						<!----//search-scripts---->


					</div>
				</div>

			</div>

			<ul class="icon1">
				<li><a class="active-icon c1"
					href="${pageContext.request.contextPath}/cart">

						<div class="cartText" color="white">${fn:length(cart)}</div>
				</a>
					<ul class="sub-icon1 list">


						<c:if test="${empty productArr }">
							<li class="list_desc"><h4>Cart is Empty!</h4></li>

						</c:if>

						<c:forEach var="product" items="${ productArr }">

							<li class="list_img"><img
								src="${pageContext.request.contextPath}/download/${product.image}"
								alt="" style="width: 60px;" /></li>
							<li class="list_desc"><h4>${product.name }</h4> <span
								class="actual">${product.amount } x $${product.price }</span></li>
							<div class="clear"></div>

						</c:forEach>


						<div class="clear"></div>
						<div class="login_buttons">
							<div class="check_button">
								<a>Total: $<fmt:formatNumber type="number"
										maxFractionDigits="2" value="${totalPrice}" /></a>
							</div>
							<div class="login_button">
								<a href="${pageContext.request.contextPath}/cart">Go to Cart</a>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</ul></li>
			</ul>

			<div class="menu" align="center">
				<a class="toggleMenu" href="#"><img src="${pageContext.request.contextPath}/images/nav.png" alt="" /></a>
				<ul class="nav" id="nav">

					<c:forEach var="category" items="${ categories }" varStatus="loop">

						<c:if
							test="${(category.parentCategoryId == 0 && !loop.first) || loop.last}">
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
			<a
				href="${pageContext.request.contextPath}/products/${category.categoryId}">${category.categoryName }</a>
		</c:if>

		</c:forEach>

		<div class="clear"></div>

		</ul>
	</div>


	</div>
	</div>


	<br>
	<br>
</body>
</html>