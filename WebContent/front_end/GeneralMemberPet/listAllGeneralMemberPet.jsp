<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import ="java.util.*" %>
<%@page import = "com.generalMemberPet.model.*" %>

<%
	GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
	List<GeneralMemberPetVO> list = gmpSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ��@��|���d�����</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��@��|���d����� - listAllGeneralMemberPet.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>�@��|���d���s��</th>
		<th>��i�d���s��</th>
		<th>�@��|���s��</th>
		<th>�d���~��</th>
		<th>�ʧO</th>
		<th>�������X</th>
		<th>�O�_���|</th>
		<th>���</th>
		<th>�d���߱o</th>
		<th>�@��|���d�����A</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="gmpVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
	
	<tr>
		<td>${gmpVO.gen_meb_pet_no}</td>
		<td>${gmpVO.adopt_pet_no}</td>
		<td>${gmpVO.gen_meb_no}</td>
		<td>${gmpVO.gen_meb_pet_breeds}</td>
		<td>${gmpVO.gen_meb_pet_gender}</td>
		<td>${gmpVO.gen_meb_pet_chip}</td>
		<td>${gmpVO.gen_meb_pet_sterilization}</td>
		<td>${gmpVO.gen_pet_color}</td>
		<td>${gmVO.gen_pet_comment}</td>
		<td>${gmVO.gen_pet_state}</td>
		
		<td>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/gmp/gmp.do">
				<input type="submit" value="�ק�">
				<input type="hidden" name="gmpno" value="${gmpVO.gen_meb_pet_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</form>
		</td>
		<td>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/gmp/gmp.do">
				<input type="submit" value="�R��">
				<input type="hidden" name="gmpno" value="${gmVO.gen_meb_pet_no}">
				<input type="hidden" name="action" value="delete">
			</form>	
		</td>
			
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
	
</body>
</html>