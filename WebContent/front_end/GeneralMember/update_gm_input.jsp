<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%-- <%=empVO=null %> --${empVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改會員資料 - update_generalmember_input.jsp</title>

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
		 <h3>修改會員資料 - update_generalmember_input.jsp</h3>

	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=gmVO.getGer_meb_no()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="meb_name" size="10" value="<%=gmVO.getMeb_name()%>" /></td>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><input type="TEXT" name="phone" size="10"	value="<%=gmVO.getPhone()%>" /></td>
	</tr>
	
	<tr>
		<td>會員生日:</td>
		<td><input  name="birthday" type="Date" value="<%=gmVO.getBirthday() %>" /></td>
	</tr>
	<tr>
		<td>會員照片:</td>
		<td><input type="file" name="photo" /></td>
	</tr>
	<tr>
		<td>會員簡介:</td>
		<td><input type="TEXT" name="comment" size="65" value="<%=gmVO.getComment()%>" /></td>
	</tr>
	
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="address" size="65" value="<%=gmVO.getAddress()%>" /></td>
	</tr>
	
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="30" value="<%=gmVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="account" size="16" value="<%=gmVO.getAccount()%>" /></td>
	</tr>
	
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="password" size="16" value="<%=gmVO.getPassword()%>" /></td>
	</tr>
	<tr>
		<td>會員性別:</td>
		<td><input type="TEXT" name="gender" value="<%=gmVO.getGender()%>" /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="gmno" value="<%=gmVO.getGer_meb_no()%>">
<input type="submit" value="修改完畢"></FORM>
</body>

</html>