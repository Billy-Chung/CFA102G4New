package com.adoptPet.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adoptPet.model.AdoptPetService;
import com.adoptPet.model.AdoptPetVO;
import com.adoptPetPhoto.model.AdoptPetPhotoService;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;
import com.petClass.model.PetClassService;
import com.petClass.model.PetClassVO;
import com.petClassList.model.PetClassListService;
import com.petClassList.model.PetClassListVO;

public class AdoptPetServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addPet".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adoptMebNo = null;
				Integer genMebNo = null;
				String adoptPetBreeds = req.getParameter("adopt_pet_breeds");
				String adoptPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String adoptPetGender = req.getParameter("adopt_pet_gender");
				String adoptPetComeForm = req.getParameter("adopt_pet_come_form");
				String adoptPetHaveSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-)]*$";
				java.sql.Date adoptPetJoinDate = null;
				String adoptPetChip = req.getParameter("adopt_pet_chip");
				String adoptPetJoinReason = req.getParameter("adopt_pet_join_reason");
				String captureAddress = req.getParameter("capture_address");
				String adoptPetSterilization = req.getParameter("adopt_pet_sterilization");
				String containNumber = req.getParameter("contain_number");
				String adoptPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String adoptPetColor = req.getParameter("adopt_pet_color");
				String adoptPetState = req.getParameter("adopt_pet_state");
				String[] petClassNoBox = req.getParameterValues("petClassNo");
//				領養會員FK
				try {
					adoptMebNo = new Integer(req.getParameter("adopt_meb_no").trim());
				} catch (Exception e) {
					errorMsgs.put("adoptMebNo", "寵物領養會員: 請勿竄改資料!");
				}

//				一般會員FK
				if (req.getParameter("gen_meb_no").trim().isEmpty()) {
					genMebNo = 0;
				}

//				寵物品種
				if (adoptPetBreeds.trim().length() == 0) {
					adoptPetBreeds = "品種未知";
				} else if (!adoptPetBreeds.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物品種: 只能是中、英文字母!!");
				}

//				寵物性別

				if (adoptPetGender == null) {
					adoptPetGender = "性別未知";
				}

//				寵物來源
				if (adoptPetComeForm.trim().length() == 0) {
					adoptPetComeForm = "來源未知";
				} else if (!adoptPetComeForm.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("adoptPetComeForm", "領養寵物來源: 只能是中、英文字母、數字、_和-符號!!");
				}

//				入所日期
				try {
					adoptPetJoinDate = java.sql.Date.valueOf(req.getParameter("adopt_pet_join_date").trim());
				} catch (IllegalArgumentException e) {
					long miliseconds = System.currentTimeMillis();
					java.sql.Date date = new java.sql.Date(miliseconds);
					adoptPetJoinDate = date;
				}

//				晶片號碼
				if (adoptPetChip.trim().length() == 0) {
					errorMsgs.put("adoptPetChip", "請輸入領養寵物晶片號碼!!");
				} else if (!adoptPetChip.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("adoptPetChip", "領養寵物晶片號碼: 只能是英文字母和數字!!");
				}

//				進所原因
				if (adoptPetJoinReason.trim().length() == 0) {
					adoptPetJoinReason = "進所原因未知";
				} else if (!adoptPetJoinReason.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetJoinReason", "領養寵物進所原因: 只能是中、英文字母!!");
				}

//				捕獲地址
				if (captureAddress.trim().length() == 0) {
					captureAddress = "捕獲地址未知";
				} else if (!captureAddress.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("errorMsgs", "領養寵物捕獲地址: 只能是中、英文字母、數字、_和-符號!!");
				}

//				是否絕育
				if (adoptPetSterilization == null) {
					adoptPetSterilization = "是否絕育未知";
				}

//				收容編號

				if (containNumber.trim().length() == 0) {
					containNumber = "000000000000000";
				} else if (!containNumber.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("containNumber", "領養寵物收容編號: 只能是英文字母和數字!!");
				}

//				毛色				
				if (adoptPetColor.trim().length() == 0) {
					adoptPetColor = "毛色未知";
				} else if (!adoptPetColor.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物毛色: 只能是中、英文字母!!");
				}

