<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:if test="${ not empty promotionSvc.currentPromotion}" >
	<!-- Hero/Intro Slider Start -->
	<div class="section">
		<div class="hero-slider swiper-container">
			<div class="swiper-wrapper">
				<div class="hero-slide-item swiper-slide">
					<div class="hero-slide-bg">
						<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionSvc.currentPromotion.promot_no}&functionName=banner1">
					</div>
				</div>
				<div class="hero-slide-item swiper-slide">
					<div class="hero-slide-bg">
						<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionSvc.currentPromotion.promot_no}&functionName=banner2">
					</div>
				</div>
				<div class="hero-slide-item swiper-slide">
					<div class="hero-slide-bg">
						<img src="<%=request.getContextPath()%>/PromoPhotos?promoNo=${promotionSvc.currentPromotion.promot_no}&functionName=banner3">
					</div>
				</div>
			</div>

			<!-- Swiper Navigation Start -->
			<div
				class="home-slider-prev swiper-button-prev main-slider-nav d-lg-flex d-none">
				<i class="icon-arrow-left"></i>
			</div>
			<div
				class="home-slider-next swiper-button-next main-slider-nav d-lg-flex d-none">
				<i class="icon-arrow-right"></i>
			</div>
			<!-- Swiper Navigation End -->
		</div>
	</div>
	<!-- Hero/Intro Slider End -->
	</c:if>