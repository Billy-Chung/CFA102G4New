package com.promo_photo.model;

import java.util.List;

public interface PromoPhotoDAOInterface {
	public void add(PromoPhotoVO promo_photo);//新增
	public void update(PromoPhotoVO promo_photo);//修改
//	public void delete(Integer promot_no);//刪除
//	public promo_photoVO findByPromotNoPk(Integer promot_no);//主鍵findByPk
	public List<PromoPhotoVO> getAll();//查	
}
