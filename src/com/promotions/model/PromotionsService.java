package com.promotions.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.product.model.ProductVO;
import com.product.model.productService;
import com.product_promotions_detail.model.ProductPromotionService;
import com.product_promotions_detail.model.ProductPromotionsDetailVO;
import com.promo_photo.model.PromoPhotoService;
import com.promo_photo.model.PromoPhotoVO;

public class PromotionsService {

	private PromotionsDAO_interface dao = new PromotionsDAO();
	private productService productService = new productService();
	private ProductPromotionService productPromotionSvc = new ProductPromotionService();
	private PromoPhotoService photoService = new PromoPhotoService();

	// 新增
	public PromotionsVO addpromotions(String promot_name, java.sql.Date promot_date_start,
			java.sql.Date promot_date_end, String promot_status, String promot_type, String promot_discount_type,
			Integer promot_discount, Integer promot_reduce, String promot_comment, byte[] promot_photo) {

		PromotionsVO promotionsVO = new PromotionsVO();// promotionsVO()
		promotionsVO.setPromot_name(promot_name);
		promotionsVO.setPromot_date_start(promot_date_start);
		promotionsVO.setPromot_date_end(promot_date_end);
		promotionsVO.setPromot_status(promot_status);
		promotionsVO.setPromot_type(promot_type);
		promotionsVO.setPromot_discount_type(promot_discount_type);
		promotionsVO.setPromot_discount(promot_discount);
		promotionsVO.setPromot_reduce(promot_reduce);
		promotionsVO.setPromot_comment(promot_comment);
		promotionsVO.setPromot_photo(promot_photo);
		dao.add(promotionsVO);

		return promotionsVO;

	}

	// 更新
	public PromotionsVO updatepromotions(Integer promot_no, String promot_name, java.sql.Date promot_date_start,
			java.sql.Date promot_date_end, String promot_status, String promot_type, String promot_discount_type,
			Integer promot_discount, Integer promot_reduce, String promot_comment) {

		PromotionsVO promotionsVO = new PromotionsVO();// promotionsVO()
		promotionsVO.setPromot_no(promot_no);
		promotionsVO.setPromot_name(promot_name);
		promotionsVO.setPromot_date_start(promot_date_start);
		promotionsVO.setPromot_date_end(promot_date_end);
		promotionsVO.setPromot_status(promot_status);
		promotionsVO.setPromot_type(promot_type);
		promotionsVO.setPromot_discount_type(promot_discount_type);
		promotionsVO.setPromot_discount(promot_discount);
		promotionsVO.setPromot_reduce(promot_reduce);
		promotionsVO.setPromot_comment(promot_comment);
		dao.update(promotionsVO);
		return promotionsVO;
	}

	// 刪除
	public void deletepromotions(Integer promot_no) {
		dao.delete(promot_no);
	}

	// 主鍵查詢
	public PromotionsVO getOnePromotions(Integer promot_no) {
		return dao.findByPromotNoPk(promot_no);
	}

	// 查詢
	public List<PromotionsVO> getAll() {
		return dao.getAll();
	}

