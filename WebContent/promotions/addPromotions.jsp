<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotions.model.*"%>

<%
  promotionsVO promotionsVO = (promotionsVO) request.getAttribute("promotionsVO");           
%>


                                         
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行銷活動新增 - addPromotions.jsp</title>

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
		 <h3>行銷活動新增 - addPromotions.jsp</h3></td><td>
		 <h4><a href="promotionsSelect_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>活動新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/addpromotions.do" name="form1">
<table>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="promot_name" size="45" 
			 value="<%= (promotionsVO==null)? "盛大開幕，期間限定大Fun送" : promotionsVO.getPromot_name()%>" /></td>
	</tr>
	<tr>
		<td>活動開始日期:</td>
		<td><input name="promot_date_start" id="f_promot_date_start" type="text"></td>
	</tr>
	<tr>
		<td>活動結束日期:</td>
		<td><input name="promot_date_end" id="f_promot_date_end" type="text"></td>
	</tr>
	<tr>
		<td>活動狀態:</td>
		<td><input type="TEXT" name="promot_status" size="45"
			 value="<%= (promotionsVO==null)? "1" : promotionsVO.getPromot_status()%>" /></td>
	</tr>
	<tr>
		<td>活動種類:</td>
		<td><input type="TEXT" name="promot_type" size="45"
			 value="<%= (promotionsVO==null)? "0" : promotionsVO.getPromot_type()%>" /></td>
	</tr>
	<tr>
		<td>折扣方式:</td>
		<td><input type="TEXT" name="promot_discount_type" size="45"
			 value="<%= (promotionsVO==null)? "0" : promotionsVO.getPromot_type()%>" /></td>
	</tr>
	<tr>
		<td>折數:</td>
		<td><input type="TEXT" name="promot_discount" size="45"
			 value="<%= (promotionsVO==null)? "8.8" : promotionsVO.getPromot_type()%>" /></td>
	</tr>
	<tr>
		<td>減價:</td>
		<td><input type="TEXT" name="promot_reduce" size="45"
			 value="<%= (promotionsVO==null)? "" : promotionsVO.getPromot_type()%>" /></td>
	</tr>	
	<tr>
		<td>活動描述:</td>
		<td><input type="TEXT" name="promot_comment" size="45"
			 value="<%= (promotionsVO==null)? "寵一而忠開幕特別企劃活動" : promotionsVO.getPromot_type()%>" /></td>
	</tr>
	<tr>
		<td>活動圖片:</td>
		<td><input type="TEXT" name="promot_photo" size="45"
			 value="<%= (promotionsVO==null)? "100" : promotionsVO.getPromot_type()%>" /></td>
	</tr>
	
	
	<jsp:useBean id="promotionsSvc" scope="page" class="com.promotions.model.promotionsService" />
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date promot_date_start = null;//宣告
  try {
	   promot_date_start = promotionsVO.getPromot_date_start();//第一次是空值去捕捉今日日期
   } catch (Exception e) {
	   promot_date_start = new java.sql.Date(System.currentTimeMillis());//取值
   }
  
  java.sql.Date promot_date_end = null;//宣告
  try {
	   promot_date_start = promotionsVO.getPromot_date_end();//第一次是空值去捕捉今日日期
   } catch (Exception e) {
	   promot_date_end = new java.sql.Date(System.currentTimeMillis());//取值
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
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $(function(){
        	 $('#f_promot_date_start').datetimepicker({
        	   theme: '',              //theme: 'dark',
      	       timepicker:false,       //timepicker:true,
      	       step: 1,    
        	   format:'Y-m-d H:i',
        	   value: '<%=promot_date_start%>', // value:   new Date(),
        	   //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
               //startDate:	        '2017/07/10',  // 起始日
               minDate:           '-1970-01-01', // 去除今日(不含)之前
               //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
        	   onShow:function(){
        	    this.setOptions({
        	     maxDate:$('#f_promot_date_end').val()?$('#f_promot_date_end').val():false
        	    })
        	  },
        	  timepicker:true,
        	  step: 1
        	 });
        	 
        	 $('#f_promot_date_end').datetimepicker({
        	   theme: '',              //theme: 'dark',
      	       timepicker:false,       //timepicker:true,
      	       step: 1,    
        	   format:'Y-m-d H:i',
        	
        	   onShow:function(){
        	    this.setOptions({
        	    minDate:$('#f_promot_date_start').val()?$('#f_promot_date_start').val():false
        	   })
        	  },
        	  timepicker:true,
        	  step: 1
        	 });
        });
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
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

        
        //      2.以下為某一天之後的日期無法選擇
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


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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