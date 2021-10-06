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
<title>寵一而忠</title>
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
<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h4>
<h2><a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp">回首頁</a></h2>

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
		<th>取消訂單</th>
		<th>出貨</th>
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
			<td><fmt:formatDate value="${order_formVO.order_time}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<td>${order_formVO.getOrderStatusStr()}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/order_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查詢">
			     <input type="hidden" name="order_no"  value="${order_formVO.order_no}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			
			
			<!-- -1取消訂單/0待出貨/1已出貨/2完成訂單 -->
			<td>
				<c:choose>
				    <c:when test="${order_formVO.orderStatusStr == '待出貨' }">
				        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_form/order_form.do" style="margin-bottom: 0px;">
					    	<input type="submit" value="取消訂單">
					     	<input type="hidden" name="order_no"  value="${order_formVO.order_no}">
					     	<input type="hidden" name="action"	value="cancelBackOrderForm">
					    </FORM>
				    </c:when>
	  		    
				    <c:when test="${order_formVO.orderStatusStr == '取消訂單' }">
				    	<button disabled="disabled">取消訂單</button>
				    </c:when>
					<c:otherwise>
					            商品已出貨不可取消
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
				    <c:when test="${order_formVO.orderStatusStr == '待出貨' }">
				        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_form/order_form.do" style="margin-bottom: 0px;">
					    	<input type="submit" value="出貨">
					     	<input type="hidden" name="order_no"  value="${order_formVO.order_no}">
					     	<input type="hidden" name="action"	value="deliverOrderForm">
					    </FORM>
				    </c:when>
	  		    
				    <c:when test="${order_formVO.orderStatusStr == '已出貨' }">
				    	<button disabled="disabled ">出貨</button>
				    	
				    	
				    </c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
			</td>		
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>
</body>
</html>