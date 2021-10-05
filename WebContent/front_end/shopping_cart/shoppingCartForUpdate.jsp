<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="com.shopping_cart.*" %>
<%@page import="com.order_detail.model.*" %>
<!DOCTYPE html>
<html>
<head>
<title>shoppingCartUpdate</title>

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
	<div style="height:80%; padding-top: 20%">
	
	
    <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" method="POST">
 
		<table border="1" width="740">
			<tr bgcolor="#999999">
				<th width="100">商品編號</th>
				<th width="200">商品名稱</th>
				<th width="100">販售單價</th>
				<th width="100">數量</th>
			</tr>
			<c:forEach var="item" items="${cart}">
				<tr>
					<td width="200"><div align="center"><b>${item.value.product_no}</b></div></td>
					<td width="100"><div align="center"><b>${item.value.product_name}</b></div></td>
					<td width="100"><div align="center"><b>${item.value.product_price}</b></div></td>
					<td id="item0" width="100">
						<div align="center">
							<b>
								<input type="number" min="0" name="cart_${item.value.product_no}_Num" value="${item.value.order_product_number}">
							</b>
						</div>
					</td>
				</tr>
			 </c:forEach>  
		</table>

		 <br>
      	 <input type="hidden" name="action"	value="updateCartNum"> 
         <input type="submit" value="數量確認囉">

    </form>
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