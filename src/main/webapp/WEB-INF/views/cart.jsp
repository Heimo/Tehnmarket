<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="com.tehnomarket.model.User"%>
<%@page import="com.tehnomarket.model.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Tehnomarket Cart</title>
</head>
	
	   
<body>
	
	<div class="main" style="text-align:center">
	<div class="shop_top">
	
	
	<table>
	<tr>
		<th align="center">Image</th>
		<th align="center">Name</th>
		<th align="center">Price</th>
		<th align="center"></th>
		<th align="center">Quantity</th>
		<th align="center"></th>
	</tr>
		
		    
	
	<c:forEach var="product" items="${ productArr }"  >
			<tr>
			
			<td>
			<div id="image" >
			<img src="${pageContext.request.contextPath}/download/${product.image}" style="width:128px;height:128px;border:0">
			</div>
			</td>
			
			<td>
			<div id="product_name">
				${product.name }
			</div>
			</td>
			
			
			
			<td>
			<div id="price">
			Price is:${product.price }
			</div>
			</td>
			
			<td>
			<div id="plus_sign">
					<a href="quantity/${product.id}/increase">
						<img src="https://i.imgur.com/xGPJKup.png" alt="cart" style="width:32px;height:32px;border:0">
					</a>
			</div>
			</td>
			
			<td>
			<div id="item_quantity" style="text-align:center;">
				${product.amount}
			</div>
			</td>
			
			
			<td>
			<div id="minus_sign">
					<a href="quantity/${product.id}/decrease">
						<img src="https://i.imgur.com/4BNjGLm.png" alt="cart" style="width:32px;height:32px;border:0">
					</a>
			</div>
			</td>	
			
				<td>
				</td>
			<tr>			
	</c:forEach>
	
	</table>
	
	<hr>
	
	<div id="total price">
		Total price comes to: ${totalPrice}
	</div>
	
	<hr>
	
	<h2>Input your date for the order</h2>
	
	<f:form commandName="new_order">
			City: <f:input path="City"/><br>
			Street: <f:input path="Street"/><br>
			Entrance: <f:input path="Entrance"/><br>
			Phone number: <f:input type="number" path="phoneNumber"/><br>
			
				
			<input type="submit" value="Make Order" />
	</f:form>
	

	</div>
	</div>
	
</body>
</html>