//				寵物領養狀態

				try {
					Integer adoptPetStatetest = new Integer(adoptPetState.trim());
					if (adoptPetStatetest != 0 && adoptPetStatetest != 1) {
						errorMsgs.put("adoptPetStatetest", "寵物領養狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.put("adoptPetStatetest", "寵物領養狀態: 請勿竄改資料!");
				}

				AdoptPetVO adoptPet = new AdoptPetVO();
				adoptPet.setAdopt_meb_no(adoptMebNo);
				adoptPet.setGen_meb_no(genMebNo);
				adoptPet.setAdopt_pet_breeds(adoptPetBreeds);
				adoptPet.setAdopt_pet_gender(adoptPetGender);
				adoptPet.setAdopt_pet_come_form(adoptPetComeForm);
				adoptPet.setAdopt_pet_join_date(adoptPetJoinDate);
				adoptPet.setAdopt_pet_chip(adoptPetChip);
				adoptPet.setAdopt_pet_join_reason(adoptPetJoinReason);
				adoptPet.setCapture_address(captureAddress);
				adoptPet.setAdopt_pet_sterilization(adoptPetSterilization);
				adoptPet.setContain_number(containNumber);
				adoptPet.setAdopt_pet_color(adoptPetColor);
				adoptPet.setAdopt_pet_state(adoptPetState);

//				資料錯誤return
				if (!errorMsgs.isEmpty()) {
					PetClassService petClassSvc = new PetClassService();
					List<PetClassVO> petClass = petClassSvc.getAll();
					List<PetClassVO> allPetClass = petClassSvc.getAll();
					int[] intPetClassNoBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt)
							.toArray();
					List<PetClassVO> isCheck = new ArrayList<>();
					;
					for (PetClassVO petClassVO : petClass) {
						for (int checkNo : intPetClassNoBox) {
							if (petClassVO.getPet_class_no() == checkNo) {
								isCheck.add(petClassVO);
							}
						}
					}
					petClass.removeAll(isCheck);

					req.setAttribute("adoptPetVO", adoptPet);
					req.setAttribute("petClassNoBox", intPetClassNoBox);
					req.setAttribute("myNoCheckPetClass", petClass);
					req.setAttribute("allPetClass", allPetClass);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/addPet.jsp");
					failureView.forward(req, res);
					return;
				}

				AdoptPetService adoptPetSvc = new AdoptPetService();
				adoptPetSvc.insertAdoptPet(adoptMebNo, genMebNo, adoptPetBreeds, adoptPetGender, adoptPetComeForm,
						adoptPetJoinDate, adoptPetChip, adoptPetJoinReason, captureAddress, adoptPetSterilization,
						containNumber, adoptPetColor, adoptPetState, petClassNoBox);

				String url = "/back_end/adopt/adoptPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/addPet.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs2", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer adoptMebNo = new Integer(req.getParameter("adoptPetNo"));
				/*************************** 2.開始查詢資料 ****************************************/
				AdoptPetService adoptPetService = new AdoptPetService();
				AdoptPetVO adoptPetVO = adoptPetService.findByAdoptPetNoPK(adoptMebNo);
				PetClassListService petClassListService = new PetClassListService();

				List<PetClassListVO> thisPetClass = petClassListService.findByAdoptPetNo(adoptMebNo);
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

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adoptPetVO2", adoptPetVO);
				req.setAttribute("allPetClass", allPetClass);
				req.setAttribute("thisPetClass", thisPetClass);
				String url = "/back_end/adopt/updatePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update請求

			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {

				Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
				Integer genMebNo;
				String adoptPetBreeds = req.getParameter("adopt_pet_breeds");
				String adoptPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String adoptPetGender = req.getParameter("adopt_pet_gender");
				String adoptPetComeForm = req.getParameter("adopt_pet_come_form");
				String adoptPetHaveSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-)]*$";
				java.sql.Date adoptPetJoinDate = null;
				String adoptPetChip = req.getParameter("adopt_pet_chip");
				String adoptPetJoinReason = req.getParameter("adopt_pet_join_reason");
				String captureAddress = req.getParameter("capture_address");
				String adoptPetSterilization = req.getParameter("adopt_pet_sterilization");
				String containNumber = req.getParameter("contain_number");
				String adoptPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String adoptPetColor = req.getParameter("adopt_pet_color");
				String adoptPetState = req.getParameter("adopt_pet_state");
				String[] petClassNoBox = req.getParameterValues("petClassNo");

//				一般會員FK		
				if (req.getParameter("gen_meb_no").trim().isEmpty()) {					
					genMebNo = 0;
				} else {
					genMebNo = new Integer(req.getParameter("gen_meb_no").trim());
				}

//				寵物品種
				if (adoptPetBreeds.trim().length() == 0) {
					adoptPetBreeds = "品種未知";
				} else if (!adoptPetBreeds.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物品種: 只能是中、英文字母!!");
				}

