<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotions.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    promotionsService promotionsSvc = new promotionsService();
    List<promotionsVO> list = promotionsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ���P����- listAllPromotions.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ���P���� - listAllPromotions.jsp</h3>
		 <h4><a href="promotionsSelect_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<table>
	<tr>	
		<th>���ʽs��</th>
		<th>���ʦW��</th>
		<th>���ʶ}�l���</th>
		<th>���ʵ������</th>
		<th>���ʪ��A</th>
		<th>���ʺ���</th>
		<th>�馩�覡</th>
		<th>���</th>
		<th>���</th>
		<th>���ʴy�z</th>
		<th>���ʹϤ�</th>
		<th>�ק�</th>
		<th>�R��</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="promotionsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${promotionsVO.promot_no}</td>
			<td>${promotionsVO.promot_name}</td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_start}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><fmt:formatDate value="${promotionsVO.promot_date_end}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${promotionsVO.promot_status}</td>
			<td>${promotionsVO.promot_type}</td>
			<td>${promotionsVO.promot_discount_type}</td>
			<td>${promotionsVO.promot_discount}</td>
			<td>${promotionsVO.promot_reduce}</td>
			<td>${promotionsVO.promot_comment}</td>
			<td><img src="<%=request.getContextPath()%>/DBGifReader5?promot_photo=${promotionsVO.promot_no}" width="120px"></td>
			                    
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="promot_no"  value="${promotionsVO.promot_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="empno"  value="${promotionsVO.promot_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>