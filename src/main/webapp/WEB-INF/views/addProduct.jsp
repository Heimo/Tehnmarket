<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<f:form commandName="new_product" action="addProduct" method="POST">
			Name<f:input path="name"/><br>
			Brand<f:input path="brand"/><br>
			Price<f:input path="price"/><br>
			Category
			<f:select path="categoryId">
				<c:forEach var="cat" items="${categories}">
					<f:option  name="${cat.categoryName }" value="${cat.categoryId }">${cat.categoryName }</f:option>
				</c:forEach>
				
			</f:select><br>
			<input type="submit" value="Add Product" />
		</f:form>

</body>
</html>