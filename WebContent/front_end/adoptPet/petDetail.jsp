<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>
<%@ page import="com.petClass.model.*"%>




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

	<div class="section breadcrumb-area bg-name-bright">
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<div class="breadcrumb-wrapper">
						<h2 class="breadcrumb-title">還想找找其他寵物?</h2>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">回首頁</a></li>
							<li>所有領養寵物</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="section section-margin">
		<div class="container">
			<div class="row">

				<div class="col-lg-5 offset-lg-0 col-md-8 offset-md-2">

					<!-- Product Details Image Start -->
					<div class="product-details-img">

						<!-- Single Product Image Start -->
						<div
							class="single-product-img swiper-container product-gallery-top">
							<div class="swiper-wrapper popup-gallery">
								<c:forEach var="petPhotoList" items="${petPhotoList}">
									<a class="swiper-slide w-100"
										href="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=allPhoto&PK=${petPhotoList.adopt_pet_photo_no}">
										<img class="w-100"
										src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=allPhoto&PK=${petPhotoList.adopt_pet_photo_no}">
									</a>
								</c:forEach>
							</div>
						</div>
						<!-- Single Product Image End -->

						<!-- Single Product Thumb Start -->
						<div
							class="single-product-thumb swiper-container product-gallery-thumbs">
							<div class="swiper-wrapper">
								<c:forEach var="petPhotoList" items="${petPhotoList}">
									<div class="swiper-slide">
										<img
											src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=allPhoto&PK=${petPhotoList.adopt_pet_photo_no}"
											alt="Product">
									</div>
								</c:forEach>
							</div>

							<!-- Next Previous Button Start -->
							<div class="swiper-button-next swiper-nav-button">
								<i class="ti-arrow-right"></i>
							</div>
							<div class="swiper-button-prev swiper-nav-button">
								<i class="ti-arrow-left"></i>
							</div>
							<!-- Next Previous Button End -->

						</div>
						<!-- Single Product Thumb End -->

					</div>
					<!-- Product Details Image End -->

				</div>

				<div class="col-lg-7">


					<div class="product-summery position-relative">


						<div class="product-head m-b-15">
							<h2 class="product-title">
								<strong>寵物品種:</strong> &nbsp; &nbsp;
								${adoptPetDetail.adopt_pet_breeds}
							</h2>
						</div>


						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>寵物性別:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_gender}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>來源:</strong> &nbsp;
								&nbsp; ${adoptPetDetail.adopt_pet_come_form}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>入所日期:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_join_date}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>寵物晶片號碼:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_chip}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>進所原因:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_join_reason}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>捕獲地點:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.capture_address}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>是否絕育:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_sterilization}
							</span>
						</div>


						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>收容編號:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.contain_number}
							</span>
						</div>

						<div class="product-inventroy m-b-15">
							<span class="inventroy-title"> <strong>寵物顏色:</strong>
								&nbsp; &nbsp; ${adoptPetDetail.adopt_pet_color}
							</span>
						</div>

						<c:if test="${adoptPetDetail.adopt_pet_state == 0}">
							<div class="product-inventroy m-b-15">
								<span class="inventroy-title"> <strong>領養狀態:</strong>
									&nbsp; &nbsp; 尚未被領養
								</span>
							</div>
						</c:if>

						<c:if test="${adoptPetDetail.adopt_pet_state == 1}">
							<div class="product-inventroy m-b-15">
								<span class="inventroy-title"> <strong>領養狀態:</strong>
									&nbsp; &nbsp; 已領養
								</span>
							</div>
						</c:if>

						<div class="single-product-buy m-b-30">
							<a href="checkout.html"
								class="btn btn-primary btn-hover-dark rounded-0">我想預約到現場看看牠!</a>

							<a href="checkout.html"
								class="btn btn-primary btn-hover-dark rounded-0">我已經迫不急待要領養牠了!</a>
						</div>
						
						<!-- Single Product Buy Button End -->



					</div>
					<!-- Product Summery End -->

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