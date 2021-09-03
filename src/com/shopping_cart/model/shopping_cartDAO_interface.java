package com.shopping_cart.model;

import java.util.List;



public interface shopping_cartDAO_interface {
	public void add(shopping_cartVO pay_methodVO);//新增
	public void update(shopping_cartVO pay_methodVO);//修改
	public void delete(Integer pay_no);//刪除
	public shopping_cartVO findShoppingCartPk(Integer pay_no);//主鍵findByPk
	public List<shopping_cartVO> getAllshopping_cart();//查	
}
