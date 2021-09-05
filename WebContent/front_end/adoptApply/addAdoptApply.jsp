<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adoptApply.model.*"%>

<%
  adoptApplyVO adoptApplyVO = (adoptApplyVO) request.getAttribute("adoptApplyVO");           
%>
                                       
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��i�ӽзs�W - addAdoptApply.jsp</title>

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
		 <h3>��i�ӽзs�W - addAdoptApply.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="  " width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��i�ӽзs�W:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adoptApply/addAdoptApply.do" name="form1">
<table>
	
	<tr>
		<td>��i���c�|���s��:</td>
		<td><input type="TEXT" name="adopt_meb_no" size="45"
			 value="<%= (adoptApplyVO==null)? "" : adoptApplyVO.getAdopt_meb_no()%>" /></td>
	</tr>	
	<tr>
		<td>�@��|���s��:</td>
		<td><input type="TEXT" name="gen_meb_no" size="45"
			 value="<%= (adoptApplyVO==null)? "" : adoptApplyVO.getGen_meb_no()%>" /></td>
	</tr>	
	<tr>
		<td>��i�d���s��:</td>
		<td><input type="TEXT" name="adopt_pet_no" size="45"
			 value="<%= (adoptApplyVO==null)? "" : adoptApplyVO.getAdopt_pet_no()%>" /></td>
	</tr>	
	<tr>
		<td>�f�֪��A:</td>
		<td><input type="TEXT" name="adopt_audit_state" size="45"
			 value="<%= (adoptApplyVO==null)? "1" : adoptApplyVO.getAdopt_audit_state()%>" /></td>
	</tr>
	<tr>
		<td>�ӽФH�����Ҧr��:</td>
		<td><input type="TEXT" name="adopt_apply_people_id" size="45"
			 value="<%= (adoptApplyVO==null)? "0" : adoptApplyVO.getAdopt_apply_people_id()%>" /></td>
	</tr>
	<tr>
		<td>�ӽФ��:</td>
		<td><input name="adopt_apply_date" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>��i�ӽЪ��A:</td>
		<td><input type="TEXT" name="adopt_apply_state" size="45"
			 value="<%= (adoptApplyVO==null)? "8.8" : adoptApplyVO.getAdopt_apply_state()%>" /></td>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date adopt_apply_date = null;//�ŧi
  try {
	   adopt_apply_date = adoptApplyVO.getAdopt_apply_date();//�Ĥ@���O�ŭȥh����������
   } catch (Exception e) {
	   adopt_apply_date = new java.sql.Date(System.currentTimeMillis());//����
   }
  
  
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=adopt_apply_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>