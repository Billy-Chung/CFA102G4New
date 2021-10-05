<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_form.model.*"%>
<html>
<head>
<title>所有訂單- listAllOrder_form.jsp</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

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

		<table border="1" >
		<tr bgcolor="#999999">
			<th><div align="center">訂單編號</div></th>
				<th><div align="center">結帳金額</div></th>
				<th><div align="center">收件者</div></th>
				<th><div align="center">連絡電話</div></th>
				<th><div align="center">配送地址</div></th>
				<th><div align="center">訂單產生時間</div></th>
				<th><div align="center">訂單狀態</div></th>
				<th><div align="center">訂單明細</div></th>
				<th><div align="center">取消訂單</div></th>
		</tr>

		<c:forEach var="order_formVO" items="${list}" >
		<tr>
			<td><div align="center">${order_formVO.order_no}</div></td>
			<td><div align="center">$${order_formVO.order_amount}</div></td>
			<td><div align="center">${order_formVO.order_name}</div></td>
			<td><div align="center">${order_formVO.getOrder_phone()}</div></td>
			<td><div align="center">${order_formVO.delivery_address}</div></td>
			<td><div align="center"><fmt:formatDate value="${order_formVO.order_time}" pattern="yyyy-MM-dd" /></div></td>
			<td><div align="center">${order_formVO.getOrderStatusStr()}</div></td>

<!-- 			-1取消訂單/0待出貨/1已出貨/2完成訂單 -->
<%-- 			<c:choose>			 --%>
<%-- 			　　<c:when test="${order_formVO.order_status == -1}">取消訂單</c:when> --%>
<%-- 			　　<c:when test="${order_formVO.order_status == 0}">待出貨</c:when> --%>
<%-- 			　　<c:when test="${order_formVO.order_status == 1}">已出貨</c:when> --%>
<%-- 			   <c:when test="${order_formVO.order_status == 2}">完成訂單</c:when> --%>
<%-- 			　　<c:otherwise>完成訂單</c:otherwise> --%>
<%-- 			</c:choose> --%>
								
<!-- 			<td> -->
<%-- 				${order_formVO.order_status eq -1?"取消訂單":"待出貨"} --%>
<%-- 				${order_formVO.order_status eq 1?"已出貨":"完成訂單"} --%>
<!-- 			</td> -->
			<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/order_detail.do" style="margin-bottom: 0px;">
			    	<input type="submit" value="查詢">
			     	<input type="hidden" name="order_no"  value="${order_formVO.order_no}">
			     	<input type="hidden" name="action"	value="frontEndOrderDetail">
			     </FORM>
			</td>
			<td>
				<c:choose>
				    <c:when test="${order_formVO.orderStatusStr == '待出貨' }">
				        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_form/order_form.do" style="margin-bottom: 0px;">
					    	<input type="submit" value="取消訂單">
					     	<input type="hidden" name="order_no"  value="${order_formVO.order_no}">
					     	<input type="hidden" name="action"	value="cancelOrderForm">
					    </FORM>
				    </c:when>
				    <c:when test="${order_formVO.orderStatusStr == '取消訂單' }">
				    	<button disabled="disabled">取消訂單</button>
				    </c:when>
					<c:otherwise>
					            商品已出貨不可取消
					</c:otherwise>
				</c:choose>
				
			</td>
		</tr>
	</c:forEach>
</table>

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