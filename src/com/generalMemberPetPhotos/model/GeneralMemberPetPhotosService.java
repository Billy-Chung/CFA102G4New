package com.generalMemberPetPhotos.model;

	
	import java.sql.Timestamp;
import java.util.List;

import com.adoptPetPhoto.model.AdoptPetPhotoVO;
import com.generalMemberPetPhotos.model.GeneralMemberPetPhotosVO;

	public class GeneralMemberPetPhotosService {
		
		private GeneralMemberPetPhotosDAO_Interface dao;
	
		public GeneralMemberPetPhotosService() {
		
			dao = new GeneralMemberPetPhotosDAO();
		}
	
	public GeneralMemberPetPhotosVO insertGeneralMemberPetPhotos(Integer gen_meb_no,byte[] gen_meb_pet_photo,String gen_pet_cover_state,Timestamp gen_pet_cover_change_time ) {
		
		GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
		
		
		gmppVO.setGen_meb_no(gen_meb_no);
		gmppVO.setGen_meb_pet_photo(gen_meb_pet_photo);
		gmppVO.setGen_pet_cover_state(gen_pet_cover_state);
		gmppVO.setGen_pet_cover_change_time(gen_pet_cover_change_time);
		gmppVO = dao.insert(gmppVO);
		
		
		return gmppVO;
	}
	
	
		public void updateGeneralMemberPetPhotos(String gen_pet_cover_state,Timestamp gen_pet_cover_change_time,Integer gen_meb_no ) {
			GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
			
			gmppVO.setGen_pet_cover_state(gen_pet_cover_state);
			gmppVO.setGen_pet_cover_change_time(gen_pet_cover_change_time);
			gmppVO.setGen_meb_photo_no(gen_meb_no);
			
			dao.update(gmppVO);
			
		}
		
		
		
		public void deleteGeneralMemberPetPhotos(Integer gen_meb_photo_no) {
			dao.delete(gen_meb_photo_no);
		}
		
		public GeneralMemberPetPhotosVO getOneGeneralMemberPetPhotos(Integer gen_meb_photo_no) {
			return dao.findByPrimaryKey(gen_meb_photo_no);
		}
		
		public List<GeneralMemberPetPhotosVO> findByGeneralMemberPetNO(Integer gen_meb_no) {
			List<GeneralMemberPetPhotosVO> gmppList = dao.findByGeneralMemberPetNo(gen_meb_no);
			return gmppList;
		}
		
		public List<GeneralMemberPetPhotosVO> getAll(){
			return dao.getAll();
		}
		
		public GeneralMemberPetPhotosVO findByPhotoCover(Integer gen_meb_no) {

			GeneralMemberPetPhotosVO gmppVO = dao.findByPhotoCover(gen_meb_no);

			return gmppVO;
		}
		
		public GeneralMemberPetPhotosVO findByPK(Integer gen_meb_no) {
			GeneralMemberPetPhotosVO gmppVO = dao.findByPrimaryKey(gen_meb_no);
			return gmppVO;
		}
	
}
