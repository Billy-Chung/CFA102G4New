<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotions.model.*"%>

<%
	int count = 1;
	promotionsVO promotionsVO = (promotionsVO) request.getAttribute("promotionsVO"); //PromotionsServlet.java (Concroller) 存入req的promotionsVO物件 (包括幫忙取出的promotionsVO, 也包括輸入資料錯誤時的promotionsVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>行銷活動修改 - update_promotions_input.jsp</title>

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
		<tr>
			<td>
				<h3>行銷活動修改 - update_promotions_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/promotions/promotionsSelect_page.jsp"><img
						src="<%=request.getContextPath()%>/back_end/promotions/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>行銷活動修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/promotions/promotions.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>活動編號:<font color=red><b>*</b></font></td>
				<td><%=promotionsVO.getPromot_no()%></td>
			</tr>
			<tr>
				<td>活動名稱:</td>
				<td><input type="TEXT" name="promot_name" size="45"
					value="<%=promotionsVO.getPromot_name()%>" /></td>
			</tr>
			<tr>
				<td>活動開始日期 (yyyy-MM-dd):</td>
				<td><input type="text" name="promot_date_start"
					id="f_promot_date_start"></td>
			</tr>
			<tr>
				<td>活動結束日期 (yyyy-MM-dd):</td>
				<td><input type="text" name="promot_date_end"
					id="f_promot_date_end"></td>
			</tr>
			<tr>
			<td>活動狀態:</td>
			<td>
		    	<input type="radio" name="promot_status" size="45" value="0" <%=((promotionsVO.getPromot_status()).equals("0"))? "checked":"" %>/>非活性
				<input type="radio" name="promot_status" size="45" value="1" <%=((promotionsVO.getPromot_status()).equals("1"))? "checked":"" %>/>活性
	    	</td>
			<tr>
		<td>活動種類:</td>
		<td>
			<input type="radio" name="promot_type" size="45" value="0" <%=((promotionsVO.getPromot_type()).equals("0"))? "checked":"" %>/>全館	
			<input type="radio" name="promot_type" size="45" value="1" <%=((promotionsVO.getPromot_type()).equals("1"))? "checked":"" %>/>個別		 
	</td>
	</tr>
			<tr>
		<td>折扣方式:</td>
		<td>
		   <input type="radio" name="promot_discount_type" size="45" value="0" <%=((promotionsVO.getPromot_discount_type()).equals("0"))? "checked":"" %>/>打折
		   <input type="radio" name="promot_discount_type" size="45" value="1" <%=((promotionsVO.getPromot_discount_type()).equals("1"))? "checked":"" %>/>減價
	</td>
	</tr>
			<tr>
				<td>折數%:</td>
				<td><input type="TEXT" name="promot_discount" size="45"
					value="<%=promotionsVO.getPromot_discount()%>" /></td>
			</tr>
			<tr>
				<td>減價:</td>
				<td><input type="TEXT" name="promot_reduce" size="45"
					value="<%=promotionsVO.getPromot_reduce()%>" /></td>
			</tr>
			<tr>
				<td>活動描述:</td>
				<td><input type="TEXT" name="promot_comment" size="45"
					value="<%=promotionsVO.getPromot_comment()%>" /></td>
			</tr>
			<td>活動圖片:</td>
			<td><!-- 上傳圖片 --> <%
 	for (int i = 1; i <= count; i++) {
 %> <br> <input type="file" name="promot_photo"
				onchange="PreviewImage<%=i%>(this)"> <%--      <input type="file" name="promot_photo<%=i%>" onchange="PreviewImage<%=i%>(this)"> --%>
				<%
					}
				%>
			</td>


			<jsp:useBean id="promotionsSvc" scope="page"
				class="com.promotions.model.PromotionsService" />
		</table>

		<table>
			<tr>
				<%
					for (int i = 1; i <= count; i++) {
				%>
				<td><div id="imgPreview<%=i%>"
						style="width: 133px; height: 100px; overflow: hidden;"></div></td>
				<%
					}
				%>
			</tr>
		</table>


		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="promot_no"
			value="<%=promotionsVO.getPromot_no()%>"> <input
			type="submit" value="送出修改">
	</FORM>
	<script type="text/javascript">
	<%for (int i = 1; i <= count; i++) {%>
  		function PreviewImage<%=i%>(imgFile) {
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
		alert("只支援jpg/jpeg/png/gif/bmp之格式檔案");
		imgFile.focus();
	} else {
		var path;
		if (document.all) { // IE
			imgFile.select();
			imgFile.blur();
			path = document.selection.createRange().text;
			document.getElementById("imgPreview<%=i%>").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""+ path + "\")";// 濾鏡
		} else { // FF 或 Chrome 等
			path = URL.createObjectURL(imgFile.files[0]);
			document.getElementById("imgPreview<%=i%>").innerHTML = "<img src='"+ path +"'  width='143' height='100'/>";
		}
	}
   }
<%}%>
</script>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $(function(){
        	 $('#f_promot_date_start').datetimepicker({
        	   format:'Y-m-d',
               minDate: '-1970-01-01', // 去除今日(不含)之前
               value: '<%=promotionsVO.getPromot_date_start()%>', // value:   new Date(),
        	   onShow:function(){
        	    this.setOptions({
        	     maxDate:$('#f_promot_date_end').val()?$('#f_promot_date_end').val():false
        	    })
        	  },
        	  timepicker:false,
        	 });
        	 
		
        	 $('#f_promot_date_end').datetimepicker({
        	   format:'Y-m-d',
        	   value: '<%=promotionsVO.getPromot_date_end()%>',
        	   onShow:function(){
        	    this.setOptions({
        	    minDate:$('#f_promot_date_start').val()?$('#f_promot_date_start').val():false
        	   })
        	  },
        	  timepicker:false,
        	 });
        });
 
</script>
</html>