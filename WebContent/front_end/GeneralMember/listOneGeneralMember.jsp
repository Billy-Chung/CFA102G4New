<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.generalMember.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneGeneralMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMember/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員手機</th>
		<th>會員生日</th>
		<th>會員照片</th>
		<th>會員簡介</th>
		<th>會員地址</th>
		<th>EMAIL</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>性別</th>
		<th>現今帳戶餘額</th>
		<th>發文權限</th>
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