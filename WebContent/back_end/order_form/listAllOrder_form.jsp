<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order_form.model.*"%>

<%
	Order_formService order_formSvc = new Order_formService();
    List<OrderFormVO> list = order_formSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>所有訂單- listAllOrder_form.jsp</title>

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
		 <h3>訂單管理</h3>
		 										   
		 <h4><a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp"><img src="<%=request.getContextPath()%>/back_end/order_form/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>訂單編號</th>
		<th>一般會員編號</th>
		<th>結帳金額</th>
		<th>收件者</th>
		<th>連絡電話</th>
		<th>配送地址</th>
		<th>訂單產生時間</th>
		<th>訂單狀態</th>
		<th>訂單明細</th>
<!-- 		<th>修改</th> -->
<!-- 		<th>取消</th> -->
	</tr>

	<%@ include file="page1.file" %> 
	<c:forEach var="order_formVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${order_formVO.order_no}</td>
			<td>${order_formVO.gen_meb_no}</td>
			<td>$${order_formVO.order_amount}</td>
			<td>${order_formVO.order_name}</td>
			
			<td>${order_formVO.getOrder_phone()}</td>
			<td>${order_formVO.delivery_address}</td>
			<td><fmt:formatDate value="${order_formVO.order_time}" pattern="yyyy-MM-dd" /></td>
			<td>${order_formVO.order_status}</td>
			
<!-- 			<td> -->
<!-- 			<select> -->
<!--     			<option>-1取消訂單</option> -->
<!--     			<option>0待出貨</option> -->
<!--     			<option>1已出貨</option> -->
<!--     			<option>2完成訂單</option> -->
<!-- 			</select> -->
<!-- 			</td> -->


			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/order_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查詢">
			     <input type="hidden" name="order_no"  value="${order_formVO.order_no}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/order_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="order_no"  value="${order_formVO.order_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
		
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/order_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="取消"> -->
<%-- 			     <input type="hidden" name="order_no"  value="${order_formVO.order_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="cancel"></FORM> -->
<!-- 			</td> -->
			
					
			
			
			
						
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>