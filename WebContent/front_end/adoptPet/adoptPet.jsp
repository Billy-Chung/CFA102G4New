<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>
<%@ page import="com.petClass.model.*"%>

<%
	List<AdoptPetVO> list;
	if (request.getAttribute("returnList") == null) {
		AdoptPetDAO dao = new AdoptPetDAO();
		list = dao.getAllAdoptPet();
		pageContext.setAttribute("list", list);
	} else {
		list = (List<AdoptPetVO>) request.getAttribute("returnList");
		pageContext.setAttribute("list", request.getAttribute("returnList"));
	}
%>

<%
	PetClassDAO classDao = new PetClassDAO();
	List<PetClassVO> classList = classDao.getAllpetClass();
	pageContext.setAttribute("classList", classList);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Amber - Pet Care Bootstrap 5 Template</title>


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

	<!-- Hero/Intro Slider Start -->
	<div class="section">
		<div class="hero-slider swiper-container">
			<div class="swiper-wrapper">

				<div class="hero-slide-item swiper-slide">
					<div class="hero-slide-bg">
						<img
							src="<%=request.getContextPath()%>/front_end/front_CSS/assets/images/slider/slider1-1.png"
							alt="Slider Image" />
					</div>
					<div class="container">
						<div class="hero-slide-content text-start">
							<h5 class="sub-title">We keep pets for pleasure.</h5>
							<h2 class="title m-0">Vitamins For all Pets</h2>
							<p class="ms-0">We know your concerns when you are looking
								for a chewing treat for your dog.</p>
						</div>
					</div>
				</div>

				<div class="hero-slide-item swiper-slide">
					<div class="hero-slide-bg">
						<img
							src="<%=request.getContextPath()%>/front_end/front_CSS/assets/images/slider/slider1-2.png"
							alt="Slider Image" />
					</div>
					<div class="container">
						<div class="hero-slide-content text-center text-md-end">
							<h5 class="sub-title">We keep pets for pleasure.</h5>
							<h2 class="title m-0">Vitamins For all Pets</h2>
							<p>We know your concerns when you are looking for a chewing
								treat for your dog.</p>

						</div>
					</div>
				</div>
			</div>

			<!-- Swiper Navigation Start -->
			<div
				class="home-slider-prev swiper-button-prev main-slider-nav d-lg-flex d-none">
				<i class="icon-arrow-left"></i>
			</div>
			<div
				class="home-slider-next swiper-button-next main-slider-nav d-lg-flex d-none">
				<i class="icon-arrow-right"></i>
			</div>
			<!-- Swiper Navigation End -->
		</div>
	</div>
	<!-- Hero/Intro Slider End -->

	<div class="section section-margin">
		<div class="container">
			<div class="row flex-row-reverse">
				<div class="col-lg-9 col-12">

					<!--shop toolbar start-->
					<div
						class="shop_toolbar_wrapper flex-column flex-md-row p-2 m-b-40 border">

						<!-- Shop Top Bar Left start -->
						<div class="shop-top-bar-left">

							<div class="shop_toolbar_btn">
								<button data-role="grid_3" type="button"
									class="active btn-grid-3" title="Grid">
									<i class="ti-layout-grid4-alt"></i>
								</button>
								<button data-role="grid_list" type="button" class="btn-list"
									title="List">
									<i class="ti-align-justify"></i>
								</button>
							</div>
							<div class="shop-top-show">
								<span>選擇你想要的瀏覽方式!!</span>
							</div>

						</div>
						<!-- Shop Top Bar Left end -->


					</div>
					<!--shop toolbar end-->

					<!-- Shop Wrapper Start -->
					<div class="row shop_wrapper grid_3">
						<%@ include file="page1.file"%>
						<c:forEach var="AdoptPetVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<!-- Single Product Start -->
							<div class="col-lg-4 col-md-4 col-sm-6 product">
								<div class="product-inner">
									<div class="thumb">
										<a href="single-product.html" class="image"> <img
											class="fit-image"
											src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=cover&PK=${AdoptPetVO.adopt_pet_no}"
											alt="Product" />
										</a>
										<div class="action-wrapper">
											<a href="#/" class="action quickview"><i class="ti-plus"></i></a>
											<a href="#/" class="action wishlist" title="Wishlist"><i
												class="ti-heart"></i></a>
										</div>
									</div>
									<div class="content">
										<h5 class="title">
											<a href="single-product.html">寵物品種: &nbsp;
												&nbsp;${AdoptPetVO.adopt_pet_breeds}</a>
										</h5>
										<span class="rating"> 寵物顏色: &nbsp; &nbsp;
											${AdoptPetVO.adopt_pet_color} </span> <span class="price"> <span
											class="new">進所日期: &nbsp; &nbsp;
												${AdoptPetVO.adopt_pet_join_date}</span>
										</span>

										<!-- Cart Button Start -->
										<div class="cart-btn action-btn">
											<div class="action-cart-btn-wrapper d-flex">
												<div class="add-to_cart">
													<a class="btn btn-primary btn-hover-dark rounded-0"
														href="cart.html">預約看寵物!!</a>
												</div>
											</div>
										</div>
										<!-- Cart Button End -->
									</div>
								</div>
							</div>
							<!-- Single Product End -->
						</c:forEach>
					</div>
					<!-- Shop Wrapper End -->

					<!--shop toolbar start-->
					<div class="shop_toolbar_wrapper justify-content-center m-t-50">

						<!-- Shopt Top Bar Right Start -->
						<div class="shop-top-bar-right">
							<%@ include file="page2.file"%>
						</div>
						<!-- Shopt Top Bar Right End -->

					</div>
					<!--shop toolbar end-->

				</div>
				<div class="col-lg-3 col-12">
					<!-- Sidebar Widget Start -->
					<aside class="sidebar_widget m-t-50 mt-lg-0">
						<div class="widget_inner">
							<div class="widget-list m-b-50">
								<h3 class="widget-title m-b-30">寵物分類標籤</h3>
								<div class="sidebar-body">
									<form method="post"
										action="<%=request.getContextPath()%>/petClass/petClass.do">
										<input type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="action" value="searchByClass">
										<ul class="checkbox-container categories-list">
											<c:forEach var="classList" items="${classList}">
												<c:if test="${classList.pet_class_state == 1}">
													<li>
														<div class="custom-control custom-checkbox">
															<input type="checkbox" class="custom-control-input"
																id="customCheck${classList.pet_class_no}"
																name="searchClass" value="${classList.pet_class_no}">
															<label class="custom-control-label"
																for="customCheck${classList.pet_class_no}">${classList.pet_class_name}</label>
														</div>
													</li>
												</c:if>
											</c:forEach>
											<li><button type="submit"
													class="btn btn-primary btn-hover-dark rounded-0">查詢</button></li>
										</ul>
									</form>
									<c:if test='<%=request.getAttribute("returnList") != null%>'>									
										<a href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp"
											class="btn btn-primary btn-hover-dark rounded-0 myTop">取消查詢</a>
									</c:if>
								</div>
							</div>

						</div>
					</aside>
					<!-- Sidebar Widget End -->
				</div>
			</div>
		</div>
	</div>




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