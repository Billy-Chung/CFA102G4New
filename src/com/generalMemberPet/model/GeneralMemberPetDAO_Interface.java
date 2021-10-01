package com.generalMemberPet.model;

import java.util.List;

public interface GeneralMemberPetDAO_Interface {
	public GeneralMemberPetVO insert(GeneralMemberPetVO gmpVO,String[] petClassNoBox);
	public void update(GeneralMemberPetVO gmpVO);
	public void delete(Integer gmpno);
	public GeneralMemberPetVO findByGeneralMemberPetNoPK(Integer ger_meb_pet_no);
	public GeneralMemberPetVO findByPrimaryKey(Integer gmpno);
	public List<GeneralMemberPetVO> findByGeneralMemberPetNo(Integer adopt_pet_no);
	public List<GeneralMemberPetVO> getAll();
}
