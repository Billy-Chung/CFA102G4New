<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>

<%
	AdoptPetDAO dao = new AdoptPetDAO();
	List<AdoptPetVO> list = dao.getAllAdoptPet();
	pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html lang="en">

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

<!-- PAGE TITLE HERE -->
<title>寵一而忠</title>

<!-- FAVICONS ICON -->
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>

<style>
.toServlet {
	display: inline-block;
}

.inmid {
	margin: 0 40% 0 43%;
}

.imgSize {
	width: 100%;
	height: 100%;
}
#myIcon {
	max-width: 100%;
}
</style>

</head>

<body>

	<!--*******************
        Preloader start
    ********************-->
	<%@ include file="../backend_page/Preloader.jsp"%>
	<!--*******************
        Preloader end
    ********************-->


	<!--**********************************
        Main wrapper start
    ***********************************-->
	<div id="main-wrapper">

		<!--**********************************
            Nav header start
        ***********************************-->
		<%@ include file="../backend_page/nav-header.jsp"%>
		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            Chat box start
        ***********************************-->
		<%@ include file="../backend_page/chatbox.jsp"%>
		<!--**********************************
            Chat box End
        ***********************************-->




		<!--**********************************
            Header start
        ***********************************-->
		<%@ include file="../backend_page/head.jsp"%>
		<!--**********************************
            Header end ti-comment-alt
        ***********************************-->

		<!--**********************************
            Sidebar start
        ***********************************-->
		<%@ include file="../backend_page/Sidebar.jsp"%>
		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
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

					<form method="post"
						action="<%=request.getContextPath()%>/adoptPet/addPet.do">
						<div class="input-group search-area">
							<input type="text" class="form-control" name="whichChip"
								placeholder="以晶片號尋找"> <input type="hidden" name="action"
								value="searchFromChip"> <span class="input-group-text">
								<button type="submit"
									class="btn btn-rounded btn-outline-success">搜尋</button>
							</span>
						</div>
					</form>

				</div>
				<div class="row">
					<%@ include file="page1.file"%>

					<%@ include file="petCard.jsp"%>

					<!--**********************************
            Content body end
        ***********************************-->

					<%@ include file="page2.file"%>
					<!--**********************************
            Footer start
        ***********************************-->
					<%@ include file="../backend_page/footer.jsp"%>
					<!--**********************************
            Footer end
        ***********************************-->

				</div>

			</div>
			<!--**********************************
        Main wrapper end
    ***********************************-->
		</div>
	</div>
	<!--**********************************
        Scripts
    *********************************
    
    **-->



	<!-- Rating -->
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