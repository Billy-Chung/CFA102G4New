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
    
	
}
