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
	<hr>
	
	<div id="orders">
		<c:forEach var="order" items="${ Orders }"  >
			Date : ${order.dateOfOrder } <br />
			Status : ${order.statusOut } <br />
			<c:forEach var="product" items="${order.theOrders }">
			
				Product: ${product.key.name } <br />
				Brand: ${product.key.brand } <br />
				Price: ${product.key.price } <br />
				Quantity: ${product.value } <br />
			</c:forEach>
			<br />
			Total Cost: ${order.totalCost }<td><br />
			<form action="cancelOrder/${order.id}" method="get">
					<button type="submit">Cancel Order</button>
			</form>

			<br />
			<hr>
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