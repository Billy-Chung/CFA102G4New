<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>

<%
	List<AdoptPetPhotoVO> list = (List<AdoptPetPhotoVO>) request.getAttribute("adoptMemberPhotoList");
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="zh-Hans">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>上傳寵物資訊</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/front_end/adoptPet/fontawesome-free-5.15.4-web/css/all.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/front_end/adoptPet/petView.css"
	rel="stylesheet">
<script defer
	src="<%=request.getContextPath()%>/front_end/adoptPet/fontawesome-free-5.15.4-web/js/all.js"></script>
</head>


<c:forEach var="list" items="${list}">

	<div class="card pdct_card" style="width: 18rem;">
		<img
			src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=allPhoto&PK=${list.adopt_pet_photo_no}">
		<div class="card-body">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">
				<input type="hidden" name="adoptPetNo"
					value="${list.adopt_pet_photo_no}"> <input type="hidden"
					name="action" value="deletePhoto">
				<button type="submit" class="btn btn-outline-danger">刪除該照片</button>
			</FORM>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do">
				<input type="hidden" name="adoptPetNo"
					value="${list.adopt_pet_photo_no}"> <input type="hidden"
					name="adoptPetCoverState" value="${list.adopt_pet_cover_state}">

				<input type="hidden" name="action" value="update">
				<c:if test="${list.adopt_pet_cover_state == 0}">
					<button type="submit" class="btn btn-outline-success">修改為封面圖</button>
				</c:if>
				<c:if test="${list.adopt_pet_cover_state == 1}">
					<button type="submit" class="btn btn-outline-danger">移除封面圖</button>
				</c:if>

			</FORM>
		</div>
	</div>
</c:forEach>

<a href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">
	<button type="button" class="btn btn-outline-danger"
		data-bs-dismiss="modal">回首頁</button>
</a>



<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous">
	
</script>
</body>
</html>