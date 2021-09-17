<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>
<%@ page import="com.adoptMemberNews.model.*"%>

<%-- <jsp:useBean id="allNews" class="com.adoptMemberNews.model.AdoptMemberNewsVo"/> --%>
<%
	AdoptMemberNewsDAO dao = new AdoptMemberNewsDAO();
	List<AdoptMemberNewsVo> list = dao.getAlladoptMemberNews();
	pageContext.setAttribute("list", list);
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

.mybottom2 {
	margin-bottom: 5%;
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
			<div
				class="row row-cols-lg-3 row-cols-sm-2 row-cols-1 m-b-n40 mybottom2">
				<%@ include file="page1.file"%>
				<c:forEach var="allNews" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<c:if test="${allNews.adopt_meb_news_state == 1 }">
						<div class="col m-b-40">
							<!-- Single Blog Start -->
							<div class="single-blog-wrapper">

								<!-- Blog Thumb Start -->
								<div class="blog-thumb thumb-effect">
									<a class="image"
										href="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do?action=showNewsPage&&PK=${allNews.adopt_meb_news_no}">
										<img class="fit-image oldPhoto2"
										src="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do?action=showNewsPhoto&PK=${allNews.adopt_meb_news_no}"
										alt="Blog Image">
									</a>
								</div>
								<!-- Blog Thumb End -->

								<!-- Blog Content Start -->
								<div class="blog-content">
									<div class="blog-meta">
										<ul>
											<li><span>${allNews.adopt_meb_news_date}</span></li>
										</ul>
									</div>
									<h2 class="blog-title">
										<a
											href="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do?action=showNewsPage&&PK=${allNews.adopt_meb_news_no}">${allNews.adopt_meb_news_title}</a>
									</h2>
									<a class="more-link"
										href="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do?action=showNewsPage&&PK=${allNews.adopt_meb_news_no}">查看詳細</a>
								</div>
								<!-- Blog Content End -->

							</div>
							<!-- Single Blog End -->
						</div>
					</c:if>
				</c:forEach>
			</div>


			<%@ include file="page2.file"%>
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