package com.adoptMember.controller;

import java.util.List;
import java.util.TimerTask;

import com.adoptAppointForm.model.AdoptAppointFormService;
import com.adoptAppointForm.model.AdoptAppointFormVO;

public class AdoptMemberTimer extends TimerTask {

	@Override
	public void run() {	
		AdoptAppointFormService AdoptAppointFormSvc = new AdoptAppointFormService();
		List<AdoptAppointFormVO> newDate = AdoptAppointFormSvc.create15Date();		
		
		for(AdoptAppointFormVO thisDate : newDate) {		
			AdoptAppointFormSvc.insertAdoptAppointForm(thisDate.getAdopt_meb_no(),thisDate.getAppoint_date(),thisDate.getFinifh_appoint_num(),thisDate.getAppoint_limit());		
		}
	}

}
