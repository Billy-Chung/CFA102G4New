<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:forEach var="GeneralMemberPetVO" items="${list}" begin="<%=pageIndex%>"
	end="<%=pageIndex+rowsPerPage-1%>">


	<div class="col-lg-12 col-xl-6">
		<div class="card">
			<div class="card-body">
				<div class="row m-b-30">
					<div class="col-md-5 col-xxl-12">
						<div class="new-arrival-product mb-4 mb-xxl-4 mb-md-0">
							<div class="new-arrivals-img-contnent">
								<img class="img-fluid"
									src="<%=request.getContextPath()%>/gmpp/gmpp.do?action=cover&PK=${GeneralMemberPetVO.gen_meb_pet_no}"
									alt="">
							</div>
						</div>
					</div>
					<div class="col-md-7 col-xxl-12">
						<div class="new-arrival-content position-relative">
							<h2>${GeneralMemberPetVO.gen_meb_pet_breeds}</h2>
							
							<p>
								晶片號碼: <span class="item">${GeneralMemberPetVO.gen_meb_pet_chip}</span>
							</p>
							
							<p>
								性別: <span class="item">${GeneralMemberPetVO.gen_meb_pet_gender}</span>
							</p>

							<div class="rounded-button">

								<form class="toServlet" method="post"
									action="<%=request.getContextPath()%>/gmp/gmp.do">
									<input type="hidden" name="genMebPetNo"
										value="${GeneralMemberPetVO.gen_meb_pet_no}"> <input
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


								<button type="button" class="btn btn-rounded btn-outline-info"
									data-bs-toggle="modal"
									data-bs-target="#modal${GeneralMemberPetVO.gen_meb_pet_no}">
									<span class="btn-icon-start text-info"><i
										class="fa fa-upload color-success"></i> </span>新增寵物照片
								</button>

								<div class="modal fade bd-example-modal-lg"
									id="modal${GeneralMemberPetVO.gen_meb_pet_no}" tabindex="-1"
									role="dialog" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">上傳新圖片</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>
											<%@ include file="addGeneralMemberPetPhotos.jsp"%>
										</div>
									</div>
								</div>

								<form class="toServlet" method="post"
									action="<%=request.getContextPath()%>/gmpp/gmpp.do">
									<input type="hidden" name="genNo"
										value="${GeneralMemberPetVO.gen_meb_pet_no}"> <input
										type="hidden" name="action" value="findBygenNo">
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
