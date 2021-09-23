<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMemberPet.model.*"%>

<%
  GeneralMemberPetVO gmpVO = (GeneralMemberPetVO) request.getAttribute("gmpVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增 - addGeneralMember.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>加入一般會員寵物 </h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/select_page.jsp""><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gmp/gmp.do">
<table>
	
	<tr>
		<td>一般會員編號:</td>
		<td><input type="Text" name="gmno" value="<%=(gmpVO==null) ? "" : gmpVO.getGen_meb_no() %>" /></td>
	</tr>
	<tr>
		<td>寵物品種:</td>
		<td><input type="Text" name="gmpb" size="10" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_meb_pet_breeds()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="gender" size="1" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_meb_pet_gender()%>" /></td>
	</tr>
	
	<tr>
		<td>晶片號碼:</td>
		<td><input type="TEXT" name="petchip" size="15" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_meb_pet_chip()%>" /></td>
	</tr>
	
	<tr>
		<td>是否絕育:</td>
		<td><input type="TEXT" name="petsteril" size="1" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_meb_pet_sterilization()%>" /></td>
	</tr>
	
	<tr>
		<td>毛色:</td>
		<td><input type="TEXT" name="petcolor" size="10" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_pet_color()%>" /></td>
	</tr>
	
	<tr>
		<td>寵物心得:</td>
		<td><input type="Text" name="petcomment" size="65" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_pet_comment()%>" /></td>
	</tr>
	<tr>
		<td>一般會員寵物狀態:</td>
		<td><input type="Text" name="petstate" size="1" value="<%=(gmpVO==null) ? "" :gmpVO.getGen_pet_state()%>"/></td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>