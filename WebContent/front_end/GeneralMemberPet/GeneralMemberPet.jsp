<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.generalMemberPetPhotos.model.*"%>

<%
	GeneralMemberPetDAO dao = new GeneralMemberPetDAO();
	List<GeneralMemberPetVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="shortcut icon" type="image/png" href="images/favicon.png" />
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
	
	<ul>
  		<li><a href="addGeneralMemberPet.jsp">新增會員寵物</a></li>
	</ul>

	
	<!--**********************************
        Main wrapper start
    ***********************************-->
	<div id="main-wrapper">

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
						action="<%=request.getContextPath()%>/gmp/gmp.do">
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