package com.shopping_cart.model;

import java.io.Serializable;

//可序列化
public class shopping_cartVO implements Serializable{
	private Integer gen_meb_no;
	private Integer product_no;
	private Integer cart_product_number;
	
	public Integer getGen_meb_no() {
		return gen_meb_no;
	}
	public void setGen_meb_no(Integer gen_meb_no) {
		this.gen_meb_no = gen_meb_no;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public Integer getCart_product_number() {
		return cart_product_number;
	}
	public void setCart_product_number(Integer cart_product_number) {
		this.cart_product_number = cart_product_number;
	}
	
	
	
	
	
}
