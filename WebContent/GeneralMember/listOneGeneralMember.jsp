<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.generalMember.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|�����</th>
		<th>�|���ͤ�</th>
		<th>�|���Ӥ�</th>
		<th>�|��²��</th>
		<th>�|���a�}</th>
		<th>EMAIL</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�ʧO</th>
		<th>�{���b��l�B</th>
		<th>�o���v��</th>
	</tr>
	<tr>
		<td>${gmVO.ger_meb_no}</td>
		<td>${gmVO.meb_name}</td>
		<td>${gmVO.phone}</td>
		<td>${gmVO.birthday}</td>
		<td>${gmVO.photo}</td>
		<td>${gmVO.comment}</td>
		<td>${gmVO.address}</td>
		<td>${gmVO.email}</td>
		<td>${gmVO.account}</td>
		<td>${gmVO.password}</td>
		<td>${gmVO.gender}</td>
		<td>${gmVO.meb_money}</td>
		<td>${gmVO.post_permission}</td>
	</tr>
</table>

</body>
</html>