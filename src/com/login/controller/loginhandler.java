package com.login.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adoptMember.model.AdoptMemberService;
import com.adoptMember.model.AdoptMemberVO;
import com.adoptPet.model.AdoptPetVO;

@WebServlet("/loginhandler")
public class loginhandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected List<AdoptMemberVO> allowUser(String account, String password) {
		AdoptMemberService adoptMebSvc = new AdoptMemberService();
		List<AdoptMemberVO> passMeb = new ArrayList<>();
		passMeb = adoptMebSvc.getAll().stream().filter(e -> e.getAdopt_meb_account().equals(account))
				.filter(p -> p.getAdopt_meb_password().equals(password)).collect(Collectors.toList());
		return passMeb;
	};

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		if ("login".equals(action)) {		
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<AdoptMemberVO> passMeb = allowUser(account, password);
			if (passMeb.size() == 0) {			
				errorMsgs.put("errorPhoto", "帳號密碼錯誤，請重新輸入!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/login/login.jsp");
				failureView.forward(req, res);
				return;
			} else {
				
				HttpSession session = req.getSession();
				session.setAttribute("admin", passMeb.get(0));
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {					
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {					
					
				}
				res.sendRedirect(req.getContextPath() + "/back_end/adopt/adoptPet.jsp");
			}
		}
		
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("admin");
			res.sendRedirect(req.getContextPath() + "/back_end/login/login.jsp");
		}
	}
}
