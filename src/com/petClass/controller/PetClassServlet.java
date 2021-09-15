package com.petClass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adoptPet.model.AdoptPetService;
import com.adoptPet.model.AdoptPetVO;
import com.petClass.model.PetClassService;
import com.petClass.model.PetClassVO;
import com.petClassList.model.PetClassListService;
import com.petClassList.model.PetClassListVO;


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
					errorMsgs.put("petClassName", "修改時請輸入分類名稱!!");
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
		
		if("newPetClass".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String requestURL = req.getParameter("requestURL");
				String petClassName = req.getParameter("petClassName");
				String petClassState = req.getParameter("petClassState");
				String noSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				
				if (petClassName.trim().length() == 0) {
					errorMsgs.put("petClassName", "新增時請輸入分類名稱!!");
				} else if (!petClassName.trim().matches(noSignReg)) {
					errorMsgs.put("petClassName", "分類名稱: 只能是中英文 !!");
				}			
		
				
				if (!errorMsgs.isEmpty()) {				
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/petClass.jsp");
					failureView.forward(req, res);
					return;
				}
				
				PetClassService petClassSvc = new PetClassService();
				petClassSvc.insertPetClass(petClassName,petClassState);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/petClass.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("searchByClass".equals(action)) {
			String [] searchClass = req.getParameterValues("searchClass");
			String requestURL = req.getParameter("requestURL");
			PetClassListService PetClassListSvc = new PetClassListService();
			List<PetClassListVO> allThisClassPet = new ArrayList<>();
			int[] intPetClassBox = Arrays.asList(searchClass).stream().mapToInt(Integer::parseInt)
					.toArray();
			for(int thisClass:intPetClassBox) {
				List<PetClassListVO> nowPets = PetClassListSvc.findByPetClassNo(thisClass);
				for(PetClassListVO thisPet:nowPets) {
					allThisClassPet.add(thisPet);
				}
			}
			
			List<PetClassListVO> onPetClass = allThisClassPet.stream().filter(e -> e.getPet_class_list_state().equals("1") ).collect(Collectors.toList());
			AdoptPetService AdoptPetSvc = new AdoptPetService();
			List<AdoptPetVO> allPetList = AdoptPetSvc.getAll();
			List<AdoptPetVO> returnList = new ArrayList<>();
			for(AdoptPetVO adoptPet:allPetList) {
				for(PetClassListVO needPet:onPetClass) {
					if(adoptPet.getAdopt_pet_no() == needPet.getAdopt_pat_no()) {
						returnList.add(adoptPet);
					}					
				}
			}
			PetClassService petClassSvc = new PetClassService();
			 List<PetClassVO> allPetClass = petClassSvc.getAll();
			 List<PetClassVO> checkClass = new ArrayList<>();
			 for(PetClassVO allClass:allPetClass) {
					for(int isCheck:intPetClassBox) {
						if(allClass.getPet_class_no() == isCheck) {
							checkClass.add(allClass);
						}
					}
			 }
			 
			 allPetClass.removeAll(checkClass);		
			req.setAttribute("returnList", returnList);
			req.setAttribute("isCheck", checkClass);
			req.setAttribute("noCheck", allPetClass);
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			
			
		}
		
	}
}
