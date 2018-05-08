<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>| Recover Password</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel='stylesheet' type='text/css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>

</head>
<body>
	<div class="main" style="">
		<div class="container">
			<div class="col-md-6">
				<div class="login-title">
					<h4 class="title">Recover password</h4>
					<div id="loginbox" class="loginbox">
						<f:form commandName="new_pass">
							<label for="modlgn_username">Email</label>
							<input id="modlgn_username" type="text" name="email"
								class="inputbox" size="18" autocomplete="off">
							<input type="submit" value="Get New Password" />
						</f:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>