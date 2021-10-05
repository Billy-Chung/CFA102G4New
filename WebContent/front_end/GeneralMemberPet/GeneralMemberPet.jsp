<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.generalMember.model.*"%>
<%@ page import="com.generalMemberPetPhotos.model.*"%>

<%
	GeneralMemberPetDAO dao = new GeneralMemberPetDAO();
	HttpSession session2 = request.getSession();
	GeneralMemberVO member= (GeneralMemberVO)session2.getAttribute("meb");
	List<GeneralMemberPetVO> list = dao.findByGeneralMemberNo(member.getGer_meb_no());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">
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
		<%@ include file="../front_page/head.jsp"%>
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
					<%@ include file="../front_page/footer2.jsp"%>
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
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>

</body>
</html>