//				寵物性別

				if (adoptPetGender == null) {
					adoptPetGender = "性別未知";
				}

//				寵物來源
				if (adoptPetComeForm.trim().length() == 0) {
					adoptPetComeForm = "來源未知";
				} else if (!adoptPetComeForm.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("adoptPetComeForm", "領養寵物來源: 只能是中、英文字母、數字、_和-符號!!");
				}

//				入所日期
				try {
					adoptPetJoinDate = java.sql.Date.valueOf(req.getParameter("adopt_pet_join_date").trim());
				} catch (IllegalArgumentException e) {
					long miliseconds = System.currentTimeMillis();
					java.sql.Date date = new java.sql.Date(miliseconds);
					adoptPetJoinDate = date;
				}

//				晶片號碼
				if (adoptPetChip.trim().length() == 0) {
					errorMsgs.put("adoptPetChip", "請輸入領養寵物晶片號碼!!");
				} else if (!adoptPetChip.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("adoptPetChip", "領養寵物晶片號碼: 只能是英文字母和數字!!");
				}

//				進所原因
				if (adoptPetJoinReason.trim().length() == 0) {
					adoptPetJoinReason = "進所原因未知";
				} else if (!adoptPetJoinReason.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetJoinReason", "領養寵物進所原因: 只能是中、英文字母!!");
				}

//				捕獲地址
				if (captureAddress.trim().length() == 0) {
					captureAddress = "捕獲地址未知";
				} else if (!captureAddress.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("errorMsgs", "領養寵物捕獲地址: 只能是中、英文字母、數字、_和-符號!!");
				}

//				是否絕育
				if (adoptPetSterilization == null) {
					adoptPetSterilization = "是否絕育未知";
				}

//				收容編號

				if (containNumber.trim().length() == 0) {
					containNumber = "0000000000000000";
				} else if (!containNumber.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("containNumber", "領養寵物收容編號: 只能是英文字母和數字!!");
				}

//				毛色				
				if (adoptPetColor.trim().length() == 0) {
					adoptPetColor = "毛色未知";
				} else if (!adoptPetColor.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物毛色: 只能是中、英文字母!!");
				}

