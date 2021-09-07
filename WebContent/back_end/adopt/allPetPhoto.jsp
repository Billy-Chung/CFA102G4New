<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>

<%
	List<AdoptPetPhotoVO> list = (List<AdoptPetPhotoVO>) request.getAttribute("adoptMemberPhotoList");
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
<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">

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
			<div class="container-fluid">

				<div class="row page-titles">
					<a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp"><button
							type="button" class="btn light btn-dark">
							回首頁<span class="btn-icon-end"><i class="fa fa-star"></i></span>
						</button></a>
				</div>

				<div class="row">


					<c:forEach var="list" items="${list}">


						<div class="col-xl-4">
							<div class="card">
								<img class="card-img-top img-fluid imgSize"
									src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=allPhoto&PK=${list.adopt_pet_photo_no}"
									alt="Card image cap">
								<div class="card-footer">
									<form class="card-link float-end" method="post"
										action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">
										<input type="hidden" name="adoptPetPhotoNo"
											value="${list.adopt_pet_photo_no}"> <input
											type="hidden" name="adoptPetNo"
											value="${list.adopt_pet_no}"> <input
											type="hidden" name="action" value="deletePhoto"> <input
											type="hidden" name="requestURL"
											value="<%=request.getParameter("requestURL")%>">
										<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->

										<button type="submit"
											class="btn btn-rounded btn-outline-danger">
											<span class="btn-icon-start text-danger"><i
												class="fas fa-times"></i> </span>刪除照片
										</button>
									</form>

									<form method="post"
										action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">

										<input type="hidden" name="adoptPetNo"
											value="${list.adopt_pet_photo_no}"> <input
											type="hidden" name="adoptPetCoverState"
											value="${list.adopt_pet_cover_state}"> <input
											type="hidden" name="action" value="update"> <input
											type="hidden" name="requestURL"
											value="<%=request.getParameter("requestURL")%>">
										<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
										<input type="hidden" name="whichPage"
											value="<%=request.getParameter("whichPage")%>">


										<c:if test="${list.adopt_pet_cover_state == 0}">
											<button type="submit"
												class="btn btn-rounded btn-outline-secondary">
												<span class="btn-icon-start text-secondary"><i
													class="fa fa-plus color-info"></i> </span>設為封面
											</button>
										</c:if>
										<c:if test="${list.adopt_pet_cover_state == 1}">
											<button type="submit"
												class="btn btn-rounded btn-outline-light">
												<span class="btn-icon-start text-light"><i
													class="fa fa-plus color-info"></i> </span>取消封面
											</button>

										</c:if>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--**********************************
            Content body end
        ***********************************-->


		<!--**********************************
            Footer start
        ***********************************-->
		<%@ include file="../backend_page/footer.jsp"%>
		<!--**********************************
            Footer end
        ***********************************-->


	</div>
	<!--**********************************
        Main wrapper end
    ***********************************-->

	<!--**********************************
        Scripts
    ***********************************-->

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