<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="card ">
	<img id="isNewPhoto${AdoptPetVO.adopt_pet_no}"
		class="card-img-top img-fluid imgSize">
	<div class="card-body">
		<FORM action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do"
			method="post" enctype="multipart/form-data">

			<input type="hidden" name="adopt_pet_no"
				value="${AdoptPetVO.adopt_pet_no}">
			<div class="input-group mb-3">
				<span class="input-group-text">上傳新圖片!!</span>
				<div class="form-file">
					<input id="isNew${AdoptPetVO.adopt_pet_no}" type="file"
						class="form-file-input form-control" name="adopt_pet_photo">
				</div>
			</div>

			<input type="hidden" name="adopt_pet_cover_state" value="0">
			<input type="hidden" name="action" value="addNewPetPhoto"> <input
				type="hidden" name="requestURL"
				value="<%=request.getServletPath()%>">
			<!--送出本網頁的路徑給Controller-->
			<input type="hidden" name="whichPage" value="<%=whichPage%>">
			<!--送出當前是第幾頁給Controller-->
			<input type="hidden" name="whichChip"
				value="<%=request.getParameter("whichChip")%>">
			<div class="modal-footer ">
				<button type="submit" class="btn btn-danger light"
					data-bs-dismiss="modal">關閉</button>
				<button type="submit" class="btn light btn-secondary ">新增圖片</button>
			</div>
		</FORM>
	</div>
</div>

<script>
	$(`#isNew${AdoptPetVO.adopt_pet_no}`).on(
			'change',
			function(e) {
				const file = this.files[0];

				const fr = new FileReader();
				fr.onload = function(e) {
					$(`#isNewPhoto${AdoptPetVO.adopt_pet_no}`).attr('src',
							e.target.result);
				};

				// 使用 readAsDataURL 將圖片轉成 Base64
				fr.readAsDataURL(file);
			});
</script>









