package com.product_promotions_detail.model;

import java.util.List;

public interface ProductPromotionsDetailDAO_interface {
	public void add(ProductPromotionsDetailVO product_promotions_detail);
	public void update(ProductPromotionsDetailVO product_promotions_detail);
	public void delete(Integer product_pro_detail_no);
	public List<ProductPromotionsDetailVO> getPromotionProductByPromotionNo(Integer promoNo);
	public ProductPromotionsDetailVO findByProductPro_DetailPK(Integer product_pro_detail_no);
	public List <ProductPromotionsDetailVO> getAllproductPromotions_Detail();
	public ProductPromotionsDetailVO getCurrentPromoProduct(Integer promoNo, Integer productNo);
}
