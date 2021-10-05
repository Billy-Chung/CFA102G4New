<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotions.model.*"%>

<!-- 上傳圖片 -->
<%
	int count=1;
  PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO");
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
/* 	width: 450px; */
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
		 <h4><a href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp"><img src="<%=request.getContextPath()%>/back_end/promotions/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>活動名稱:</td>
		<td><input type="TEXT" name="promot_name" size="45" 
			 value="<%= (promotionsVO==null)? "盛大開幕期間限定大Fun送" : promotionsVO.getPromot_name()%>" /></td>
	</tr>
	<tr>
		<td>活動開始日期 (yyyy-MM-dd):</td>
		<td><input name="promot_date_start" id="f_promot_date_start" type="text"></td>
	</tr>
	<tr>
		<td>活動結束日期 (yyyy-MM-dd):</td>
		<td><input name="promot_date_end" id="f_promot_date_end" type="text"></td>
	</tr>
	<tr>
		<td>活動狀態:</td>
		<td>
		   	<input type="radio" name="promot_status" size="45" value="0" ${empty promotionsVO ? "checked":""}  ${promotionsVO.promot_status == "0" ? "checked":""} />啟動
			<input type="radio" name="promot_status" size="45" value="1" ${promotionsVO.promot_status == "1" ? "checked":""} />關閉
	    </td>
	</tr>
	
	<tr>
		<td>活動種類:</td>
		<td>
		<%  if (promotionsVO==null) {%>
			<input type="radio" name="promot_type" size="45" value="0" checked/>全館
			<input type="radio" name="promot_type" size="45" value="1"/>個別
		<% } else {%>
			<input type="radio" name="promot_type" size="45" value="0" <%=((promotionsVO.getPromot_type()).equals("0"))? "checked":"" %>/>全館	
			<input type="radio" name="promot_type" size="45" value="1" <%=((promotionsVO.getPromot_type()).equals("1"))? "checked":"" %>/>個別	
		 <% }%>		 
	</td>
	</tr>

	<tr>
		<td>折扣方式:</td>
		<td>
		<%  if(promotionsVO==null) {%>
		   <input type="radio" name="promot_discount_type" size="45" value="0" checked/>打折
		   <input type="radio" name="promot_discount_type" size="45" value="1"/>減價
		<% }else{%>
		   <input type="radio" name="promot_discount_type" size="45" value="0" <%=((promotionsVO.getPromot_discount_type()).equals("0"))? "checked":"" %>/>打折
		   <input type="radio" name="promot_discount_type" size="45" value="1" <%=((promotionsVO.getPromot_discount_type()).equals("1"))? "checked":"" %>/>減價
		<% }%>
	</td>
	</tr>

	<tr>
		<td>折扣值:</td>
		<td><input type="TEXT" name="promot_discount" size="45"
			 value="<%= (promotionsVO==null)? "88" : promotionsVO.getPromot_discount()%>" /></td>
	</tr>
	<tr>
		<td>活動描述:</td>
		<td><input type="TEXT" name="promot_comment" size="45"
			 value="<%= (promotionsVO==null)? "盛大開幕期間限定大Fun送" : promotionsVO.getPromot_comment()%>" /></td>
	</tr>
	<tr>
		<td height="200px">活動banner1</td>
		<td>
	        <input type="file" name="banner1" id="promo_banner1" onchange="PreviewImage(event, 'banner1')"  accept=".jpeg, .jpg">
		</td>
		<td>
			<c:if test="${empty photoMap['banner1'] }">
				<img id="banner1_Preview"  width="300px"/>			
			</c:if>
			<c:if test="${not empty photoMap['banner1'] }">
				<img id="banner1_Preview" src="${photoMap['banner1'].base64Str }" width="300px"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td height="200px">活動banner2</td>
		<td>
	        <input type="file" name="banner2" id="promo_banner2" onchange="PreviewImage(event, 'banner2')" accept=".jpeg, .jpg">
		</td>
<td>
			<c:if test="${empty photoMap['banner2'] }">
				<img id="banner2_Preview"  width="300px"/>			
			</c:if>
			<c:if test="${not empty photoMap['banner2'] }">
				<img id="banner2_Preview" src="${photoMap['banner2'].base64Str }" width="300px"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td height="200px">活動banner3</td>
		<td>
	        <input type="file" name="banner3" id="promo_banner3" onchange="PreviewImage(event, 'banner3')"  accept=".jpeg, .jpg">
		</td>
		<td>
			<c:if test="${empty photoMap['banner3'] }">
				<img id="banner3_Preview"  width="300px"/>			
			</c:if>
			<c:if test="${not empty photoMap['banner3'] }">
				<img id="banner3_Preview" src="${photoMap['banner3'].base64Str }" width="300px"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td height="200px">活動迎賓廣告</td>
		<td>
	        <input type="file" name="custPhoto" id="promo_custPhoto" onchange="PreviewImage(event, 'custPhoto')"  accept=".jpeg, .jpg">
		</td>
		<td>
			<c:if test="${empty photoMap['custPhoto'] }">
				<img id="custPhoto_Preview"  width="300px"/>			
			</c:if>
			<c:if test="${not empty photoMap['custPhoto'] }">
				<img id="custPhoto_Preview" src="${photoMap['custPhoto'].base64Str }" width="300px"/>
			</c:if>
		</td>
	</tr>
	<jsp:useBean id="promotionsSvc" scope="page" class="com.promotions.model.PromotionsService" />
</table>

<br>
<input type="hidden" name="promot_reduce" size="45" value="0"  />
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>


<script type="text/javascript">
	function PreviewImage(event, photoId) {
	    var output = document.getElementById(photoId+"_Preview");
	    output.src = URL.createObjectURL(event.target.files[0]);
	    output.onload = function() {
	      URL.revokeObjectURL(output.src) // free memory
	    }
  	};
</script>
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
        	   format:'Y-m-d',
               minDate:'-1970-01-01', // 去除今日(不含)之前
               value: '<%=promot_date_start%>', // value:   new Date(),
        	   onShow:function(){
        	    this.setOptions({
        	     maxDate:$('#f_promot_date_end').val()?$('#f_promot_date_end').val():false
        	    })
        	  },
        	  timepicker:false
        	 });
        	 
        	 $('#f_promot_date_end').datetimepicker({
        	   format:'Y-m-d',
        	   onShow:function(){
        	    this.setOptions({
        	    minDate:$('#f_promot_date_start').val()?$('#f_promot_date_start').val():false
        	   })
        	  },
        	  timepicker:false
        	 });
        });
 
</script>
</html>