package com.generalMemberPet.model;

import java.util.List;

import com.adoptPet.model.AdoptPetVO;
import com.generalMemberPet.model.GeneralMemberPetVO;

public class GeneralMemberPetService {
	private GeneralMemberPetDAO_Interface dao;
	
	public GeneralMemberPetService() {
		dao = new GeneralMemberPetDAO();
	}
	
	public GeneralMemberPetVO addGeneralMemberPet(Integer adopt_pet_no,Integer gen_meb_no,String gen_meb_pet_breeds,String gen_meb_pet_gender,String gen_meb_pet_chip,String gen_meb_pet_sterilization,String gen_pet_color,String gen_pet_comment,String gen_pet_state,String[] petClassNoBox) 
	{
		GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
		
		
		gmpVO.setAdopt_pet_no(adopt_pet_no);
		gmpVO.setGen_meb_no(gen_meb_no);
		gmpVO.setGen_meb_pet_breeds(gen_meb_pet_breeds);
		gmpVO.setGen_meb_pet_gender(gen_meb_pet_gender);
		gmpVO.setGen_meb_pet_chip(gen_meb_pet_chip);
		gmpVO.setGen_meb_pet_sterilization(gen_meb_pet_sterilization);
		gmpVO.setGen_pet_color(gen_pet_color);
		gmpVO.setGen_pet_comment(gen_pet_comment);
		gmpVO.setGen_pet_state(gen_pet_state);
		gmpVO = dao.insert(gmpVO,petClassNoBox);
		
		
		return gmpVO;
		
	}
	
	
	
	public void updateGeneralMemberPet(Integer Adopt_pet_no,String gen_meb_pet_breeds,String gen_meb_pet_gender,String gen_meb_pet_chip,String gen_meb_pet_sterilization,String gen_pet_color,String gen_pet_comment,String gen_pet_state,Integer gen_meb_pet_no) {
		GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
		
		gmpVO.setAdopt_pet_no(Adopt_pet_no);
		gmpVO.setGen_meb_pet_breeds(gen_meb_pet_breeds);
		gmpVO.setGen_meb_pet_gender(gen_meb_pet_gender);
		gmpVO.setGen_meb_pet_chip(gen_meb_pet_chip);
		gmpVO.setGen_meb_pet_sterilization(gen_meb_pet_sterilization);
		gmpVO.setGen_pet_color(gen_pet_color);
		gmpVO.setGen_pet_comment(gen_pet_comment);
		gmpVO.setGen_pet_state(gen_pet_state);
		gmpVO.setGen_meb_pet_no(gen_meb_pet_no);
		
		dao.update(gmpVO);
	}
	
	public GeneralMemberPetVO findByGeneralMemberPetNoPK(Integer gen_meb_pet_no) {

		GeneralMemberPetVO gmpVO = dao.findByGeneralMemberPetNoPK(gen_meb_pet_no);

		return gmpVO;
	}
	
	public List<GeneralMemberPetVO> findByAdoptMebNo(Integer adopt_pet_no) {

		List<GeneralMemberPetVO> gmpList = dao.findByGeneralMemberPetNo(adopt_pet_no);

		return gmpList;
	}
	
	public List<GeneralMemberPetVO> findByGenMebNo(Integer gen_meb_no) {

		List<GeneralMemberPetVO> gmpList = dao.findByGeneralMemberNo(gen_meb_no);

		return gmpList;
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
