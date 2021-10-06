<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.generalMemberPetPhotos.model.*"%>

<%
	List<GeneralMemberPetPhotosVO> list = (List<GeneralMemberPetPhotosVO>) request.getAttribute("gmppList");
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
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

</head>

<body>

	<!--*******************
        Preloader start
    ********************-->
	
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
		
		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            Chat box start
        ***********************************-->
		
		<!--**********************************
            Chat box End
        ***********************************-->




		<!--**********************************
            Header start
        ***********************************-->
		<%@ include file="../front_page/head.jsp"%>
		<!--**********************************
            Header end ti-comment-alt
        ***********************************-->

		<!--**********************************
            Sidebar start
        ***********************************-->
		
		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body">
			<div class="container-fluid">

				<div class="row page-titles">
					<a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/GeneralMemberPet.jsp"><button
							type="button" class="btn light btn-dark">
							回首頁<span class="btn-icon-end"><i class="fa fa-star"></i></span>
						</button></a>
				</div>

				<div class="row">


					<c:forEach var="list" items="${list}">


						<div class="col-xl-4">
							<div class="card">
								<img class="card-img-top img-fluid imgSize"
									src="<%=request.getContextPath()%>/gmpp/gmpp.do?action=allPhoto&PK=${list.gen_meb_photo_no}"
									alt="Card image cap">
								<div class="card-footer">
									<form class="card-link float-end" method="post"
										action="<%=request.getContextPath()%>/gmpp/gmpp.do">
										<input type="hidden" name="gmppno"
											value="${list.gen_meb_photo_no}"> <input
											type="hidden" name="gmno"
											value="${list.gen_meb_no}"> <input
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
										action="<%=request.getContextPath()%>/gmpp/gmpp.do">

										<input type="hidden" name="gmno"
											value="${list.gen_meb_photo_no}"> <input
											type="hidden" name="genPetCoverState"
											value="${list.gen_pet_cover_state}"> <input
											type="hidden" name="action" value="update"> <input
											type="hidden" name="requestURL"
											value="<%=request.getParameter("requestURL")%>">
										<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
										<input type="hidden" name="whichPage"
											value="<%=request.getParameter("whichPage")%>">
											


										<c:if test="${list.gen_pet_cover_state == 0}">
											<button type="submit"
												class="btn btn-rounded btn-outline-secondary">
												<span class="btn-icon-start text-secondary"><i
													class="fa fa-plus color-info"></i> </span>設為封面
											</button>
										</c:if>
										<c:if test="${list.gen_pet_cover_state == 1}">
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
		<%@ include file="../front_page/footer2.jsp"%>	
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
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
		
	
</body>

</html>