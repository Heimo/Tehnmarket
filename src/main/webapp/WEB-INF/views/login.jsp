<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
		<meta charset="UTF-8">
	</head>
	<body>
		
		<div class="limiter">
			<div class="container-login100">
				<div class="wrap-login100 p-t-50 p-b-90">
					<form class="login100-form validate-form flex-sb flex-w" action="login" method="post">
						<span class="login100-form-title p-b-51">
							Login
						</span>
						
						<% if(request.getAttribute("error")!=null){ %>
						<span class="login100-form-error-text p-b-51">
							<%= request.getAttribute("error") %>
						</span>
						<%} %>
						
						<div class="wrap-input100 validate-input m-b-16" data-validate = "Email is required">
							<input class="input100" type="email" name="email" placeholder="Email" value="test@test.test">
							<span class="focus-input100"></span>
						</div>
						
						
						<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
							<input class="input100" type="password" name="pass" placeholder="Password" value="test">
							<span class="focus-input100"></span>
						</div>
						
						<div class="flex-sb-m w-full p-t-3 p-b-24">
							<div class="contact100-form-checkbox">
								<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
								<label class="label-checkbox100" for="ckb1">
									Remember me
								</label>
							</div>
	
							<div>
								<a href="register" class="txt1">
									New? Create account
								</a>
							</div>
						</div>
	
						<div class="container-login100-form-btn m-t-17">
							<button class="login100-form-btn">
								Login
							</button>
						</div>
	
					</form>
				</div>
			</div>
		</div>
</body>
</html>