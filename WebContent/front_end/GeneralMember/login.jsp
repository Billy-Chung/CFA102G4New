<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.generalMember.model.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

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




<!-- Login Section Start -->
    <div class="section section-margin">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-md-8 m-auto">
                    <div class="login-wrapper">

                        <!-- Login Title & Content Start -->
                        <div class="section-content text-center m-b-30">
                            <h2 class="title m-b-10">會員登入</h2>
                        </div>
                        <!-- Login Title & Content End -->

                        <!-- Form Action Start -->
                        <form action="<%=request.getContextPath()%>/LoginServlet" method="post">

                            <!-- Input Account Start -->
                            <div class="single-input-item m-b-10">
                                <input type="TEXT" name="account" size="16" placeholder="請輸入會員帳號">
                            </div>
                            <!-- Input Account End -->

                            <!-- Input Password Start -->
                            <div class="single-input-item m-b-10">
                                <input type="password" name="password" size="16" placeholder="請輸入會員密碼">
                            </div>
                            <!-- Input Password End -->

                            <!-- Button/Forget Password Start -->
                            <div class="single-input-item m-b-15">
                                <div class="login-reg-form-meta m-b-n15">
                                    <input type="hidden" name="action" value="login">
                                    <input type="hidden" name="PK" value="${PK}">
                                    <button class="btn btn btn-gray-deep btn-hover-primary m-b-15">送出</button>
                                    <a href="<%=request.getContextPath()%>/front_end/GeneralMember/forgotPassword.jsp" class="forget-pwd m-b-15">忘記密碼</a>
                                </div>
                            </div>
                            <!-- Button/Forget Password End -->

                            <!-- Lost Password & Creat New Account Start -->
                            
                            <!-- Lost Password & Creat New Account End -->

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
    
    <%@ include file="../front_page/footer2.jsp"%>
    
    <script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>

	<!--Main JS-->
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>

</body>
</html>