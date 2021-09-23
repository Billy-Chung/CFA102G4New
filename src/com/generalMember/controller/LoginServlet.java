package com.generalMember.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import com.generalMember.model.GeneralMemberDAO;
import com.generalMember.model.GeneralMemberDAO_Interface;
import com.generalMember.model.GeneralMemberService;
import com.generalMember.model.GeneralMemberVO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected List<GeneralMemberVO> allowUser(String account, String password) {
		GeneralMemberService gmSvc = new GeneralMemberService();
		List<GeneralMemberVO> checkmeb = new ArrayList<>();
		checkmeb = gmSvc.getAll().stream().filter(e -> e.getAccount().equals(account))
				.filter(p -> p.getPassword().equals(password)).collect(Collectors.toList());
		return checkmeb;
	};
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("login".equals(action)) {		//登入會員
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			List<GeneralMemberVO> checkmeb = allowUser(account,password);
			
			if(checkmeb.size()==0) {
				errorMsgs.add("帳號密碼錯誤,請重新輸入");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMember/login.jsp");
				failureView.forward(req, res);
				return;
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("meb", checkmeb.get(0));
				
				try {                                                        
			         String location = (String) session.getAttribute("location");
			         if (location != null) {
			           session.removeAttribute("location");   
			           res.sendRedirect(location);            
			           return;
			         }
				} catch(Exception ignored) {}
				res.sendRedirect(req.getContextPath() + "/front_end/GeneralMember/select_page.jsp");
			}
		}
		
		if("update_password".equals(action)) { 			//修改密碼
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			String newpassword = req.getParameter("newpassword");
			String newpassword1 = req.getParameter("newpassword1");
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			List<GeneralMemberVO> checkmeb = allowUser(account,password);
			
			
			if(account==null || account.trim().length()==0) {
				errorMsgs.add("會員帳號不可以空白");
			}
			
			if(password==null || password.trim().length()==0) {
				errorMsgs.add("會員舊密碼不可以空白");
			}
			
			if(newpassword==null || newpassword.trim().length()==0) {
				errorMsgs.add("新密碼不可以空白");
			}
			
			if(newpassword1==null || newpassword1.trim().length()==0) {
				errorMsgs.add("確認新密碼不可以空白");
			}
			
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMember/updatePassword.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			
			GeneralMemberVO gmVO = new GeneralMemberVO();
			GeneralMemberDAO_Interface gmdao = new GeneralMemberDAO();
			gmVO.setAccount(account);
			gmVO.setPassword(newpassword);
			gmdao.updatePassword(gmVO);
			
			if(checkmeb.size()==0) {
				errorMsgs.add("帳號密碼錯誤,請重新輸入");
			}
			else if(!newpassword.equals(newpassword1)) {
				errorMsgs.add("兩次輸入的新密碼不一致");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMember/updatePassword.jsp");
				failureView.forward(req, res);
				return;
			}
			
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/GeneralMember/login.jsp");
			successView.forward(req,res);
				
		}
	}
}	
