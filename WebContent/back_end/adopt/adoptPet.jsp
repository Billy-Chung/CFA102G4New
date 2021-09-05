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
<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">

<style>
.toServlet {
	display: inline-block;
}

.inmid {
	margin: 0 40% 0 43%;
}

.inmid2 {
	margin: 0 50% 0 25%;
}

.imgSize {
	width: 100%;
	height: 100%;
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
				<c:if test="${errorMsgs.containsKey('errorPhoto')}">
					<div class="col-xl-6 inmid2">
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
			</c:if>



			<div class="container-fluid">
				<div class="row page-titles">
					<li class="nav-item">
						<div class="input-group search-area">
							<input type="text" class="form-control" placeholder="Search here">
							<span class="input-group-text"><a
								href="javascript:void(0)"><i class="flaticon-381-search-2"></i></a></span>
						</div>
					</li>
				</div>
				<div class="row">
					<%@ include file="page1.file"%>

					<c:forEach var="AdoptPetVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">


						<div class="col-lg-12 col-xl-6">
							<div class="card">
								<div class="card-body">
									<div class="row m-b-30">
										<div class="col-md-5 col-xxl-12">
											<div class="new-arrival-product mb-4 mb-xxl-4 mb-md-0">
												<div class="new-arrivals-img-contnent">
													<img class="img-fluid"
														src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=cover&PK=${AdoptPetVO.adopt_pet_no}"
														alt="">
												</div>
											</div>
										</div>
										<div class="col-md-7 col-xxl-12">
											<div class="new-arrival-content position-relative">
												<h2>${AdoptPetVO.adopt_pet_breeds}</h2>
												<p>
													來源: <span class="item">${AdoptPetVO.adopt_pet_come_form}</span>
												</p>
												<p>
													晶片號碼: <span class="item">${AdoptPetVO.adopt_pet_chip}</span>
												</p>
												<p>
													收容編號: <span class="item">${AdoptPetVO.contain_number}</span>
												</p>
												<p>
													性別: <span class="item">${AdoptPetVO.adopt_pet_gender}</span>
												</p>

												<div class="rounded-button">

													<form class="toServlet" method="post"
														action="<%=request.getContextPath()%>/adoptPet/addPet.do">
														<input type="hidden" name="adoptPetNo"
															value="${AdoptPetVO.adopt_pet_no}"> <input
															type="hidden" name="action" value="getOne_For_Update">


														<button type="submit"
															class="btn btn-rounded btn-outline-secondary">
															<span class="btn-icon-start text-secondary"><i
																class="fa fa-plus color-info"></i> </span>修改詳細資料
														</button>
													</form>


													<form class="toServlet" method="post"
														action="<%=request.getContextPath()%>/adoptPet/addPet.do">

														<input type="hidden" name="adopt_pet_no"
															value="${AdoptPetVO.adopt_pet_no}"> <input
															type="hidden" name="gen_meb_no"
															value="${AdoptPetVO.gen_meb_no}"> <input
															type="hidden" name="adopt_pet_breeds"
															value="${AdoptPetVO.adopt_pet_breeds}"> <input
															type="hidden" name="adopt_pet_gender"
															value="${AdoptPetVO.adopt_pet_gender}"> <input
															type="hidden" name="adopt_pet_come_form"
															value="${AdoptPetVO.adopt_pet_come_form}"> <input
															type="hidden" name="adopt_pet_join_date"
															value="${AdoptPetVO.adopt_pet_join_date}"> <input
															type="hidden" name="adopt_pet_chip"
															value="${AdoptPetVO.adopt_pet_chip}"> <input
															type="hidden" name="adopt_pet_join_reason"
															value="${AdoptPetVO.adopt_pet_join_reason}"> <input
															type="hidden" name="capture_address"
															value="${AdoptPetVO.capture_address}"> <input
															type="hidden" name="capture_address"
															value="${AdoptPetVO.capture_address}"> <input
															type="hidden" name="contain_number"
															value="${AdoptPetVO.contain_number}"> <input
															type="hidden" name="adopt_pet_color"
															value="${AdoptPetVO.adopt_pet_color}"> <input
															type="hidden" name="adopt_pet_state"
															value="${AdoptPetVO.adopt_pet_state}"> <input
															type="hidden" name="action" value="delete">

														<c:if test="${AdoptPetVO.adopt_pet_state == 0}">
															<button type="submit"
																class="btn btn-rounded btn-outline-success">
																<span class="btn-icon-start text-success"><i
																	class="fa fa-plus color-info"></i> </span>修改為以領養
															</button>
														</c:if>
														<c:if test="${AdoptPetVO.adopt_pet_state == 1}">
															<button type="submit"
																class="btn btn-rounded btn-outline-light">
																<span class="btn-icon-start text-light"><i
																	class="fa fa-plus color-info"></i> </span>修改為未領養
															</button>
														</c:if>
													</form>


													<button type="button"
														class="btn btn-rounded btn-outline-info"
														data-bs-toggle="modal"
														data-bs-target="#modal${AdoptPetVO.adopt_pet_no}">
														<span class="btn-icon-start text-info"><i
															class="fa fa-upload color-success"></i> </span>新增寵物照片
													</button>

													<div class="modal fade bd-example-modal-lg"
														id="modal${AdoptPetVO.adopt_pet_no}" tabindex="-1"
														role="dialog" aria-hidden="true">
														<div class="modal-dialog modal-lg">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title">上傳新圖片</h5>
																	<button type="button" class="btn-close"
																		data-bs-dismiss="modal"></button>
																</div>
																<%@ include file="addPhoto.jsp"%>
															</div>
														</div>
													</div>

													<form class="toServlet" method="post"
														action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">
														<input type="hidden" name="adopt_pet_no"
															value="${AdoptPetVO.adopt_pet_no}"> <input
															type="hidden" name="action" value="findByadoptPetNo">
														<button type="submit"
															class="btn btn-rounded btn-outline-warning">
															<span class="btn-icon-start text-warning"><i
																class="fa fa-share-alt color-secondary"></i> </span>查看所有照片
														</button>
													</form>

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
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