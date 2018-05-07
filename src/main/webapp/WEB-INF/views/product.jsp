
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tehnomarket.model.Product"%>
<%@page import="com.tehnomarket.model.User"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${product.name}</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
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
<!----details-product-slider--->
<!-- Include the Etalage files -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/etalage.css">
<script src="${pageContext.request.contextPath}/js/jquery.etalage.min.js"></script>
<!-- Include the Etalage files -->
<script>
	jQuery(document)
			.ready(
					function($) {

						$('#etalage')
								.etalage(
										{
											thumb_image_width : 300,
											thumb_image_height : 400,

											show_hint : true,
											click_callback : function(
													image_anchor, instance_id) {
												alert('Callback example:\nYou clicked on an image with the anchor: "'
														+ image_anchor
														+ '"\n(in Etalage instance: "'
														+ instance_id + '")');
											}
										});
						// This is for the dropdown list example:
						$('.dropdownlist').change(
								function() {
									etalage_show($(this)
											.find('option:selected').attr(
													'class'));
								});

					});
</script>
<!----//details-product-slider--->
</head>

<body>

	<jsp:include page="header.jsp" />


	<div style="height: 300px; width: 400px;">
		<div align="left">
			<br> Name: ${product.name}<br> Brand: ${product.brand}<br>

			<div style="max-width: 300px">
				<img
					src="${pageContext.request.contextPath}/download/${product.brand}"
					style="max-width: 100%;" />
			</div>
		</div>

		<div align="right" style="font-size: 40px;">
			Price: ${product.price}<br> <br>
			<div id="cart">
				<a
					href="${pageContext.request.contextPath}/add_to_cart/${product.id}">

					<img src="https://i.imgur.com/0WDHoat.png" alt="cart"
					style="width: 42px; height: 42px; border: 0">
				</a>
			</div>
			<div id="fav">
				<a
					href="${pageContext.request.contextPath}/add_to_fav/${product.id}">
					<img src="https://i.imgur.com/66Z0rB5.png" alt="heart"
					style="width: 42px; height: 42px; border: 0">
				</a>
			</div>
		</div>

		<table>
			<c:forEach var="product" items="${ characeristics }">
				<tr>
					<td>${product.name }</td>
					<td>${product.input }</td>

				</tr>
			</c:forEach>
		</table>

		<br> <br> <br> <br> <br>

		<form action="rateProduct" method="post">
			Rate:<select name="rate" id="rate">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select><br> Comment:<input type="text" id="comment" name="comment" />
			<input type="text" id="user_id" name="user_id"
				value="${sessionScope.user.id}" /> <input type="text"
				id="product_id" name="product_id" value="${product.id}" /> <input
				type="submit" value="Give Feedback">
		</form>

		<c:forEach var="review" items="${reviews}">
		ID:${review.id} User:${review.userId} Date:${review.creationDate.time}<br>
		Rate:${review.rating} Comment:${review.comment}<br>
			<br>
		</c:forEach>

		<div class="main">
			<div class="shop_top">
				<div class="container">
					<div class="row">
						<div class="col-md-9 single_left">
							<div class="single_image">
								<ul id="etalage">

									<li><img class="etalage_thumb_image"
										src="${pageContext.request.contextPath}/download/${product.brand}" />
										<img class="etalage_source_image"
										src="${pageContext.request.contextPath}/download/${product.brand}" /></li>

								</ul>
							</div>
							<!-- end product_slider -->
							<div class="single_right">

								<div class="desc">
									<h4>${product.name}</h4>
									<h4>${product.brand}</h4>
									<table>
										<c:forEach var="product" items="${ characeristics }">
											<tr>
												<td>${product.name }</td>
												<td>${product.input }</td>

											</tr>
										</c:forEach>
									</table>

								</div>
								<ul class="add-to-links">
									<li><img src="images/wish.png" alt=""><a
										href="${pageContext.request.contextPath}/add_to_fav/${product.id}">Add
											to favourites</a></li>
								</ul>

							</div>
							<div class="clear"></div>
						</div>
						<div class="col-md-3">
							<div class="box-info-product">
								<p class="price2">$130.25</p>
								<ul class="prosuct-qty">

								</ul>
								<a
									href="${pageContext.request.contextPath}/add_to_cart/${product.id}">

									<img src="https://i.imgur.com/0WDHoat.png" alt="cart"
									style="width: 42px; height: 42px; border: 0">
								</a>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
</body>
</html>