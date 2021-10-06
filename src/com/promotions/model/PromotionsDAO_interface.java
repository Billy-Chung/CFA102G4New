package com.promotions.model;

import java.util.List;


public interface PromotionsDAO_interface { 
	// 此"介面"定義對資料庫的相關存取"抽象方法"，定義規格，以後換資料庫不用改
	public Integer add(PromotionsVO promotions);//新增
	public void update(PromotionsVO promotions);//修改
	public void delete(Integer promot_no);//刪除
	public PromotionsVO findByPromotNoPk(Integer promot_no);//主鍵findByPk
	public List<PromotionsVO> getAll();//查	
	public PromotionsVO getCurrentPromotion();
}


