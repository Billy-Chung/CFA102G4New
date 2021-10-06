package com.shpping_information.model;

import java.io.Serializable;

public class Shpping_informationVO implements Serializable {
	
	private Integer Shopping_information_no;
	private byte[] Shopping_img;
	private String Shopping_date;
	private String Shopping_tel;
	private String Shopping_fax;
	private String Shopping_add;
	
	public Integer getShopping_information_no() {
		return Shopping_information_no;
	}
	public void setShopping_information_no(Integer shopping_information_no) {
		Shopping_information_no = shopping_information_no;
	}
	public byte[] getShopping_img() {
		return Shopping_img;
	}
	public void setShopping_img(byte[] shopping_img) {
		Shopping_img = shopping_img;
	}
	public String getShopping_date() {
		return Shopping_date;
	}
	public void setShopping_date(String shopping_date) {
		Shopping_date = shopping_date;
	}
	public String getShopping_tel() {
		return Shopping_tel;
	}
	public void setShopping_tel(String shopping_tel) {
		Shopping_tel = shopping_tel;
	}
	public String getShopping_fax() {
		return Shopping_fax;
	}
	public void setShopping_fax(String shopping_fax) {
		Shopping_fax = shopping_fax;
	}
	public String getShopping_add() {
		return Shopping_add;
	}
	public void setShopping_add(String shopping_add) {
		Shopping_add = shopping_add;
	}
	
}
