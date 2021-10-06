<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%@ page import="com.petClass.model.*"%>
<%@ page import="java.util.*"%>

<%
  GeneralMemberPetVO gmpVO = (GeneralMemberPetVO) request.getAttribute("gmpVO");
%>

<%
	PetClassDAO dao = new PetClassDAO();
	List<PetClassVO> petClass = dao.getAllpetClass();
	pageContext.setAttribute("petClass", petClass);
%>

<html>
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

<title>寵一而忠</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

<style>
.myright {
	margin-left: 80%;
}
</style>	

</head>
<body>

	<div id="main-wrapper">
		<%@ include file="../front_page/head.jsp"%>
		
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
					<div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">新增一般會員寵物</h4>
							</div>
							<div class="card-body">
								<div class="basic-form">
									<form method="POST"
										action="<%=request.getContextPath()%>/gmp/gmp.do"
										name="newPet">
										<input type="hidden" name="adpno" value=""> <input
											type="hidden" name="gmno" value="${meb.ger_meb_no}">


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">寵物品種</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="gmpb" placeholder="請填入寵物品種"
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
											<label class="col-sm-3 col-form-label">寵物心得</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="petcomment" placeholder="請填入寵物心得"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_pet_comment()%>">
											</div>
										</div>

										


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">毛色</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="petcolor" placeholder="請填入毛色"
													value="<%=(gmpVO == null) ? "" : gmpVO.getGen_pet_color()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class=" col-form-label">寵物分類</label>
											<c:forEach var="petClass" items="${petClass}">
												<c:if test="${petClass.pet_class_state == 1 && petClassNoBox == null}">
													<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
														<div
															class="form-check custom-checkbox mb-3 checkbox-success">
															<input type="checkbox" class="form-check-input"
																id="petClassNoCheckBox${petClass.pet_class_no}" name="petClassNo"
																value="${petClass.pet_class_no}"> <label
																class="form-check-label" for="petClassNoCheckBox${petClass.pet_class_no}">${petClass.pet_class_name}
															</label>
														</div>
													</div>
												</c:if>					
																										
											</c:forEach>	
											
											<c:if test="${petClassNoBox != null}">												
											<c:forEach var="allPetClass" items="${allPetClass}">																												
												<c:forEach var="petClassNoBox" items="${petClassNoBox}">											
												<c:if test="${allPetClass.pet_class_no == petClassNoBox && allPetClass.pet_class_state == 1}">											
														<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
															<div
																class="form-check custom-checkbox mb-3 checkbox-success">
																<input type="checkbox" class="form-check-input"
																	id="petClassNoCheckBox${petClass.pet_class_no}" name="petClassNo"
																	value="${allPetClass.pet_class_no}" checked> <label
																	class="form-check-label" for="petClassNoCheckBox${petClass.pet_class_no}">${allPetClass.pet_class_name}
																</label>
															</div>
														</div>
												</c:if>
												</c:forEach>	
											</c:forEach>												
											<c:forEach var="myNoCheckPetClass" items="${myNoCheckPetClass}">	
												<c:if test="${myNoCheckPetClass.pet_class_state == 1}">
													<div class="col-xl-2 col-xxl-2 col-2 offset-md-3">
														<div
															class="form-check custom-checkbox mb-3 checkbox-success">
															<input type="checkbox" class="form-check-input"
																id="petClassNoCheckBox${petClass.pet_class_no}" name="petClassNo"
																value="${myNoCheckPetClass.pet_class_no}"> <label
																class="form-check-label" for="petClassNoCheckBox${petClass.pet_class_no}">${myNoCheckPetClass.pet_class_name}
															</label>
														</div>
													</div>
												</c:if>												
											</c:forEach>																			
											</c:if>														
										</div>


										<input type="hidden" name="petstate" value="0">
										<input type="hidden" name="action" value="addGenMebPet">

										<div class="mb-3 row myright">
											<div class="col-sm-10">
												<button type="submit" class="btn light btn-secondary">新增寵物</button>
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
				<%@ include file="../front_page/footer2.jsp"%>
			</div>	
	<script>
		if (
	<%=("公").equals((gmpVO == null) ? "" : gmpVO.getGen_meb_pet_gender())%>
		) {
			document.getElementById("adopt_pet_genderMan").checked = true;

		} else if (
	<%=("母").equals((gmpVO == null) ? "" : gmpVO.getGen_meb_pet_gender())%>
		) {
			document.getElementById("adopt_pet_genderWoman").checked = true;
		}

		if (
	<%=("是").equals((gmpVO == null) ? "" : gmpVO.getGen_meb_pet_sterilization())%>
		) {
			document.getElementById("isSterilization").checked = true;
		} else if (
	<%=("否").equals((gmpVO == null) ? "" : gmpVO.getGen_meb_pet_sterilization())%>
		) {
			document.getElementById("noSterilization").checked = true;
		}
	</script>			
				
				
				
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
</body>

</html>