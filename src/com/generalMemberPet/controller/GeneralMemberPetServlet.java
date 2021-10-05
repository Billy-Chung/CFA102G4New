package com.generalMemberPet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adoptPet.model.AdoptPetVO;
import com.generalMemberPet.model.GeneralMemberPetService;
import com.generalMemberPet.model.GeneralMemberPetVO;
import com.generalMemberPetPhotos.model.GeneralMemberPetPhotosService;
import com.petClass.model.PetClassService;
import com.petClass.model.PetClassVO;
import com.petClassList.model.PetClassListService;
import com.petClassList.model.PetClassListVO;


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
		
		
		if("addGenMebPet".equals(action)) { 
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				Integer adoptPetNo = null;
				Integer genMebNo = null;
				String genMebPetBreeds =req.getParameter("gmpb");
				String genPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String genMebPetGender = req.getParameter("gender");
				String genMebPetChip = req.getParameter("petchip");
				String genPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String genMebPetSteril = req.getParameter("petsteril");
				String genMebPetColor = req.getParameter("petcolor");
				String genMebPetComment = req.getParameter("petcomment");
				String genMebPetState = req.getParameter("petstate");
				String[] petClassNoBox = req.getParameterValues("petClassNo");
			
				//領養寵物FK
				
				if(req.getParameter("adpno").trim().isEmpty()) {
					adoptPetNo = 0;
				}
				//System.out.println(adoptPetNo);
				
				//一般會員FK
				genMebNo = Integer.valueOf(req.getParameter("gmno"));
				
				//寵物品種
				if(genMebPetBreeds.trim().length() == 0) {
					genMebPetBreeds ="品種未知";
				}else if (!genMebPetBreeds.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetBreeds","請輸入中、英文字母!!");
				}
				
				//寵物性別
				if(genMebPetGender==null) {
					genMebPetGender ="性別未知";
				}
				
				//寵物晶片號碼
				if(genMebPetChip.trim().length()==0) {
					errorMsgs.put("genMebPetChip", "請輸入寵物晶片號碼");
				}else if (!genMebPetChip.trim().matches(genPetNoChinessReg)) {
					errorMsgs.put("genMebPetChip","寵物晶片號碼: 只能是英文字母和數字!!");
				}
				
				//是否絕育
				if(genMebPetSteril == null) {
					genMebPetSteril ="是否絕育未知";
				}
				
				//毛色
				if(genMebPetColor.trim().length() == 0) {
					genMebPetColor="毛色未知";
				} else if(!genMebPetColor.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetColor", "寵物毛色: 請輸入中、英文字母!!");
					
				}
				
				//寵物心得
				if(genMebPetComment == null) {
					genMebPetComment ="未輸入寵物心得";
				}
				
				//寵物狀態
				
				try {
					Integer genMebPetStatetest = new Integer(genMebPetState.trim());
					if(genMebPetStatetest !=0 && genMebPetStatetest!=1) {
						errorMsgs.put("genMebPetStatetest","寵物領養狀態: 請勿修改狀態!");
					}
				} catch(Exception e) {
					errorMsgs.put("genMebPetStatetest", "寵物領養狀態: 請勿修改狀態!");
				}
				
				if(petClassNoBox == null) {
					errorMsgs.put("adoptPetClass", "寵物至少需要一個分類!!");
				}
				
				
				GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
				gmpVO.setAdopt_pet_no(adoptPetNo);
				gmpVO.setGen_meb_no(genMebNo);
				gmpVO.setGen_meb_pet_breeds(genMebPetBreeds);
				gmpVO.setGen_meb_pet_gender(genMebPetGender);
				gmpVO.setGen_meb_pet_chip(genMebPetChip);
				gmpVO.setGen_meb_pet_sterilization(genMebPetSteril);
				gmpVO.setGen_pet_color(genMebPetColor);
				gmpVO.setGen_pet_comment(genMebPetComment);
				gmpVO.setGen_pet_state(genMebPetState);
					
				
				//錯誤處理
				if(!errorMsgs.isEmpty()) {
					PetClassService petClassSvc = new PetClassService();
					List<PetClassVO> petClass = petClassSvc.getAll();
					List<PetClassVO> allPetClass = petClassSvc.getAll();
					int[] intPetClassNoBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt)
							.toArray();
					List<PetClassVO> check = new ArrayList<>();
					
					for(PetClassVO petClassVO : petClass) {
						for(int checkno : intPetClassNoBox) {
							if(petClassVO.getPet_class_no()== checkno) {
								check.add(petClassVO);
							}
						}
					}
					petClass.removeAll(check);
					
					req.setAttribute("GeneralMemberPetVO",gmpVO);
					req.setAttribute("petClassNoBox", intPetClassNoBox);
					req.setAttribute("myNoCheckPetClass", petClass);
					req.setAttribute("allPetClass", allPetClass);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/addGeneralMemberPet.jsp");
					failureView.forward(req, res);
					return;
				}
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				gmpSvc.addGeneralMemberPet(adoptPetNo,genMebNo,genMebPetBreeds,genMebPetGender,genMebPetChip,genMebPetSteril,genMebPetColor,genMebPetComment,genMebPetState, petClassNoBox);
				
				String url = "/front_end/GeneralMemberPet/GeneralMemberPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			
			} catch(Exception e) {
				errorMsgs.put("無法取得資料:",  e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/addGeneralMemberPet.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				Integer genMebPetNo = new Integer(req.getParameter("genMebPetNo"));
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				GeneralMemberPetVO gmpVO = gmpSvc.findByGeneralMemberPetNoPK(genMebPetNo);
				PetClassListService petClassListService = new PetClassListService();
				
				List<PetClassListVO> thisPetClass = petClassListService.findByGeneralMemberPetNo(genMebPetNo);
				PetClassService petClassService = new PetClassService();
				List<PetClassVO> allPetClass = petClassService.getAll();
				List<PetClassVO> myPetClass = new ArrayList<>();
				
				for (PetClassVO petClass : allPetClass) {
					for (PetClassListVO petClassList : thisPetClass) {
						if (petClass.getPet_class_no() == petClassList.getPet_class_no()) {
							myPetClass.add(petClass);
						}
					}
				}
				allPetClass.removeAll(myPetClass);
				
				req.setAttribute("GeneralMemberPetVO2", gmpVO);
				req.setAttribute("allPetClass", allPetClass);
				req.setAttribute("thisPetClass", thisPetClass);
				String url = "/front_end/GeneralMemberPet/update_gmp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/GeneralMemberPet.jsp");
				failureView.forward(req,res);
			}
		}
		
		if("update".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				
				Integer genMebPetNo = new Integer(req.getParameter("genMebPetNo"));
				Integer adoptNo;
				String genMebPetBreeds =req.getParameter("gmpb");
				String genPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String genMebPetGender = req.getParameter("gender");
				String genMebPetChip = req.getParameter("petchip");
				String genPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String genMebPetSteril = req.getParameter("petsteril");
				String genMebPetColor = req.getParameter("petcolor");
				String genMebPetComment = req.getParameter("petcomment");
				String genMebPetState = req.getParameter("petstate");
				String[] petClassNoBox = req.getParameterValues("petClassNo"); 
				
				//領養寵物FK				
				if(req.getParameter("adpno").trim().isEmpty()) {
					adoptNo = 0;
				} else {
					adoptNo = new Integer(req.getParameter("adpno").trim());
				}
				
				//寵物品種
				if(genMebPetBreeds.trim().length() == 0) {
					genMebPetBreeds ="品種未知";
				}else if (!genMebPetBreeds.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetBreeds","請輸入中、英文字母!!");
				}
				
				//寵物性別
				if(genMebPetGender==null) {
					genMebPetGender ="性別未知";
				}
				
				//寵物晶片號碼
				if(genMebPetChip.trim().length()==0) {
					errorMsgs.put("genMebPetChip", "請輸入寵物晶片號碼");
				}else if (!genMebPetChip.trim().matches(genPetNoChinessReg)) {
					errorMsgs.put("genMebPetChip","寵物晶片號碼: 只能是英文字母和數字!!");
				}
				
				//是否絕育
				if(genMebPetSteril == null) {
					genMebPetSteril ="是否絕育未知";
				}
				
				//毛色
				if(genMebPetColor.trim().length() == 0) {
					genMebPetColor="毛色未知";
				} else if(!genMebPetColor.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetColor", "寵物毛色: 請輸入中、英文字母!!");
					
				}
				
				//寵物心得
				if(genMebPetComment == null) {
					genMebPetComment ="未輸入寵物心得";
				}
				
				//寵物狀態
				
				try {
					Integer genMebPetStatetest = new Integer(genMebPetState.trim());
					if(genMebPetStatetest !=0 && genMebPetStatetest!=1) {
						errorMsgs.put("genMebPetStatetest","寵物領養狀態: 請勿修改狀態!");
					}
				} catch(Exception e) {
					errorMsgs.put("genMebPetStatetest", "寵物領養狀態: 請勿修改狀態!");
				}
				
				GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
				
				gmpVO.setAdopt_pet_no(adoptNo);
				gmpVO.setGen_meb_pet_breeds(genMebPetBreeds);
				gmpVO.setGen_meb_pet_gender(genMebPetGender);
				gmpVO.setGen_meb_pet_chip(genMebPetChip);
				gmpVO.setGen_meb_pet_sterilization(genMebPetSteril);
				gmpVO.setGen_pet_color(genMebPetColor);
				gmpVO.setGen_pet_comment(genMebPetComment);
				gmpVO.setGen_pet_state(genMebPetState);
				gmpVO.setGen_meb_pet_no(genMebPetNo);
				
			
				
				
				
				PetClassService petClassService = new PetClassService();
				PetClassListService petClassListService = new PetClassListService();
				List<PetClassVO> allPetClass = petClassService.getAll();
				List<PetClassVO> myPetClass = new ArrayList<>();
				if(petClassNoBox == null) {
					errorMsgs.put("adoptPetClass", "寵物至少需要一個分類!!");
				}else {				
//					List<PetClassListVO> thisFailPetClass = petClassListService.findByAdoptPetNo(adoptPetNo);
					int[] intPetClassBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt).toArray();
					for (PetClassVO petClass : allPetClass) {
						for (int petClassList : intPetClassBox) {
							if (petClass.getPet_class_no() == petClassList) {
								myPetClass.add(petClass);
							}
						}
					}
					allPetClass.removeAll(myPetClass);
				}						
				
				//錯誤處理
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("GeneralMemberPetVO2",gmpVO);
					req.setAttribute("allPetClass", allPetClass);
					req.setAttribute("checkPetClass", myPetClass);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/update_gmp_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				
				gmpSvc.updateGeneralMemberPet(adoptNo,genMebPetBreeds,genMebPetGender,genMebPetChip,genMebPetSteril,genMebPetColor,genMebPetComment,genMebPetState,genMebPetNo);
				
				List<PetClassListVO> thisPetClass = petClassListService.findByGeneralMemberPetNo(genMebPetNo);
				List<Integer> allOkPetClass = Arrays.stream(petClassNoBox).map(Integer::parseInt)
						.collect(Collectors.toList());				
				int[] intPetClassNoBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt).toArray();
				
				List<Integer> myOkPetClass = new ArrayList<>();
				
				for (PetClassListVO xxx : thisPetClass) {
					petClassListService.updatePetClassListAdp(null, "0", xxx.getPet_class_list_no());
					for (int yyy : intPetClassNoBox) {
						if (xxx.getPet_class_no() == yyy) {
							myOkPetClass.add(yyy);
							petClassListService.updatePetClassListAdp(null, "1", xxx.getPet_class_list_no());
						}
					}

				}
				allOkPetClass.removeAll(myOkPetClass);
				for(int zzz : allOkPetClass) {
					petClassListService.updateNewClassGen(null, zzz, genMebPetNo, "1");
				}

				if (requestURL.equals("/front_end/GeneralMemberPet/searchPetPage.jsp")) {
					List<GeneralMemberPetVO> searchPet = gmpSvc.getAll();
					List<GeneralMemberPetVO> searchList = new ArrayList<>();
					searchList = searchPet.stream()
							.filter(p -> p.getGen_meb_pet_chip().contains(req.getParameter("whichChip")))
							.collect(Collectors.toList());

					req.setAttribute("searchList", searchList);
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch(Exception e) {
				
				errorMsgs.put("errorMsgs",e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/update_gmp_input.jsp");
				failureView.forward(req,res);
				
			}
		}
		
		if("delete".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs",errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				Integer genMebPetNo = new Integer(req.getParameter("genMebPetNo"));
				Integer genMebNo = null;
				String genMebPetBreeds =req.getParameter("gmpb");
				String genPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String genMebPetGender = req.getParameter("gender");
				String genMebPetChip = req.getParameter("petchip");
				String genPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String genMebPetSteril = req.getParameter("petsteril");
				String genMebPetColor = req.getParameter("petcolor");
				String genMebPetComment = req.getParameter("petcomment");
				Integer genMebPetState = new Integer(req.getParameter("petstate"));
				
				if(genMebPetState == 0) {
					genMebPetState=1;
				} else {
					genMebPetState=0;
				}
				
				//一般會員FK
				if(req.getParameter("gmno").trim().isEmpty()) {
					genMebNo = 0;
				} else {
					genMebNo = new Integer(req.getParameter("gmno").trim());
				}
				
				//寵物品種
				if(genMebPetBreeds.trim().length() == 0) {
					genMebPetBreeds ="品種未知";
				}else if (!genMebPetBreeds.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetBreeds","請輸入中、英文字母!!");
				}
				
				//寵物性別
				if(genMebPetGender==null) {
					genMebPetGender ="性別未知";
				}
				
				//寵物晶片號碼
				if(genMebPetChip.trim().length()==0) {
					errorMsgs.put("genMebPetChip", "請輸入寵物晶片號碼");
				}else if (!genMebPetChip.trim().matches(genPetNoChinessReg)) {
					errorMsgs.put("genMebPetChip","寵物晶片號碼: 只能是英文字母和數字!!");
				}
				
				//是否絕育
				if(genMebPetSteril == null) {
					genMebPetSteril ="是否絕育未知";
				}
				
				//毛色
				if(genMebPetColor.trim().length() == 0) {
					genMebPetColor="毛色未知";
				} else if(!genMebPetColor.trim().matches(genPetNoSignReg)) {
					errorMsgs.put("genMebPetColor", "寵物毛色: 請輸入中、英文字母!!");
				}
				
				//寵物心得
				if(genMebPetComment == null) {
					genMebPetComment ="未輸入寵物心得";
				}
				
				GeneralMemberPetVO gmpVO = new GeneralMemberPetVO();
				gmpVO.setGen_meb_no(genMebNo);
				gmpVO.setGen_meb_pet_breeds(genMebPetBreeds);
				gmpVO.setGen_meb_pet_gender(genMebPetGender);
				gmpVO.setGen_meb_pet_chip(genMebPetChip);
				gmpVO.setGen_meb_pet_sterilization(genMebPetSteril);
				gmpVO.setGen_pet_color(genMebPetColor);
				gmpVO.setGen_pet_comment(genMebPetComment);
				gmpVO.setGen_pet_state(genMebPetState.toString());
				gmpVO.setGen_meb_pet_no(genMebPetNo);
				
				//錯誤處理
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("gmpVO",gmpVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/select_page.jsp"); 
					failureView.forward(req,res);
					return;
				}
				
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				gmpSvc.updateGeneralMemberPet(genMebNo,genMebPetBreeds,genMebPetGender,genMebPetChip,genMebPetSteril,genMebPetColor,genMebPetComment,genMebPetState.toString(),genMebPetNo);
				
				if(requestURL.equals("/front_end/GeneralMember/searchPetPage.jsp")) {
					List<GeneralMemberPetVO> searchPet = gmpSvc.getAll();
					List<GeneralMemberPetVO> searchList = new ArrayList<>();
					searchList = searchPet.stream()
							.filter(p -> p.getGen_meb_pet_chip().contains(req.getParameter("whichChip")))
							.collect(Collectors.toList());

					req.setAttribute("searchList", searchList);
					
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
				
			} catch(Exception e) {
				errorMsgs.put("刪除資料失敗:", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/listAllGeneralMemberPet.jsp");
				failureView.forward(req, res);
			}
		}
		
		if(("searchFromChip").equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			String searchWord = req.getParameter("whichChip");
			
			if(searchWord.trim().length() == 0) {
				errorMsgs.put("searchFile","請輸入晶片號碼");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMemberPet/GeneralMemberPet.jsp");
				failureView.forward(req,res);
				return;
			}
			
			GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
			List<GeneralMemberPetVO> searchPet = gmpSvc.getAll();
			List<GeneralMemberPetVO> searchList = new ArrayList<>();
			searchList = searchPet.stream().filter(p -> p.getGen_meb_pet_chip().contains(searchWord))
					.collect(Collectors.toList());
			req.setAttribute("searchList", searchList);
			String url = "/front_end/GeneralMemberPet/searchPetPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if("goToDetail".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			GeneralMemberPetVO gmpDetail = gmpSvc.findByGeneralMemberPetNoPK(PK);
			//List<GeneralMemberPetPhotosVO> gmppList = gmppSvc.findByadoptPetNo(PK);
			//req.setAttribute("gmppList", gmppList);
			req.setAttribute("gmpDetail",gmpDetail);
			String url = "/front_end/GeneralMemberPet/petDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}	
	}

}
