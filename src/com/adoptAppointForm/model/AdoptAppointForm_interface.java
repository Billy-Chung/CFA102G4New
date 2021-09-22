package com.adoptAppointForm.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface AdoptAppointForm_interface {
	public AdoptAppointFormVO insert (AdoptAppointFormVO adoptAppointForm);
	
	public void update (AdoptAppointFormVO adoptAppointForm, Connection con);
	
	public AdoptAppointFormVO findByPK (Integer appoint_form_no );
	
	public AdoptAppointFormVO findByDate (Date reserve_date );
	
	public List<AdoptAppointFormVO> findAdoptMebNo (Integer adopt_meb_no );
	
	public List<AdoptAppointFormVO> createDate ();
}
