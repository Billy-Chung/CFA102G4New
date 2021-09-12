<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.petClass.model.*"%>

<%
	PetClassDAO dao = new PetClassDAO();
	List<PetClassVO> list = dao.getAllpetClass();
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
								<h1 class="card-title">寵物分類清單</h1>
								<button type="button"
									class="btn btn-rounded btn-outline-success"
									data-bs-toggle="modal" data-bs-target="#modal1">
									<span class="btn-icon-start text-success"><i
										class="fa fa-upload color-success"></i> </span>新增寵物分類
								</button>
								<div class="modal fade bd-example-modal-lg" id="modal1"
									tabindex="-1" role="dialog" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">新分類!!</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>
											<div class="card ">
												<div class="card-body">
													<FORM
														action="<%=request.getContextPath()%>/petClass/petClass.do"
														method="post">

														<div class="mb-3">
															<input type="text" class="form-control input-default "
																placeholder="請輸入分類名稱" name="petClassName">
														</div>
														<input type="hidden" name="petClassState" value="1">
														<input type="hidden" name="action" value="newPetClass"><input
															type="hidden" name="requestURL"
															value="<%=request.getServletPath()%>">											
														


														<div class="modal-footer ">
															<button type="submit" class="btn btn-danger light"
																data-bs-dismiss="modal">關閉</button>
															<button type="submit" class="btn light btn-secondary ">新增分類</button>
														</div>
													</FORM>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-responsive-md">
										<thead>
											<tr>

												<th><h4>
														<strong>分類名稱</strong>
													</h4></th>
												<th><h4>
														<strong>當前狀態</strong>
													</h4></th>
											</tr>
										</thead>
										<%@ include file="page1.file"%>
										<c:forEach var="PetClassVO" items="${list}"
											begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
											<tbody>
												<tr>

													<td><h4>${PetClassVO.pet_class_name}</h4></td>
													<c:if test="${PetClassVO.pet_class_state == 1}">
														<td><span class="badge light badge-primary">顯示中</span></td>
														<td>
															<div class="dropdown">
																<button type="button"
																	class="btn btn-primary light sharp"
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
																	<button type="button" class="dropdown-item"
																		data-bs-toggle="modal"
																		data-bs-target="#modal${PetClassVO.pet_class_no}">
																		修改名稱</button>
																	<form method="post"
																		action="<%=request.getContextPath()%>/petClass/petClass.do">
																		<input type="hidden" name="petClassNo"
																			value="${PetClassVO.pet_class_no}"> <input
																			type="hidden" name="petClassName"
																			value="${PetClassVO.pet_class_name}"> <input
																			type="hidden" name="petClassState"
																			value="${PetClassVO.pet_class_state}"> <input
																			type="hidden" name="action" value="updateClassState">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getServletPath()%>">
																		<!--送出本網頁的路徑給Controller-->
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																		<button type="submit" class="dropdown-item">
																			隱藏該分類</button>
																	</form>
																</div>
															</div>
														</td>
													</c:if>
													<c:if test="${PetClassVO.pet_class_state == 0}">
														<td><span class="badge light badge-dark">隱藏中</span></td>
														<td>
															<div class="dropdown">
																<button type="button" class="btn btn-dark light sharp"
																	data-bs-toggle="dropdown">
																	<svg width="20px" height="20px" viewBox="0 0 24 24"
																		version="1.1">
																		<g stroke="none" stroke-width="1" fill="none"
																			fill-rule="evenodd">
																		<rect x="0" y="0" width="24" height="24" />
																		
																		<circle fill="#000000" cx="5" cy="12" r="2" />
																		
																		<circle fill="#000000" cx="12" cy="12" r="2" />
																		
																		<circle fill="#000000" cx="19" cy="12" r="2" /></g></svg>
																</button>
																<div class="dropdown-menu">
																	<button type="button" class="dropdown-item"
																		data-bs-toggle="modal"
																		data-bs-target="#modal${PetClassVO.pet_class_no}">
																		修改名稱</button>
																	<form method="post"
																		action="<%=request.getContextPath()%>/petClass/petClass.do">
																		<input type="hidden" name="petClassNo"
																			value="${PetClassVO.pet_class_no}"> <input
																			type="hidden" name="petClassName"
																			value="${PetClassVO.pet_class_name}"> <input
																			type="hidden" name="petClassState"
																			value="${PetClassVO.pet_class_state}"> <input
																			type="hidden" name="action" value="updateClassState">
																		<input type="hidden" name="requestURL"
																			value="<%=request.getServletPath()%>">
																		<!--送出本網頁的路徑給Controller-->
																		<input type="hidden" name="whichPage"
																			value="<%=whichPage%>">
																		<button type="submit" class="dropdown-item">
																			顯示該分類</button>
																	</form>
																</div>
															</div>
														</td>
													</c:if>
												</tr>
											</tbody>
											<div class="modal fade bd-example-modal-lg"
												id="modal${PetClassVO.pet_class_no}" tabindex="-1"
												role="dialog" aria-hidden="true">
												<div class="modal-dialog modal-lg">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title">更改分類名稱</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>
														<div class="card ">

															<div class="card-body">
																<FORM
																	action="<%=request.getContextPath()%>/petClass/petClass.do"
																	method="post">
																	<input type="hidden" name="petClassNo"
																		value="${PetClassVO.pet_class_no}">
																	<div class="mb-3 row">
																		<label class="col-sm-3 col-form-label">修改分類名稱</label>
																		<div class="col-sm-9">
																			<input type="text" class="form-control"
																				name="petClassName" placeholder="請填入分類名稱"
																				value="${PetClassVO.pet_class_name}">

																		</div>
																	</div>
																	<input type="hidden" name="petClassState"
																		value="${PetClassVO.pet_class_state}"> <input
																		type="hidden" name="action" value="updateClassName">
																	<input type="hidden" name="requestURL"
																		value="<%=request.getServletPath()%>">
																	<!--送出本網頁的路徑給Controller-->
																	<input type="hidden" name="whichPage"
																		value="<%=whichPage%>">

																	<div class="modal-footer ">
																		<button type="submit" class="btn btn-danger light"
																			data-bs-dismiss="modal">關閉</button>
																		<button type="submit" class="btn light btn-secondary ">送出修改</button>
																	</div>
																</FORM>
															</div>
														</div>
													</div>
												</div>
											</div>
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
