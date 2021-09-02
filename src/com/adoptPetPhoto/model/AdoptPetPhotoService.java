package com.adoptPetPhoto.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AdoptPetPhotoService {
	AdoptPetPhoto_interface dao;

	public AdoptPetPhotoService() {
		dao = new AdoptPetPhotoDAO();
	}

	public AdoptPetPhotoVO insertAdoptPetPhoto(Integer adopt_pet_no, byte[] adopt_pet_photo,String adopt_pet_cover_state,Timestamp adopt_pet_cover_change_time) {
		AdoptPetPhotoVO adoptPetPhoto = new AdoptPetPhotoVO();

		adoptPetPhoto.setAdopt_pet_no(adopt_pet_no);
		adoptPetPhoto.setAdopt_pet_photo(adopt_pet_photo);
		adoptPetPhoto.setAdopt_pet_cover_state(adopt_pet_cover_state);
		adoptPetPhoto.setAdopt_pet_cover_change_time(adopt_pet_cover_change_time);
		adoptPetPhoto = dao.insert(adoptPetPhoto);

		return adoptPetPhoto;
	}

	public void updateAdoptPetPhoto(String adopt_pet_cover_state,Timestamp adopt_pet_cover_change_time,Integer adopt_pet_no) {
		AdoptPetPhotoVO adoptPetPhoto = new AdoptPetPhotoVO();
		adoptPetPhoto.setAdopt_pet_cover_state(adopt_pet_cover_state);
		adoptPetPhoto.setAdopt_pet_cover_change_time(adopt_pet_cover_change_time);
		adoptPetPhoto.setAdopt_pet_photo_no(adopt_pet_no);
		dao.update(adoptPetPhoto);
	}

	public void deleteAdoptPetPhoto(Integer adopt_pet_photo_no) {
		dao.delete(adopt_pet_photo_no);
	}

	public List<AdoptPetPhotoVO> findByadoptPetNo(Integer adopt_pet_no) {

		List<AdoptPetPhotoVO> adoptMemberPhotoList = dao.findByadoptPetNo(adopt_pet_no);

		return adoptMemberPhotoList;
	}

	public AdoptPetPhotoVO findByPhotoCover(Integer adopt_pet_no) {

		AdoptPetPhotoVO photoCover = dao.findByPhotoCover(adopt_pet_no);

		return photoCover;
	}
	
	public AdoptPetPhotoVO findByPK(Integer adopt_pet_no) {

		AdoptPetPhotoVO photoCover = dao.findByPK(adopt_pet_no);

		return photoCover;
	}

}
