<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.adoptPetPhoto.model.*"%>

<%
	Map<String, String> map = (Map<String, String>) request.getAttribute("adoptMemberPhotoMap");
	pageContext.setAttribute("map", map);
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
<link href="fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
<link href="petView.css" rel="stylesheet">
<script defer src="fontawesome-free-5.15.4-web/js/all.js"></script>
</head>


<c:forEach var="map" items="${map}">

	<div class="card pdct_card" style="width: 18rem;">
		<img src="data:image/jpg;base64,${map.value}">
		<div class="card-body">${map.key}</div>
	</div>
</c:forEach>



<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous">
	
</script>
</body>
</html>