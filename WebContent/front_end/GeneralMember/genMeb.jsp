<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adoptMember.model.*"%>
<%@ page import="com.generalMember.model.*"%>

<%
  GeneralMemberVO gmVO = (GeneralMemberVO) request.getAttribute("gmVO"); 
%>




<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>寵一而忠</title>


<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_page/images/favicon.ico" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">
	
<link href='<%=request.getContextPath()%>/front_end/GeneralMember/lib/main.css' rel='stylesheet' />
  <script src='<%=request.getContextPath()%>/front_end/GeneralMember/lib/main.js'></script>
  <script src='<%=request.getContextPath()%>/front_end/GeneralMember/lib/locales-all.js'></script>
  
  <script>


    document.addEventListener('DOMContentLoaded', function () {
      var calendarEl = document.getElementById('calendar');
    
      $.ajax({
          url: "<%=request.getContextPath()%>/adoptPet/reservePet.do?action=showReserve&PK=${meb.ger_meb_no}",
          method: "get",
          dataType: "json",         
        }).done(
          function (e) {           
        	  var calendar = new FullCalendar.Calendar(calendarEl, {
        		  locale:'zh-tw',
        	        headerToolbar: {
        	          left: 'prev,next today',
        	          center: 'title',
        	          right: 'dayGridMonth'
        	        },
//         	        initialDate: '2020-09-12',
        	        navLinks: true, // can click day/week names to navigate views
        	        selectable: true,
        	        selectMirror: true,
        	        eventClick: function (arg) {
        	          if (confirm('是否要取消該筆預約?')) {        	        	
        	        	$.ajax({
          					url: "<%=request.getContextPath()%>/adoptPet/reservePet.do?action=cancelReserve&PK=" + arg.event.id,
          					method: "post",
          					dataType: "json",
          					success:arg.event.remove(),
        				})
        	          }
        	        },        	       
        	        editable: true,
        	        dayMaxEvents: true, // allow "more" link when too many events
        	        events: e,
        	      });
        	      calendar.render();
        	    });
          }
        );
 </script>

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
</style>

</head>

