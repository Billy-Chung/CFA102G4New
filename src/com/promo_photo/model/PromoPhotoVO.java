package com.promo_photo.model;

import java.io.Serializable;
import java.util.Base64;

public class PromoPhotoVO implements Serializable{
	private Integer promot_no;
	private String promo_function;	
	private byte[] photo;
	private String mimetype;
	public Integer getPromot_no() {
		return promot_no;
	}
	public void setPromot_no(Integer promot_no) {
		this.promot_no = promot_no;
	}
	public String getPromo_function() {
		return promo_function;
	}
	public void setPromo_function(String promo_function) {
		this.promo_function = promo_function;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	
	public String getBase64Str() {
		String template = "data:%s;base64,%s";
		
		String encodedPhoto = Base64.getEncoder().encodeToString(getPhoto());
		
		return String.format(template, mimetype, encodedPhoto);
	}






}
