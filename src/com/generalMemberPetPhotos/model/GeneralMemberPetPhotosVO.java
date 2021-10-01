package com.generalMemberPetPhotos.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class GeneralMemberPetPhotosVO implements Serializable {
	private Integer gen_meb_photo_no;
	private Integer gen_meb_no;
	private byte[] gen_meb_pet_photo;
	private String gen_pet_cover_state;
	private Timestamp gen_pet_cover_change_time;
	
	
	public Integer getGen_meb_photo_no() {
		return gen_meb_photo_no;
	}
	public void setGen_meb_photo_no(Integer gen_meb_photo_no) {
		this.gen_meb_photo_no = gen_meb_photo_no;
	}
	public Integer getGen_meb_no() {
		return gen_meb_no;
	}
	public void setGen_meb_no(Integer gen_meb_no) {
		this.gen_meb_no = gen_meb_no;
	}
	public byte[] getGen_meb_pet_photo() {
		return gen_meb_pet_photo;
	}
	public void setGen_meb_pet_photo(byte[] gen_meb_pet_photo) {
		this.gen_meb_pet_photo = gen_meb_pet_photo;
	}
	public String getGen_pet_cover_state() {
		return gen_pet_cover_state;
	}
	public void setGen_pet_cover_state(String gen_pet_cover_state) {
		this.gen_pet_cover_state = gen_pet_cover_state;
	}
	public Timestamp getGen_pet_cover_change_time() {
		return gen_pet_cover_change_time;
	}
	public void setGen_pet_cover_change_time(Timestamp gen_pet_cover_change_time) {
		this.gen_pet_cover_change_time = gen_pet_cover_change_time;
	}
	
	
	
}
