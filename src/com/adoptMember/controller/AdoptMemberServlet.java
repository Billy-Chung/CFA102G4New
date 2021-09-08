package com.adoptMember.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptMember.model.AdoptMemberService;
import com.adoptMember.model.AdoptMemberVO;
import com.adoptPet.model.AdoptPetService;
import com.adoptPet.model.AdoptPetVO;
import com.adoptPetPhoto.model.AdoptPetPhotoService;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;

@javax.servlet.annotation.MultipartConfig
public class AdoptMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");	
		if(("gotoUpdate").equals(action)) {			
			Integer adoptMebNo = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);		
			req.setAttribute("adoptMemberVO", adoptMemberVO); 
			String url = "/back_end/adoptMember/adoptMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if(("showMebPhoto").equals(action)) {
			res.setContentType("img/jepg");
			Integer PK = new Integer(req.getParameter("adoptMebNo"));
			AdoptMemberService adoptMemberSvc = new AdoptMemberService();
			AdoptMemberVO mebPhoto = adoptMemberSvc.findByAdoptMebNoPK(PK);
			byte[] showMebPhoto = mebPhoto.getAdopt_meb_photo();
			ServletOutputStream out = res.getOutputStream();
			out.write(showMebPhoto);
			out.close();
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
			}else if(oldPhoto.length != 0 && in.available() == 0) {
				adoptMemberSvc.updateAdoptMember(adoptMebName, adoptMebComment, oldPhoto, adoptMebAddress, adoptMebPhone,
						adoptMebEmail, adoptMebAccount, adoptMebPassword, adoptMebState, adoptMebAuth, adoptMebHoliday,
						adoptMebLimit, adoptMebNo);
				
				AdoptMemberVO adoptMemberVO = adoptMemberSvc.findByAdoptMebNoPK(adoptMebNo);		
				req.setAttribute("adoptMemberVO", adoptMemberVO);
				String url = "/back_end/adoptMember/adoptMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}		
			else {
				
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

	}
}
