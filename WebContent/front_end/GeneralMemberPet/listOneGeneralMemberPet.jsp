<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.generalMemberPet.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  GeneralMemberPetVO gmpVO = (GeneralMemberPetVO) request.getAttribute("gmpVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�@��|���d����� - listOneEmp.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�@��|���d����� - ListOneGeneralMember.jsp</h3>
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
	</tr>
</table>

</body>
</html>