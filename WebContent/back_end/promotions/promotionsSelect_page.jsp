<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<title>Pet Promotions: Home</title>
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />
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
<h4><img src="<%=request.getContextPath()%>/back_end/promotions/images/cat.png" width="600"></h4>
<h2><a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp">回首頁</a></h2>


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
                                   
  <li><a href="<%=request.getContextPath()%>/back_end/promotions/listAllPromotions.jsp">List</a> all Promotions.  <br><br></li>
  
  
  <li>
  
                                                        
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" >
        <b>輸入活動編號 (ex.1~10...):</b>
        <input type="text" name="promot_no">
        <input type="hidden" name="action" value="getOne_For_Display"> <%--controller Servlet --%>>
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="promotionsSvc" scope="page" class="com.promotions.model.PromotionsService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" >
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
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotions/promotions.do" >
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