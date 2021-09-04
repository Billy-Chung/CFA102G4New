<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import ="java.util.*" %>
<%@page import = "com.generalMember.model.*" %>

<%
	GeneralMemberService gmSvc = new GeneralMemberService();
	List<GeneralMemberVO> list = gmSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ��|�����</title>
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
		 <h3>�Ҧ��|����� - listAllGeneralMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>�|���s��</th>
		<th>�m�W</th>
		<th>���</th>
		<th>�ͤ�</th>
		<th>�Ӥ�</th>
		<th>²��</th>
		<th>�a�}</th>
		<th>EMAIL</th>
		<th>�b��</th>
		<th>�K�X</th>
		<th>�ʧO</th>
		<th>�b��l�B</th>
		<th>�o���v��</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="gmVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
	
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
		
		<td>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do">
				<input type="submit" value="�ק�">
				<input type="hidden" name="gmno" value="${gmVO.ger_meb_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</form>
		</td>
		<td>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do">
				<input type="submit" value="�R��">
				<input type="hidden" name="gmno" value="${gmVO.ger_meb_no}">
				<input type="hidden" name="action" value="delete">
			</form>	
		</td>
			
	</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
	
</body>
</html>