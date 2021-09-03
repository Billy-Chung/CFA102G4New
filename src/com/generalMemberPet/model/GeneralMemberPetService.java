package com.generalMemberPet.model;

import java.util.List;

import com.generalMemberPet.model.GeneralMemberPetVO;

public class GeneralMemberPetService {
	private GeneralMemberPetDAO_Interface dao;
	
	public GeneralMemberPetService() {
		dao = new GeneralMemberPetDAO();
	}
	
	public GeneralMemberPetVO addGeneralMemberPet(Integer gen_meb_pet_no,Integer adopt_pet_no,Integer pet_class_no,Integer gen_meb_no,String gen_meb_pet_breeds,String gen_meb_pet_gender,String gen_meb_pet_chip,String gen_meb_pet_sterilization,String gen_pet_color,String gen_pet_comment,String gen_pet_state) 
	{
		GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
		
		gmpVO.setGen_meb_pet_no(gen_meb_pet_no);
		gmpVO.setAdopt_pet_no(adopt_pet_no);
		gmpVO.setPet_class_no(pet_class_no);
		gmpVO.setGen_meb_no(gen_meb_no);
		gmpVO.setGen_meb_pet_breeds(gen_meb_pet_breeds);
		gmpVO.setGen_meb_pet_gender(gen_meb_pet_gender);
		gmpVO.setGen_meb_pet_chip(gen_meb_pet_chip);
		gmpVO.setGen_meb_pet_sterilization(gen_meb_pet_sterilization);
		gmpVO.setGen_pet_color(gen_pet_color);
		gmpVO.setGen_pet_comment(gen_pet_comment);
		gmpVO.setGen_pet_state(gen_pet_state);
		
		dao.insert(gmpVO);
		
		return gmpVO;
		
	}
	
	//預留給 Struts 2 用的
	public void addGeneralMemberPet(GeneralMemberPetVO gmpVO) {
		dao.insert(gmpVO);
	}
	
	public GeneralMemberPetVO updateGeneralMemberPet(Integer gen_meb_pet_no,Integer adopt_pet_no,Integer pet_class_no,Integer gen_meb_no,String gen_meb_pet_breeds,String gen_meb_pet_gender,String gen_meb_pet_chip,String gen_meb_pet_sterilization,String gen_pet_color,String gen_pet_comment,String gen_pet_state) {
		GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
		
		gmpVO.setGen_meb_pet_no(gen_meb_pet_no);
		gmpVO.setAdopt_pet_no(adopt_pet_no);
		gmpVO.setPet_class_no(pet_class_no);
		gmpVO.setGen_meb_no(gen_meb_no);
		gmpVO.setGen_meb_pet_breeds(gen_meb_pet_breeds);
		gmpVO.setGen_meb_pet_gender(gen_meb_pet_gender);
		gmpVO.setGen_meb_pet_chip(gen_meb_pet_chip);
		gmpVO.setGen_meb_pet_sterilization(gen_meb_pet_sterilization);
		gmpVO.setGen_pet_color(gen_pet_color);
		gmpVO.setGen_pet_comment(gen_pet_comment);
		gmpVO.setGen_pet_state(gen_pet_state);
		
		dao.update(gmpVO);
		
		return dao.findByPrimaryKey(gen_meb_pet_no); 
	}
	
	//預留給 Struts 2 用的
	public void updateGeneralMemberPet(GeneralMemberPetVO gmpVO) {
		dao.update(gmpVO);
	}
	
	public void deleteGeneralMemberPet(Integer gen_meb_pet_no) {
		dao.delete(gen_meb_pet_no);
	}
	
	public GeneralMemberPetVO getOneGeneralMemberPet(Integer gen_meb_pet_no) {
		return dao.findByPrimaryKey(gen_meb_pet_no);
	}
	
	public List<GeneralMemberPetVO> getAll(){
		return dao.getAll();
	}
	
}
