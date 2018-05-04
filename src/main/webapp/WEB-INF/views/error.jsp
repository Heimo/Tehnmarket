<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/">
    	<input type="submit" value="Go Back" />
	</form>
	
	<h1>Something went wrong</h1>
	<h2>error: ${ error }</h2> 
	<img src="https://i.imgur.com/kAqFB3f.jpg" alt="monkey business"> 
</body>
</html>