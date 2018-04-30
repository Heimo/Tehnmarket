
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="com.tehnomarket.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

	<% User user = (User) session.getAttribute("user");
	Product product = (Product) request.getAttribute("product");
	 ArrayList<Integer> cart =(ArrayList<Integer>) session.getAttribute("cart");
   %>
	   
<body>

	<jsp:include page="header.jsp"/>
	
	<a href="cart">
		Cart:
		<% if(cart == null){ %>
		 0
		<% }else{%>
			<%=cart.size()%>
			<% }%>
	</a>
	<br>
	<% if(user != null) {%>
	Hello <%= user.getFirstName()+" "+user.getLastName() %>
	<a href="login">Click here to logout</a>
	<%} else {%>
	<a href="login">Click here to login</a>
	<%} %>
	
	<div style="height: 300px;width: 400px;">
		<div align="left">
			<br>
				Name:<%=product.getName() %><br>
				Brand:<%=product.getBrand() %><br>
				
		</div>
		
		<div align="right" style="font-size: 40px;">
			Price:<%=product.getPrice() %><br>
			<form action="add_to_cart" method="get">
				<button name ="id" type="submit" value="<%= product.getId() %>">Add to Basket</button>
			</form>
		</div>
	
</body>
</html>