<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>    

<html>
<head>
<title>寵一而忠</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />
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
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h2>
<h2><a href="<%=request.getContextPath()%>/back_end/order_form/listAllOrder_form.jsp">回首頁</a></h1>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




	<table border="1" >
		<tr bgcolor="#999999">
				<th><div align="center">訂單明細編號</div></th>
				<th><div align="center">訂單編號</div></th>
				<th><div align="center">商品編號</div></th>
				<th><div align="center">購買數量</div></th>
				<th><div align="center">販售單價</div></th>
				<th><div align="center">商品名稱</div></th>
				<th><div align="center">促銷商品編號</div></th>
				<th><div align="center">活動名稱</div></th>
		</tr>

 
	<c:forEach var="order_detailVO" items="${allDetail}" >
		<tr>
		<td><div align="center">${order_detailVO.order_detail_no}</div></td>
		<td><div align="center">${order_detailVO.order_no}</div></td>
		<td><div align="center">${order_detailVO.product_no}</div></td>
		<td><div align="center">${order_detailVO.order_product_number}</div></td>
		<td><div align="center">${order_detailVO.product_price}</div></td>
		<td><div align="center">${order_detailVO.product_name}</div></td>
		<td><div align="center">${order_detailVO.product_pro_detail_no}</div></td>
		<td><div align="center">${order_detailVO.promot_name}</div></td>	
		</tr>
	</c:forEach>
</table>

</body>
</html>