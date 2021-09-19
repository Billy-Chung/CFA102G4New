package com.reservePet.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservePet.model.ReservePetService;
import com.reservePet.model.ReservePetVO;

public class ReservePetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("addReserve".equals(action)) {
			Integer whichPet = new Integer(req.getParameter("whichPet"));
			String reserveName = req.getParameter("reserveName");
			String reservePhone = req.getParameter("reservePhone");
			Date reserveDate = new java.sql.Date(System.currentTimeMillis());		
			Integer timeSelect = null;
		
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			if (reserveName.trim().length() == 0) {
				errorMsgs.put("reserveName", "預約人姓名: 請勿空白!!");
			}
			
			if (reservePhone.trim().length() == 0) {
				errorMsgs.put("reservePhone", "預約人電話: 請勿空白!!");
			}
			
			try{
				reserveDate = java.sql.Date.valueOf(req.getParameter("reserveDate"));
			}catch(Exception e) {
				errorMsgs.put("reserveDate", "預約日期: 請勿空白!!");
			}
			try{
				timeSelect = new Integer(req.getParameter("timeSelect"));
			}catch(Exception e) {
				errorMsgs.put("timeSelect", "預約時段: 請勿空白!!");
			}
			ReservePetVO reservePet = new ReservePetVO();
			reservePet.setAdopt_pet_no(whichPet);
			reservePet.setReserve_people_name(reserveName);
			reservePet.setReserve_people_phone(reservePhone);
			reservePet.setReserve_date(reserveDate);
			
			if (!errorMsgs.isEmpty()) {	
				req.setAttribute("reservePet", reservePet);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/adoptPet/reservePet.jsp");
				failureView.forward(req, res);
				return;
			}
			StringBuilder reserveTime = new StringBuilder();
			for (int i = 0; i < 24; i++) {
				if(i == timeSelect) {
					reserveTime.append(1);					
				}else {
					reserveTime.append(0);	
				}
			}
			ReservePetService ReservePetSvc = new ReservePetService();
			ReservePetSvc.insertReservePet(1, 1, whichPet, reserveName, reservePhone, reserveDate, reserveTime.toString(), "1",timeSelect);
			req.setAttribute("seccessReserve", "1");
			String url = "/adoptPet/addPet.do?action=goToDetail&PK=" + whichPet ;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
	}
	
}
