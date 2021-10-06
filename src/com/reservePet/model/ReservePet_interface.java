package com.reservePet.model;

import java.sql.Date;
import java.util.List;

import com.adoptAppointForm.model.AdoptAppointFormVO;


public interface ReservePet_interface {
	
	public ReservePetVO insert(ReservePetVO reservePet, Date reserve_date ,Integer timeSelect);
	
	public void update(ReservePetVO reservePet,AdoptAppointFormVO adoptAppointForm);
	
	public ReservePetVO findByReservePetPK(Integer reserve_pet_no);
	
	public List<ReservePetVO> findByAdoptMebNo(Integer adopt_meb_no);
	
	public List<ReservePetVO> findByGenMebNo(Integer gen_meb_no);
	
	public List<ReservePetVO> findByAdoptPetNo(Integer adopt_pet_no);
}
