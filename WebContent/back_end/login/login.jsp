<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

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
<title>寵一而忠登入頁</title>

<!-- FAVICONS ICON -->
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">


<style>
    .return{
        max-width: 100%;
        max-height: 100%;
    }
</style>
</head>

<body class="vh-100">
	<div class="authincation h-100">
		<div class="container h-100">
			<div class="row justify-content-center h-100 align-items-center">
				<div class="col-md-6">
					<div class="authincation-content">
						<div class="row no-gutters">
							<div class="col-xl-12">
								<div class="auth-form">
									<div class="text-center mb-3">
										 <a href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp" > <img class="return" src="<%=request.getContextPath()%>/back_end/backend_page/images/pet.jpg"></a>
									</div>

									<h4 class="text-center mb-4">後台登入</h4>
									<c:if test="${not empty errorMsgs}">
										<div class="col-xl-12 ">
											<div
												class="alert alert-danger left-icon-big alert-dismissible fade show">
												<button type="button" class="btn-close"
													data-bs-dismiss="alert" aria-label="btn-close">
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
									<form action="<%=request.getContextPath()%>/loginhandler"
										method="post">
										<div class="mb-3">
											<label class="mb-1"><strong>帳號</strong></label> <input
												type="text" class="form-control" name="account" value="">
										</div>
										<div class="mb-3">
											<label class="mb-1"><strong>密碼</strong></label> <input
												type="password" name="password" class="form-control" value="">
										</div>
										<input type="hidden" name="action" value="login">

										<div class="text-center">
											<button type="submit" class="btn btn-primary btn-block">登入</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--**********************************
        Scripts
    ***********************************-->
	<!-- Required vendors -->
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/global/global.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/custom.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/deznav-init.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/styleSwitcher.js"></script>
</body>

</html>