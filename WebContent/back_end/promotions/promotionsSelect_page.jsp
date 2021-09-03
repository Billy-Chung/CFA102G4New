<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<title>Pet Promotions: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
				
   <tr><td><h3>Pet Promotions: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Pet Promotions: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPromotions.jsp'>List</a> all Promotions.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/addpromotions.do" >
        <b>輸入活動編號 (ex.1~10...):</b>
        <input type="text" name="promot_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="promotionsSvc" scope="page" class="com.promotions.model.promotionsService" />
   
  <li>
     <FORM METHOD="post" ACTION="promotions.do" >
       <b>選擇活動編號:</b>
       <select size="1" name="promot_no">
         <c:forEach var="promotionsVO" items="${promotionsSvc.all}" > 
          <option value="${promotionsVO.promot_no}">${promotionsVO.promot_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="promotions.do" >
       <b>選擇活動名稱:</b>
       <select size="1" name="promot_no">
         <c:forEach var="promotionsVO" items="${promotionsSvc.all}" > 
          <option value="${promotionsVO.promot_no}">${promotionsVO.promot_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>行銷活動管理</h3>

<ul>
  <li><a href='addPromotions.jsp'>Add</a> a new Promotion.</li>
</ul>

</body>
</html>