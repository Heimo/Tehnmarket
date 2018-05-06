<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Products</title>
	</head>
	
	<body>
		
		<jsp:include page="header.jsp"/>
		
		<div id="arrow_down">
					<a href="${pageContext.request.contextPath}/sort/min">
						<img src="https://i.imgur.com/WJEhGM1.png" alt="cart" style="width:42px;height:42px;border:0">
					</a>
		</div>
	
		<div id="arrow_up">
					<a href="${pageContext.request.contextPath}/sort/max">
						<img src="https://i.imgur.com/oG3CR7i.png" alt="cart" style="width:42px;height:42px;border:0">
					</a>
		</div>
		
		<c:if test="${empty products }">
			No products found
		</c:if>
		
		<table>
		<c:forEach var="product" items="${ products }"  >
			<tr>
			<div id="product">
				<td>
				<div id="product_name">
					${product.name }
				</div>
				</td>
				<td>
				<div id="product_price">
					${product.price }
				</div>
				</td>
				<td>
				<div style="max-width: 300px" >
					<img src="${pageContext.request.contextPath}/download/${product.brand}" style="max-width:100%;" />
				</div>
				</td>
				<td>
				<div id="product">
					<a href="${pageContext.request.contextPath}/product/${product.id}">
						<img src="https://i.imgur.com/01HYjJA.png" alt="cart" style="width:42px;height:42px;border:0">
					</a>
				</div>
				</td>
				<td>
				<div id="cart">	
					<a href="${pageContext.request.contextPath}/add_to_cart/${product.id}">

  					<img src="https://i.imgur.com/0WDHoat.png" alt="cart" style="width:42px;height:42px;border:0">
					</a>
				</div>
				</td>
				<td>
				<div id="fav">
					<a href="${pageContext.request.contextPath}/add_to_fav/${product.id}">
  					<img src="https://i.imgur.com/66Z0rB5.png" alt="heart" style="width:42px;height:42px;border:0">
					</a>
				</div>
				</td>
			</div>
			</tr>
		</c:forEach>
		</table>
		
		
		
		
	
	
	</body>
	
</html>