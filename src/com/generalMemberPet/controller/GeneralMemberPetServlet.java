package com.generalMemberPet.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generalMemberPet.model.GeneralMemberPetService;
import com.generalMemberPet.model.GeneralMemberPetVO;


public class GeneralMemberPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GeneralMemberPetServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) { //來自select_page_jsp的請求
			
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				String str = req.getParameter("gmpno");
				if(str == null || (str.trim().length()==0)) {
					errorMsgs.add("請輸入一般會員寵物編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				Integer gmpno = null;
				try {
					gmpno = new Integer(str);
				} catch(Exception e) {
					errorMsgs.add("一般會員寵物編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				GeneralMemberPetVO gmpVO = gmpSvc.getOneGeneralMemberPet(gmpno);
				if(gmpVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMember/select_page.jsp");
					failureView.forward(req,res);
					return;
				}
				
				req.setAttribute("gmpVO",gmpVO);
				String url = "/front_end/GeneralMemberPet/listOneGeneralMemberPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
			
			
			} catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("front_end/GeneralMemberPet/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		//if("")
		
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				
				Integer adpno = new Integer(req.getParameter("adpno").trim());
				if(adpno==null) {
					errorMsgs.add("寵物編號請勿空白");
				}
				
				Integer gmno = new Integer(req.getParameter("gmno").trim());
				if(gmno == null) {
					errorMsgs.add("會員編號請勿空白");
				}
				
				String gmpb = req.getParameter("gmpb").trim();
				if(gmpb==null || gmpb.trim().length()==0) {
					errorMsgs.add("毛色請勿空白");
				}
				
				String gender = req.getParameter("gender").trim();
				if(gender==null || gender.trim().length()==0) {
					errorMsgs.add("性別請勿空白");
				}
				
				String petchip = req.getParameter("petchip").trim();
				if(petchip==null || petchip.trim().length()==0) {
					errorMsgs.add("晶片號碼請勿空白");
				}
				
				String petsteril = req.getParameter("petsteril").trim();
				if(petsteril==null || petsteril.trim().length()==0) {
					errorMsgs.add("此欄位請勿空白");
				}
				
				String petcolor = req.getParameter("petcolor").trim();
				if(petcolor==null || petcolor.trim().length()==0) {
					errorMsgs.add("毛色請勿空白");
				}
				
				String petcomment = req.getParameter("petcomment").trim();
				if(petcomment==null || petcomment.trim().length()==0) {
					errorMsgs.add("寵物心得請勿空白");
				}
				
				String petstate = req.getParameter("petstate").trim();
				if(petstate==null || petstate.trim().length()==0) {
					errorMsgs.add("一般會員寵物狀態請勿空白");
				}
				
				GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
				
				gmpVO.setGen_meb_no(gmno);
				gmpVO.setGen_meb_pet_breeds(gmpb);
				gmpVO.setGen_meb_pet_gender(gender);
				gmpVO.setGen_meb_pet_chip(petchip);
				gmpVO.setGen_meb_pet_sterilization(petsteril);
				gmpVO.setGen_pet_color(petcolor);
				gmpVO.setGen_pet_comment(petcomment);
				gmpVO.setGen_pet_state(petstate);
				
				if(!errorMsgs.isEmpty()) {
					
					req.setAttribute("gmpVO",gmpVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/listAllGeneralMemberPet.jsp");
					failureView.forward(req,res);
					return;
				}
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				gmpVO = gmpSvc.addGeneralMemberPet(adpno,gmno, gmpb, gender,petchip, petsteril, petcolor, petcomment, petstate);
				
				
				String url = "/front_end/GeneralMemberPet/listAllGeneralMemberPet.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/addGeneralMemberPet.jsp");
				failureView.forward(req,res);
			}
		}
		
		
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			System.out.println(action);
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				Integer gmpno = new Integer(req.getParameter("gmpno"));
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				gmpSvc.deleteGeneralMemberPet(gmpno);
				
				String url = "front_end/GeneralMemberPet/listAllGeneralMemberPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
				
			} catch(Exception e) {
				errorMsgs.add("刪除資料失敗:" +e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/listAllGeneralMemberPet.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
