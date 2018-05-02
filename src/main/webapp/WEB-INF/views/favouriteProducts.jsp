<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.name }</td>	
				<td>${product.brand }</td>	
				<td>${product.price }</td>	
				<td>${product.categoryId }</td>	
				<td><form action="removeFavourite" method="get">
						<button type="submit" name="id" value="${product.id }">Remove</button>
					</form>
				</td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>
</body>
</html>