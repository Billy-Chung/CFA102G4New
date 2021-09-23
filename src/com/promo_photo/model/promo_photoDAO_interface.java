package com.promo_photo.model;

import java.util.List;

public interface promo_photoDAO_interface {
	public void add(promo_photoVO promo_photo);//新增
	public void update(promo_photoVO promo_photo);//修改
//	public void delete(Integer promot_no);//刪除
//	public promo_photoVO findByPromotNoPk(Integer promot_no);//主鍵findByPk
	public List<promo_photoVO> getAll();//查	
}
