<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>





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
						<h2 class="breadcrumb-title">寵牠，就讓牠陪你到終老!!</h2>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">返回領養頁面</a></li>
							<li>所有領養寵物</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section section-margin">
		<div class="container">
			<div class="row row-cols-lg-3 row-cols-sm-2 row-cols-1 m-b-n40">
			

				<div class="col m-b-40">
					<!-- Single Blog Start -->
					<div class="single-blog-wrapper">

						<!-- Blog Thumb Start -->
						<div class="blog-thumb thumb-effect">
							<a class="image" href="blog-details.html"> <img
								class="fit-image"
								src="../front_CSS/assets/images/blog/medium-size/1.jpg"
								alt="Blog Image">
							</a>
						</div>
						<!-- Blog Thumb End -->

						<!-- Blog Content Start -->
						<div class="blog-content">
							<div class="blog-meta">
								<ul>
									<li><span>By</span><a href="#/">Admin</a></li>
									<li><span>27, Jan, 2021</span></li>
								</ul>
							</div>
							<h2 class="blog-title">
								<a href="blog-details.html">How to take care of your fish</a>
							</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut...</p>
							<a class="more-link" href="blog-details.html">Read More</a>
						</div>
						<!-- Blog Content End -->

					</div>
					<!-- Single Blog End -->
				</div>

			
			

			

					
			

			

			

				

					

			</div>
		
		
		
		</div>
	</div>
	<!-- Blog Grid Section End -->




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