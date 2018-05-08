<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
            $(".dropdown img.flag").addClass("flagvisibility");

            $(".dropdown dt a").click(function() {
                $(".dropdown dd ul").toggle();
            });
                        
            $(".dropdown dd ul li a").click(function() {
                var text = $(this).html();
                $(".dropdown dt a span").html(text);
                $(".dropdown dd ul").hide();
                $("#result").html("Selected value is: " + getSelectedValue("sample"));
            });
                        
            function getSelectedValue(id) {
                return $("#" + id).find("dt a span.value").html();
            }

            $(document).bind('click', function(e) {
                var $clicked = $(e.target);
                if (! $clicked.parents().hasClass("dropdown"))
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
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form class="login100-form validate-form flex-sb flex-w"
					action="login" method="post">
					<span class="login100-form-title p-b-51"> Login </span>

					<%
						if (request.getAttribute("error") != null) {
					%>
					<span class="login100-form-error-text p-b-51"> <%=request.getAttribute("error")%>
					</span>
					<%
						}
					%>

					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Email is required">
						<input class="input100" type="email" name="email"
							placeholder="Email" value="test@test.test"> <span
							class="focus-input100"></span>
					</div>


					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Password is required">
						<input class="input100" type="password" name="pass"
							placeholder="Password" value="test"> <span
							class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-24">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox"
								name="remember-me"> <label class="label-checkbox100"
								for="ckb1"> Remember me </label>
						</div>

						<div>
							<a href="register" class="txt1"> New? Create account </a>
						</div>

						<div>
							<a href="forgotPass"> Forgot your Password? </a>
						</div>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn">Login</button>
					</div>

				</form>
			</div>
		</div>
	</div>
	-->
	
	<div class="container">
		<div class="col-md-6">
			<div class="login-page">
				
				<h4 class="title">New Customers</h4>
				<p>This is a modern site. A site for the millennium. I'm new wave but I'm old school. I interface with my database and my database is in cyberspace, so I'm interactive, I'm hyperactive, and from time to time I'm radioactive. Behind the eight ball, ahead of the curve, riding the wave, dodging the bullet, and pushing the envelope. I'm on point, on task, on message, and off drugs. I got no need for coke and speed. I'm in the moment, on the edge, over the top, but under the radar. A high concept, low profile, medium range ballistic missionary. I wear power ties; I tell power lies; I take power naps; I take victory laps. I eat fast food in the slow lane. I'm toll free, bite size, ready to wear, and I come in all sizes; a fully equipped, factory authorized, hospital tested, clinically proven, scientifically formulated medical miracle. I've been pre-washed, pre-cooked, pre-heated, pre-screened, pre-approved, pre-packaged, post-dated, freeze-dried, double wrapped, vacuum packed, and I have an unlimited broadband capacity. I take it slow; I go with the flow; I ride with the tide; I got glide in my stride; driving and moving, sailing and spinning, living and grooving, wailing and winning. I don't snooze, so I don't lose. I party hardy and lunchtime is crunchtime. I'm hanging in, there ain't no doubt, and I'm hanging tough, over and out."</p>
				<div class="button1">
					<a href="register"><input type="submit" name="Submit"
						value="Create an Account"></a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		
		<div class="col-md-6">
			<div class="login-title">
				<div id=error_messege style="background-color:red">
					${errorMessage }
				</div>
				<h4 class="title">Registered Customers</h4>
				<div id="loginbox" class="loginbox">
					<form action="login" method="post" name="login" id="login-form">
						<fieldset class="input">
							<p id="login-form-username">
								<label for="modlgn_username">Email</label> <input
									id="modlgn_username" type="text" name="email" class="inputbox"
									size="18" autocomplete="off" required="required">
							</p>
							<p id="login-form-password">
								<label for="modlgn_passwd">Password</label> <input
									id="modlgn_passwd" type="password" name="pass"
									class="inputbox" size="18" autocomplete="off" required="required">
							</p>
							<div class="remember">
								<p id="login-form-remember">
									<label for="modlgn_remember"><a href="forgotPass">Forget
											Your Password ? </a></label>
								</p>
								<input type="submit" name="Submit" class="button" value="Login">
								<div class="clear"></div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>