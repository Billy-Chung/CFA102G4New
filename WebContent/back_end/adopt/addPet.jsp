<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adoptPet.model.*"%>

<%
	AdoptPetVO adoptPet = (AdoptPetVO) request.getAttribute("adoptPetVO");
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

<style>
.myright {
	margin-left: 80%;
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
					<div class="col-xl-12 col-lg-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">新增寵物</h4>
							</div>
							<div class="card-body">
								<div class="basic-form">
									<form method="POST"
										action="<%=request.getContextPath()%>/adoptPet/addPet.do"
										name="newPet">
										<input type="hidden" name="adopt_meb_no" value="2"> <input
											type="hidden" name="gen_meb_no" value="">


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">領養寵物品種</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_breeds" placeholder="請填入領養寵物品種"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_breeds()%>">
											</div>
										</div>				
										
										<fieldset class="mb-3">
											<div class="row">
												<label class="col-form-label col-sm-3 pt-0">寵物性別</label>
												<div class="col-sm-9">												
													<div class="form-check">
														<label class="radio-inline me-3"><input
															type="radio" id="adopt_pet_genderMan" name="adopt_pet_gender" value="公" > 公</label>
														<label class="radio-inline me-3"><input
															type="radio" id="adopt_pet_genderWoman" name="adopt_pet_gender" value="母"> 母</label>
													</div>
												</div>
											</div>
										</fieldset>


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">來源</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_come_form" placeholder="請填入來源"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_come_form()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">入所日期</label>
											<div class="col-sm-9">
												<input type="Date" class="form-control"
													name="adopt_pet_join_date" placeholder="請填入入所日期"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_join_date()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">晶片號碼</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_chip" placeholder="請填入晶片號碼"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_chip()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">進所原因</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_join_reason" placeholder="請填入進所原因"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_join_reason()%>">
											</div>
										</div>

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">捕獲地址</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="capture_address" placeholder="請填入捕獲地址"
													value="<%=(adoptPet == null) ? "" : adoptPet.getCapture_address()%>">
											</div>
										</div>					
										
										<fieldset class="mb-3">
											<div class="row">
												<label class="col-form-label col-sm-3 pt-0">是否絕育</label>
												<div class="col-sm-9">												
													<div class="form-check">
														<label class="radio-inline me-3"><input
															type="radio" id="isSterilization" name="adopt_pet_sterilization" value="是" > 是</label>
														<label class="radio-inline me-3"><input
															type="radio" id="noSterilization" name="adopt_pet_sterilization" value="否"> 否</label>
													</div>
												</div>
											</div>
										</fieldset>	

										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">收容編號</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="contain_number" placeholder="請填入收容編號"
													value="<%=(adoptPet == null) ? "" : adoptPet.getContain_number()%>">
											</div>
										</div>


										<div class="mb-3 row">
											<label class="col-sm-3 col-form-label">毛色</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													name="adopt_pet_color" placeholder="請填入毛色"
													value="<%=(adoptPet == null) ? "" : adoptPet.getAdopt_pet_color()%>">
											</div>
										</div>


										<input type="hidden" name="adopt_pet_state" value="0">
										<input type="hidden" name="action" value="addPet">

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
		<%@ include file="../backend_page/footer.jsp"%>
	</div>
	
	<script>		
	if(<%=("公").equals((adoptPet == null) ? "" :adoptPet.getAdopt_pet_gender())%>){		
		document.getElementById("adopt_pet_genderMan").checked = true;
		
	}else if (<%=("母").equals((adoptPet == null) ? "" :adoptPet.getAdopt_pet_gender())%>){	
		document.getElementById("adopt_pet_genderWoman").checked = true;
	}	
	
	
	if(<%=("是").equals((adoptPet == null) ? "" :adoptPet.getAdopt_pet_sterilization())%>){
		document.getElementById("isSterilization").checked = true;		
	}else if(<%=("否").equals((adoptPet == null) ? "" :adoptPet.getAdopt_pet_sterilization())%>){
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