<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="header">
      <div class="header-content">
        <nav class="navbar navbar-expand">
          <div class="collapse navbar-collapse justify-content-between">
            <div class="header-left">
              <div class="dashboard_bar">

              </div>
            </div>
            <ul class="navbar-nav header-right">            
             

              <li class="nav-item dropdown header-profile">
                <a class="nav-link" href="javascript:void(0);" role="button" data-bs-toggle="dropdown">
                  <img src="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=showMebPhoto&adoptMebNo=${admin.adopt_meb_no}" alt="">
                  <div class="header-info ms-3">
                    <span>${admin.adopt_meb_name}</span>
                    <small>${admin.adopt_meb_email}</small>
                  </div>
                </a>
                <div class="dropdown-menu dropdown-menu-end">
                  <a href="<%=request.getContextPath()%>/adoptMeb/adoptMeb.do?action=gotoUpdate&adoptMebNo=${admin.adopt_meb_no}" class="dropdown-item ai-icon">
                    <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18"
                      viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                      stroke-linejoin="round">
                      <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                      <circle cx="12" cy="7" r="4"></circle>
                    </svg>
                    <span class="ms-2">機構資訊 </span>
                  </a>                
                  <a href="<%=request.getContextPath()%>/loginhandler?action=logout" class="dropdown-item ai-icon">
                    <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18"
                      viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                      stroke-linejoin="round">
                      <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                      <polyline points="16 17 21 12 16 7"></polyline>
                      <line x1="21" y1="12" x2="9" y2="12"></line>
                    </svg>
                    <span class="ms-2">登出 </span>
                  </a>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>