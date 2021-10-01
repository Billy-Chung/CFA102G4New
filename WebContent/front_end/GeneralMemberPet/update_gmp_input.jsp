<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.petClassList.model.*"%>
<%@ page import="com.petClass.model.*"%>
<%@ page import="java.util.*"%>

<%
  GeneralMemberPetVO gmpVO = (GeneralMemberPetVO) request.getAttribute("gmpVO");
  PetClassDAO dao = new PetClassDAO();
  List<PetClassVO> petClass = dao.getAllpetClass();
  pageContext.setAttribute("petClass", petClass);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>一般會員寵物資料修改 - update_generalmemberpet_input.jsp</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">

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
		 <h3>一般會員寵物資料修改 - update_generalmemberpet_input.jsp</h3>

	</td></tr>
</table>

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




				<div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">修改寵物</h4>
							</div>
							<div class="card-body">
								<div class="basic-form">
									<form method="POST"
										action="<%=request.getContextPath()%>/gmp/gmp.do">
										<input type="hidden" name="adpno"
											value=<%=(gmpVO.getAdopt_pet_no() == 0) ? "" : gmpVO.getAdopt_pet_no()%>">

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">新增會員</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" name="gmno"
													placeholder="請輸入一般會員編號"
													value="<%=(gmpVO.getGen_meb_no() == 0) ? "" : gmpVO.getGen_meb_no()%>">
											</div>
										</div>


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">寵物品種</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_breeds" placeholder="請填入領養寵物品種"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_meb_pet_breeds()%>">
											</div>
										</div>

										<fieldset class="mb-3">
											<div class="row">
												<label class="col-form-label col-sm-3 pt-0">寵物性別</label>
												<div class="col-sm-9">
													<div class="form-check">
														<label class="radio-inline me-3"><input
															type="radio" id="adopt_pet_genderMan"
															name="gender" value="公"> 公</label> <label
															class="radio-inline me-3"><input type="radio"
															id="adopt_pet_genderWoman" name="gender"
															value="母"> 母</label>
													</div>
												</div>
											</div>
										</fieldset>




										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">晶片號碼</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="petchip" placeholder="請填入晶片號碼"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_meb_pet_chip()%>">
											</div>
										</div>




										<fieldset class="mb-3">
											<div class="row">
												<label class="col-form-label col-sm-3 pt-0">是否絕育</label>
												<div class="col-sm-9">
													<div class="form-check">
														<label class="radio-inline me-3"><input
															type="radio" id="isSterilization"
															name="petsteril" value="是"> 是</label> <label
															class="radio-inline me-3"><input type="radio"
															id="noSterilization" name="petsteril"
															value="否"> 否</label>
													</div>
												</div>
											</div>
										</fieldset>
										

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">毛色</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="petcolor" placeholder="請填入毛色"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_pet_color()%>">
											</div>
										</div>
										
										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">寵物心得</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_join_reason" placeholder="請填入寵物心得"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_pet_comment()%>">
											</div>
										</div>
										

										<c:if test="${checkPetClass == null}">
											<div class="mb-3 row">
												<label class=" col-form-label">寵物分類</label>
												<c:forEach var="petClass" items="${petClass}">
													<c:forEach var="thisPetClass" items="${thisPetClass}">
														<c:if
															test="${petClass.pet_class_no == thisPetClass.pet_class_no && petClass.pet_class_state != 0 && thisPetClass.pet_class_list_state != 0}">
															<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
																<div
																	class="form-check custom-checkbox mb-3 checkbox-success">
																	<input type="checkbox" class="form-check-input"
																		id="petClassNoCheckBox${petClass.pet_class_no}"
																		name="petClassNo" value="${petClass.pet_class_no}"
																		checked> <label class="form-check-label"
																		for="petClassNoCheckBox${petClass.pet_class_no}">${petClass.pet_class_name}
																	</label>
																</div>
															</div>
														</c:if>
														<c:if
															test="${petClass.pet_class_no == thisPetClass.pet_class_no && petClass.pet_class_state != 0 && thisPetClass.pet_class_list_state != 1}">
															<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
																<div
																	class="form-check custom-checkbox mb-3 checkbox-success">
																	<input type="checkbox" class="form-check-input"
																		id="petClassNoCheckBox${petClass.pet_class_no}"
																		name="petClassNo" value="${petClass.pet_class_no}">
																	<label class="form-check-label"
																		for="petClassNoCheckBox${petClass.pet_class_no}">${petClass.pet_class_name}
																	</label>
																</div>
															</div>
														</c:if>
													</c:forEach>
												</c:forEach>
												<c:forEach var="allPetClass" items="${allPetClass}">
													<c:if test="${allPetClass.pet_class_state != 0}">
														<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
															<div
																class="form-check custom-checkbox mb-3 checkbox-success">
																<input type="checkbox" class="form-check-input"
																	id="petClassNoCheckBox${allPetClass.pet_class_no}"
																	name="petClassNo" value="${allPetClass.pet_class_no}">
																<label class="form-check-label"
																	for="petClassNoCheckBox${allPetClass.pet_class_no}">${allPetClass.pet_class_name}
																</label>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:if>
										<c:if test="${checkPetClass != null}">
											<div class="mb-3 row">
												<label class=" col-form-label">寵物分類</label>
												<c:forEach var="checkPetClass" items="${checkPetClass}">
													<c:if test="${checkPetClass.pet_class_state != 0}">
														<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
															<div
																class="form-check custom-checkbox mb-3 checkbox-success">
																<input type="checkbox" class="form-check-input"
																	id="petClassNoCheckBox${checkPetClass.pet_class_no}"
																	name="petClassNo" value="${checkPetClass.pet_class_no}"
																	checked> <label class="form-check-label"
																	for="petClassNoCheckBox${checkPetClass.pet_class_no}">${checkPetClass.pet_class_name}
																</label>
															</div>
														</div>
													</c:if>
												</c:forEach>
												<c:forEach var="allPetClass" items="${allPetClass}">
													<c:if test="${allPetClass.pet_class_state != 0}">
														<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
															<div
																class="form-check custom-checkbox mb-3 checkbox-success">
																<input type="checkbox" class="form-check-input"
																	id="petClassNoCheckBox${allPetClass.pet_class_no}"
																	name="petClassNo" value="${allPetClass.pet_class_no}">
																<label class="form-check-label"
																	for="petClassNoCheckBox${allPetClass.pet_class_no}">${allPetClass.pet_class_name}
																</label>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:if>

										<input type="hidden" class="form-control"
											name="petstate"
											value="<%=(gmpVO == null) ? "" : gmpVO.getGen_pet_state()%>">

										<input type="hidden" name="action" value="update"> <input
											type="hidden" name="genMebPetNo"
											value="<%=gmpVO.getGen_meb_no()%>"> <input
											type="hidden" name="requestURL"
											value="<%=request.getParameter("requestURL")%>">
										<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
										<input type="hidden" name="whichPage"
											value="<%=request.getParameter("whichPage")%>"> <input
											type="hidden" name="whichChip"
											value="<%=request.getParameter("whichChip")%>">

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