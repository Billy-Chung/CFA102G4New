package com.petClassList.model;

import java.sql.Connection;
import java.util.List;

public interface PetClassList_interface {
	
	public PetClassListVO insert(PetClassListVO petClassList, Connection con);	
	
	public void update(PetClassListVO petClassList);
	
	public void updateNewClass(PetClassListVO petClassList);	
	
	public List<PetClassListVO> findByAdoptPetNo(Integer adopt_pat_no);	
	
	public List<PetClassListVO> findByPetClassNo(Integer pet_class_no);	
	
}
