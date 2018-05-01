<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Tehnomarket</title>
		
</head>
	<body>
		
		
		<jsp:include page="header.jsp"/>
		
		<%
	       ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
	    %>
		
		
		
		<%if(products!= null){
		for(Product p: products){ %>
			<a href="product/<%=p.getId()%>">
				Id:<%=p.getId() %><br>
				Name:<%=p.getName() %><br>
				Brand:<%=p.getBrand() %><br>
				Price:<%=p.getPrice() %><br>
			</a>
			<br>
				
		<%}
		}%>

	</body>
</html>