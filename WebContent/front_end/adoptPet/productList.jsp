<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<% productService productService = new productService(); 
		List<productVO> productList = productService.getAll();
		pageContext.setAttribute("prodList", productList);
	%>




<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>寵一而忠</title>


<link rel="shortcut icon" href="assets/images/favicon.ico">
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

.inmid2 {
	margin: 0 40% 0 23%;
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
</style>

</head>

<body>

	<%@ include file="../front_page/head.jsp"%>

	<div class="section breadcrumb-area bg-name-bright">
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<div class="breadcrumb-wrapper">
						<h2 class="breadcrumb-title">看看我們為您精挑細選的商品吧!!</h2>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">回商城首頁</a></li>
							<li>所有商品</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="section section-margin">
		<div class="container">
			<div class="row">		
				<div class="col-12">
                    <div class="wishlist-table table-responsive">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th class="pro-thumbnail">商品照片</th>
                                    <th class="pro-title">商品名稱</th>
                                    <th class="pro-price">價格</th>
                                    <th class="pro-cart">加入購物車</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="productList" items="${prodList}">
                                <tr>
                                    <td class="pro-thumbnail"><img class="fit-image" src="<%=request.getContextPath()%>/product/product.do?action=cover&PK=${productList.product_no}" alt="Product" /></td>
                                    <td class="pro-title"><a href="single-product.html">${productList.product_name}</a></td>
                                    <td class="pro-price"><span>${productList.product_price}</span></td>
                                    <td class="pro-cart"><a href="cart.html" class="btn btn-primary btn-hover-dark">加入購物車!!</a></td>
                                </tr>  
                                </c:forEach>                             
                            </tbody>
                        </table>
                    </div>
                </div>
			

			</div>
		</div>
	</div>
	<!-- Single Product Section End -->



	<%@ include file="../front_page/footer.jsp"%>



	<!-- Scroll Top Start -->
	<a href="#" class="scroll-top show" id="scroll-top"> <i
		class="arrow-top ti-angle-double-up"></i> <i
		class="arrow-bottom ti-angle-double-up"></i>
	</a>
	<!-- Scroll Top End -->



	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
</body>

</html>