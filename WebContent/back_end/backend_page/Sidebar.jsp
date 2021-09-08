<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     <div class="deznav">
      <div class="deznav-scroll">
        <ul class="metismenu" id="menu">
          <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
              <i class="flaticon-086-star"></i>
              <span class="nav-text">寵物資料管理</span>
            </a>
            <ul aria-expanded="false">
              <li><a href="<%=request.getContextPath()%>/back_end/adopt/adoptPet.jsp">寵物清單</a></li>
              <li><a href="<%=request.getContextPath()%>/back_end/adopt/addPet.jsp">新增寵物</a></li>
            </ul>

          </li>
          <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
              <i class="flaticon-086-star"></i>
              <span class="nav-text">機構資訊管理</span>
            </a>
            <ul aria-expanded="false">
              <li><a href="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=gotoUpdate&adoptMebNo=${admin.adopt_meb_no}">機構資訊修改</a></li>
              <li><a href="./app-profile.html">最新消息管理</a></li>
              <li><a href="./post-details.html">可預約時段修改</a></li>

            </ul>
          </li>
          <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
              <i class="flaticon-086-star"></i>
              <span class="nav-text">領養管理</span>
            </a>
            <ul aria-expanded="false">
              <li><a href="./chart-flot.html">預約看寵物</a></li>
              <li><a href="./chart-morris.html">領養申請</a></li>        
            </ul>
          </li>
        </ul>
      </div>
    </div>