<%@page import="com.tehnomarket.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

	<% User user = (User) session.getAttribute("user");
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
	
	<form action="addProduct" method="get">
		<button type="submit">Add Product</button>
	</form>
			
	<form action="changeProduct" method="get">
				<button type="submit">Change Product</button>
			</form>
	
</body>
</html>