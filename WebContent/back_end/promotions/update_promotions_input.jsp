<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotions.model.*"%>

<%
	int count = 1;
	PromotionsVO promotionsVO = (PromotionsVO) request.getAttribute("promotionsVO"); //PromotionsServlet.java (Concroller) 存入req的promotionsVO物件 (包括幫忙取出的promotionsVO, 也包括輸入資料錯誤時的promotionsVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h2>
<h2><a href="<%=request.getContextPath()%>/back_end/promotions/listAllPromotions.jsp">回全部行銷活動</a></h1>
	

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
				<td>活動開始日期 :</td>
				<td><input type="text" name="promot_date_start"
					id="f_promot_date_start"></td>
			</tr>
			<tr>
				<td>活動結束日期 :</td>
				<td><input type="text" name="promot_date_end"
					id="f_promot_date_end"></td>
			</tr>
			<tr>
			<td>活動狀態:</td>
			<td>
		    	<input type="radio" name="promot_status" size="45" value="0" <%=((promotionsVO.getPromot_status()).equals("0"))? "checked":"" %>/>啟動
				<input type="radio" name="promot_status" size="45" value="1" <%=((promotionsVO.getPromot_status()).equals("1"))? "checked":"" %>/>關閉
	    	</td>
			<tr>
		<td>活動種類:</td>
		<td>
			<%=((promotionsVO.getPromot_type()).equals("0"))? "全館":"個別" %>	
		</td>
	</tr>
			<tr>
		<td>折扣方式:</td>
		<td>
			<%=promotionsVO.getPromot_discount_type().equals("0")? "打折":"減價" %>
		</td>
	</tr>
		<tr>
			<td>折扣數值:</td>
			<td>
				<%=promotionsVO.getPromot_discount_type().equals("0")? "打折":"減價" %>
				<%=promotionsVO.getPromot_discount()%>
				<%=promotionsVO.getPromot_discount_type().equals("0")? "%":"元" %>
			</td>
		</tr>
			<tr>
				<td>活動描述:</td>
				<td><input type="TEXT" name="promot_comment" size="45"
					value="<%=promotionsVO.getPromot_comment()%>" /></td>
			</tr>
			<tr>
				<td height="200px">活動banner1</td>
				<td>
			        <input type="file" name="banner1" id="promo_banner1" onchange="PreviewImage(event, 'banner1')"  accept=".jpeg, .jpg">
				</td>
				<td>
					<c:if test="${empty photoMap['banner1'] }">
						<img id="banner1_Preview" src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner1" width="300px">	
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
						<img id="banner2_Preview" src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner2" width="300px">	
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
						<img id="banner3_Preview" src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=banner3" width="300px">	
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
						<img id="custPhoto_Preview" src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionsVO.promot_no}&functionName=custPhoto" width="300px">			
					</c:if>
					<c:if test="${not empty photoMap['custPhoto'] }">
						<img id="custPhoto_Preview" src="${photoMap['custPhoto'].base64Str }" width="300px"/>
					</c:if>
				</td>
			</tr>
			<jsp:useBean id="promotionsSvc" scope="page" class="com.promotions.model.PromotionsService" />
		</table>

		<br> 
		<input type="hidden" name="action" value="update_bt"> 
		<input type="hidden" name="promot_no" value="<%=promotionsVO.getPromot_no()%>">
		<input type="submit" value="送出修改">
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