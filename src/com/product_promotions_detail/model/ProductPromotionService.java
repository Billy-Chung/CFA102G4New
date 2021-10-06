package com.product_promotions_detail.model;

import java.util.List;

import com.promotions.model.PromotionsDAO;
import com.promotions.model.PromotionsDAO_interface;
import com.promotions.model.PromotionsService;
import com.promotions.model.PromotionsVO;

public class ProductPromotionService {

	private ProductPromotionsDetailDAO_interface dao = new ProductPromotionsDetailDAO();
	private PromotionsDAO_interface promoDao = new PromotionsDAO();

	public void addProductPromotion(ProductPromotionsDetailVO productPromotionsDetailVO) {
		dao.add(productPromotionsDetailVO);
	}

	public List<ProductPromotionsDetailVO> getProductPromoDetails(Integer promoNo) {

		List<ProductPromotionsDetailVO> detailList = dao.getPromotionProductByPromotionNo(promoNo);
		return detailList;
	}

	public void updateProductPromoDetail(ProductPromotionsDetailVO productPromoDetail) {
		dao.update(productPromoDetail);
	}

	public List<ProductPromotionsDetailVO> findProductPromoDetailsByPromoNo(Integer promoNo) {

		List<ProductPromotionsDetailVO> list = dao.getPromotionProductByPromotionNo(promoNo);

		return list;
	}

	public ProductPromotionsDetailVO getCurrentProductPromotion(Integer productNo) {
		PromotionsVO promotionVO = promoDao.getCurrentPromotion();
		if (promotionVO == null) {
			return null;
		}

		Integer promoNo = promotionVO.getPromot_no();
		ProductPromotionsDetailVO productPromotionsDetailVO = dao.getCurrentPromoProduct(promoNo, productNo);

		return productPromotionsDetailVO;
	}

}
