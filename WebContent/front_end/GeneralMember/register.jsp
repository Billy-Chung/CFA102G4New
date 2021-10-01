<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增</title>

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

<table id="table-1">
	<tr><td>
		 <h3>會員資料註冊</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMember/select_page.jsp""><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

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
						<h2 class="title m-b-10">Create Account</h2>
					</div>
					<!-- Register Title & Content End -->

					<!-- Form Action Start -->
					<form action="<%=request.getContextPath()%>/gm/gm.do" method="post">

						<!-- Input First Name Start -->
						<div class="single-input-item m-b-10">
							會員姓名:<input type="TEXT" name="meb_name" size="45" value="<%=(gmVO==null) ? "" :gmVO.getMeb_name()%>" placeholder="姓名">
						</div>
						<!-- Input First Name End -->

						<!-- Input Last Name Start -->
						<div class="single-input-item m-b-10">
							會員手機:<input type="TEXT"  name="phone" size="45"	value="<%=(gmVO==null) ? "" :gmVO.getPhone()%>" placeholder="手機">
						</div>
						<!-- Input Last Name End -->

						<!-- Input Email Start -->
						<div class="single-input-item m-b-10">
							會員生日:<input name="birthday" type="Date" value="<%=(gmVO==null) ? "" : gmVO.getBirthday() %>" placeholder="生日">
						</div>
						<!-- Input Email End -->

						<!-- Input Password Start -->
						<div class="single-input-item m-b-10">
							會員簡介:<input type="TEXT" name="comment" size="45" value="<%=(gmVO==null) ? "" :gmVO.getComment()%>" placeholder="簡介" >
						</div>

						<div class="single-input-item m-b-10">
							會員地址:<input type="TEXT" name="address" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAddress()%>" placehlder="地址" >
						</div>

						<div class="single-input-item m-b-10">
							EMAIL:<input type="TEXT" name="email" size="45" value="<%=(gmVO==null) ? "" :gmVO.getEmail()%>" placeholder="email" >
						</div>

						<div class="single-input-item m-b-10">
							帳號:<input type="TEXT" name="account" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" placeholder="帳號" >
						</div>

						<div class="single-input-item m-b-10">
							密碼:<input type="TEXT" name="password" size="45" value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" placeholder="密碼" >
						</div>

						<div class="single-input-item m-b-10">
							性別:<input type="radio" name="gender" value="<%=(gmVO==null) ? "" :gmVO.getGender()%>" checked />男
								<input type="radio" name="gender" value="<%=(gmVO==null) ? "" :gmVO.getGender()%>" />女
						</div>
						

						<!-- Button/Forget Password Start -->
						<div class="single-input-item">
							<div class="login-reg-form-meta m-b-n15">
								<button class="btn btn btn-gray-deep btn-hover-primary m-b-15">Create</button>
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


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do" enctype="multipart/form-data">
<table>
	<tr>
		<label><td>會員姓名:</td>
		<td><input type="TEXT" name="meb_name" size="45" value="<%=(gmVO==null) ? "" :gmVO.getMeb_name()%>" /></td></label>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><input type="TEXT" name="phone" size="45"	value="<%=(gmVO==null) ? "" :gmVO.getPhone()%>" /></td>
	</tr>
	
	<tr>
		<td>會員生日:</td>
		<td><input name="birthday" type="Date" value="<%=(gmVO==null) ? "" : gmVO.getBirthday() %>" /></td>
	</tr>
	<tr>
		<td>會員照片:</td>
		<td><input type="file" name="photo" /></td>
	</tr>
	<tr>
		<td>會員簡介:</td>
		<td><input type="TEXT" name="comment" size="45" value="<%=(gmVO==null) ? "" :gmVO.getComment()%>" /></td>
	</tr>
	
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="address" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAddress()%>" /></td>
	</tr>
	
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="45" value="<%=(gmVO==null) ? "" :gmVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="account" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" /></td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="password" size="45" value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" /></td>
	</tr>
	<tr>
		<td>會員性別:</td>
		<td><input type="radio" name="gender" value="<%=(gmVO==null) ? "" :gmVO.getGender()%>" checked />男</td>
		<td><input type="radio" name="gender" value="<%=(gmVO==null) ? "" :gmVO.getGender()%>" />女</td>
	</tr>
	
	<tr>
		<td><input type="hidden" name="money" size="45" value="<%=(gmVO==null) ? "" :gmVO.getMeb_money()%>" /></td>
	</tr>
	
	<tr>
		<td><input type="hidden" name="post_permission" size="45" value="<%=(gmVO==null)?"":gmVO.getPost_permission()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>