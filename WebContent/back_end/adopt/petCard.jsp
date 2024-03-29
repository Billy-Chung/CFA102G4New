<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:forEach var="AdoptPetVO" items="${list}" begin="<%=pageIndex%>"
	end="<%=pageIndex+rowsPerPage-1%>">


	<div class="col-lg-12 col-xl-6">
		<div class="card">
			<div class="card-body">
				<div class="row m-b-30">
					<div class="col-md-5 col-xxl-12">
						<div class="new-arrival-product mb-4 mb-xxl-4 mb-md-0">
							<div class="new-arrivals-img-contnent">
								<img class="img-fluid"
									src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=cover&PK=${AdoptPetVO.adopt_pet_no}"
									alt="">
							</div>
						</div>
					</div>
					<div class="col-md-7 col-xxl-12">
						<div class="new-arrival-content position-relative">
							<h2>${AdoptPetVO.adopt_pet_breeds}</h2>
							<p>
								來源: <span class="item">${AdoptPetVO.adopt_pet_come_form}</span>
							</p>
							<p>
								晶片號碼: <span class="item">${AdoptPetVO.adopt_pet_chip}</span>
							</p>
							<p>
								收容編號: <span class="item">${AdoptPetVO.contain_number}</span>
							</p>
							<p>
								性別: <span class="item">${AdoptPetVO.adopt_pet_gender}</span>
							</p>

							<div class="rounded-button">

								<form class="toServlet" method="post"
									action="<%=request.getContextPath()%>/adoptPet/addPet.do">
									<input type="hidden" name="adoptPetNo"
										value="${AdoptPetVO.adopt_pet_no}"> <input
										type="hidden" name="action" value="getOne_For_Update">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="whichChip"
										value="<%=request.getParameter("whichChip")%>">

									<button type="submit"
										class="btn btn-rounded btn-outline-secondary">
										<span class="btn-icon-start text-secondary"><i
											class="fa fa-plus color-info"></i> </span>修改詳細資料
									</button>
								</form>


								<form class="toServlet" method="post"
									action="<%=request.getContextPath()%>/adoptPet/addPet.do">

									<input type="hidden" name="adopt_pet_no"
										value="${AdoptPetVO.adopt_pet_no}"> <input
										type="hidden" name="gen_meb_no"
										value="${AdoptPetVO.gen_meb_no}"> <input type="hidden"
										name="adopt_pet_breeds" value="${AdoptPetVO.adopt_pet_breeds}">
									<input type="hidden" name="adopt_pet_gender"
										value="${AdoptPetVO.adopt_pet_gender}"> <input
										type="hidden" name="adopt_pet_come_form"
										value="${AdoptPetVO.adopt_pet_come_form}"> <input
										type="hidden" name="adopt_pet_join_date"
										value="${AdoptPetVO.adopt_pet_join_date}"> <input
										type="hidden" name="adopt_pet_chip"
										value="${AdoptPetVO.adopt_pet_chip}"> <input
										type="hidden" name="adopt_pet_join_reason"
										value="${AdoptPetVO.adopt_pet_join_reason}"> <input
										type="hidden" name="capture_address"
										value="${AdoptPetVO.capture_address}"> <input
										type="hidden" name="capture_address"
										value="${AdoptPetVO.capture_address}"> <input
										type="hidden" name="contain_number"
										value="${AdoptPetVO.contain_number}"> <input
										type="hidden" name="adopt_pet_color"
										value="${AdoptPetVO.adopt_pet_color}"> <input
										type="hidden" name="adopt_pet_state"
										value="${AdoptPetVO.adopt_pet_state}"> <input
										type="hidden" name="action" value="delete"> <input
										type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="whichChip"
										value="<%=request.getParameter("whichChip")%>">

									<c:if test="${AdoptPetVO.adopt_pet_state == 0}">
										<button type="submit"
											class="btn btn-rounded btn-outline-success">
											<span class="btn-icon-start text-success"><i
												class="fa fa-plus color-info"></i> </span>修改為已領養
										</button>
									</c:if>
									<c:if test="${AdoptPetVO.adopt_pet_state == 1}">
										<button type="submit"
											class="btn btn-rounded btn-outline-light">
											<span class="btn-icon-start text-light"><i
												class="fa fa-plus color-info"></i> </span>修改為未領養
										</button>
									</c:if>
								</form>


								<button type="button" class="btn btn-rounded btn-outline-info"
									data-bs-toggle="modal"
									data-bs-target="#modal${AdoptPetVO.adopt_pet_no}">
									<span class="btn-icon-start text-info"><i
										class="fa fa-upload color-success"></i> </span>新增寵物照片
								</button>

								<div class="modal fade bd-example-modal-lg"
									id="modal${AdoptPetVO.adopt_pet_no}" tabindex="-1"
									role="dialog" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">上傳新圖片</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>
											<%@ include file="addPhoto.jsp"%>
										</div>
									</div>
								</div>

								<form class="toServlet" method="post"
									action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">
									<input type="hidden" name="adopt_pet_no"
										value="${AdoptPetVO.adopt_pet_no}"> <input
										type="hidden" name="action" value="findByadoptPetNo">
									<input type="hidden" name="requestURL"
										value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="whichChip"
										value="<%=request.getParameter("whichChip")%>">
									<button type="submit"
										class="btn btn-rounded btn-outline-warning">
										<span class="btn-icon-start text-warning"><i
											class="fa fa-share-alt color-secondary"></i> </span>查看所有照片
									</button>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>