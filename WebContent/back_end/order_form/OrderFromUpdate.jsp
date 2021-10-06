<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_form.model.*"%>

<%
	OrderFormVO order_formVO = (OrderFormVO) request.getAttribute("order_formVO");
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單狀態修改 - OrderFromUpdate.jsp</title>
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
<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h2>
<h2><a href="<%=request.getContextPath()%>/back_end/promotions/promotions/listAllPromotions.jsp">回全部行銷活動</a></h1>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>訂單編號</th>
		<th>一般會員編號</th>
		<th>訂單狀態</th>
		<th>確認修改</th>
	</tr>


	<c:forEach var="order_formVO" items="${list}" >	
		<tr>
			<td>${order_formVO.order_no}</td>
			<td>${order_formVO.gen_meb_no}</td>
			<td>${order_formVO.delivery_address}</td>
<%-- 			<td>${order_formVO.order_status}</td> --%>
			<td>
			<select>
    			<option>-1取消訂單</option>
    			<option>0待出貨</option>
    			<option>1已出貨</option>
    			<option>2完成訂單</option>
			</select>
			</td>


		
		
		
<!-- 			<input type="hidden" name="action" value="update"> 			 -->
<%-- 			<input type="hidden" name="order_no"  value="${order_formVO.order_no}"> --%>
<!-- 			<input type="submit" value="確定送出狀態"> -->
		
		
		</tr>
	</c:forEach>
</table>

</body>
</html>