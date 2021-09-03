package com.shopping_cart.model;

import java.util.List;

import com.followPet.model.FollowPetDAO;
import com.followPet.model.FollowPetDAO_Interface;
import com.followPet.model.FollowPetVO;

public class shopping_cartTest {
	public static void main(String[] args) {
		//實作DAO
		shopping_cartDAO_interface dao = new shopping_cartDAO();
	
		// 新增(雙主鍵)，FK要有東西才可以
//		shopping_cartVO shopping_cart1 = new shopping_cartVO();
//		shopping_cart1.setGen_meb_no(2);
//		shopping_cart1.setProduct_no(1);
//		shopping_cart1.setCart_product_number(6);
//		
//		dao.add(shopping_cart1);
//		System.out.println("新增成功");
			

		// 修改(雙主鍵)，FK要有東西才可以
		shopping_cartVO shopping_cart2 = new shopping_cartVO();
		shopping_cart2.setGen_meb_no(1);//修改目標
		shopping_cart2.setProduct_no(2);
		shopping_cart2.setCart_product_number(100);
		dao.update(shopping_cart2);
		System.out.println("更新成功");
				
		// 刪除，有綁FK參考表要先砍
//		dao.delete(1);
//		System.out.println("刪除成功");
		
		//主鍵查詢
//		shopping_cartVO shopping_cart3 =dao.findShoppingCartPk(1);//主鍵名稱
//		System.out.print(shopping_cart3.getGen_meb_no()+ ",");
//		System.out.print(shopping_cart3.getProduct_no()+ ",");
//		System.out.print(shopping_cart3.getCart_product_number());
	
	    // 查詢
		List<shopping_cartVO> list = dao.getAllshopping_cart();
//		for (shopping_cartVO shopping_cart : list) {
//			System.out.print(shopping_cart.getGen_meb_no()+ ",");
//			System.out.print(shopping_cart.getProduct_no()+ ",");
//			System.out.print(shopping_cart.getCart_product_number());
//			System.out.println();
//		}			
	}		
}
