<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Products</title>
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

	<jsp:include page="header.jsp" />

	

	<div class="main" style="text-align:center">
	<div id="arrow_down" align="center" style="display:inline-block;">
		<a href="${pageContext.request.contextPath}/sort/min"> <img
			src="https://i.imgur.com/WJEhGM1.png" alt="cart"
			style="width: 42px; height: 42px; border: 0">
		</a>
	</div>

	<div id="arrow_up" align="center"style="display:inline-block;">
		<a href="${pageContext.request.contextPath}/sort/max"> <img
			src="https://i.imgur.com/oG3CR7i.png" alt="cart"
			style="width: 42px; height: 42px; border: 0">
		</a>
	</div>
			<div class="shop_top">
			
			<div class="container">
			<div class="row shop_box-top">
			
			

	<c:if test="${empty products }">
			No products found
		</c:if>
		<br>
	<c:forEach var="product" items="${ products }" varStatus="loop">
		
				
					
						<div class="col-md-3 shop_box">
							<a
								href="${pageContext.request.contextPath}/product/${product.id}">
								<img
								src="${pageContext.request.contextPath}/download/${product.image}"
								class="img-responsive" alt="" align="center"/> <span class="new-box">
									<span class="new-label">New</span>
							</span>

								<div class="shop_desc">
									<h3>
										<a href="#">${product.name }</a>
									</h3>
									<p>${product.brand }</p>
									<span class="actual">$${product.price }</span><br>
									<ul class="buttons">
										<li class="cart"><a
											href="${pageContext.request.contextPath}/add_to_cart/${product.id}">Add
												To Cart</a></li>
										<li class="shop_btn"><a
											href="${pageContext.request.contextPath}/product/${product.id}">Read
												More</a></li>
										<div class="clear"></div>
									</ul>
								</div>
							</a>
						</div>
						<c:if test="${(loop.index+1) %4 ==0 }">
						</div>
						<div class="row shop_box-top">
						</c:if>
							
	</c:forEach>
	</div>
	</div>
	  </div>
	</div>
</body>

</html>