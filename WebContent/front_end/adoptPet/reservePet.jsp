<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>
<%@ page import="com.adoptMemberNews.model.*"%>

<%-- <jsp:useBean id="allNews" class="com.adoptMemberNews.model.AdoptMemberNewsVo"/> --%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Amber - Pet Care Bootstrap 5 Template</title>


<link rel="shortcut icon" href="assets/images/favicon.ico">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/adoptPet/datetimepicker/jquery.datetimepicker.css" />

<style>
.myTop {
	margin-top: 10%;
}

.myTop2 {
	margin-top: 3%;
}

.myTop3 {
	margin-top: 15%;
}

.mybottom {
	margin-bottom: 3%;
}

.inmid {
	margin: 0 40% 0 43%;
}

.listPhoto {
	max-width: 100%;
	max-height: 100%;
}

.toServlet {
	display: inline-block;
}

.product {
	height: 100%
}

.myTextSize {
	font-size: 1.5rem;
}

.myTextSize2 {
	font-size: 1.2rem;
}

.oldPhoto2 {
	max-width: 100%;
	max-height: 100%;
}

 .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

</head>

<body>

	<%@ include file="../front_page/head.jsp"%>

	<div class="section breadcrumb-area bg-name-bright">
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<div class="breadcrumb-wrapper">
						<h2 class="breadcrumb-title">以領養代替購買，一起把愛宣揚在世間!!</h2>
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">返回領養頁面</a></li>
							<li>所有領養寵物</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section section-margin">
		<div class="container">
		
		<div class="row">
				<div class="col-lg-9 m-auto overflow-hidden">
					<!-- Blog Details Wrapper Start -->
					<div class="blog-details-wrapper">
						<!-- Blog Details Content Start -->
						<div class="blog-details-content">
							<!-- Blog Image Start -->
							<div class="blog-image">
								<img src="<%=request.getContextPath()%>/adoptPet/addPetPhoto.do?action=cover&PK=1"
									alt="Blog Image" class="fit-image">
							</div>										
							<!-- Content End -->
						</div>
					</div>
					<!-- Blog Details Wrapper End -->
				</div>
			</div>		
			
			 <div class="row m-b-n20">
                <div class="col-lg-12 col-12 m-b-20">
                    <!-- Checkbox Form Start -->
                    <form action="#">
                        <div class="checkbox-form">

                            <!-- Checkbox Form Title Start -->
                            <h3 class="title">毛小孩預約表</h3>
                            <!-- Checkbox Form Title End -->

                            <div class="row">
                                <!-- Company Name Input Start -->
                                <div class="col-md-12">
                                    <div class="checkout-form-list">
                                        <label>預約人姓名</label>
                                        <input placeholder="" type="text">
                                    </div>
                                </div>
                                <!-- Company Name Input End -->

                                <div class="col-md-12">
                                    <div class="checkout-form-list">
                                        <label>預約人電話<span class="required">*</span></label>
                                        <input placeholder="" type="text">
                                    </div>
                                </div>



                                <!-- State or Country Input Start -->
                                <div class="col-md-6">
                                    <div class="checkout-form-list">
                                        <label>預約日期 <span class="required">*</span></label>
                                        <input id="f_date1" placeholder="" name="reserveDate" type="text">
                                    </div>
                                </div>
                                <!-- State or Country Input End -->

                                <!-- Postcode or Zip Input Start -->
                                <div class="col-md-6">
                                    <div class="checkout-form-list">
                                        <label>預約時段<span class="required">*</span></label>
                                        <select class="myniceselect nice-select wide rounded-0">
                                            <option value="Bangladesh">Bangladesh</option>
                                            <option value="uk">London</option>
                                            <option value="rou">Romania</option>
                                            <option value="fr">French</option>
                                            <option value="de">Germany</option>
                                            <option value="aus">Australia</option>
                                        </select>
                                    </div>
                                </div>
                                <!-- Postcode or Zip Input End -->
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary btn-hover-dark inmid ">送出預約</button>
                    </form>
                    <!-- Checkbox Form End -->
                </div>
            </div>       
		</div>
	</div>




	<%@ include file="../front_page/footer.jsp"%>



	<!-- Scroll Top Start -->
	<a href="#" class="scroll-top show" id="scroll-top"> <i
		class="arrow-top ti-angle-double-up"></i> <i
		class="arrow-bottom ti-angle-double-up"></i>
	</a>
	<!-- Scroll Top End -->



	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
		
<% 
  java.sql.Date reserveDate = null;
  try {
	  reserveDate = java.sql.Date.valueOf(request.getParameter("reserveDate").trim());
   } catch (Exception e) {
	   reserveDate = new java.sql.Date(System.currentTimeMillis());
   }
%>


<script src="<%=request.getContextPath()%>/front_end/adoptPet/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front_end/adoptPet/datetimepicker/jquery.datetimepicker.full.js"></script>


<script>

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=reserveDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:'-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

            
            Date.prototype.addDays = function(days) {
    			var date = new Date(this.valueOf());
    			date.setDate(date.getDate() + days);
    			return date;
			}
            
			//          2.以下為某一天之後的日期無法選擇		
			 var somedate1 = new Date();
                  var somedate2 = somedate1.addDays(14);
                  $('#f_date1').datetimepicker({
                      beforeShowDay: function(date) {
                    	  if (  date.getYear() >  somedate2.getYear() || 
            		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
            		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                          ) {
                               return [false, ""]
                          }
                          return [true, ""];
                  }});

        
        
        
</script>
</body>

</html>