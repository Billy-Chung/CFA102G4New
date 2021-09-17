<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�|����Ʒs�W - addGeneralMember.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�|����Ʒs�W - addGeneralMember.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front_end/GeneralMember/select_page.jsp""><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gm/gm.do" enctype="multipart/form-data">
<table>
	<tr>
		<td>�|���m�W:</td>
		<td><input type="TEXT" name="meb_name" size="45" value="<%=(gmVO==null) ? "" :gmVO.getMeb_name()%>" /></td>
	</tr>
	<tr>
		<td>�|�����:</td>
		<td><input type="TEXT" name="phone" size="45"	value="<%=(gmVO==null) ? "" :gmVO.getPhone()%>" /></td>
	</tr>
	
	<tr>
		<td>�|���ͤ�:</td>
		<td><input name="birthday" type="Date" value="<%=(gmVO==null) ? "" : gmVO.getBirthday() %>" /></td>
	</tr>
	<tr>
		<td>�|���Ӥ�:</td>
		<td><input type="file" name="photo" /></td>
	</tr>
	<tr>
		<td>�|��²��:</td>
		<td><input type="TEXT" name="comment" size="45" value="<%=(gmVO==null) ? "" :gmVO.getComment()%>" /></td>
	</tr>
	
	<tr>
		<td>�|���a�}:</td>
		<td><input type="TEXT" name="address" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAddress()%>" /></td>
	</tr>
	
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email" size="45" value="<%=(gmVO==null) ? "" :gmVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>�b��:</td>
		<td><input type="TEXT" name="account" size="45" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" /></td>
	</tr>
	
	<tr>
		<td>�K�X:</td>
		<td><input type="TEXT" name="password" size="45" value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" /></td>
	</tr>
	<tr>
		<td>�|���ʧO:</td>
		<td><input type="TEXT" name="gender" size="45" value="<%=(gmVO==null) ? "" :gmVO.getGender()%>" /></td>
	</tr>
	
	<tr>
		<td>�{���b��l�B:</td>
		<td><input type="TEXT" name="money" size="45" value="<%=(gmVO==null) ? "" :gmVO.getMeb_money()%>" /></td>
	</tr>
	
	<tr>
		<td>�o���v��:</td>
		<td><input type="TEXT" name="post_permission" size="45" value="<%=(gmVO==null)?"":gmVO.getPost_permission()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%-- <%-- <%  --%> --%>
<!-- //   java.sql.Date birthday = null; -->
<!-- //   try { -->
<!-- // 	    birthday = gmVO.getBirthday(); -->
<!-- //    } catch (Exception e) { -->
<!-- // 	   birthday = new java.sql.Date(System.currentTimeMillis()); -->
<!-- //    } -->
<%-- <%-- %> --%> --%>
<%-- <%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%> --%>
<%-- <%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%> --%>
<%-- <%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%> --%>

<!-- <!-- <style> --> -->
<!-- /*   .xdsoft_datetimepicker .xdsoft_datepicker { */ -->
<!-- /*            width:  300px;   /* width:  300px; */ */ -->
<!-- /*   } */ -->
<!-- /*   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */ -->
<!-- /*            height: 151px;   /* height:  151px; */ */ -->
<!-- /*   } */ -->
<!-- <!-- </style> --> -->

<!-- <!-- <script> --> -->
<!-- //         $.datetimepicker.setLocale('zh'); -->
<!-- //         $('#f_date1').datetimepicker({ -->
<!-- // 	       theme: '',              //theme: 'dark', -->
<!-- // 	       timepicker:false,       //timepicker:true, -->
<!-- // 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����) -->
<!-- // 	       format:'Y-m-d',         //format:'Y-m-d H:i:s', -->
<%-- <%-- 		   value: '<%=birthday%>', // value:   new Date(), --%> --%>
<!-- //            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t -->
<!-- //            //startDate:	            '2017/07/10',  // �_�l�� -->
<!-- //            //minDate:               '-1970-01-01', // �h������(���t)���e -->
<!-- //            //maxDate:               '+1970-01-01'  // �h������(���t)���� -->
<!-- //         }); -->
        
        
   
<!--         // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����----------------------------------------------------------- -->

<!--         //      1.�H�U���Y�@�Ѥ��e������L�k��� -->
<!--         //      var somedate1 = new Date('2017-06-15'); -->
<!--         //      $('#f_date1').datetimepicker({ -->
<!--         //          beforeShowDay: function(date) { -->
<!--         //        	  if (  date.getYear() <  somedate1.getYear() ||  -->
<!--         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||  -->
<!--         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate()) -->
<!--         //              ) { -->
<!--         //                   return [false, ""] -->
<!--         //              } -->
<!--         //              return [true, ""]; -->
<!--         //      }}); -->

        
<!--         //      2.�H�U���Y�@�Ѥ��᪺����L�k��� -->
<!--         //      var somedate2 = new Date('2017-06-15'); -->
<!--         //      $('#f_date1').datetimepicker({ -->
<!--         //          beforeShowDay: function(date) { -->
<!--         //        	  if (  date.getYear() >  somedate2.getYear() ||  -->
<!--         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||  -->
<!--         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate()) -->
<!--         //              ) { -->
<!--         //                   return [false, ""] -->
<!--         //              } -->
<!--         //              return [true, ""]; -->
<!--         //      }}); -->


<!--         //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���) -->
<!--         //      var somedate1 = new Date('2017-06-15'); -->
<!--         //      var somedate2 = new Date('2017-06-25'); -->
<!--         //      $('#f_date1').datetimepicker({ -->
<!--         //          beforeShowDay: function(date) { -->
<!--         //        	  if (  date.getYear() <  somedate1.getYear() ||  -->
<!--         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||  -->
<!--         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate()) -->
<!--         //		             || -->
<!--         //		            date.getYear() >  somedate2.getYear() ||  -->
<!--         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||  -->
<!--         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate()) -->
<!--         //              ) { -->
<!--         //                   return [false, ""] -->
<!--         //              } -->
<!--         //              return [true, ""]; -->
<!--         //      }}); -->
        
</script>
</html>