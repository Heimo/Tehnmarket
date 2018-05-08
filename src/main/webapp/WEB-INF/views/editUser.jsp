<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Edit me</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel='stylesheet' type='text/css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='${pageContext.request.contextPath}/http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".dropdown img.flag").addClass("flagvisibility");

				$(".dropdown dt a").click(function() {
					$(".dropdown dd ul").toggle();
				});

				$(".dropdown dd ul li a").click(
						function() {
							var text = $(this).html();
							$(".dropdown dt a span").html(text);
							$(".dropdown dd ul").hide();
							$("#result").html(
									"Selected value is: "
											+ getSelectedValue("sample"));
						});

				function getSelectedValue(id) {
					return $("#" + id).find("dt a span.value").html();
				}

				$(document).bind('click', function(e) {
					var $clicked = $(e.target);
					if (!$clicked.parents().hasClass("dropdown"))
						$(".dropdown dd ul").hide();
				});

				$("#flagSwitcher").click(function() {
					$(".dropdown img.flag").toggleClass("flagvisibility");
				});
			});
</script>
</head>
<body>

	<!-- 
	<f:form commandName="edit_user">
			First name<f:input path="firstName"/><br>
			Last name<f:input path="lastName"/><br>
			password<f:input type="password" path="password" required="required"/><br>
			check password<f:input type="password" path="passwordCheck" required="required"/><br>
			
			Birthdate<f:input type="date" path="dateOfBirth"/></br>
			
			<f:radiobutton path = "gender" value = "M" label = "Male" />
            <f:radiobutton path = "gender" value = "F" label = "Female" />
            <f:radiobutton path = "gender" value = "G" label = "God" />
			
			
			<input type="submit" value="Edit" />
		</f:form>
	 -->	
	<div class="main">
		<div class="shop_top">
			<div class="container">
				<f:form commandName="edit_user">
					<div class="register-top-grid">
						<h3>PERSONAL INFORMATION</h3>
						<div>
							<span>First Name<label>*</label></span> <f:input maxlength="45" path="firstName" />
						</div>
						<div>
							<span>Last Name<label>*</label></span> <f:input maxlength="45" path="lastName" />
						</div>
						<div>
							<span>Email Address<label>*</label></span> <f:input maxlength="100" path="email" />
						</div>
						<div>
							<span>Birth date<label>*</label></span> <f:input type="date" path="dateOfBirth" />
						</div>
						<div>
							<f:radiobutton path="gender" value="M" label="Male" />
							<f:radiobutton path="gender" value="F" label="Female" />
							<f:radiobutton path="gender" value="G" label="God" />
						</div>
						<div class="clear"></div>
						
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
					<div class="register-bottom-grid">
						<h3>LOGIN INFORMATION</h3>
						<div>
							<span>Password<label>*</label></span> <f:input maxlength="100" type="password" path="password" required="required"/>
						</div>
						<div>
							<span>Confirm Password<label>*</label></span> <f:input maxlength="100" type="password"
						path="passwordCheck" required="required"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
					<input type="submit" value="Edit">
				</f:form>
			</div>
		</div>
	</div>

</body>
</html>