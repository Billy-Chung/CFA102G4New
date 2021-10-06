<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>寵一而忠</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>

<%@ include file="../front_page/head.jsp"%>

<div class="section section-margin">
	<div class="container">
		<div class="row">
			<div class="col-lg-7 col-md-8 m-auto">
				<div class="login-wrapper">

					<!-- Register Title & Content Start -->
					<div class="section-content text-center m-b-30">
						<h2 class="title m-b-10">會員資料</h2>
					</div>
					<!-- Register Title & Content End -->

					<!-- Form Action Start -->
					<form action="" method="post" >

						<!-- Input First Name Start -->
						<div class="single-input-item m-b-10">
							會員編號: ${gmVO.ger_meb_no}
						</div>
						<!-- Input First Name End -->
						<div class="single-input-item m-b-10">
							會員姓名: <input type="TEXT" name="meb_name" size="10" value="${gmVO.meb_name}" disabled>
						</div>
						<!-- Input Last Name Start -->
						<div class="single-input-item m-b-10">
							會員手機: <input type="TEXT"  name="phone" size="10"	value="${gmVO.phone}" disabled>
						</div>
						<!-- Input Last Name End -->
						

						<!-- Input Email Start -->
						<div class="single-input-item m-b-10">
							會員生日: <input name="birthday" id="f_date1" type="text" value="${gmVO.birthday}" disabled>
						</div>
						<!-- Input Email End -->

						<!-- Input Password Start -->
						<div class="single-input-item m-b-10">
							會員簡介: <input type="TEXT" name="comment" size="65" value="${gmVO.comment}" disabled >
						</div>

						<div class="single-input-item m-b-10">
							會員地址: <input type="TEXT" name="address" size="65" value="${gmVO.address}" disabled >
						</div>

						<div class="single-input-item m-b-10">
							EMAIL: <input type="TEXT" name="email" size="30" value="${gmVO.email}" disabled >
						</div>

						<div>
							性別: ${gmVO.gender}
						</div><p>
						
						<!-- Button/Forget Password Start -->
						
						<!-- Button/Forget Password End -->

					</form>
					<!-- Form Action End -->
					
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以上錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
</c:if>

				</div>
			</div>
		</div>
	</div>
</div>


	

<%@ include file="../front_page/footer2.jsp"%>

<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>


</body>
</html>