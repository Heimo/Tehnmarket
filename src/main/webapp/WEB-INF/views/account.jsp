<%@page import="com.tehnomarket.model.User"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

	<% User user = (User) session.getAttribute("user");
	 HashMap<Product,Integer> cart = (HashMap<Product,Integer>) session.getAttribute("cart");
   %>
	   
<body>
	
	<jsp:include page="header.jsp"/>
	
	<hr>
	<a2>Orders</a2>
	<div id="orders">
		<c:forEach var="order" items="${ Orders }"  >
			Date : ${order.dateOfOrder } <td>
			Status : ${order.status } <td>
			<c:forEach var="product" items="${order.theOrders }">
				Product: ${product.name } <td>
				Brand: ${product.brand } <td>
				Price: ${product.price } <td>
				Quantity: ${product.amount }
			</c:forEach>
			Total Paid: ${order.totalCost }<td>
		</c:forEach>
	</div>
	
	<hr>
	
	
	<form action="addProduct" method="get">
		<button type="submit">Add Product</button>
	</form>
			
	<form action="listProducts" method="get">
				<button type="submit">Edit Products</button>
			</form>
			
	<form action="favourites" method="get">
				<button type="submit">Get Favourite Products</button>
			</form>
	
	<form action="editUser" method="get">
				<button type="submit">Edit User Data</button>
			</form>
	
</body>
</html>