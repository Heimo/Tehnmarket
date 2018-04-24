<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
	<body>
		<f:form commandName="new_user">
			<f:input path="email"/><br>
			<f:input path="firstName"/><br>
			<f:input path="lastName"/><br>
			<f:input path="password"/><br>
			<f:input path="passwordCheck"/><br>
			
			<f:input type="date" path="dateOfBirth"/></br>
			
			<f:radiobutton path = "gender" value = "M" label = "Male" />
            <f:radiobutton path = "gender" value = "F" label = "Female" />
            <f:radiobutton path = "gender" value = "G" label = "God" />
			
		</f:form>
	
	
	</body>
</html>