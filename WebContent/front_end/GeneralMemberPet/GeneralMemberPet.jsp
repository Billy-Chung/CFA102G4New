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
		<%@ include file="../front_page/Sidebar.jsp"%>
		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body">
			

			<div class="container-fluid">
				
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
					<%@ include file="../front_page/footer.jsp"%>
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