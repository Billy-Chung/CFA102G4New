<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.promotions.model.*"%>

<%
	PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO"); //promotiosServlet.java(Concroller), 存入req的promotiosVO物件
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


<table id="table-1">
	<tr><td>
		 <h3>行銷活動 - ListOnePromotions.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp"><img src="<%=request.getContextPath()%>/back_end/promotions/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動編號</th><td><%=promotionsVO.getPromot_no()%></td>
		
	</tr>
	<tr>	          
		<th>活動名稱</th>
		<td><%=promotionsVO.getPromot_name()%></td>
	</tr>
	<tr>
		<th>活動開始日期</th><td><%=promotionsVO.getPromot_date_start()%></td>
	</tr>
	<tr>
		<th>活動結束日期</th><td><%=promotionsVO.getPromot_date_end()%></td>
	</tr>
	<tr>
		<th>活動狀態</th>
		<td><%=promotionsVO.getPromot_status().equals("0")?"啟動":"關閉"%></td>
	</tr>
	<tr>
		<th>活動種類</th>
		<td><%=promotionsVO.getPromot_type().equals("0")?"全館":"個別"%></td>
	</tr>
	<tr>
		<th>折扣方式</th>	
		<td><%=promotionsVO.getPromot_discount_type().equals("0")?"打折":"減價"%></td>
	</tr>
	<tr>
		<th>折扣值</th>		
		<td>
			<%=promotionsVO.getPromot_discount_type().equals("0")?"原價":"減價"%>
			<%=promotionsVO.getPromot_discount()%>
			<%=promotionsVO.getPromot_discount_type().equals("0")?"%":"元"%>
		</td>
	</tr>
	<tr>
		<th>活動描述</th>		
		<td><%=promotionsVO.getPromot_comment()%></td>
	</tr>
	<tr>
		<th>
			活動Banner1
		</th>		
		<td>
			<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner1" width="120px">
		</td>
	</tr>
		<tr>
		<th>
			活動Banner2
		</th>		
		<td>
			<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner2" width="120px">
		</td>
	</tr>
		<tr>
		<th>
			活動Banner3
		</th>		
		<td>
			<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner3" width="120px">
		</td>
	</tr>
		<tr>
		<th>
			活動custPhoto
		</th>		
		<td>
			<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=custPhoto" width="120px">
		</td>
	</tr>
</table>

</body>
</html>