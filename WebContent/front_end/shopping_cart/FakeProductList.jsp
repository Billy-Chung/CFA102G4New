<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	
	<% productService productService = new productService(); 
		List<productVO> productList = productService.getAll();
		pageContext.setAttribute("prodList", productList);
	%>
	
	<table>
		<c:forEach var="product" items="${prodList}">
			<tr>
				<td>${product.product_no}</td>
				<td>${product.product_name}</td>
				<td>${product.product_price}</td>
				<td>

					<form method="POST" action="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" >
						<input type="hidden" name="product_no" value="${product.product_no}">
						<input type="hidden" name="action" value="addToCart">
						<button>加入購物車</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	
	</table>

<p> 
 
	
</body>
</html>