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
<title>所有會員資料</title>
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
		 <h3>所有會員資料 - listAllGeneralMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>會員編號</th>
		<th>姓名</th>
		<th>手機</th>
		<th>生日</th>
		<th>照片</th>
		<th>簡介</th>
		<th>地址</th>
		<th>EMAIL</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>性別</th>
		<th>帳戶餘額</th>
		<th>發文權限</th>
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
				<input type="submit" value="修改">
				<input type="hidden" name="gmno" value="${gmVO.ger_meb_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</form>
		</td>
		<td>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do">
				<input type="submit" value="刪除">
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