<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adoptPet.model.*"%>
<%@ page import="com.petClass.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="" />
<meta name="author" content="" />
<meta name="robots" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Innap : Hotel Admin Template" />
<meta property="og:title" content="Innap : Hotel Admin Template" />
<meta property="og:description" content="Innap : Hotel Admin Template" />
<meta property="og:image" content="social-image.png" />
<meta name="format-detection" content="telephone=no">

<!-- PAGE TITLE HERE -->
<title>寵一而忠</title>

<!-- FAVICONS ICON -->
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/back_end/backend_page/images/favicon.ico" />


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/star-rating/star-rating-svg.css">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/css/nice-select.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css"
	rel="stylesheet">
	<link href='<%=request.getContextPath()%>/back_end/adoptMember/lib/main.css' rel='stylesheet' />
  <script src='<%=request.getContextPath()%>/back_end/adoptMember/lib/main.js'></script>
  <script src='<%=request.getContextPath()%>/back_end/adoptMember/lib/locales-all.js'></script>
 <script>


    document.addEventListener('DOMContentLoaded', function () {
      var calendarEl = document.getElementById('calendar');
    
      $.ajax({
          url: "<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=showReserve&PK=${admin.adopt_meb_no}",
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
          					url: "<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=cancelReserve&PK=" + arg.event.id,
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
.myright {
	margin-left: 80%;
}
body {
      margin: 40px 10px;
      padding: 0;
      font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
      font-size: 18px;
    }

    #calendar {
      max-width: 1100px;
      margin: 0 auto;
      background-color: #FCFCFC;
    }
</style>

</head>

<body>
	<%@ include file="../backend_page/Preloader.jsp"%>
	<div id="main-wrapper">
		<%@ include file="../backend_page/nav-header.jsp"%>
		<%@ include file="../backend_page/chatbox.jsp"%>
		<%@ include file="../backend_page/head.jsp"%>
		<%@ include file="../backend_page/Sidebar.jsp"%>

		<div class="content-body">
			<div class="container-fluid">				

				
					 <div id='calendar'></div>
				
			</div>

		</div>
		
		<%@ include file="../backend_page/footer.jsp"%>
	</div>

	

	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/global/global.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/custom.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/deznav-init.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/back_CSS/js/styleSwitcher.js"></script>
</body>

</html>