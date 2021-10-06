<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>

<%
	AdoptMemberVO adoptMember = (AdoptMemberVO) request.getAttribute("adoptMemberVO");
	pageContext.setAttribute("adoptMember", adoptMember);
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
.myright {
	margin-left: 80%;
}

#isChangePhoto {
	width: 30%;
	height: 30%;
	margin-left: 30%;
}

.scroll {
	overflow: auto;
}

#myIcon {
	max-width: 100%;
}
</style>

</head>

<body>

	<%@ include file="../backend_page/Preloader.jsp"%>
	<div id="main-wrapper">
		<%@ include file="../backend_page/nav-header.jsp"%>
		<%@ include file="../backend_page/chatbox.jsp"%>
		<%@ include file="../backend_page/head.jsp"%>
		<%@ include file="../backend_page/Sidebar.jsp"%>

		<div class="content-body">
			<div class="container-fluid">			
				<div class="row">
					<div class="row page-titles">
						<a
							href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp"><button
								type="button" class="btn light btn-dark ">
								回首頁<span class="btn-icon-end"><i class="fa fa-star"></i></span>
							</button></a>
					</div>
					<div class="card scroll ">
						<div class="card-header">
							<h4 class="card-title">可預約時段修改</h4>
						</div>
						<div class="card-body">
							<form method="POST"
								action="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do">
								<div class="row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox1" name="ifDay" value="1"> <label
												class="form-check-label" for="customCheckBox1">星期一 </label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox2" name="ifDay" value="2"> <label
												class="form-check-label" for="customCheckBox2">星期二</label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox3" name="ifDay" value="3"> <label
												class="form-check-label" for="customCheckBox3">星期三</label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox4" name="ifDay" value="4"> <label
												class="form-check-label" for="customCheckBox4">星期四</label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox5" name="ifDay" value="5"> <label
												class="form-check-label" for="customCheckBox5">星期五</label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox6" name="ifDay" value="6"> <label
												class="form-check-label" for="customCheckBox6">星期六</label>
										</div>
									</div>
									<div class="col-xl-4 col-xxl-6 col-6">
										<div class="form-check custom-checkbox mb-3 checkbox-success">
											<input type="checkbox" class="form-check-input" 
												id="customCheckBox7" name="ifDay" value="7"> <label
												class="form-check-label" for="customCheckBox7">星期日</label>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨12點-凌晨1點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time0" name="time0"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3" selected>3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨1點-凌晨2點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time1" name="time1"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨2點-凌晨3點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time2" name="time2"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨3點-凌晨4點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time3" name="time3"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨4點-凌晨5點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time4" name="time4"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">凌晨5點-早上6點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time5" name="time5"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上6點-早上7點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time6" name="time6"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上7點-早上8點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time7" name="time7"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上8點-早上9點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time8" name="time8"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上9點-早上10點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time9" name="time9"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上10點-早上11點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time10" name="time10"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">早上11點-中午12點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time11" name="time11"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">中午12點-下午1點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time12" name="time12"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">下午1點-下午2點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time13" name="time13"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">下午2點-下午3點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time14" name="time14"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">下午3點-下午4點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time15" name="time15"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">下午4點-下午5點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time16" name="time16"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">下午5點-晚上6點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time17" name="time17"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上6點-晚上7點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time18" name="time18"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上7點-晚上8點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time19" name="time19"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上8點-晚上9點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time20" name="time20"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上9點-晚上10點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time21" name="time21"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上10點-晚上11點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time22" name="time22"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>
								<div class="mb-3 row">
									<div class="col-xl-4 col-xxl-6 col-6">
										<h4 class="form-check-label">晚上11點-凌晨12點</h4>
									</div>
									<div class="col-sm-9">
										<div class="mb-3 ">
											<h5 class="form-label">該時段預約人數上限:</h5>
											<select id="time23" name="time23"
												class="default-select  form-control wide">
												<option value="0">0人</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
										</div>
									</div>
								</div>

								<input type="hidden" name="adoptMebNo"
									value="${adoptMember.adopt_meb_no}"> <input
									type="hidden" name="action" value="updateMebTime">

								<div class="mb-3 row  myright">
									<div class="col-sm-10">
										<button type="submit" class="btn light btn-secondary">送出修改</button>
									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="../backend_page/footer.jsp"%>

<script>

  let s = "<%=adoptMember.getAdopt_meb_holiday()%>";
  let s2 = "<%=adoptMember.getAdopt_meb_limit()%>";
  for (let i = 0; i < s.length; i++) {
	  if(s.charAt(i) == 1){		
		  let c = i+1
		  document.getElementById("customCheckBox"+c).checked = true;
	  };
  
  }
   for(let i = 0; i < s2.length; i++ ){  	
	 	 $("#time"+i).val(s2.charAt(i)).change();   
   }
</script>



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




