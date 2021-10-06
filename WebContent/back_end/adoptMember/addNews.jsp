<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.adoptMemberNews.model.*"%>
<%
	Date today = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	String newTime = ft.format(today);
%>
<%
	AdoptMemberNewsVo adoptMebNewsFail = null;
	if (request.getAttribute("adoptMemberNewsVo") != null) {
		adoptMebNewsFail = (AdoptMemberNewsVo) request.getAttribute("adoptMemberNewsVo");
	} else {
		adoptMebNewsFail = null;
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


					<div class="col-xl-12 col-xxl-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">新增最新消息</h4>
							</div>

							<div class="card-body">
								<div class="basic-form">
									<form method="post"
										action="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do"
										enctype="multipart/form-data">


										<input type="hidden" name="adopt_meb_no"
											value="${admin.adopt_meb_no}"> <input type="hidden"
											name="adopt_meb_news_state" value="1"> <input
											type="hidden" name="adopt_meb_news_date" value="<%=newTime%>">
										<input type="hidden" name="action" value="addNews"> <img
											id="isChangePhoto">
										<div class="mb-3 row">
											<label class="col-sm-2 col-form-label col-form-label-lg">最新消息標題</label>
											<div class="col-sm-10">
												<input type="text" class="form-control form-control-lg"
													placeholder="請輸入標題" name="adopt_meb_news_title"
													value="<%=(adoptMebNewsFail == null) ? "" : adoptMebNewsFail.getAdopt_meb_news_title()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class="col-sm-2 col-form-label col-form-label-lg">最新消息照片</label>
											<div class="input-group ">
												<div class="form-file">
													<input type="file" id="isChange"
														class="form-file-input form-control"
														name="adopt_meb_news_photo">
												</div>
											</div>
										</div>


										<div class="mb-3 row">
											<label class="col-sm-2 col-form-label col-form-label-lg">最新消息內文</label>
											<div class="input-group">
												<textarea id="newsArea" class="form-control"
													style="height: 150px;" placeholder="請輸入最新消息"
													name="adopt_meb_news_comment"></textarea>

											</div>

										</div>

										<div class="mb-3 row myright">
											<div class="col-sm-10">
												<button type="submit" class="btn light btn-secondary">新增最新消息</button>
											</div>
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
	<%@ include file="../backend_page/footer.jsp"%>

	<script>
		if(<%=adoptMebNewsFail != null%>) {
			document.getElementById("newsArea").value = "<%=(adoptMebNewsFail == null) ? "" : adoptMebNewsFail.getAdopt_meb_news_comment()%>";
		}
	</script>

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