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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>上傳會員寵物資料 - addGeneralMemberPet.jsp</title>

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
											type="hidden" name="gmno" value="">


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