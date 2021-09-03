package com.followPet.model;

import java.util.List;

import com.generalMember.model.GeneralMemberVO;

public class FollowPetService {
	private FollowPetDAO_Interface  dao;
	
	public FollowPetService() {
		dao = new FollowPetDAO();
	}
	
	public FollowPetVO addFollowPet(Integer gen_meb_no,Integer adopt_pet_no) 
	{
		FollowPetVO fpVO = new FollowPetVO();
		
		fpVO.setGen_meb_no(gen_meb_no);
		fpVO.setAdopt_pet_no(adopt_pet_no);
		
		return fpVO;
	}
	
	//預留給 Struts 2 用的
		public void addFollowPet(FollowPetVO fpVO) {
			dao.insert(fpVO);
		}
		
		public FollowPetVO updateFollowPet(Integer gen_meb_no,Integer adopt_pet_no) {
			FollowPetVO fpVO = new FollowPetVO();
			
			fpVO.setGen_meb_no(gen_meb_no);
			fpVO.setAdopt_pet_no(adopt_pet_no);
			
			
			dao.update(fpVO);
			
			return dao.findByPrimaryKey(gen_meb_no,adopt_pet_no); 
		}
		
		//預留給 Struts 2 用的
		public void updateFollowPet(FollowPetVO fpVO) {
			dao.update(fpVO);
		}
		
		public void deleteFollowPet(Integer gen_meb_no,Integer adopt_pet_no) {
			dao.delete(gen_meb_no,adopt_pet_no);
		}
		
		public FollowPetVO getOneFollowPet(Integer gen_meb_no,Integer adopt_pet_no) {
			return dao.findByPrimaryKey(gen_meb_no,adopt_pet_no);
		}
		
		public List<FollowPetVO> getAll(){
			return dao.getAll();
		}
	
}
