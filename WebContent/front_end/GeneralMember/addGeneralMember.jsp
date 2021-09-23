<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|����Ʒs�W - addGeneralMember.jsp</title>

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
		 <h3>�|�����U </h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMember/select_page.jsp""><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>


<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>�|���m�W:</td>
		<td><input type="TEXT" name="meb_name" size="10" value="<%=(gmVO==null) ? "" :gmVO.getMeb_name()%>" /></td>
	</tr>
	<tr>
		<td>�|�����:</td>
		<td><input type="TEXT" name="phone" size="10"	value="<%=(gmVO==null) ? "" :gmVO.getPhone()%>" /></td>
	</tr>
	
	<tr>
		<td>�|���ͤ�:</td>
		<td><input name="birthday" type="Date" value="<%=(gmVO==null) ? "" : gmVO.getBirthday() %>" /></td>
	</tr>
	<tr>
		<td>�|���Ӥ�:</td>
		<td><input type="file" name="photo" /></td>
	</tr>
	<tr>
		<td>�|��²��:</td>
		<td><input type="TEXT" name="comment" size="65" value="<%=(gmVO==null) ? "" :gmVO.getComment()%>" /></td>
	</tr>
	
	<tr>
		<td>�|���a�}:</td>
		<td><input type="TEXT" name="address" size="65" value="<%=(gmVO==null) ? "" :gmVO.getAddress()%>" /></td>
	</tr>
	
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="30" value="<%=(gmVO==null) ? "" :gmVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>�b��:</td>
		<td><input type="TEXT" name="account" size="16" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" /></td>
	</tr>
	
	<tr>
		<td>�K�X:</td>
		<td><input type="Text" name="password" size="16" value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" /></td>
	</tr>
	<tr>
		<td>�|���ʧO:</td>
		<td><input type="radio" name="gender" value="1" checked />�k
			<input type="radio" name="gender" value="0" />�k
		</td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>