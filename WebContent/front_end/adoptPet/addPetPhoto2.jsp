<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<FORM action="addPetPhoto.do" method="post"
	enctype="multipart/form-data">

	<input type="hidden" name="adopt_pet_no"
		value="${AdoptPetVO.adopt_pet_no}">

	<div class="form-floating mb-3">
		<input type="file" class="form-control" name="adopt_pet_photo">
	</div>

	<input type="hidden" name="action" value="addNewPetPhoto">

	<div class="modal-footer">
		<button type="button" class="btn btn-outline-danger"
			data-bs-dismiss="modal">關閉</button>
		<button type="submit" class="btn btn-outline-success">新增</button>
	</div>
</FORM>
