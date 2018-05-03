<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Product List</title>
<style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Brand</td>
			<td>Price</td>
			<td>Category</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.id}</td>
				<td>${product.name }</td>	
				<td>${product.brand }</td>	
				<td>${product.price }</td>	
				<td>${product.categoryId }</td>	
				<td><form action="editProduct" method="get">
						<button type="submit" name="id" value="${product.id }">Edit Product</button>
					</form>
				</td>
				<td><form action="deleteProduct" method="get">
						<button type="submit" name="id" value="${product.id }">Delete Product</button>
					</form>
				</td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>