<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/account">
    	<input type="submit" value="Go Back to Account Page" />
	</form>
	
	<f:form commandName="edit_product" action="editProduct" method="POST">
			ID: ${edit_product.id}<br>
			Name<f:input path="name" id="pName"/><br>
			Brand<f:input path = "brand" id="pBrand"/><br>
			Price<f:input path="price" id="pPrice"/><br>
			Category
			<f:select path="categoryId" id="category" onchange="getCharacteristics()">
				<c:forEach var="cat" items="${categories}">
					<f:option  name="${cat.categoryName }" value="${cat.categoryId }">${cat.categoryName }</f:option>
				</c:forEach>
				
			</f:select> <br>
			
			<f:hidden path="id" /><br>
			<button type="button" onclick="saveProduct()" >Edit Product</button>
	</f:form>
	
	<div style="max-width: 300px" >
		<img src= "download/${edit_product.image }">
	</div>
	<br>
	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="file" name="file"><br>
		<input type="text" id="id" name="id" value="${edit_product.id }" hidden>
		<input type="submit" value="upload file">
	</form>
	
	<p id="productStatus"></p><br>
	
	<div id="old_characts">
	<h3>Characteristics</h3>
		<table>
			<c:forEach var="c" items="${ characts }"  >
				<tr>
					<td>${c.name }</td>
					<td>${c.input }</td>
			
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<h3>New Characteristics</h3><br>
	<div id="new_characts">
		
	</div>
	
	
	<script>
		function getCharacteristics(){
			var category = document.getElementById("category");
			var id = category.options[category.selectedIndex].value;
			console.log(id);
			var test = new Object;
			test.help = "yes";
			console.log(test.help);
			var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	var arr = JSON.parse(this.responseText);
			    	document.getElementById('new_characts').innerHTML = '<form id="charactsForm"></form>';
			    	console.log(this.responseText);
			    	for(var c in arr){
			    		document.getElementById('charactsForm').innerHTML += arr[c].name+'<input type="text" id='+arr[c].characteristicsId + '><br>';
			    	}
			    	document.getElementById('new_characts').innerHTML += '<button type="button" onclick="saveCharacteristics()">Save Characteristics</button>';
			    }
			  };
			  xhttp.open("GET", "${pageContext.request.contextPath}/getCharacteristics/"+id, true);
			  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			  xhttp.send();
		}	
		
		function saveCharacteristics(){
			var inputs = document.getElementById("charactsForm").elements;
			var arr = [];
			for (i = 0; i < inputs.length; i++) {
				var charact= new Object;
				charact.input = inputs[i].value;
				charact.characteristicsId = inputs[i].id;
				charact.productsId = ${edit_product.id};
				arr.push(charact);
			}
			console.log(arr);
			
			var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	console.log(this.responseText);
			    	document.getElementById('new_characts').innerHTML += this.responseText;
			    }
			  };
			  xhttp.open("POST", "${pageContext.request.contextPath}/saveCharacteristics", true);
			  xhttp.setRequestHeader("Content-type", "application/json; charset=utf-8");
			  xhttp.send(JSON.stringify(arr));
		}
		
		function saveProduct(){
			var category = document.getElementById("category");
			var id = category.options[category.selectedIndex].value;
			var product = new Object;
			product.id = ${edit_product.id};
			product.name = document.getElementById("pName").value;
			product.brand = document.getElementById("pBrand").value;
			product.price = document.getElementById("pPrice").value;
			product.info = ${(empty edit_product.info) ? "null" : edit_product.info};
			product.discount = ${edit_product.discount};
			product.discountEnd = ${(empty edit_product.discountEnd) ? "null" : edit_product.discountEnd};
			product.image = ${(empty edit_product.image) ? "null" : edit_product.image};
			product.categoryId = id;
			
			var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	console.log(this.responseText);
			    	document.getElementById('productStatus').innerHTML = this.responseText;
			    }
			  };
			  xhttp.open("POST", "${pageContext.request.contextPath}/editProduct", true);
			  xhttp.setRequestHeader("Content-type", "application/json; charset=utf-8");
			  xhttp.send(JSON.stringify(product));
		}
	</script>
</body>
</html>