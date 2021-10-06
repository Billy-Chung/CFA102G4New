<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.generalMemberPetPhotos.model.*"%>

<%
	List<GeneralMemberPetVO> list2 = (List<GeneralMemberPetVO>) request.getAttribute("searchList");
	pageContext.setAttribute("list", list2);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="" />
<meta name="author" content="" />
<meta name="robots" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Innap : Hotel Admin Template" />
<meta property="og:title" content="Innap : Hotel Admin Template" />
<meta property="og:description" content="Innap : Hotel Admin Template" />
<meta property="og:image" content="social-image.png" />
<meta name="format-detection" content="telephone=no">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
</head>
<body>

<div class="content-body">
			<c:if test="${not empty errorMsgs}">
				<div class="col-xl-6 ">
					<div
						class="alert alert-danger left-icon-big alert-dismissible fade show">
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="btn-close">
							<span><i class="mdi mdi-btn-close"></i></span>
						</button>
						<div class="media">
							<div class="alert-left-icon-big">
								<span><i class="mdi mdi-alert"></i></span>
							</div>
							<div class="media-body">
								<h5 class="mt-1 mb-2">請修正以下錯誤:</h5>
								<c:forEach var="message" items="${errorMsgs}">
									<p class="mb-0">${message.value}</p>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</c:if>

			<div class="container-fluid">
				<div class="row page-titles ">

					<a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/GeneralMemberPet.jsp"><button
							type="button" class="btn light btn-dark">
							返回清單<span class="btn-icon-end"><i class="fa fa-star"></i></span>
						</button></a>

				</div>
				<div class="row">


					<%@ include file="page3.file"%>

					<%@ include file="petCard.jsp"%>

					<!--**********************************
            Content body end
        ***********************************-->

					<%@ include file="page4.file"%>
					<!--**********************************
            Footer start
        ***********************************-->
					<%@ include file="../front_page/footer2.jsp"%>

				</div>

			</div>
			<!--**********************************
        Main wrapper end
    ***********************************-->
		</div>


<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/global/global.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/custom.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/deznav-init.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/styleSwitcher.js"></script>

</body>
</html>