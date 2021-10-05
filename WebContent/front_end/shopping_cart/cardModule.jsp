<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="promoProduct" >
	${promotionDetailSvc.getCurrentProductPromotion(product.product_no).productSpecialPrice }
</c:set>

<c:if test="${empty promoProduct }"> 
	<!-- Single Product Start -->
	<div class="col-lg-4 col-md-4 col-sm-6 product">
		<div class="product-inner">
			<!-- Cart Button Start -->
			<div class="thumb">
				<img class="fit-image" src="<%=request.getContextPath() %>/prodPhoto?prodNo=${product.product_no}"/>
			</div>
			<div class="content">
				<h5 class="title">${product.product_name}</h5>
				<span class="rating">${product.product_comment}</span> 
				<span class="price"> 
					<span class="new">${product.product_price}</span>
				</span>
				<button class="btn btn-primary" onclick="addToCart(${product.product_no})">讓我加一</button>
				<!-- Cart Button Start -->
				<div class="cart-btn action-btn">
					<div class="action-cart-btn-wrapper d-flex">
						<div class="add-to_cart">
							<a class="btn btn-primary btn-hover-dark rounded-0" href="<%=request.getContextPath()%>/adoptPet/addPet.do?action=goToReserve&PK=${AdoptPetVO.adopt_pet_no}">預約看寵物!!</a>
						</div>
						<a href="wishlist.html" title="Wishlist" class="action">
							<i class="ti-heart"></i>
						</a> 
						<a href="<%=request.getContextPath()%>/adoptPet/addPet.do?action=goToDetail&PK=${AdoptPetVO.adopt_pet_no}" class="action quickview">
							<i class="ti-plus"></i>
						</a>
					</div>
				</div>
				<!-- Cart Button End -->
			</div>
		</div>
	</div>
	<!-- Single Product End -->
</c:if>

<c:if test="${not empty promoProduct }"> 
	<!-- Single Product Start -->
	<div class="col-lg-4 col-md-4 col-sm-6 product">
		<div class="product-inner">
			<!-- Cart Button Start -->
			<div class="thumb">
				<img class="fit-image" src="<%=request.getContextPath() %>/prodPhoto?prodNo=${product.product_no}"/>
			</div>
			<div class="content">
				<h5 class="title">${product.product_name}</h5>
				<span class="rating">${product.product_comment}</span> 
				<span class="price"> 
					<del><span class="new">${product.product_price}</span></del>
					只要 <span class="new" style="color: red">${promoProduct}</span>元
				</span>
				<button class="btn btn-primary" onclick="addToCart(${product.product_no})">讓我加一</button>
				<!-- Cart Button Start -->
				<div class="cart-btn action-btn">
					<div class="action-cart-btn-wrapper d-flex">
						<div class="add-to_cart">
							<a class="btn btn-primary btn-hover-dark rounded-0" href="<%=request.getContextPath()%>/adoptPet/addPet.do?action=goToReserve&PK=${AdoptPetVO.adopt_pet_no}">預約看寵物!!</a>
						</div>
						<a href="wishlist.html" title="Wishlist" class="action">
							<i class="ti-heart"></i>
						</a> 
						<a href="<%=request.getContextPath()%>/adoptPet/addPet.do?action=goToDetail&PK=${AdoptPetVO.adopt_pet_no}" class="action quickview">
							<i class="ti-plus"></i>
						</a>
					</div>
				</div>
				<!-- Cart Button End -->
			</div>
		</div>
	</div>
	<!-- Single Product End -->
</c:if>