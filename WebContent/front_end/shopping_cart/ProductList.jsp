<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品資料</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

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
	
	
	<%@ include file="./sideBar.jsp"%>
	
	<%@ include file="../front_page/head.jsp"%>
	<jsp:useBean id="promotionSvc" class="com.promotions.model.PromotionsService"></jsp:useBean>
	<jsp:useBean id="promotionDetailSvc" class="com.product_promotions_detail.model.ProductPromotionService"></jsp:useBean>
	
	
	<%@ include file="./PromoBanner.jsp"%>
	
	<div class="section section-margin">
		<div class="container">
	
			<%
				productService productService = new productService();
				List<ProductVO> productList = productService.getAll();
				pageContext.setAttribute("prodList", productList);
			%>
			<div class="row flex-row-reverse">
				<div class="col-lg-9 col-12">
					<div class="row shop_wrapper grid_3">
						<c:forEach var="product" items="${prodList}">
							<%@ include file="./cardModule.jsp"%>
						</c:forEach>
					</div>
				</div>	
			</div>
		</div>
		
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