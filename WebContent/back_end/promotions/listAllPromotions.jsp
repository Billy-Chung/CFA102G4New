<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotions.model.*"%>


<%
	PromotionsService promotionsSvc = new PromotionsService();
    List<promotionsVO> list = promotionsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有行銷活動- listAllPromotions.jsp</title>

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
	width: 1500px;
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
		 <h3>所有行銷活動 - listAllPromotions.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp"><img src="<%=request.getContextPath()%>/back_end/promotions/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<table>
	<tr>	
		<th>活動編號</th>
		<th>活動名稱</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動狀態</th>
		<th>活動種類</th>
		<th>折扣方式</th>
		<th>折數</th>
		<th>減價</th>
		<th>活動描述</th>
		<th>活動圖片</th>
		<th>修改</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="promotionsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${promotionsVO.promot_no}</td>
			<td>${promotionsVO.promot_name}</td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_start}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_end}" pattern="yyyy-MM-dd" /></td>
			<td>${promotionsVO.promot_status}</td>
			<td>${promotionsVO.promot_type}</td>
			<td>${promotionsVO.promot_discount_type}</td>
			<td>${promotionsVO.promot_discount}</td>
			<td>${promotionsVO.promot_reduce}</td>
			<td>${promotionsVO.promot_comment}</td>
			<td><img src="<%=request.getContextPath()%>/DBGifReader5?promot_photo=${promotionsVO.promot_no}" width="120px"></td>
			                    
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" style="margin-bottom: 0px;"><%--路徑位置從back_end第一層算起--%>
			     <input type="submit" value="修改">
			     <input type="hidden" name="promot_no"  value="${promotionsVO.promot_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>