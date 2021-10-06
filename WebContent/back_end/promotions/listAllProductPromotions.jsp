<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotions.model.*"%>






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
/* 	width: 1500px; */
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
 
<table id="table-1">
<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h2>
<h2><a href="<%=request.getContextPath()%>/back_end/promotions/listAllPromotions.jsp">回全部行銷活動</a></h1>


行銷活動
檔期:${promotionsVO.promot_date_start}
	${promotionsVO.promot_date_end}
活動名稱:${promotionsVO.promot_name}
折扣數:${promotionsVO.promot_discount}
底價: 100

<table>
	<tr>	
		<th>行銷產品編號</th>
		<th>產品編號</th>
		<th>產品圖</th>
		<th>產品名稱</th>
		<th>產品原價</th>
		<th>活動價格</th>
	</tr>

	<c:forEach var="promProductVO" items="${list}">
		<tr>
			<td>${promProductVO.product_pro_detail_no}</td>
			<td>${promProductVO.product_no}</td>	
			<td>			
				<img class="fit-image" src="<%=request.getContextPath() %>/prodPhoto?prodNo=${promProductVO.product_no}" width="150px"/>
			</td>
			<td>
				<c:forEach var="promproduct" items="${productList}">
					<c:if test="${promproduct.product_no ==  promProductVO.product_no}">
						${promproduct.product_name}
					</c:if>
				</c:forEach>
			</td>
			<td>${promProductVO.product_pro_price}</td>
			<td>${promProductVO.productSpecialPrice}</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>