package com.reservePet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adoptAppointForm.model.AdoptAppointFormService;
import com.adoptAppointForm.model.AdoptAppointFormVO;
import com.google.gson.Gson;
import com.reservePet.model.ReservePetService;
import com.reservePet.model.ReservePetVO;

public class ReservePetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		if ("showReserve".equals(action)) {
			PrintWriter out = res.getWriter();
			List<Map<String, String>> jsonList = new ArrayList<>();
			ReservePetService reservePetSvc = new ReservePetService();
			List<ReservePetVO> allReserve = reservePetSvc.findByGenMebNo(new Integer(req.getParameter("PK"))).stream()
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

		if ("addReserve".equals(action)) {
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

			try {
				reserveDate = java.sql.Date.valueOf(req.getParameter("reserveDate"));
			} catch (Exception e) {
				errorMsgs.put("reserveDate", "預約日期: 請勿空白!!");
			}
			try {
				timeSelect = new Integer(req.getParameter("timeSelect"));
			} catch (Exception e) {
				errorMsgs.put("timeSelect", "預約時段: 請勿空白!!");
			}
			ReservePetVO reservePet = new ReservePetVO();
			reservePet.setAdopt_pet_no(whichPet);
			reservePet.setReserve_people_name(reserveName);
			reservePet.setReserve_people_phone(reservePhone);
			reservePet.setReserve_date(reserveDate);
			StringBuilder reserveTime = new StringBuilder();
			synchronized(this) {				
				AdoptAppointFormService AdoptAppointFormSvc = new AdoptAppointFormService();
				AdoptAppointFormVO adoptAppointFormVO = AdoptAppointFormSvc.findByAdoptAppointFormDate(reserveDate);
				String limit = adoptAppointFormVO.getAppoint_limit();
				String nowMeb = adoptAppointFormVO.getFinifh_appoint_num();
				for (int i = 0; i < 24; i++) {
					Integer nowWhichLimit = new Integer(limit.charAt(i));
					Integer nowWhichMeb = new Integer(nowMeb.charAt(i));
					if (i == timeSelect && nowWhichLimit>nowWhichMeb) {
						reserveTime.append(1);
					}else if(i == timeSelect && nowWhichLimit<=nowWhichMeb) {
						errorMsgs.put("reserveDate", "預約時段: 該時段已滿，請重新選擇");
					}
					else {
						reserveTime.append(0);
					}
				}
				}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("reservePet", reservePet);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/adoptPet/reservePet.jsp");
				failureView.forward(req, res);
				return;
			}
			
			ReservePetService ReservePetSvc = new ReservePetService();
			ReservePetSvc.insertReservePet(1, 1, whichPet, reserveName, reservePhone, reserveDate,
					reserveTime.toString(), "1", timeSelect);
			req.setAttribute("seccessReserve", "1");
			String url = "/adoptPet/addPet.do?action=goToDetail&PK=" + whichPet;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if("cancelReserve".equals(action)) {
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

	}

}
