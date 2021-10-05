<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%-- <%=empVO=null %> --${empVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

<title>寵一而忠</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="../front_page/head.jsp"%>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- Register Section Start -->
<div class="section section-margin">
	<div class="container">
		<div class="row">
			<div class="col-lg-7 col-md-8 m-auto">
				<div class="login-wrapper">

					<!-- Register Title & Content Start -->
					<div class="section-content text-center m-b-30">
						<h2 class="title m-b-10">修改會員資料</h2>
					</div>
					<!-- Register Title & Content End -->

					<!-- Form Action Start -->
					<form action="<%=request.getContextPath()%>/gm/gm.do" method="post" enctype="multipart/form-data">
						<div class="single-input-item m-b-10">
							會員編號:<%=gmVO.getGer_meb_no()%>
						</div>
						<!-- Input First Name Start -->
						<div class="single-input-item m-b-10">
							會員姓名:<input type="TEXT" name="meb_name" size="10" value="<%=gmVO.getMeb_name()%>" placeholder="姓名">
						</div>
						<!-- Input First Name End -->

						<!-- Input Last Name Start -->
						<div class="single-input-item m-b-10">
							會員手機:<input type="TEXT"  name="phone" size="10"	value="<%=gmVO.getPhone()%>" placeholder="手機">
						</div>
						<!-- Input Last Name End -->
						
						<div class="single-input-item m-b-10">
							會員照片:<input type="file"  name="photo">
						</div>

						<!-- Input Email Start -->
						<div class="single-input-item m-b-10">
							會員生日:<input name="birthday" type="Date" value="<%=gmVO.getBirthday() %>" placeholder="生日">
						</div>
						<!-- Input Email End -->

						<!-- Input Password Start -->
						<div class="single-input-item m-b-10">
							會員簡介:<input type="TEXT" name="comment" size="65" value="<%=gmVO.getComment()%>" placeholder="簡介" >
						</div>

						<div class="single-input-item m-b-10">
							會員地址:<input type="TEXT" name="address" size="65" value="<%=gmVO.getAddress()%>" placehlder="地址" >
						</div>

						<div class="single-input-item m-b-10">
							EMAIL:<input type="TEXT" name="email" size="30" value="<%=gmVO.getEmail()%>" placeholder="email" >
						</div>

						<div class="single-input-item m-b-10">
							帳號:<input type="TEXT" name="account" size="16" value="<%=gmVO.getAccount()%>" placeholder="帳號" >
						</div>

						<div class="single-input-item m-b-10">
							密碼:<input type="TEXT" name="password" size="16" value="<%=gmVO.getPassword()%>" placeholder="密碼" >
						</div>

						<div>
							性別:<input type="radio" name="gender" value="1" checked />男
								<input type="radio" name="gender" value="0" />女
						</div><p>
						

						<!-- Button/Forget Password Start -->
						<div class="single-input-item">
							<div class="login-reg-form-meta m-b-n15">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="gmno" value="<%=gmVO.getGer_meb_no()%>">
								<button align="center" class="btn btn btn-gray-deep btn-hover-primary m-b-15">修改完畢</button>
							</div>
						</div>
						<!-- Button/Forget Password End -->

					</form>
					<!-- Form Action End -->
					

				</div>
			</div>
		</div>
	</div>
</div>
<!-- Register Section End -->

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