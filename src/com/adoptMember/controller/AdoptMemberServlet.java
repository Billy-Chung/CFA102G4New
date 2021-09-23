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
import java.util.Timer;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptAppointForm.model.AdoptAppointFormDAO;
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
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
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

		if ("showReserve".equals(action)) {
			PrintWriter out = res.getWriter();
			List<Map<String, String>> jsonList = new ArrayList<>();
			ReservePetService reservePetSvc = new ReservePetService();
			List<ReservePetVO> allReserve = reservePetSvc.findByAdoptMebNo(new Integer(req.getParameter("PK"))).stream()
					.filter(r -> r.getReserve_state().equals("1")).collect(Collectors.toList());
			for (ReservePetVO thisReserve : allReserve) {
				String whichDate = thisReserve.getReserve_date().toString();
				String whichTime = thisReserve.getReserve_time().toString();
				String jsonStartDateTime = null;
				String jsonEndDateTime = null;
				for (int i = 0; i < 24; i++) {
					if (Character.getNumericValue(whichTime.charAt(i)) == 1) {
						Map<String, String> jsonMap = new HashMap();
						jsonMap.put("title", "預約者姓名: " + thisReserve.getReserve_people_name() + " ---------- 預約者電話: "
								+ thisReserve.getReserve_people_phone());
						jsonStartDateTime = whichDate + "T" + i + ":00:00";
						jsonEndDateTime = whichDate + "T" + (i + 1) + ":00:00";
						jsonMap.put("start", jsonStartDateTime);
						jsonMap.put("end", jsonEndDateTime);
						jsonMap.put("id", thisReserve.getReserve_pet_no().toString());
						jsonList.add(jsonMap);
					}
				}
			}

			Gson gson = new Gson();
			String jsonString = gson.toJson(jsonList);
			out.write(jsonString);
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

		if ("cancelReserve".equals(action)) {
			PrintWriter out = res.getWriter();
			Integer PK = new Integer(req.getParameter("PK"));
			Map<String, String> jsonMap = new HashMap();
			ReservePetService reservePetSvc = new ReservePetService();
			ReservePetVO upDate = reservePetSvc.findByReservePetPK(PK);
			Date reserveDate = upDate.getReserve_date();
			String reservTime = upDate.getReserve_time();
			AdoptAppointFormService adoptAppointFormSvc = new AdoptAppointFormService();
			AdoptAppointFormVO whichData = adoptAppointFormSvc.findByAdoptAppointFormDate(reserveDate);
			String oldTimeDate = whichData.getFinifh_appoint_num();
			StringBuilder reserveNewTime = new StringBuilder();
			for (int i = 0; i < 24; i++) {
				if (Character.getNumericValue(reservTime.charAt(i)) == 1) {
					reserveNewTime.append((Character.getNumericValue(oldTimeDate.charAt(i)) - 1));
				} else {
					reserveNewTime.append(oldTimeDate.charAt(i));
				}
			}

			AdoptAppointFormVO adoptAppointFormVO = new AdoptAppointFormVO();
			adoptAppointFormVO.setAppoint_form_no(whichData.getAppoint_form_no());
			adoptAppointFormVO.setFinifh_appoint_num(reserveNewTime.toString());
			adoptAppointFormVO.setAppoint_limit(reservTime);
			reservePetSvc.upodateReservePet(upDate.getReserve_people_name(), upDate.getReserve_people_phone(),
					reserveDate, reservTime, "0", PK, adoptAppointFormVO);

			jsonMap.put("deleteReserve", "seccess");
			Gson gson = new Gson();
			String jsonString = gson.toJson(jsonMap);
			out.write(jsonString);
			out.close();
		}

		if ("setNoReserve".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			String inputDate = req.getParameter("noReserveDate");
			if (inputDate.trim().length() == 0) {
				errorMsgs.put("noSet", "請選擇日期!!");
				req.setAttribute("choseDate", "");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/addNoReserve.jsp");
				failureView.forward(req, res);
				return;
			}
			Date noReserveDate = java.sql.Date.valueOf(inputDate);
			Integer mebNo = new Integer(req.getParameter("adoptMebNo"));

			AdoptAppointFormService AdoptAppointFormSvc = new AdoptAppointFormService();
			AdoptAppointFormVO whichDate = AdoptAppointFormSvc.findByAdoptAppointFormDate(noReserveDate);

			try {
				int noReserve = 0;
				for (int i = 0; i < 24; i++) {
					if (Character.getNumericValue(whichDate.getFinifh_appoint_num().charAt(i)) != 0) {
						noReserve++;
					}
				}
				if (noReserve != 0) {
					errorMsgs.put("haveReserve", "請先取消該日的預約!!");
					req.setAttribute("choseDate", inputDate);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/addNoReserve.jsp");
					failureView.forward(req, res);
					return;
				} else {
					AdoptAppointFormSvc.setNoreserveDate("000000000000000000000000", "000000000000000000000000",
							whichDate.getAppoint_form_no());
					req.setAttribute("successSet", "已成功設定不可預約日期");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/addNoReserve.jsp");
					failureView.forward(req, res);
					return;
				}
			} catch (Exception e) {
				AdoptAppointFormSvc.insertAdoptAppointForm(mebNo, noReserveDate, "000000000000000000000000",
						"000000000000000000000000");
				req.setAttribute("successSet", "已成功設定不可預約日期");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/addNoReserve.jsp");
				failureView.forward(req, res);
				return;
			}

		}
	}
}
