<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main" style="">
		<div class="container">
		
		<c:if test="${empty products}">
		<h1>No Favourite Products. </h1>
		</c:if>
	<table>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.name }</td>	
				<td>${product.brand }</td>	
				<td>${product.price }</td>	
				<td>${product.categoryId }</td>	
				<td><form action="removeFavourite/${product.id}" method="get">
						<button type="submit" name="id" value="">Remove</button>
					</form>
				</td>			
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	
</body>
</html>
</body>
</html>