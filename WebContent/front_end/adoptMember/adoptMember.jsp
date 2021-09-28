<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>


<%
	AdoptMemberDAO dao = new AdoptMemberDAO();
	List<AdoptMemberVO> adoptMeb = dao.getAllAdoptMeb();
	StringBuilder newComment = new StringBuilder();
	for(AdoptMemberVO thisMeb: adoptMeb){
		String [] oldContext = thisMeb.getAdopt_meb_comment().split("\\r\\n");
		for(String comment : oldContext) {
			newComment.append(comment + "<br>");
		}
		thisMeb.setAdopt_meb_comment(newComment.toString());
	}
	
	pageContext.setAttribute("adoptMeb", adoptMeb);
	List<String> memberHoliday = new ArrayList();
	for(AdoptMemberVO member:adoptMeb){
		for(int i = 0; i < 7; i++){
			memberHoliday.add(String.valueOf(member.getAdopt_meb_holiday().charAt(i)));			
		}
	}
	pageContext.setAttribute("memberHoliday", memberHoliday);
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


	<!-- About Section Start -->
	<div class="section section-margin">
		<div class="container">
			<div class="row m-b-n30">
				<c:forEach var="adoptMeb" items="${adoptMeb}">
					<div class="col-md-6 pe-lg-5 p-r-15 m-b-30" data-aos="fade-up"
						data-aos-duration="1000">
						<!-- About Thumb Start -->

						<div class="about-thumb">
							<img class="fit-image listPhoto"
								src="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=showMebPhoto&adoptMebNo=${adoptMeb.adopt_meb_no}"
								alt="About Image">
						</div>
						<!-- About Thumb End -->
					</div>
					<div class="col-md-6 align-self-center m-b-30" data-aos="fade-up"
						data-aos-duration="1500">
						<!-- About Content Start -->
						<div class="about-content">
							<h2 class="title">關於我們</h2>
							<p>${adoptMeb.adopt_meb_comment}</p>
							<ul class="about-content-list">
								<li><span><i class="ti-angle-double-right"></i></span>
									機構地址: &nbsp; &nbsp; ${adoptMeb.adopt_meb_address}</li>
								<li><span><i class="ti-angle-double-right"></i></span>
									機構電話: &nbsp; &nbsp; ${adoptMeb.adopt_meb_phone}</li>
								<li><span><i class="ti-angle-double-right"></i></span>
									機構Email: &nbsp; &nbsp; ${adoptMeb.adopt_meb_email}</li>
							</ul>

							<div class="product-material m-b-25 d-flex">
								<span><strong>營業日 :</strong></span>
								<c:forEach var="memberHoliday" items="${memberHoliday}" varStatus="status">
								<c:if test="${memberHoliday != 0 && status.count != 7}">								
								<p>&nbsp; 星期${status.count} &nbsp;</p>								
								</c:if>
								<c:if test="${memberHoliday != 0 && status.count == 7}">								
								<p>&nbsp; 星期日 &nbsp;</p>								
								</c:if>

								</c:forEach>
							</div>
							<a href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMemberNews.jsp" class="btn btn-primary btn-hover-dark">查看最新消息</a>
						</div>

						<!-- About Content End -->
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- About Section End -->



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