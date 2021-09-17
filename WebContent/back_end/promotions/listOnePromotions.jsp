<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.promotions.model.*"%>

<%
  promotionsVO promotionsVO = (promotionsVO) request.getAttribute("promotionsVO"); //promotiosServlet.java(Concroller), 存入req的promotiosVO物件
%>

<html>
<head>
<title>行銷活動 - listOnePromostions.jsp</title>

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
		 <h3>員工資料 - ListOnePromotions.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp"><img src="<%=request.getContextPath()%>/back_end/promotions/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>	          
		<td><%=promotionsVO.getPromot_no()%></td>
		<td><%=promotionsVO.getPromot_name()%></td>
		<td><%=promotionsVO.getPromot_date_start()%></td>		
		<td><%=promotionsVO.getPromot_date_end()%></td>
		<td><%=promotionsVO.getPromot_status()%></td>
		<td><%=promotionsVO.getPromot_type()%></td>
		<td><%=promotionsVO.getPromot_discount_type()%></td>
		<td><%=promotionsVO.getPromot_discount()%></td>
		<td><%=promotionsVO.getPromot_reduce()%></td>
		<td><%=promotionsVO.getPromot_comment()%></td>
		<td><img src="<%=request.getContextPath()%>/DBGifReader5?promot_photo=${promotionsVO.promot_no}" width="120px"></td>
	</tr>
</table>

</body>
</html>