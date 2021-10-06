<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.order_detail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<head>
<meta charset="UTF-8">
<title>thank.jsp</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<style>
.myTop {
	margin-top: 10%;
}

.myTop2 {
	margin-top: 3%;
}

.myTop3 {
	margin-top: 15%;
}

.mybottom {
	margin-bottom: 3%;
}

.inmid {
	margin: 0 40% 0 43%;
}

.listPhoto {
	max-width: 100%;
	max-height: 100%;
}

.toServlet {
	display: inline-block;
}

.product {
	height: 100%
}

.myTextSize {
	font-size: 1.5rem;
}

.myTextSize2 {
	font-size: 1.2rem;
}


div.center_{
	right: 10px;
	top: 50%;
}
</style>
</head>



<body bgcolor='white'>
	<a href="<%=request.getContextPath() %>/front_end/shopping_cart/shoppingCart.jsp">
		<img src="<%=request.getContextPath()%>/front_end/shopping_cart/images/cart.png" width="50" height="50" border="0" style="position: fixed;width:50px;height:50px; top:300px; right:0; z-index:9999999" >
	</a>
	<c:if test="${not empty meb }">
		<a href="<%=request.getContextPath() %>/order_form/order_form.do?action=getMemOrderList">
			<img src="<%=request.getContextPath()%>/front_end/shopping_cart/images/orderlist.png" width="60" height="60" border="0" style="position: fixed;width:60px;height:60px; top:400px; right:0; z-index:9999999" >
		</a>
	</c:if>
	
	<%@ include file="../front_page/head.jsp"%>
	
	<div class="section breadcrumb-area bg-name-bright">
       <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <div class="breadcrumb-wrapper">
                        <h2 class="breadcrumb-title">寵一而忠</h2>
                        <ul>
                              <li><font size="5"><a href="<%=request.getContextPath()%>/front_end/shopping_cart/ProductList.jsp">Home-回商城首頁</a></font></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>


	<h3>
		<font style="color: red">感謝${meb.meb_name }的購買!歡迎再次選購</font>
	</h3>
	<br>
	<c:if test="${not empty list }">
	
<%-- 	訂單編號:${orderFormVO.order_no } --%>
	訂單狀態:${orderFormVO.orderStatusStr }<br>
	送貨地址:${orderFormVO.delivery_address }<br>
	收件人: ${orderFormVO.order_name }<br/>
	連絡電話: ${orderFormVO.order_phone }<br/>
	<ul>
	<li><font size="3" style="color: orange"><a href="<%=request.getContextPath()%>/order_form/order_form.do?action=getMemOrderList">查詢其他紀錄...</a></font></li>
	</ul>

		<table border="1" >
		<tr bgcolor="#999999">
			<th><div align="center">商品圖</div></th>
<!-- 			<th><div align="center">商品編號</div></th> -->
			<th><div align="center">購買數量</div></th>
			<th><div align="center">販售單價</div></th>
			<th><div align="center">商品名稱</div></th>
			<th><div align="center">促銷商品編號</div></th>
			<th><div align="center">活動名稱</div></th>
		
		</tr>


		<c:forEach var="order_detailVO" items="${list}">
		<tr>
		<td width="200"><div align="center"><img class="fit-image" src="<%=request.getContextPath() %>/prodPhoto?prodNo=${order_detailVO.product_no}"/></div></td>
<%-- 		<td><div align="center">${order_detailVO.product_no}</div></td> --%>
		<td><div align="center">${order_detailVO.order_product_number}</div></td>
		<td><div align="center">$${amount}</div></td>
		<td><div align="center">${order_detailVO.product_name}</div></td>
		<td><div align="center">${order_detailVO.product_pro_detail_no}</div></td>
		<td><div align="center">${order_detailVO.promot_name}</div></td>
		</tr>
		</c:forEach>
</table>
</c:if>

<c:if test="${empty list }">
	請勿重複點擊 F5刷新資料
</c:if>
</div>
	<%@ include file="../front_page/footer2.jsp"%>
	<script>
		const server = "<%=request.getContextPath()%>";
	</script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/shopping_cart/js/ajax.js"></script>
</body>
</html>