//				寵物領養狀態

				try {
					Integer adoptPetStatetest = new Integer(adoptPetState.trim());
					if (adoptPetStatetest != 0 && adoptPetStatetest != 1) {
						errorMsgs.put("adoptPetStatetest", "寵物領養狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.put("adoptPetStatetest", "寵物領養狀態: 請勿竄改資料!");
				}

				AdoptPetVO adoptPet = new AdoptPetVO();
				
				adoptPet.setGen_meb_no(genMebNo);				
				adoptPet.setAdopt_pet_breeds(adoptPetBreeds);
				adoptPet.setAdopt_pet_gender(adoptPetGender);
				adoptPet.setAdopt_pet_come_form(adoptPetComeForm);
				adoptPet.setAdopt_pet_join_date(adoptPetJoinDate);
				adoptPet.setAdopt_pet_chip(adoptPetChip);
				adoptPet.setAdopt_pet_join_reason(adoptPetJoinReason);
				adoptPet.setCapture_address(captureAddress);
				adoptPet.setAdopt_pet_sterilization(adoptPetSterilization);
				adoptPet.setContain_number(containNumber);
				adoptPet.setAdopt_pet_color(adoptPetColor);
				adoptPet.setAdopt_pet_state(adoptPetState);
				adoptPet.setAdopt_pet_no(adoptPetNo);
				
				PetClassListService petClassListService = new PetClassListService();
//				List<PetClassListVO> thisFailPetClass = petClassListService.findByAdoptPetNo(adoptPetNo);
				int[] intPetClassBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt).toArray();
				PetClassService petClassService = new PetClassService();
				List<PetClassVO> allPetClass = petClassService.getAll();
				List<PetClassVO> myPetClass = new ArrayList<>();

				for (PetClassVO petClass : allPetClass) {
					for (int petClassList : intPetClassBox) {
						if (petClass.getPet_class_no() == petClassList) {
							myPetClass.add(petClass);
						}
					}
				}
				allPetClass.removeAll(myPetClass);

//				資料錯誤return
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptPetVO2", adoptPet);
					req.setAttribute("allPetClass", allPetClass);
					req.setAttribute("checkPetClass", myPetClass);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/updatePet.jsp");
					failureView.forward(req, res);
					return;
				}
		
				AdoptPetService adoptPetSvc = new AdoptPetService();
			
				adoptPetSvc.updateAdoptPet(genMebNo, adoptPetBreeds, adoptPetGender, adoptPetComeForm, adoptPetJoinDate,
						adoptPetChip, adoptPetJoinReason, captureAddress, adoptPetSterilization, containNumber,
						adoptPetColor, adoptPetState, adoptPetNo);
			
				List<PetClassListVO> thisPetClass = petClassListService.findByAdoptPetNo(adoptPetNo);
				List<Integer> allOkPetClass = Arrays.stream(petClassNoBox).map(Integer::parseInt)
						.collect(Collectors.toList());				
				int[] intPetClassNoBox = Arrays.asList(petClassNoBox).stream().mapToInt(Integer::parseInt).toArray();
				
				List<Integer> myOkPetClass = new ArrayList<>();

				for (PetClassListVO xxx : thisPetClass) {
					petClassListService.updatePetClassList(null, "0", xxx.getPet_class_list_no());
					for (int yyy : intPetClassNoBox) {
						if (xxx.getPet_class_no() == yyy) {
							myOkPetClass.add(yyy);
							petClassListService.updatePetClassList(null, "1", xxx.getPet_class_list_no());
						}
					}

				}
				allOkPetClass.removeAll(myOkPetClass);
				for(int zzz : allOkPetClass) {
					petClassListService.updateNewClass(adoptPetNo, zzz, null, "1");
				}

				if (requestURL.equals("/back_end/adopt/searchPetPage.jsp")) {
					List<AdoptPetVO> searchPet = adoptPetSvc.getAll();
					List<AdoptPetVO> searchList = new ArrayList<>();
					searchList = searchPet.stream()
							.filter(p -> p.getAdopt_pet_chip().contains(req.getParameter("whichChip")))
							.collect(Collectors.toList());

					req.setAttribute("searchList", searchList);
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
			
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/updatePet.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
				Integer genMebNo = null;
				String adoptPetBreeds = req.getParameter("adopt_pet_breeds");
				String adoptPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String adoptPetGender = req.getParameter("adopt_pet_gender");
				String adoptPetComeForm = req.getParameter("adopt_pet_come_form");
				String adoptPetHaveSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-)]*$";
				java.sql.Date adoptPetJoinDate = null;
				String adoptPetChip = req.getParameter("adopt_pet_chip");
				String adoptPetJoinReason = req.getParameter("adopt_pet_join_reason");
				String captureAddress = req.getParameter("capture_address");
				String adoptPetSterilization = req.getParameter("adopt_pet_sterilization");
				String containNumber = req.getParameter("contain_number");
				String adoptPetNoChinessReg = "^[(a-zA-Z0-9)]*$";
				String adoptPetColor = req.getParameter("adopt_pet_color");

				Integer adoptPetState = new Integer(req.getParameter("adopt_pet_state"));

				if (adoptPetState == 0) {
					adoptPetState = 1;
				} else {
					adoptPetState = 0;
				}

//				一般會員FK

				if (req.getParameter("gen_meb_no").trim().isEmpty()) {
					genMebNo = 0;
				} else {
					genMebNo = new Integer(req.getParameter("gen_meb_no").trim());
				}

//				寵物品種
				if (adoptPetBreeds.trim().length() == 0) {
					adoptPetBreeds = "品種未知";
				} else if (!adoptPetBreeds.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物品種: 只能是中、英文字母!!");
				}

//				寵物性別

				if (adoptPetGender == null) {
					adoptPetGender = "性別未知";
				}

//				寵物來源
				if (adoptPetComeForm.trim().length() == 0) {
					adoptPetComeForm = "來源未知";
				} else if (!adoptPetComeForm.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("adoptPetComeForm", "領養寵物來源: 只能是中、英文字母、數字、_和-符號!!");
				}

//				入所日期
				try {
					adoptPetJoinDate = java.sql.Date.valueOf(req.getParameter("adopt_pet_join_date").trim());
				} catch (IllegalArgumentException e) {
					long miliseconds = System.currentTimeMillis();
					java.sql.Date date = new java.sql.Date(miliseconds);
					adoptPetJoinDate = date;
				}

//				晶片號碼
				if (adoptPetChip.trim().length() == 0) {
					errorMsgs.put("adoptPetChip", "請輸入領養寵物晶片號碼!!");
				} else if (!adoptPetChip.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("adoptPetChip", "領養寵物晶片號碼: 只能是英文字母和數字!!");
				}

