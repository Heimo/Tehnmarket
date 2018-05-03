
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="com.tehnomarket.model.User"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	   
<body>

	<jsp:include page="header.jsp"/>
	

	<div style="height: 300px;width: 400px;">
		<div align="left">
			<br>
				Name: ${product.name}<br>
				Brand: ${product.brand}<br>
				
				<div style="max-width: 300px" >
					<img src="${pageContext.request.contextPath}/download/${product.image}" style="max-width:100%;" />
				</div>
		</div>
		
		<div align="right" style="font-size: 40px;">
			Price: ${product.price}<br><br>
			<div id="cart">	
					<a href="${pageContext.request.contextPath}/add_to_cart/${product.id}">

  					<img src="https://i.imgur.com/0WDHoat.png" alt="cart" style="width:42px;height:42px;border:0">
					</a>
			</div>
			<div id="fav">
					<a href="${pageContext.request.contextPath}/add_to_fav/${product.id}">
  					<img src="https://i.imgur.com/66Z0rB5.png" alt="heart" style="width:42px;height:42px;border:0">
					</a>
				</div>
		</div>
	
	<table>
	<c:forEach var="product" items="${ characeristics }"  >
		<tr>
			<td>${product.name }</td>
			<td>${product.input }</td>
			
		</tr>
	</c:forEach>
	</table>
	
	
</body>
</html>