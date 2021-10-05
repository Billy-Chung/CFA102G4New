package com.promo_photo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PromoPhotoService {

	PromoPhotoDAOInterface dao = new PromoPhotoDAO();

	public void insertPromoPhotos(Integer promoNo, Map<String, PromoPhotoVO> photoMap) {
		// 建立行銷圖片
		for (PromoPhotoVO promoPhotoVO : photoMap.values()) {
			promoPhotoVO.setPromot_no(promoNo);
			dao.add(promoPhotoVO);
		}
	}

	public PromoPhotoVO getPromoPhotoVO(int promoNo, String functionName) {

		List<PromoPhotoVO> photos = dao.getAll();
		for (PromoPhotoVO photo : photos) {
			if (photo.getPromot_no() == promoNo && photo.getPromo_function().equals(functionName)) {
				return photo;
			}
		}

		return null;
	}

	public void updatePhoto(PromoPhotoVO promoPhotoVO) {

		dao.update(promoPhotoVO);
	}

	public Map<String, PromoPhotoVO> getPromoPhotoVOMap(Integer promot_no) {
		List<PromoPhotoVO> photoList = dao.getAll();
		Map<String, PromoPhotoVO> photoMap = new HashMap<>();
		
		for(PromoPhotoVO photoObj:photoList) {
			if(photoObj.getPromot_no().equals(promot_no)) {
				String function = photoObj.getPromo_function();
				photoMap.put(function, photoObj);
			}
			
		}
		
		
		return photoMap;
	}

}
