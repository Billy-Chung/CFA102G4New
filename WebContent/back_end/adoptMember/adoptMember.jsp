<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>

<%
	AdoptMemberVO adoptMeb = (AdoptMemberVO) request.getAttribute("adoptMemberVO");
	pageContext.setAttribute("adoptMeb", adoptMeb);
%>
<%
	AdoptMemberVO adoptMebFail = null;
	if (request.getAttribute("adoptMebVO") != null) {
		adoptMebFail = (AdoptMemberVO) request.getAttribute("adoptMebVO");
	} else {
		adoptMebFail = null;
	}
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
				<c:if test="${not empty errorMsgs}">
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

				<div class="row">
					<div class="row page-titles">
						<a
							href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp"><button
								type="button" class="btn light btn-dark">
								回首頁<span class="btn-icon-end"><i class="fa fa-star"></i></span>
							</button></a>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="profile card card-body px-3 pt-3 pb-0">
								<div class="profile-head">
									<div class="photo-content">
										<div class=" rounded">
											<c:if test="<%=adoptMeb.getAdopt_meb_photo() != null%>">
												<img
													src="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=showMebPhoto&adoptMebNo=${adoptMeb.adopt_meb_no}">
											</c:if>
										</div>
									</div>
									<div class="profile-info">
										<div class="profile-details">
											<div class="profile-name px-3 pt-2">
												<h4 class="text-primary mb-0">${adoptMeb.adopt_meb_name}</h4>
												<p>機構姓名</p>
											</div>
											<div class="profile-name px-3 pt-2">
												<h4 class="text-muted mb-0">${adoptMeb.adopt_meb_email}</h4>
												<p>Email</p>
											</div>
											<div class="profile-name px-3 pt-2">
												<h4 class="text-muted mb-0">${adoptMeb.adopt_meb_address}</h4>
												<p>地址</p>
											</div>
											<div class="profile-name px-3 pt-2">
												<h4 class="text-muted mb-0">${adoptMeb.adopt_meb_phone}</h4>
												<p>電話</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xl-12 col-lg-12">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">修改機構資料</h4>
					</div>
					<div class="card-body">
						<div class="basic-form">
							<form method="POST"
								action="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do"
								enctype="multipart/form-data">

								<input type="hidden" name="adopt_meb_no"
									value="${adoptMeb.adopt_meb_no}">

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">修改機構名稱</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" name="adopt_meb_name"
											placeholder="請填入領養機構名稱"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_name() : adoptMeb.getAdopt_meb_name()%>">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構簡介</label>
									<div class="col-sm-9">
										<div class="input-group">
											<textarea class="form-control" name="adopt_meb_comment"
												placeholder="請填入領養機構簡介"><%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_comment() : adoptMeb.getAdopt_meb_comment()%></textarea>
											<div class="input-group-append"></div>
										</div>
									</div>
								</div>
								<img id="isChangePhoto">
								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構照片</label>
									<div class="col-sm-9">
										<input id="isChange" type="file"
											class="form-file-input form-control" name="adopt_meb_photo">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構地址</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											name="adopt_meb_address" placeholder="請填入領養機構地址"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_address() : adoptMeb.getAdopt_meb_address()%>">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構電話</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" name="adopt_meb_phone"
											placeholder="請填入領養機構電話"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_phone() : adoptMeb.getAdopt_meb_phone()%>">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構EMAIL</label>
									<div class="col-sm-9">
										<input type="email" class="form-control"
											name="adopt_meb_email" placeholder="請填入領養機構EMAIL"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_email() : adoptMeb.getAdopt_meb_email()%>">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構帳號</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											name="adopt_meb_account" placeholder="請填入領養機構帳號"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_account() : adoptMeb.getAdopt_meb_account()%>">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-3 col-form-label">領養機構密碼</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											name="adopt_meb_password" placeholder="請填入領養機構密碼"
											value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_password() : adoptMeb.getAdopt_meb_password()%>">
									</div>
								</div>

								<input type="hidden" class="form-control" name="adopt_meb_state"
									value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_state() : adoptMeb.getAdopt_meb_state()%>">

								<input type="hidden" class="form-control" name="adopt_meb_auth"
									value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_auth() : adoptMeb.getAdopt_meb_auth()%>">


								<input type="hidden" class="form-control"
									name="adopt_meb_holiday"
									value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_holiday() : adoptMeb.getAdopt_meb_holiday()%>">

								<input type="hidden" class="form-control" name="adopt_meb_limit"
									value="<%=(adoptMebFail != null) ? adoptMebFail.getAdopt_meb_limit() : adoptMeb.getAdopt_meb_limit()%>">

								<input type="hidden" name="action" value="updateMeb">

								<div class="mb-3 row myright">
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
		$('#isChange').on('change', function(e) {
			const file = this.files[0];

			const fr = new FileReader();
			fr.onload = function(e) {
				console.log(111);
				$('#isChangePhoto').attr('src', e.target.result);
			};
			// 使用 readAsDataURL 將圖片轉成 Base64
			fr.readAsDataURL(file);
		});
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




