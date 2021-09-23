<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" %>
<%@page import="com.shopping_cart.*" %>
<%@page import="com.order_detail.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>shoppingCart</title>
</head>
<body>


<!--  從客戶端得到 key為cart的參數， session.getAttribute("cart")是object，強轉型為Map型別         -->
<!-- 將Map內的所有值轉存成集合Collection，取值所有的值 ，存到購物車的清單中cartlist-->
<%
	Map<Integer, order_detailVO> cart =  (Map<Integer, order_detailVO>) session.getAttribute("cart");
	Collection<order_detailVO> cartlist = cart.values();
%>


	<tr><td>
		 <h3>購物車的內容如下- shoppingCart.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/shopping_cart/FakeProductList.jsp"><img src="<%=request.getContextPath()%>/front_end/shopping_cart/images/back1.gif" width="100" height="32" border="0">回商品清單選購</a></h4>
	</td></tr>



<table border="1" width="740">

	<tr bgcolor="#999999">
		<th width="100">商品編號</th><th width="200">商品名稱</th><th width="100">販售單價</th><th width="120">數量</th>
	</tr>
	
	
<!-- 集合Collection提供迭代器Iterator的方法，可以取得所有的元素 -->
<!-- 因為會加入n次，用while -->
	<%
	 Iterator<order_detailVO> cartlistAll = cartlist.iterator();
	 while (cartlistAll.hasNext()) {
		 order_detailVO prod = cartlistAll.next(); 
	%>
<!--畫面看到的部分 -->	
	<tr>
		<td width="200"><div align="center"><b><%=prod.getProduct_no()%></b></div></td>
		<td width="100"><div align="center"><b><%=prod.getProduct_name()%></b></div></td>
		<td width="100"><div align="center"><b><%=prod.getProduct_price()%></b></div></td>
		<td width="100"><div align="center"><b><%=prod.getOrder_product_number() %></b></div></td>
        </td>
	</tr>
	<%}%>
</form>
</table>
<p>
                                           
          <form name="checkoutForm" action="<%=request.getContextPath()%>/front_end/shopping_cart/Checkout.jsp" method="POST">
                                   <!--action送出請求到控制器(Shopping_cartServlet.java)結果會是字串CHECKOUT -->
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>

	
			

	
	
			

</body>
</html>