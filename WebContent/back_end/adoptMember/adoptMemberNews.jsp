<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMemberNews.model.*"%>

<%
	AdoptMemberNewsDAO dao = new AdoptMemberNewsDAO();
	List<AdoptMemberNewsVo> list = dao.getAlladoptMemberNews();
	pageContext.setAttribute("list", list);
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

.inmid {
	margin: 0 40% 0 43%;
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
				<div class="row page-titles">
					<a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp"><button
							type="button" class="btn light btn-dark ">
							回首頁<span class="btn-icon-end"><i class="fa fa-star"></i></span>
						</button></a>
				</div>
				<c:if test="${not empty errorMsgs}">
					<div class="col-xl-6 ">
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
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">機構最新消息清單</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-responsive-md">
										<thead>
											<tr>
												<th><strong>最新消息標題</strong></th>
												<th><strong>最新消息時間</strong></th>
												<th><strong>最新消息狀態</strong></th>
												<th></th>
											</tr>
										</thead>
										<%@ include file="page1.file"%>
										<c:forEach var="AdoptMemberNewsVo" items="${list}"
											begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
											<tbody>
												<tr>
													<td>${AdoptMemberNewsVo.adopt_meb_news_title}</td>
													<td>${AdoptMemberNewsVo.adopt_meb_news_date}</td>
													<c:if test="${AdoptMemberNewsVo.adopt_meb_news_state == 1}">
														<td><span class="badge light badge-success">顯示中</span></td>
														<td>
															<div class="dropdown">
																<button type="button"
																	class="btn btn-success light sharp"
																	data-bs-toggle="dropdown">
																	<svg width="20px" height="20px" viewBox="0 0 24 24"
																		version="1.1">
                                <g stroke="none" stroke-width="1"
																			fill="none" fill-rule="evenodd">
                                  <rect x="0" y="0" width="24"
																			height="24" />
                                  <circle fill="#000000" cx="5" cy="12"
																			r="2" />
                                  <circle fill="#000000" cx="12" cy="12"
																			r="2" />
                                  <circle fill="#000000" cx="19" cy="12"
																			r="2" />
                                </g>
                              </svg>
																</button>
																<div class="dropdown-menu">
																	<form method="post"
																		action="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do">
																		<input type="hidden"
																			value="${AdoptMemberNewsVo.adopt_meb_news_no}"
																			name="adopt_meb_news_no"> <input
																			type="hidden" name="action" value="updateNewsPage">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getServletPath()%>">
																		<!--送出本網頁的路徑給Controller-->
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																		<!--送出當前是第幾頁給Controller-->
																		<button type="submit" class="dropdown-item">修改</button>
																	</form>

																	<form method="post"
																		action="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do">
																		<input type="hidden"
																			value="${AdoptMemberNewsVo.adopt_meb_news_no}"
																			name="adopt_meb_news_no"> <input
																			type="hidden" name="action" value="updateNewsStatus">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getServletPath()%>">
																		<!--送出本網頁的路徑給Controller-->
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																		<!--送出當前是第幾頁給Controller-->
																		<button type="submit" class="dropdown-item">隱藏</button>
																	</form>
																</div>
															</div>
														</td>
													</c:if>
													<c:if test="${AdoptMemberNewsVo.adopt_meb_news_state == 0}">
														<td><span class="badge light badge-danger">隱藏中</span></td>
														<td>
															<div class="dropdown">
																<button type="button" class="btn btn-danger light sharp"
																	data-bs-toggle="dropdown">
																	<svg width="20px" height="20px" viewBox="0 0 24 24"
																		version="1.1">
                                <g stroke="none" stroke-width="1"
																			fill="none" fill-rule="evenodd">
                                  <rect x="0" y="0" width="24"
																			height="24" />
                                  <circle fill="#000000" cx="5" cy="12"
																			r="2" />
                                  <circle fill="#000000" cx="12" cy="12"
																			r="2" />
                                  <circle fill="#000000" cx="19" cy="12"
																			r="2" />
                                </g>
                              </svg>
																</button>
																<div class="dropdown-menu">
																	<form method="post"
																		action="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do">
																		<input type="hidden"
																			value="${AdoptMemberNewsVo.adopt_meb_news_no}"
																			name="adopt_meb_news_no"> <input
																			type="hidden" name="action" value="updateNewsPage">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getParameter("requestURL")%>">
																		<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
																		<input type="hidden" name="whichPage"
																			value="<%=request.getParameter("whichPage")%>">
																		<button type="submit" class="dropdown-item">修改</button>
																	</form>
																	<form method="post"
																		action="<%=request.getContextPath()%>/adoptMeb/adoptMebNews.do">
																		<input type="hidden"
																			value="${AdoptMemberNewsVo.adopt_meb_news_no}"
																			name="adopt_meb_news_no"> <input
																			type="hidden" name="action" value="updateNewsStatus">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getServletPath()%>">
																		<!--送出本網頁的路徑給Controller-->
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																		<!--送出當前是第幾頁給Controller-->
																		<button type="submit" class="dropdown-item">顯示</button>
																	</form>
																</div>
															</div>
														</td>
													</c:if>
												</tr>
											</tbody>
										</c:forEach>
									</table>
								</div>
								<%@ include file="page2.file"%>
							</div>
						</div>
					</div>
				</div>






			</div>
		</div>
	</div>


	<%@ include file="../backend_page/footer.jsp"%>





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
