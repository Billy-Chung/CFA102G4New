<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.promotions.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  promotionsVO promotionsVO = (promotionsVO) request.getAttribute("promotionsVO"); //promotiosServlet.java(Concroller), �s�Jreq��promotiosVO����
%>
 EmpVO empVO = (EmpVO) request.getAttribute("empVO")
<html>
<head>
<title>��P���� - listOnePromostions.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOnePromotions.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>	          
		<td><%=promotionsVO.getPromot_no()%></td>
		<td><%=promotionsVO.getPromot_name()%></td>
		<td><%=promotionsVO.getPromot_date_start()%></td>
		<td><%=promotionsVO.getPromot_status()%></td>	
		<td><%=promotionsVO.getPromot_date_end()%></td>		
		<td><%=promotionsVO.getPromot_type()%></td>
		<td><%=promotionsVO.getPromot_discount_type()%></td>
		<td><%=promotionsVO.getPromot_discount()%></td>
		<td><%=promotionsVO.getPromot_reduce()%></td>
		<td><%=promotionsVO.getPromot_comment()%></td>
		<td><%=promotionsVO.getPromot_photo()%></td>
	</tr>
</table>

</body>
</html>