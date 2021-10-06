package com.adoptMember.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.adoptAppointForm.model.AdoptAppointFormService;
import com.adoptAppointForm.model.AdoptAppointFormVO;
import com.adoptPet.controller.AdoptPetServlet;


@WebListener
public class AdoptMemberListener implements ServletContextListener {
	Timer timer;
	private Logger logger = Logger.getLogger(AdoptPetServlet.class);
    public void contextDestroyed(ServletContextEvent sce)  { 
    	timer.cancel();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	timer = new Timer();
    	logger.info("新增預約表資料");
    	timer.schedule(new AdoptMemberTimer(), 1000, 10000);
    }
    
	
}
