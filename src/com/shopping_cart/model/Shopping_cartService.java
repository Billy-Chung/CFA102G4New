package com.shopping_cart.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.promotions.model.promotionsDAO;
import com.promotions.model.promotionsDAO_interface;
import com.promotions.model.promotionsVO;

public class Shopping_cartService {
	private shopping_cartDAO_interface dao;
	public Shopping_cartService() {
		dao=new shopping_cartDAO();
	}
	
	//新增
		public shopping_cartVO addshopping_cart(Integer gen_meb_no, Integer product_no, Integer cart_product_number) {
			
			shopping_cartVO shopping_cartVO=new shopping_cartVO();
			shopping_cartVO.setProduct_no(product_no);
			shopping_cartVO.setGen_meb_no(gen_meb_no);
			shopping_cartVO.setCart_product_number(cart_product_number);
			dao.add(shopping_cartVO);
			return shopping_cartVO;
		}
		
		public shopping_cartVO  updateshopping_cart(Integer gen_meb_no, Integer product_no, Integer cart_product_number) {
			
			shopping_cartVO shopping_cartVO=new shopping_cartVO();
			shopping_cartVO.setProduct_no(product_no);
			shopping_cartVO.setGen_meb_no(gen_meb_no);
			shopping_cartVO.setCart_product_number(cart_product_number);
			dao.update(shopping_cartVO);
			return shopping_cartVO;
		}
		

		public void deleteshopping_cart(Integer shopping_cart) {
			dao.delete(shopping_cart);
		}
				
		
		public shopping_cartVO getOneshopping_cart(Integer gen_meb_no) {
			return dao.findShoppingCartPk(gen_meb_no);
		}		
				
		public List<shopping_cartVO> getAll() {
			return dao.getAllshopping_cart();
		}
	
	
	
	
	
	
	


}
