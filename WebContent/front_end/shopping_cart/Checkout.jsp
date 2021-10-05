<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_form.model.*"%>



<%
	OrderFormVO order_formVO = (OrderFormVO) request.getAttribute("order_formVO");
%>

<html>
<head>
<title>Checkout.jsp</title>
<script src="<%=request.getContextPath()%>/front_end/shopping_cart/zipCode.js"></script>
<script src="<%=request.getContextPath()%>/front_end/shopping_cart/zipcodeAddr.js"></script>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/vendor/vendor.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/plugins/plugins.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS/assets/css/style.min.css">

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


div.center_{
	right: 10px;
	top: 50%;
}
</style>
</head>


<body bgcolor='white' onload="injectData()">
<a href="<%=request.getContextPath() %>/front_end/shopping_cart/shoppingCart.jsp">
		<img src="<%=request.getContextPath()%>/front_end/shopping_cart/images/cart.png" width="50" height="50" border="0" style="position: fixed;width:50px;height:50px; top:300px; right:0; z-index:9999999" >
	</a>
	<c:if test="${not empty meb }">
		<a href="<%=request.getContextPath() %>/order_form/order_form.do?action=getMemOrderList">
			<img src="<%=request.getContextPath()%>/front_end/shopping_cart/images/orderlist.png" width="60" height="60" border="0" style="position: fixed;width:60px;height:60px; top:400px; right:0; z-index:9999999" >
		</a>
	</c:if>
	
	<%@ include file="../front_page/head.jsp"%>
	
	<div class="section breadcrumb-area bg-name-bright">
       <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <div class="breadcrumb-wrapper">
                        <h2 class="breadcrumb-title">寵一而忠</h2>
                        <ul>
                              <li><font size="5"><a href="<%=request.getContextPath()%>/front_end/shopping_cart/ProductList.jsp">Home-回商城首頁</a></font></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div style="height:90%; padding-top: 15%">


	
<!-- 	取出金額 -->
<%-- 	<%String amount =  (String) session.getAttribute("amount"); %> --%>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<font style="color:red" >哈囉哈囉~${meb.meb_name }您好!</font>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_form/order_form.do" name="form1" >

<table>

	<tr>
		<td>結帳金額:</td>
                                   <!--取出金額 -->
		<td> <font color="red"><b>$${amount}</b></font> </td>
	
	</tr>
	
	<tr>
	<td>收件者:</td>
		<td><input type="TEXT" name="order_name" size="10"
			 value="<%= (order_formVO==null)? "張婷婷" : order_formVO.getOrder_name()%>" /></td>
	</tr>
	<tr>
	<td>連絡電話:</td>
		<td><input type="TEXT" name="order_phone" size="10"
			 value="<%= (order_formVO==null)? "0932095969" : order_formVO.getOrder_phone()%>" /></td>
	</tr>
			
	<tr>
		<td>配送地址:</td>
		<td>
		<select name="city" id="city" onchange="whenCityChange()"></select>
        <select name="distinct" id="distinct"></select>
		<input type="TEXT" name="delivery_address" size="45" id="addr"
			 value="<%= (order_formVO==null)? "" : order_formVO.getDelivery_address()%>" />		
       </select>	
	</td>
	
		
	</tr>
	

</table>
<br>

<input type="hidden" name="action" value="fillform">
<input type="hidden" name="order_amount" value="${amount}">
<input type="hidden" id="distinctValue" name="distinctValue" value="">
 <input type="hidden" name="genMebNo"	value="${meb.ger_meb_no}"> 
<%-- <input type="hidden" name="order_no"  value="${order_formVO.order_no}"> --%>
<!-- <input type="hidden" name="action"	value="getOne_For_Display"></FORM> -->

<input type="submit" value="完成訂單">

</FORM>

<button type="button" onclick="fillAddr()">展示用快速填址</button>

</body>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
	
	
	<script>
	$("#distinct").change(function() {
		let city = $("#distinct").val();
		console.log($(".distinctValue").val());
		$("#distinctValue").val(city);
		console.log($("#distinctValue").val());
	});
	
	function fillAddr(){
		document.getElementById("addr").value = "復興路46號";
	}	
	</script>
		</div>
	<%@ include file="../front_page/footer2.jsp"%>
	<script>
		const server = "<%=request.getContextPath()%>";
	</script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/vendor.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/plugins.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/front_CSS/assets/js/main.js"></script>
	<script
		src="<%=request.getContextPath()%>/front_end/shopping_cart/js/ajax.js"></script>
	







</html>