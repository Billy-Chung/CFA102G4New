package com.adoptMember.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.adoptAppointForm.model.AdoptAppointFormService;
import com.adoptAppointForm.model.AdoptAppointFormVO;


@WebListener
public class AdoptMemberListener implements ServletContextListener {
	Timer timer;
 
    public void contextDestroyed(ServletContextEvent sce)  { 
    	timer.cancel();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	timer = new Timer();
    	timer.schedule(new AdoptMemberTimer(), 1000, 1209600000);
    }
    
//    class AdoptMemberTimer extends TimerTask {
//
//    	@Override
//    	public void run() {	
//    		AdoptAppointFormService AdoptAppointFormSvc = new AdoptAppointFormService();
//    		List<AdoptAppointFormVO> newDate = AdoptAppointFormSvc.create15Date();		
//    		
//    		for(AdoptAppointFormVO thisDate : newDate) {		
//    			AdoptAppointFormSvc.insertAdoptAppointForm(thisDate.getAdopt_meb_no(),thisDate.getAppoint_date(),thisDate.getFinifh_appoint_num(),thisDate.getAppoint_limit());		
//    		}
//    	}
//
//    }
	
}
