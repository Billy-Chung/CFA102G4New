<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品假資料</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="../front_page/head.jsp"%>


	
	<%
			productService productService = new productService(); 
				List<ProductVO> productList = productService.getAll();
				pageContext.setAttribute("prodList", productList);
		%>
	
	
	
	
	<table border="1" width="740">
	<tr bgcolor="#ffc107">
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>販售單價</th>
		<th>購物車</th>
	</tr>
	<table>
		<c:forEach var="product" items="${prodList}">
				<tr>
					<form name="shoppingForm" method="POST" action="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" >
						<td><div align="center">${product.product_no}</div></td>
						<td><div align="center">${product.product_name}</div></td>
						<td><div align="center">${product.product_price}</div></td>
						<td>
							<input type="hidden" name="product_no" value="${product.product_no}">
							<input type="hidden" name="action" value="addToCart">
							<button>加入</button>
						</td>
					</form>
				</tr>
		</c:forEach>
	</table>
<p> 



 <%@ include file="../front_page/footer2.jsp"%>

<script src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>

</body>
</html>