<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改密碼 </title>

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
		 <h3>修改密碼 </h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMember/select_page.jsp"">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/LoginServlet">
<table>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="account" size="16" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" /></td>
	</tr>
	<tr>
		<td>舊密碼:</td>
		<td><input type="TEXT" name="password" size="16"	value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" /></td>
	</tr>
	
	<tr>
		<td>新密碼:</td>
		<td><input type="TEXT" name="newpassword" size="16"	 /></td>
	</tr>
	
	<tr>
		<td>確認新密碼:</td>
		<td><input type="TEXT" name="newpassword1" size="16" /></td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update_password">
<input type="submit" value="送出">
</FORM>
</body>