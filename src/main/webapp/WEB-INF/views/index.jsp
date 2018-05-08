<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Map"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Tehnomarket</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="js/jquery.min.js"></script>
<!--<script src="js/jquery.easydropdown.js"></script>-->
<!--start slider -->
<link rel="stylesheet" href="css/fwslider.css" media="all">
<script src="js/jquery-ui.min.js"></script>
<script src="js/fwslider.js"></script>
<!--end slider -->
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

	<div class="banner">
		<!-- start slider -->
		<div id="fwslider">
			<div class="slider_container">
				<div class="slide">
					<!-- Slide image -->
					<img src="https://i.imgur.com/Ad1Vo2t.jpg" class="img-responsive"
						alt="" />
					<!-- /Slide image -->
					<!-- Texts container -->
					<div class="slide_content">
						<div class="slide_content_wrap">
							<!-- Text title -->
							<h1 class="title">
								BUY <br>Everything
							</h1>
							<!-- /Text title -->
							<div class="button">
								<a href="#">See Details</a>
							</div>
						</div>
					</div>
					<!-- /Texts container -->
				</div>
				<!-- /Duplicate to create more slides -->
				<div class="slide">
					<img src="https://i.imgur.com/xZDHfYn.jpg" class="img-responsive"
						alt="" />
					<div class="slide_content">
						<div class="slide_content_wrap">
							<h1 class="title">
								YOU NEED<br>Everything
							</h1>
							<div class="button">
								<a href="#">See Details</a>
							</div>
						</div>
					</div>
				</div>
				<!--/slide -->
			</div>
			<div class="timers"></div>
			<div class="slidePrev">
				<span></span>
			</div>
			<div class="slideNext">
				<span></span>
			</div>
		</div>
		<!--/slider -->
	</div>


</body>
</html>