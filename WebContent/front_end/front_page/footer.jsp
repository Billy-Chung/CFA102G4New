<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Newsletter Section Start -->
<div class="section section-padding bg-name-splashy ">
	<div class="container">
		<div class="row align-items-center m-b-n30 ">

			<div class="col-12 col-md-6 m-b-30 " data-aos="fade-up"
				data-aos-duration="1000">
				<div class="newsletter-content">
					<h3 class="title">加入我們</h3>
					<p>此為註冊功能</p>
				</div>
			</div>

			<div class="col-12 col-md-6 m-b-30" data-aos="fade-up"
				data-aos-duration="1400">
				<!-- Newsletter Form Start -->
				<div class="newsletter-form-wrap newsletter-inner">
					<form id="mc-form" class="mc-form">
						<input type="email" id="mc-email" class="form-control email-box"
							placeholder="demo@example.com" name="EMAIL">
						<button id="mc-submit" class="newsletter-btn" type="submit">送出</button>
					</form>
					<!-- mailchimp-alerts Start -->
					<div class="mailchimp-alerts text-centre">
						<div class="mailchimp-submitting"></div>
						<!-- mailchimp-submitting end -->
						<div class="mailchimp-success text-success"></div>
						<!-- mailchimp-success end -->
						<div class="mailchimp-error text-danger"></div>
						<!-- mailchimp-error end -->
					</div>
					<!-- mailchimp-alerts end -->
				</div>
				<!-- Newsletter Form End -->
			</div>

		</div>
	</div>
</div>
<!-- Newsletter Section End -->

<!-- Footer Section Start -->
<footer class="section footer-section">
	<!-- Footer Top Start -->
	<div class="footer-top bg-name-bright section-padding">
		<div class="container">
			<div class="row m-b-n40">
				<div class="col-12 col-sm-6 col-lg-3 m-b-40" data-aos="fade-up"
					data-aos-duration="1000">
					<div class="single-footer-widget">
						<h1 class="widget-title">我們的資訊</h1>
						<ul class="widget-list">
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMember.jsp">關於我們</a></li>
						</ul>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-lg-3 m-b-40" data-aos="fade-up"
					data-aos-duration="1200">
					<div class="single-footer-widget">
						<h2 class="widget-title">成為毛小孩的夥伴</h2>
						<ul class="widget-list">
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMember.jsp">領養機構中心</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">寵物中心</a></li>
							<li><a
								href="<%=request.getContextPath()%>/back_end/login/login.jsp">後臺登入</a></li>
						</ul>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-lg-3 m-b-40" data-aos="fade-up"
					data-aos-duration="1400">
					<div class="single-footer-widget">
						<h2 class="widget-title">前往商城</h2>
						<ul class="widget-list">
							<li><a href="wishlist.html">商城首頁</a></li>
							<li><a href="contact.html">購物車</a></li>
							<li><a href="contact.html">當前行銷活動</a></li>
						</ul>
					</div>
				</div>
				<div class="col-12 col-sm-6 col-lg-3 m-b-40" data-aos="fade-up"
					data-aos-duration="1600">
					<div class="single-footer-widget">
						<h2 class="widget-title">會員中心</h2>
						<ul class="widget-list">
							<li><a href="login.html">登入</a></li>
							<li><a href="my-account.html">我的帳號</a></li>
							<li><a href="checkout.html">登出</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer Top End -->

	<!-- Footer Bottom Start -->
	<!-- Footer Bottom End -->
</footer>

<!-- Mobile Menu Start -->
<div class="mobile-menu-wrapper">
	<div class="offcanvas-overlay"></div>

	<!-- Mobile Menu Inner Start -->
	<div class="mobile-menu-inner">

		<!-- Button Close Start -->
		<div class="offcanvas-btn-close">
			<i class="fa fa-times"></i>
		</div>
		<!-- Button Close End -->

		<!-- Mobile Menu Inner Wrapper Start -->
		<div class="mobile-menu-inner-wrapper">
			<!-- Mobile Menu Start -->
			<div class="mobile-navigation">
				<nav>
					<ul class="mobile-menu">
						<li class="has-children"><a href="#">寵物商城</a></li>
						<li class="has-children position-static"><a
							href="<%=request.getContextPath()%>/front_end/adoptPet/adoptPet.jsp">成為毛小孩的夥伴</a>
						</li>
						<li class="has-children"><a
							href="<%=request.getContextPath()%>/front_end/adoptMember/adoptMember.jsp">關於我們</a></li>

					</ul>
				</nav>
			</div>
			<!-- Mobile Menu End -->
		</div>
		<!-- Mobile Menu Inner Wrapper End -->

	</div>
	<!-- Mobile Menu Inner End -->
</div>