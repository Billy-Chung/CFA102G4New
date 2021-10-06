package com.petClassList.model;

import java.sql.Connection;
import java.util.List;

public interface PetClassList_interface {
	
	public PetClassListVO insertGen(PetClassListVO petClassList, Connection con);
	
	public PetClassListVO insert(PetClassListVO petClassList, Connection con);	
	
	public void update(PetClassListVO petClassList);
	
	public void updateAdp(PetClassListVO petClassList);
	
	public void updateNewClass(PetClassListVO petClassList);
	
	public void updateNewClassGen(PetClassListVO petClassList);
	
	public List<PetClassListVO> findByAdoptPetNo(Integer adopt_pat_no);	
	
	public List<PetClassListVO> findByPetClassNo(Integer pet_class_no);	
	
	public List<PetClassListVO> findByGeneralMemberPetNo(Integer gen_meb_pet_no);
	
}
