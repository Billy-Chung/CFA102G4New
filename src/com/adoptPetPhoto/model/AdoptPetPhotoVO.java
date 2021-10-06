package com.adoptPetPhoto.model;

import java.io.Serializable;
import java.sql.Timestamp;



public class AdoptPetPhotoVO implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private Integer adopt_pet_photo_no;
	private Integer adopt_pet_no;	
	private byte[] adopt_pet_photo;
	private String adopt_pet_cover_state;
	private Timestamp adopt_pet_cover_change_time;
	
	public Integer getAdopt_pet_photo_no() {
		return adopt_pet_photo_no;
	}
	public void setAdopt_pet_photo_no(Integer adopt_pet_photo_no) {
		this.adopt_pet_photo_no = adopt_pet_photo_no;
	}
	public Integer getAdopt_pet_no() {
		return adopt_pet_no;
	}
	public void setAdopt_pet_no(Integer adopt_pet_no) {
		this.adopt_pet_no = adopt_pet_no;
	}
	public byte[] getAdopt_pet_photo() {
		return adopt_pet_photo;
	}
	public void setAdopt_pet_photo(byte[] adopt_pet_photo) {
		this.adopt_pet_photo = adopt_pet_photo;
	}
	public String getAdopt_pet_cover_state() {
		return adopt_pet_cover_state;
	}
	public void setAdopt_pet_cover_state(String adopt_pet_cover_state) {
		this.adopt_pet_cover_state = adopt_pet_cover_state;
	}
	public Timestamp getAdopt_pet_cover_change_time() {
		return adopt_pet_cover_change_time;
	}
	public void setAdopt_pet_cover_change_time(Timestamp adopt_pet_cover_change_time) {
		this.adopt_pet_cover_change_time = adopt_pet_cover_change_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
