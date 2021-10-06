<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotions.model.*"%>


<%
	PromotionsService promotionsSvc = new PromotionsService();
    List<PromotionsVO> list = promotionsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有行銷活動- listAllPromotions.jsp</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />
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
/* 	width: 1500px; */
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

<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h2>
<h2><a href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp">回首頁</a></h1>


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
		<th>折扣值</th>
		<th>活動描述</th>
		<th>修改</th>
		<th>查看活動折扣商品</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="promotionsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
		<tr>
			<td>${promotionsVO.promot_no}</td>
			<td>${promotionsVO.promot_name}</td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_start}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_end}" pattern="yyyy-MM-dd" /></td>
			<td>${promotionsVO.promot_status eq 0?"啟動":"關閉"}</td>
			<td>${promotionsVO.promot_type eq 0?"全館":"個別"}</td>
			<td>${promotionsVO.promot_discount_type eq 0?"打折":"減價"}</td>
			<td>${promotionsVO.promot_discount}</td>
			<td>${promotionsVO.promot_comment}</td>
			                    
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" style="margin-bottom: 0px;"><%--路徑位置從back_end第一層算起--%>
			     <input type="submit" value="修改">
			     <input type="hidden" name="promot_no"  value="${promotionsVO.promot_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductPromoDetailServlet" style="margin-bottom: 0px;"><%--路徑位置從back_end第一層算起--%>
				    <input type="submit" value="商品清單">
				    <input type="hidden" name="promot_no"  value="${promotionsVO.promot_no}">
				    <input type="hidden" name="action"	value="listProductPromos">
				</FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>