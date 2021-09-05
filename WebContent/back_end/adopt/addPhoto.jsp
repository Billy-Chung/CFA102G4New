<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty errorMsgs}">
	<c:if test="${errorMsgs.containsKey('errorPhoto')}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message.value}</li>
			</c:forEach>

		</ul>
	</c:if>
</c:if>

<FORM action="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do"
	method="post" enctype="multipart/form-data">

	<input type="hidden" name="adopt_pet_no" value="${AdoptPetVO.adopt_pet_no}">

	<div class="input-group mb-3">
		<span class="input-group-text">上傳新圖片!!</span>
		<div class="form-file">
			<input type="file" class="form-file-input form-control" name="adopt_pet_photo">
		</div>
	</div>

	<input type="hidden" name="adopt_pet_cover_state" value="0"> <input
		type="hidden" name="action" value="addNewPetPhoto">

	<div class="modal-footer">
		<button type="submit" class="btn btn-danger light"
			data-bs-dismiss="modal">關閉</button>
		<button type="submit" class="btn light btn-secondary">新增圖片</button>
	</div>
</FORM>
