<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<div class="header section">
    <!-- Header Bottom Start -->
    <div class="header-bottom">
      <div class="header-sticky">
        <div class="container">
          <div class="row align-items-center position-relative">

            <!-- Header Logo Start -->
            <div class="col-lg-3 col-md-4 col-6">
              <div class="header-logo">
                <a href="index.html"><img class="return" src="<%=request.getContextPath()%>/back_end/backend_page/images/pet2.jpg"></a>
              </div>
            </div>
            <!-- Header Logo End -->

            <!-- Header Menu Start -->
            <div class="col-lg-6 d-none d-lg-block">
              <div class="main-menu">
                <ul>
                  <li class="has-children">
                    <a href="#">寵物商城</a>
                  </li>

                  <li class="has-children position-static">
                    <a href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">成為毛小孩的夥伴</a>
                  </li>

                  <li class="has-children"><a href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMember.jsp">關於我們</a></li>                 
                </ul>
              </div>
            </div>
            <!-- Header Menu End -->

            <!-- Header Action Start -->
            <div class="col-lg-3 col-md-8 col-6">
              <div class="header-actions">
              
                <!-- Header My Account Button Start -->
                <a href="my-account.html" class="header-action-btn header-action-btn-wishlist">
                  <i class="icon-user icons"></i>
                </a>
                <!-- Header My Account Button End -->

                <!-- Header Action Button Start -->
                <div class="header-action-btn header-action-btn-cart d-none d-sm-flex">
                  <a class="cart-visible" href="#">
                    <i class="icon-handbag icons"></i>
                  </a>              

                </div>           
                <!-- Header Action Button End -->

                <!-- Mobile Menu Hambarger Action Button Start -->
                <a href="javascript:void(0)" class="header-action-btn header-action-btn-menu d-lg-none d-md-flex">
                  <i class="icon-menu"></i>
                </a>
                <!-- Mobile Menu Hambarger Action Button End -->

              </div>
            </div>
            <!-- Header Action End -->

          </div>
        </div>
      </div>
    </div>
    <!-- Header Bottom End -->

  </div>
  <!-- Header Section End -->