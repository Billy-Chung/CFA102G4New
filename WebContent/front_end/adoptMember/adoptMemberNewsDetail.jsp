<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>
<%@ page import="com.adoptMemberNews.model.*"%>

<%-- <jsp:useBean id="allNews" class="com.adoptMemberNews.model.AdoptMemberNewsVo"/> --%>



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

.listPhoto {
	width:50%;
	height:50%;
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

.oldPhoto2 {
	max-width: 100%;
	max-height: 100%;
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
						<h2 class="breadcrumb-title">持續關注我們以獲得毛小孩的最新消息!!</h2>
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
			<div class="row">
				<div class="col-lg-9 m-auto overflow-hidden">
					<!-- Blog Details Wrapper Start -->
					<div class="blog-details-wrapper">

						<!-- Blog Details Content Start -->
						<div class="blog-details-content">

							<!-- Blog Image Start -->
							<div class="blog-image listPhoto">
								<img src="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do?action=showNewsPhoto&PK=${thisNew.adopt_meb_news_no}"
									alt="Blog Image" class="fit-image">
							</div>
							<!-- Blog Image End -->

							<!-- Blog details title & Meta Start -->
							<div class="blog-details-title-meta">
								<h2 class="title">${thisNew.adopt_meb_news_title}</h2>
								<ul class="blog-meta">
									<li><span>${thisNew.adopt_meb_news_date}</span></li>

								</ul>
							</div>
							<!-- Blog details title & Meta End -->

							<!-- Content Start -->
							<p>${thisNew.adopt_meb_news_comment}</p>
							<!-- Blogquote Start -->

							<!-- Content End -->
						</div>
						<!-- Blog Details Content End -->
						<div class="col-12 col-custom m-t-20">
							<a
								href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMemberNews.jsp"
								class="btn btn-primary btn-hover-dark">返回最新消息列表</a>

						</div>
					</div>
					<!-- Blog Details Wrapper End -->
				</div>
			</div>
		</div>
	</div>




	<%@ include file="../front_page/footer2.jsp"%>



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