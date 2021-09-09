<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>



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
					<form>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨12點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨1點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨2點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨3點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨4點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">凌晨5點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上6點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上7點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上8點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上9點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上10點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">早上11點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">中午12點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="passtextword" class="form-control"
									placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">下午1點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">下午2點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">下午3點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">下午4點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">下午5點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上6點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上7點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上8點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上9點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上10點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-xl-4 col-xxl-6 col-6">
								<div class="form-check custom-checkbox mb-3 checkbox-success">
									<input type="checkbox" class="form-check-input" checked
										id="customCheckBox3" required> <label
										class="form-check-label" for="customCheckBox3">晚上11點</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="請輸入該時段上限">
							</div>
						</div>

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



	<%@ include file="../backend_page/footer.jsp"%>
	</div>
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