//				進所原因
				if (adoptPetJoinReason.trim().length() == 0) {
					adoptPetJoinReason = "進所原因未知";
				} else if (!adoptPetJoinReason.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetJoinReason", "領養寵物進所原因: 只能是中、英文字母!!");
				}

//				捕獲地址
				if (captureAddress.trim().length() == 0) {
					captureAddress = "捕獲地址未知";
				} else if (!captureAddress.trim().matches(adoptPetHaveSignReg)) {
					errorMsgs.put("errorMsgs", "領養寵物捕獲地址: 只能是中、英文字母、數字、_和-符號!!");
				}

//				是否絕育
				if (adoptPetSterilization == null) {
					adoptPetSterilization = "是否絕育未知";
				}

//				收容編號

				if (containNumber.trim().length() == 0) {
					containNumber = "0000000000000000";
				} else if (!containNumber.trim().matches(adoptPetNoChinessReg)) {
					errorMsgs.put("containNumber", "領養寵物收容編號: 只能是英文字母和數字!!");
				}

//				毛色				
				if (adoptPetColor.trim().length() == 0) {
					adoptPetColor = "毛色未知";
				} else if (!adoptPetColor.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("adoptPetBreeds", "領養寵物毛色: 只能是中、英文字母!!");
				}

				AdoptPetVO adoptPet = new AdoptPetVO();
				adoptPet.setGen_meb_no(genMebNo);
				adoptPet.setAdopt_pet_breeds(adoptPetBreeds);
				adoptPet.setAdopt_pet_gender(adoptPetGender);
				adoptPet.setAdopt_pet_come_form(adoptPetComeForm);
				adoptPet.setAdopt_pet_join_date(adoptPetJoinDate);
				adoptPet.setAdopt_pet_chip(adoptPetChip);
				adoptPet.setAdopt_pet_join_reason(adoptPetJoinReason);
				adoptPet.setCapture_address(captureAddress);
				adoptPet.setAdopt_pet_sterilization(adoptPetSterilization);
				adoptPet.setContain_number(containNumber);
				adoptPet.setAdopt_pet_color(adoptPetColor);
				adoptPet.setAdopt_pet_state(adoptPetState.toString());
				adoptPet.setAdopt_pet_no(adoptPetNo);

//				資料錯誤return
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptPetVO", adoptPet);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
					failureView.forward(req, res);
					return;
				}

				AdoptPetService adoptPetSvc = new AdoptPetService();
				adoptPetSvc.updateAdoptPet(genMebNo, adoptPetBreeds, adoptPetGender, adoptPetComeForm, adoptPetJoinDate,
						adoptPetChip, adoptPetJoinReason, captureAddress, adoptPetSterilization, containNumber,
						adoptPetColor, adoptPetState.toString(), adoptPetNo);

				if (requestURL.equals("/back_end/adopt/searchPetPage.jsp")) {
					List<AdoptPetVO> searchPet = adoptPetSvc.getAll();
					List<AdoptPetVO> searchList = new ArrayList<>();
					searchList = searchPet.stream()
							.filter(p -> p.getAdopt_pet_chip().contains(req.getParameter("whichChip")))
							.collect(Collectors.toList());

					req.setAttribute("searchList", searchList);
				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
				failureView.forward(req, res);
			}
		}

		if (("searchFromChip").equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			String searchWord = req.getParameter("whichChip");

			if (searchWord.trim().length() == 0) {
				errorMsgs.put("seachFile", "搜尋的晶片號碼請勿留空!!");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
				failureView.forward(req, res);
				return;
			}

			AdoptPetService adoptPetSvc = new AdoptPetService();
			List<AdoptPetVO> searchPet = adoptPetSvc.getAll();
			List<AdoptPetVO> searchList = new ArrayList<>();
			searchList = searchPet.stream().filter(p -> p.getAdopt_pet_chip().contains(searchWord))
					.collect(Collectors.toList());
			req.setAttribute("searchList", searchList);
			String url = "/back_end/adopt/searchPetPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if("goToDetail".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));			
			AdoptPetService adoptPetSvc = new AdoptPetService();
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			AdoptPetVO adoptPetDetail = adoptPetSvc.findByAdoptPetNoPK(PK);
			List<AdoptPetPhotoVO> petPhotoList = adoptPetPhotoService.findByadoptPetNo(PK);
			req.setAttribute("petPhotoList", petPhotoList);
			req.setAttribute("adoptPetDetail", adoptPetDetail);
			String url = "/front_end/adoptPet/petDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		}
		
		if("goToReserve".equals(action)) {
			String url = "/front_end/adoptPet/reservePet.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}
}
