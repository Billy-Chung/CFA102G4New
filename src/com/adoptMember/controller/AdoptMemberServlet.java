package com.adoptMember.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptAppointForm.model.AdoptAppointFormService;
import com.adoptAppointForm.model.AdoptAppointFormVO;
import com.adoptMember.model.AdoptMemberService;
import com.adoptMember.model.AdoptMemberVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reservePet.model.ReservePetService;
import com.reservePet.model.ReservePetVO;

@javax.servlet.annotation.MultipartConfig
public class AdoptMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (("gotoUpdate").equals(action)) {
			Integer adoptMebNo = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
			req.setAttribute("adoptMemberVO", adoptMemberVO);
			String url = "/back_end/adoptMember/adoptMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if (("gotoUpdateTime").equals(action)) {
			Integer adoptMebNo = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
			req.setAttribute("adoptMemberVO", adoptMemberVO);
			String url = "/back_end/adoptMember/adoptMemberTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if (("showMebPhoto").equals(action)) {
			res.setContentType("img/jepg");
			Integer PK = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO mebPhoto = adoptMemberSvc.findByAdoptMebNoPK(PK);
			byte[] showMebPhoto = mebPhoto.getAdopt_meb_photo();
			ServletOutputStream out = res.getOutputStream();
			out.write(showMebPhoto);
			out.close();
		}

		if ("showMebTime".equals(action)) {
			String whichDate = req.getParameter("whichDate").trim();
			AdoptAppointFormService AdoptAppointFormSvc = new AdoptAppointFormService();
			Optional<AdoptAppointFormVO> adoptAppointForm = AdoptAppointFormSvc.findAdoptMebNo(1).stream()
					.filter(e -> e.getAppoint_date().toString().equals(whichDate)).findFirst();

			if (adoptAppointForm.isPresent()) {
				PrintWriter out = res.getWriter();
				String finish_appoint_num = adoptAppointForm.get().getFinifh_appoint_num();
				String appoint_limit = adoptAppointForm.get().getAppoint_limit();
				List<Integer> okTime = new ArrayList<>();
				List<Integer> isMebTime = new ArrayList<>();
				Map<String, int[]> jsonMap = new HashMap();
				for (int i = 0; i < 24; i++) {
					okTime.add(new Integer(String.valueOf(appoint_limit.charAt(i))));
					isMebTime.add(new Integer(String.valueOf(finish_appoint_num.charAt(i))));
				}
				int[] okIntTime = okTime.stream().mapToInt(i -> i).toArray();
				int[] isMebIntTime = isMebTime.stream().mapToInt(i -> i).toArray();
				jsonMap.put("okTime", okIntTime);
				jsonMap.put("isMebTime", isMebIntTime);

				Gson gson = new Gson();
				String jsonString = gson.toJson(jsonMap);
				out.write(jsonString);
				out.close();
			}
		}

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("updateMeb".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			Part photo = req.getPart("adopt_meb_photo");

			Integer adoptMebNo = null;

			String adoptMebName = new String(req.getParameter("adopt_meb_name"));

			String adoptMebComment = new String(req.getParameter("adopt_meb_comment"));

			String adoptMebAddress = new String(req.getParameter("adopt_meb_address"));
			String adoptMebPhone = new String(req.getParameter("adopt_meb_phone"));
			String adoptMebEmail = new String(req.getParameter("adopt_meb_email"));
			String adoptMebAccount = new String(req.getParameter("adopt_meb_account"));
			String adoptMebPassword = new String(req.getParameter("adopt_meb_password"));
			String adoptMebState = new String(req.getParameter("adopt_meb_state"));
			String adoptMebAuth = new String(req.getParameter("adopt_meb_auth"));
			String adoptMebHoliday = new String(req.getParameter("adopt_meb_holiday"));
			String adoptMebLimit = new String(req.getParameter("adopt_meb_limit"));
			String adoptMebSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
			String adoptMebHaveSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9-)]*$";
			String adoptPetOnlyNumReg = "^[(0-9-)]*$";

			try {
				adoptMebNo = new Integer(req.getParameter("adopt_meb_no").trim());
			} catch (Exception e) {
				errorMsgs.put("adoptMebNo", "領養會員編號: 請勿竄改資料!");
			}

			if (adoptMebName.trim().length() == 0) {
				errorMsgs.put("adoptMebName", "請輸入領養機構名稱!!");
			} else if (!adoptMebName.trim().matches(adoptMebSignReg)) {
				errorMsgs.put("adoptPetBreeds", "領養機構名稱: 只能是中、英文字母!!");
			}

			if (adoptMebComment.trim().length() == 0) {
				adoptMebComment = "尚無簡介";
			}

			if (adoptMebAddress.trim().length() == 0) {
				errorMsgs.put("adoptMebAddress", "請輸入領養機構地址!!");
			} else if (!adoptMebAddress.trim().matches(adoptMebHaveSignReg)) {
				errorMsgs.put("adoptMebAddress", "領養機構地址: 只能是中、英文字母、數字、符號只有- !!");
			}

			if (adoptMebPhone.trim().length() == 0) {
				errorMsgs.put("adoptMebPhone", "請輸入領養機構電話!!");
			} else if (!adoptMebPhone.trim().matches(adoptPetOnlyNumReg)) {
				errorMsgs.put("adoptMebPhone", "領養機構電話: 只能是數字、符號只有- !!");
			}

			if (adoptMebEmail.trim().length() == 0) {
				errorMsgs.put("adoptMebEmail", "請輸入領養機構EMAIL !!");
			}

			if (adoptMebAccount.trim().length() == 0) {
				errorMsgs.put("adoptMebAccount", "請輸入領養機構帳號 !!");
			}

			if (adoptMebPassword.trim().length() == 0) {
				errorMsgs.put("adoptMebPassword", "請輸入領養機構密碼 !!");
			}

			AdoptMemberVO adoptMeb = new AdoptMemberVO();
			adoptMeb.setAdopt_meb_no(adoptMebNo);
			adoptMeb.setAdopt_meb_name(adoptMebName);
			adoptMeb.setAdopt_meb_comment(adoptMebComment);
			adoptMeb.setAdopt_meb_address(adoptMebAddress);
			adoptMeb.setAdopt_meb_phone(adoptMebPhone);
			adoptMeb.setAdopt_meb_email(adoptMebEmail);
			adoptMeb.setAdopt_meb_account(adoptMebAccount);
			adoptMeb.setAdopt_meb_password(adoptMebPassword);
			adoptMeb.setAdopt_meb_state(adoptMebState);
			adoptMeb.setAdopt_meb_auth(adoptMebAuth);
			adoptMeb.setAdopt_meb_holiday(adoptMebHoliday);
			adoptMeb.setAdopt_meb_limit(adoptMebLimit);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adoptMebVO", adoptMeb);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/adoptMember.jsp");
				failureView.forward(req, res);
				return;
			}

			Part adoptMebPhoto = req.getPart("adopt_meb_photo");
			InputStream in = adoptMebPhoto.getInputStream();
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO oldAdoptMember = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
			byte[] oldPhoto = oldAdoptMember.getAdopt_meb_photo();
			if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				adoptMemberSvc.updateAdoptMember(adoptMebName, adoptMebComment, buf, adoptMebAddress, adoptMebPhone,
						adoptMebEmail, adoptMebAccount, adoptMebPassword, adoptMebState, adoptMebAuth, adoptMebHoliday,
						adoptMebLimit, adoptMebNo);

				AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
				req.setAttribute("adoptMemberVO", adoptMemberVO);
				String url = "/back_end/adoptMember/adoptMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else if (oldPhoto.length != 0 && in.available() == 0) {
				adoptMemberSvc.updateAdoptMember(adoptMebName, adoptMebComment, oldPhoto, adoptMebAddress,
						adoptMebPhone, adoptMebEmail, adoptMebAccount, adoptMebPassword, adoptMebState, adoptMebAuth,
						adoptMebHoliday, adoptMebLimit, adoptMebNo);

				AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
				req.setAttribute("adoptMemberVO", adoptMemberVO);
				String url = "/back_end/adoptMember/adoptMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {

				adoptMemberSvc.updateAdoptMember(adoptMebName, adoptMebComment, null, adoptMebAddress, adoptMebPhone,
						adoptMebEmail, adoptMebAccount, adoptMebPassword, adoptMebState, adoptMebAuth, adoptMebHoliday,
						adoptMebLimit, adoptMebNo);
				AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);
				req.setAttribute("adoptMemberVO", adoptMemberVO);
				String url = "/back_end/adoptMember/adoptMember.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}

		if ("updateMebTime".equals(action)) {
			StringBuilder adoptMebHoliday = new StringBuilder();
			String[] ifDay = req.getParameterValues("ifDay");
			for (int d = 1; d < 8; d++) {
				String s = Integer.toString(d);
				if (Arrays.stream(ifDay).anyMatch(s::equals)) {
					adoptMebHoliday.append(1);
				} else {
					adoptMebHoliday.append(0);
				}
			}
			StringBuilder adoptMebLimit = new StringBuilder();
			for (int i = 0; i < 24; i++) {
				adoptMebLimit.append(req.getParameter("time" + i));
			}
			Integer PK = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO mebData = adoptMemberSvc.findByAdoptMebNoPK(PK);
			String name = mebData.getAdopt_meb_name();
			String comment = mebData.getAdopt_meb_comment();
			byte[] photo = mebData.getAdopt_meb_photo();
			String address = mebData.getAdopt_meb_address();
			String phone = mebData.getAdopt_meb_phone();
			String email = mebData.getAdopt_meb_email();
			String accoun = mebData.getAdopt_meb_account();
			String password = mebData.getAdopt_meb_password();
			String state = mebData.getAdopt_meb_state();
			String auth = mebData.getAdopt_meb_auth();
			String adoptMebHolidays = adoptMebHoliday.toString();
			String adoptMebLimits = adoptMebLimit.toString();

			adoptMemberSvc.updateAdoptMember(name, comment, photo, address, phone, email, accoun, password, state, auth,
					adoptMebHolidays, adoptMebLimits, PK);

			AdoptMemberVO newMebData = adoptMemberSvc.findByAdoptMebNoPK(PK);
			req.setAttribute("adoptMemberVO", newMebData);
			String url = "/back_end/adoptMember/adoptMemberTime.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
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
			ReservePetSvc.insertReservePet(1, 1, whichPet, reserveName, reservePhone, reserveDate, reserveTime.toString(), "1");
			req.setAttribute("seccessReserve", "1");
			String url = "/adoptPet/addPet.do?action=goToDetail&PK=" + whichPet ;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}

	}
}
