<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recover Password</title>
</head>
<body>
	<h3>You will get your new password in the email if you are an existing user</h3>
	<br>
	<f:form commandName="new_pass">
	
			E-mail<f:input path="email"/><br>
			
			<input type="submit" value="Get New Password" />
	</f:form>

</body>
</html>