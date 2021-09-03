package com.generalMemberPetPhotos.model;

	
	import java.util.List;
	
	import com.generalMemberPetPhotos.model.GeneralMemberPetPhotosVO;

	public class GeneralMemberPetPhotosService {
		
		private GeneralMemberPetPhotosDAO_Interface dao;
	
		public GeneralMemberPetPhotosService() {
		
			dao = new GeneralMemberPetPhotosDAO();
		}
	
	public GeneralMemberPetPhotosVO addAdoptGeneralMemberPetPhotos(Integer gen_meb_photo_no,Integer gen_meb_no,byte[] gen_meb_pet_photo) {
		
		GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
		
		gmppVO.setGen_meb_photo_no(gen_meb_photo_no);
		gmppVO.setGen_meb_no(gen_meb_no);
		gmppVO.setGen_meb_pet_photo(gen_meb_pet_photo);
		
		
		dao.insert(gmppVO);
		
		return gmppVO;
	}
	
	//預留給 Struts 2 用的
		public void addGeneralMemberPetPhotos(GeneralMemberPetPhotosVO gmppVO) {
			dao.insert(gmppVO);
		}
		
		public GeneralMemberPetPhotosVO updateGeneralMemberPetPhotos(Integer gen_meb_photo_no,Integer gen_meb_no,byte[] gen_meb_pet_photo) {
			GeneralMemberPetPhotosVO gmppVO = new GeneralMemberPetPhotosVO();
			
			gmppVO.setGen_meb_photo_no(gen_meb_photo_no);
			gmppVO.setGen_meb_no(gen_meb_no);
			gmppVO.setGen_meb_pet_photo(gen_meb_pet_photo);
			
			dao.update(gmppVO);
			
			return dao.findByPrimaryKey(gen_meb_photo_no); 
		}
		
		
		public void updateGeneralMemberPetPhotos(GeneralMemberPetPhotosVO gmppVO) {
			dao.update(gmppVO);
		}
		
		public void deleteGeneralMemberPetPhotos(Integer gen_meb_photo_no) {
			dao.delete(gen_meb_photo_no);
		}
		
		public GeneralMemberPetPhotosVO getOneGeneralMemberPetPhotos(Integer gen_meb_photo_no) {
			return dao.findByPrimaryKey(gen_meb_photo_no);
		}
		
		public List<GeneralMemberPetPhotosVO> getAll(){
			return dao.getAll();
		}
	
}
