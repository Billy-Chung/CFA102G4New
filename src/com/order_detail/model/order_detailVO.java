package com.order_detail.model;

import java.io.Serializable;

public class order_detailVO implements Serializable{
	private Integer order_detail_no;
	private Integer order_no;
	private Integer product_no;
	private Integer order_product_number;
	private Integer product_price;//
	private String product_name;//
	private Integer product_pro_detail_no;//
	private String promot_name;//
	
	



	public Integer getOrder_detail_no() {
		return order_detail_no;
	}





	public void setOrder_detail_no(Integer order_detail_no) {
		this.order_detail_no = order_detail_no;
	}





	public Integer getOrder_no() {
		return order_no;
	}





	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}





	public Integer getProduct_no() {
		return product_no;
	}





	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}





	public Integer getOrder_product_number() {
		return order_product_number;
	}





	public void setOrder_product_number(Integer order_product_number) {
		this.order_product_number = order_product_number;
	}





	public Integer getProduct_price() {
		return product_price;
	}





	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}





	public String getProduct_name() {
		return product_name;
	}





	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}





	public Integer getProduct_pro_detail_no() {
		return product_pro_detail_no;
	}





	public void setProduct_pro_detail_no(Integer product_pro_detail_no) {
		this.product_pro_detail_no = product_pro_detail_no;
	}





	public String getPromot_name() {
		return promot_name;
	}





	public void setPromot_name(String promot_name) {
		this.promot_name = promot_name;
	}





	@Override
	public String toString() {
		return "order_detailVO [ order_detail_no=" + order_detail_no + ", order_no=" + order_no +", product_no="+ product_no +
				", order_product_number=" + order_product_number + ", product_price=" + product_price +
				", product_name=" + product_name+", product_pro_detail_no=" + product_pro_detail_no+
				",promot_name=" + promot_name+"]";
	}
}