<body>

	<%@ include file="../front_page/head.jsp"%>

	<div class="section breadcrumb-area bg-name-bright">
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<div class="breadcrumb-wrapper">
						<h2 class="breadcrumb-title">會員中心</h2>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="section section-margin">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">

                    <!-- My Account Page Start -->
                    <div class="myaccount-page-wrapper">
                        <div class="row">

                            <!-- My Account Tab Menu Start -->
                            <div class="col-lg-3 col-md-4">
                                <div class="myaccount-tab-menu nav" role="tablist">
                                    <a href="#dashboad" class="active" data-bs-toggle="tab"><i class="fa fa-dashboard"></i>
                                        查看預約</a>
                                    <a href="<%=request.getContextPath() %>/order_form/order_form.do?action=getMemOrderList"><i class="fa fa-cart-arrow-down"></i> 歷史訂單</a>
                                    <a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/addGeneralMemberPet.jsp"><i class="fa fa-credit-card"></i> 新增寵物</a>
                                    <a href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/GeneralMemberPet.jsp"><i class="fa fa-credit-card"></i> 寵物清單</a>
                                    <a href="<%=request.getContextPath()%>/gm/gm.do?action=getOne_For_Update&gmno=${meb.getGer_meb_no()}" ><i class="fa fa-user"></i> 修改會員資料</a>
                                    <a href="<%=request.getContextPath()%>/front_end/GeneralMember/updatePassword.jsp" ><i class="fa fa-user"></i> 修改密碼</a>
                                    <a href="<%=request.getContextPath()%>/LoginServlet?action=logout"><i class="fa fa-sign-out"></i> Logout</a>
                                </div>
                            </div>
                            <!-- My Account Tab Menu End -->

                            <!-- My Account Tab Content Start -->
                            <div class="col-lg-9 col-md-8">
                                <div class="tab-content" id="myaccountContent">

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade show active" id="dashboad" role="tabpanel">
                                        <div class="myaccount-content">
                                             <div id='calendar'></div>
                                        </div>
                                    </div>
                                    <!-- Single Tab Content End -->

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade" id="orders" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">Orders</h3>
                                            <div class="myaccount-table table-responsive text-center">
                                                <table class="table table-bordered">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th>Order</th>
                                                            <th>Date</th>
                                                            <th>Status</th>
                                                            <th>Total</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>Aug 22, 2018</td>
                                                            <td>Pending</td>
                                                            <td>$3000</td>
                                                            <td><a href="cart.html" class="btn btn btn-dark btn-hover-primary btn-sm rounded-0">View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>2</td>
                                                            <td>July 22, 2018</td>
                                                            <td>Approved</td>
                                                            <td>$200</td>
                                                            <td><a href="cart.html" class="btn btn btn-dark btn-hover-primary btn-sm rounded-0">View</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>3</td>
                                                            <td>June 12, 2019</td>
                                                            <td>On Hold</td>
                                                            <td>$990</td>
                                                            <td><a href="cart.html" class="btn btn btn-dark btn-hover-primary btn-sm rounded-0">View</a></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Single Tab Content End -->

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade" id="download" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">Downloads</h3>
                                            <div class="myaccount-table table-responsive text-center">
                                                <table class="table table-bordered">
                                                    <thead class="thead-light">
                                                        <tr>
                                                            <th>Product</th>
                                                            <th>Date</th>
                                                            <th>Expire</th>
                                                            <th>Download</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Haven - Free Real Estate PSD Template</td>
                                                            <td>Aug 22, 2018</td>
                                                            <td>Yes</td>
                                                            <td><a href="#" class="btn btn btn-dark btn-hover-primary rounded-0"><i class="fa fa-cloud-download m-r-5"></i> Download File</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>TechWorld - Profolio Business Template</td>
                                                            <td>Sep 12, 2018</td>
                                                            <td>Never</td>
                                                            <td><a href="#" class="btn btn btn-dark btn-hover-primary rounded-0"><i class="fa fa-cloud-download m-r-5"></i> Download File</a></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Single Tab Content End -->

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade" id="payment-method" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">Payment Method</h3>
                                            <p class="saved-message">You Can't Saved Your Payment Method yet.</p>
                                        </div>
                                    </div>
                                    <!-- Single Tab Content End -->

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade" id="address-edit" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">Billing Address</h3>
                                            <address>
                                                <p><strong>Alex Aya</strong></p>
                                                <p>1234 Market ##, Suite 900 <br>Lorem Ipsum, ## 12345</p>
                                                <p>Mobile: (123) 123-456789</p>
                                            </address>
                                            <a href="#" class="btn btn btn-dark btn-hover-primary rounded-0"><i class="fa fa-edit m-r-10"></i>Edit Address</a>
                                        </div>
                                    </div>
                                    <!-- Single Tab Content End -->

                                    <!-- Single Tab Content Start -->
                                    <div class="tab-pane fade" id="account-info" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">Account Details</h3>
                                            <div class="account-details-form">
                                                <form action="#">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="single-input-item m-b-15">
                                                                <label for="first-name" class="required m-b-10">First Name</label>
                                                                <input type="text" id="first-name" placeholder="First Name" />
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="single-input-item m-b-15">
                                                                <label for="last-name" class="required m-b-10">Last Name</label>
                                                                <input type="text" id="last-name" placeholder="Last Name" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="single-input-item m-b-15">
                                                        <label for="display-name" class="required m-b-10">Display Name</label>
                                                        <input type="text" id="display-name" placeholder="Display Name" />
                                                    </div>
                                                    <div class="single-input-item m-b-15">
                                                        <label for="email" class="required m-b-5">Email Addres</label>
                                                        <input type="email" id="email" placeholder="Email Address" />
                                                    </div>
                                                    
                                                    <div class="single-input-item single-item-button m-t-30">
                                                        <button class="btn btn btn-primary btn-hover-dark rounded-0">修改完畢</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div> <!-- Single Tab Content End -->
                                    
                                    <div class="tab-pane fade" id="password-info" role="tabpanel">
                                        <div class="myaccount-content">
                                            <h3 class="title">PASSWORD CHANGE</h3>
                                            <div class="account-details-form">
                                                <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="single-input-item m-b-15">
                                                                <label for="account" class="required m-b-10">會員帳號</label>
                                                                <input type="TEXT" name="account" id="account" size="16" placeholder="請輸入會員帳號" />
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="single-input-item m-b-15">
                                                                <label for="password" class="required m-b-10">舊密碼</label>
                                                                <input type="password" id="password" name="password" size="16" placeholder="Last Name" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="single-input-item m-b-15">
                                                        <label for="newpassword" class="required m-b-10">新密碼</label>
                                                        <input type="password" id="newpassword" name="newpassword" size="16" placeholder="請輸入新密碼" />
                                                    </div>
                                                    <div class="single-input-item m-b-15">
                                                        <label for="newpassword1" class="required m-b-5">確認新密碼</label>
                                                        <input type="password" id="newpassword1" name="newpassword1" size="16" placeholder="請再輸入一次新密碼" />
                                                    </div>
                                                    
                                                    <div class="single-input-item single-item-button m-t-30">
                                                        <input type="hidden" name="action" value="update_password">
                                                        <button class="btn btn btn-primary btn-hover-dark rounded-0">送出修改</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div> <!-- Single Tab Content End -->

                                </div>
                            </div>
                            <!-- My Account Tab Content End -->

                        </div>
                    </div>
                    <!-- My Account Page End -->

                </div>
            </div>

        </div>
    </div>



	<%@ include file="../front_page/footer2.jsp"%>



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
</body>

</html>