<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="com.shopping_cart.*" %>
<%@page import="com.order_detail.model.*" %>
<!DOCTYPE html>
<html>
<head>
<title>寵一而忠</title>

<meta charset="UTF-8">
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
	<!--  從客戶端得到 key為cart的參數， session.getAttribute("cart")是object，強轉型為Map型別         -->
	<!-- 將Map內的所有值轉存成集合Collection，取值所有的值 ，存到購物車的清單中cartlist-->



	<c:if test="${empty cart }">
	您尚未選購商品，請<a href="<%=request.getContextPath()%>/front_end/shopping_cart/ProductList.jsp">點此</a>前往購物

</div>
</c:if>
<c:if test="${not empty cart }">
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" style="margin-bottom: 0px;">
		<input type="submit" value="我想調整數量">
		<input type="hidden" name="action"	value="update">
	</FORM>
	
	<table border="1" width="740">
		<tr bgcolor="#999999">
			<th width="100"><div align="center">商品編號</div></th>
			<th width="200"><div align="center">商品名稱</div></th>
			<th width="100"><div align="center">販售單價</div></th>
			<th width="100"><div align="center">數量</div></th>
			<th width="50"><div align="center">刪除</div></th>
		</tr>
		
		
	<!-- 集合Collection提供迭代器Iterator的方法，可以取得所有的元素 -->
	<!-- 因為會加入n次，用while -->
		
		<c:forEach var="orderDetailVO" items="${cart.values() }">
	<!--畫面看到的部分 -->	
		<tr>
			<td width="200"><div align="center"><b>${orderDetailVO.product_no }</b></div></td>
			<td width="100"><div align="center"><b>${orderDetailVO.product_name }</b></div></td>
			<td width="100"><div align="center"><b>${orderDetailVO.product_price }</b></div></td>
			<td id="item0" width="100"><div align="center"><b>${orderDetailVO.order_product_number }</b></div></td>
			
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" style="margin-bottom: 0px;"><%--路徑位置從back_end第一層算起--%>
				     <input type="submit" value="移除購物車">
				     <input type="hidden" name="product_no"  value="${orderDetailVO.product_no }">
				     <input type="hidden" name="action"	value="delete">
				</FORM>
			</td>  
		</tr>
		</c:forEach>
	</table>
	<p>                   
    <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping_cart/shoppingCart.html" method="POST">
    	<!--action送出請求到控制器(Shopping_cartServlet.java)結果會是字串CHECKOUT -->
        <input type="hidden" name="action"	value="CHECKOUT"> 
        <input type="submit" value="我要付款結帳">   
    </form>
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