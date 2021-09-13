package com.petClass.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.petClass.model.PetClassService;


public class PetClassServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("updateClassName".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {					
				String requestURL = req.getParameter("requestURL");
				Integer petClassNo = new Integer( req.getParameter("petClassNo"));
				String petClassName = req.getParameter("petClassName");
				String petClassState = req.getParameter("petClassState");
				String noSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				
				if (petClassName.trim().length() == 0) {
					errorMsgs.put("petClassName", "請輸入分類名稱!!");
				} else if (!petClassName.trim().matches(noSignReg)) {
					errorMsgs.put("petClassName", "分類名稱: 只能是中英文 !!");
				}			
		
				
				if (!errorMsgs.isEmpty()) {				
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/petClass.jsp");
					failureView.forward(req, res);
					return;
				}
				PetClassService petClassSvc = new PetClassService();
				petClassSvc.updatePetClass(petClassName, petClassState, petClassNo);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/petClass.jsp");
				failureView.forward(req, res);
			}
	
		}
		
		if("updateClassState".equals(action)){
			String requestURL = req.getParameter("requestURL");
			Integer petClassNo = new Integer( req.getParameter("petClassNo"));
			String petClassName = req.getParameter("petClassName");
			String petClassState = req.getParameter("petClassState");			
			Integer changeState = new Integer(petClassState);
			String afterChange;
			
			if(changeState == 0) {
				afterChange = "1";
			}else {
				afterChange="0";
			}
			
			PetClassService petClassSvc = new PetClassService();
			petClassSvc.updatePetClass(petClassName, afterChange, petClassNo);
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}		
		
	}
}