	// 新增照片 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	// 將照片顯示出來
	public static void readPicture(byte[] bytes) throws IOException {
		String imgurl = "images/promotions1.png";
		FileOutputStream fos = new FileOutputStream(imgurl);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	public PromotionsVO addPromoWithAllProdDiscount(PromotionsVO promotionsVO, Map<String, PromoPhotoVO> photoMap) {

		// 建立行銷活動
		Integer promoNo = dao.add(promotionsVO);

		// 由 promoPhotoSvc 建立圖片庫
		photoService.insertPromoPhotos(promoNo, photoMap);

		// 折扣類型 0打折/1減價
		String discountType = promotionsVO.getPromot_discount_type();
		// 折扣值
		Integer discount = promotionsVO.getPromot_discount();
		List<ProductVO> prodList = productService.getAll();

		for (ProductVO prod : prodList) {
			Integer discountPrice = getDiscountPrice(prod, discountType, discount);
			ProductPromotionsDetailVO productPromotionsDetailVO = new ProductPromotionsDetailVO();
			productPromotionsDetailVO.setProduct_no(prod.getProduct_no());
			productPromotionsDetailVO.setProduct_pro_price(prod.getProduct_price());
			productPromotionsDetailVO.setProductSpecialPrice(discountPrice);
			productPromotionsDetailVO.setPromot_no(promoNo);

			productPromotionSvc.addProductPromotion(productPromotionsDetailVO);
		}
		return promotionsVO;
	}

	// 設計折扣類型的方法
	public Integer getDiscountPrice(ProductVO productVO, String discountType, Integer discountValue) {
		Integer finalVal = 0;
		Integer newPrice = 0;

		// 從產品表取得舊的產品價格
		Integer oldPrice = productVO.getProduct_price();
		// 判斷折扣類型

		if (discountType.equals("0")) {
			// 折扣類型0打折，商品打折
			newPrice = (oldPrice * discountValue) / 100;
		} else if (discountType.equals("1")) {
			// 折扣類型1減價，商品減價
			newPrice = (oldPrice - discountValue);
		} else {
			System.out.println("還沒設計");
		}

		// 最低為10，判斷價格後設定成最後結果價
		Integer lowestPrice = 100;
		if (newPrice < lowestPrice) {
			finalVal = lowestPrice;
		} else {
			finalVal = newPrice;
		}
		return finalVal;
	}

	public void updatePromotion(PromotionsVO promotionsVO, Map<String, PromoPhotoVO> photoMap) {
		// 取得所有行銷商品明細

		List<ProductPromotionsDetailVO> promoDetailList = productPromotionSvc
				.getProductPromoDetails(promotionsVO.getPromot_no());

		// 更新全部的行銷商品項目
		for (ProductPromotionsDetailVO promoDetail : promoDetailList) {
			ProductPromotionsDetailVO updatedProductPromoDetail = updateDiscountPrice(promoDetail,
					promotionsVO.getPromot_discount_type(), promotionsVO.getPromot_discount());
			productPromotionSvc.updateProductPromoDetail(updatedProductPromoDetail);
		}

		for (PromoPhotoVO promoPhotoVO : photoMap.values()) {
			promoPhotoVO.setPromot_no(promotionsVO.getPromot_no());
			photoService.updatePhoto(promoPhotoVO);
		}

	}
	public void updatePromotion2(PromotionsVO promotionsVO, Map<String, PromoPhotoVO> photoMap) {
		// 取得所有行銷商品明細
		
		updatepromotions(promotionsVO.getPromot_no(), promotionsVO.getPromot_name(), promotionsVO.getPromot_date_start(), promotionsVO.getPromot_date_end(), promotionsVO.getPromot_status(), promotionsVO.getPromot_type(), promotionsVO.getPromot_discount_type(), promotionsVO.getPromot_discount(), promotionsVO.getPromot_reduce(), promotionsVO.getPromot_comment());
		
		for (PromoPhotoVO promoPhotoVO : photoMap.values()) {
			promoPhotoVO.setPromot_no(promotionsVO.getPromot_no());
			photoService.updatePhoto(promoPhotoVO);
		}
		
	}

	// 設計折扣類型的方法
	public ProductPromotionsDetailVO updateDiscountPrice(ProductPromotionsDetailVO productPromotionsDetailVO,
			String discountType, Integer discountValue) {
		Integer finalVal = 0;
		Integer newPrice = 0;

		// 從產品表取得舊的產品價格
		Integer oldPrice = productPromotionsDetailVO.getProduct_pro_price();

		// 判斷折扣類型
		if (discountType.equals("0")) {
			// 折扣類型0打折，商品打折
			newPrice = (oldPrice * discountValue) / 100;
		} else if (discountType.equals("1")) {
			// 折扣類型1減價，商品減價
			newPrice = (oldPrice - discountValue);
		} else {
			System.out.println("還沒設計");
		}

		// 最低為10，判斷價格後設定成最後結果價
		Integer lowestPrice = 100;
		if (newPrice < lowestPrice) {
			finalVal = lowestPrice;
		} else {
			finalVal = newPrice;
		}

		productPromotionsDetailVO.setProductSpecialPrice(finalVal);

		return productPromotionsDetailVO;
	}

	public PromotionsVO getCurrentPromotion() {

		return dao.getCurrentPromotion();

	}

}
