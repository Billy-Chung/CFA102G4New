<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>寵一而忠</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
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
<%@ include file="../front_page/head.jsp"%>



<!-- Register Section Start -->
<div class="section section-margin">
	<div class="container">
		<div class="row">
			<div class="col-lg-7 col-md-8 m-auto">
				<div class="login-wrapper">

					<!-- Register Title & Content Start -->
					<div class="section-content text-center m-b-30">
						<h2 class="title m-b-10">會員註冊</h2>
					</div>
					<!-- Register Title & Content End -->

					<!-- Form Action Start -->
					<form action="<%=request.getContextPath()%>/gm/gm.do" method="post" enctype="multipart/form-data">

						<!-- Input First Name Start -->
						<div class="single-input-item m-b-10">
							會員姓名:<input type="TEXT" name="meb_name" size="10" value="<%=(gmVO==null) ? "" :gmVO.getMeb_name()%>" placeholder="姓名">
						</div>
						<!-- Input First Name End -->

						<!-- Input Last Name Start -->
						<div class="single-input-item m-b-10">
							會員手機:<input type="TEXT"  name="phone" size="10"	value="<%=(gmVO==null) ? "" :gmVO.getPhone()%>" placeholder="手機">
						</div>
						<!-- Input Last Name End -->
						
						<div class="single-input-item m-b-10">
							會員照片:<input type="file"  name="photo">
						</div>

						<!-- Input Email Start -->
						<div class="single-input-item m-b-10">
							會員生日:<input name="birthday" id="f_date1" type="text">
						</div>
						<!-- Input Email End -->

						<!-- Input Password Start -->
						<div class="single-input-item m-b-10">
							會員簡介:<input type="TEXT" name="comment" size="65" value="<%=(gmVO==null) ? "" :gmVO.getComment()%>" placeholder="簡介" >
						</div>

						<div class="single-input-item m-b-10">
							會員地址:<input type="TEXT" name="address" size="65" value="<%=(gmVO==null) ? "" :gmVO.getAddress()%>" placehlder="地址" >
						</div>

						<div class="single-input-item m-b-10">
							EMAIL:<input type="TEXT" name="email" size="30" value="<%=(gmVO==null) ? "" :gmVO.getEmail()%>" placeholder="email" >
						</div>

						<div class="single-input-item m-b-10">
							帳號:<input type="TEXT" name="account" size="16" value="<%=(gmVO==null) ? "" :gmVO.getAccount()%>" placeholder="帳號" >
						</div>

						<div class="single-input-item m-b-10">
							密碼:<input type="password" name="password" size="16" value="<%=(gmVO==null) ? "" :gmVO.getPassword()%>" placeholder="密碼" >
						</div>

						<div>
							性別:<input type="radio" name="gender" value="1" checked />男
								<input type="radio" name="gender" value="0" />女
						</div><p>
						

						<!-- Button/Forget Password Start -->
						<div class="single-input-item">
							<div class="login-reg-form-meta m-b-n15">
								<input type="hidden" name="action" value="insert">
								<button align="center" class="btn btn btn-gray-deep btn-hover-primary m-b-15">Create</button>
							</div>
						</div>
						<!-- Button/Forget Password End -->

					</form>
					<!-- Form Action End -->
					
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以上錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
</c:if>

				</div>
			</div>
		</div>
	</div>
</div>
<!-- Register Section End -->

<%@ include file="../front_page/footer2.jsp"%>

<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
		
<% 
  java.sql.Date birthday = null;
  try {
	  birthday = gmVO.getBirthday();
   } catch (Exception e) {
	   birthday = new java.sql.Date(System.currentTimeMillis());
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
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=birthday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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


</body>

</html>