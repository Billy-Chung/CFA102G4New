package com.generalMember.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.generalMember.model.GeneralMemberService;
import com.generalMember.model.GeneralMemberVO;


public class GeneralMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GeneralMemberServlet() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("gmno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/GeneralMember/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer gmno = null;
				try {
					gmno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/GeneralMember/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GeneralMemberService gmSvc = new GeneralMemberService();
				GeneralMemberVO gmVO = gmSvc.getOneGeneralMember(gmno);
				if (gmVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/GeneralMember/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("gmVO", gmVO); // 資料庫取出的empVO物件,存入req
				String url = "/GeneralMember/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/GeneralMember/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
		
		
		try {
			 Integer gmno = new Integer(req.getParameter("gmno"));
			 
			 GeneralMemberService gmSvc = new GeneralMemberService();
			 GeneralMemberVO gmVO = gmSvc.getOneGeneralMember(gmno);
			 
			 req.setAttribute("gmVO",gmVO);
			 String url ="/GeneralMember/update_gm_input.jsp";
			 RequestDispatcher successView = req.getRequestDispatcher(url);
			 successView.forward(req, res);
		}catch(Exception e) {
			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/listAllGeneralMember");
			failureView.forward(req,res);
			
		}
		}
		
		if("update".equals("action")) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer gmno = new Integer (req.getParameter("gmno").trim());
				
				String meb_name = req.getParameter("meb_name");
				String gmnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if(meb_name == null || meb_name.trim().length()==0) {
					errorMsgs.add("會員姓名:請勿空白");
				}else if(!meb_name.trim().matches(gmnameReg)) {
					errorMsgs.add("會員姓名:只能是中、英文字母、數字和_,且長度必需在2到10之間");
				}
				
				String phone = req.getParameter("phone").trim();
				if(phone==null || phone.trim().length()==0) {
					errorMsgs.add("手機請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birhtday").trim());	
				}catch(IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日");
				}
				
				
				String comment = req.getParameter("comment").trim();
				if(comment == null || comment.trim().length()==0) {
					errorMsgs.add("會員簡介請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if(address == null || comment.trim().length()==0) {
					errorMsgs.add("會員地址請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if(email == null || email.trim().length()==0) {
					errorMsgs.add("會員信箱請勿空白");
				}
				
				String account = req.getParameter("account").trim();
				if(account == null || account.trim().length()==0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				
				String password = req.getParameter("password").trim();
				if(password == null || password.trim().length()==0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String gender = req.getParameter("gender").trim();
				if(gender == null || gender.trim().length()==0) {
					errorMsgs.add("會員性別請勿空白");
				}
				
				Integer meb_money = null;
				try {
					meb_money = new Integer(req.getParameter("meb_money").trim());
				}catch(NumberFormatException e) {
					meb_money = 0;
					errorMsgs.add("儲存金額請填數字");
				}
				
				String post_permission = req.getParameter("post_permission").trim();
				if(post_permission == null || post_permission.trim().length()==0) {
					errorMsgs.add("發文權限請勿空白");
				}
				
				
				GeneralMemberVO gmVO = new GeneralMemberVO();
				gmVO.setGer_meb_no(gmno);
				gmVO.setMeb_name(meb_name);
				gmVO.setPhone(phone);
				gmVO.setBirthday(birthday);
				gmVO.setPhoto(photo);
				gmVO.setComment(comment);
				gmVO.setAddress(address);
				gmVO.setEmail(email);
				gmVO.setAccount(account);
				gmVO.setPassword(password);
				gmVO.setGender(gender);
				gmVO.setMeb_money(meb_money);
				gmVO.setPost_permission(post_permission);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("gmVO",gmVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/update_gm_input.jsp");
					failureView.forward(req,res);
					return;
				}	
				
				GeneralMemberService gmSvc = new GeneralMemberService();
				gmVO = gmSvc.updateGeneralMember(meb_name, phone, birthday, photo, comment, address, email, account, password, gender, meb_money, post_permission, gmno);
				
				req.setAttribute("gmVO",gmVO);
				String url = "/GeneralMember/listOneGeneralMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
			} 	catch(Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/update_gm_input.jsp");
				failureView.forward(req, res);
				}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer gmno = new Integer (req.getParameter("gmno").trim()); 
				String meb_name = req.getParameter("meb_name");
				String gmnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (meb_name == null || meb_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!meb_name.trim().matches(gmnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String phone = req.getParameter("phone").trim();
				if(phone==null || phone.trim().length()==0) {
					errorMsgs.add("手機請勿空白");
				}
				
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birhtday").trim());	
				}catch(IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日");
				}
				
				 photo = req.getParameter("photo");
				
				String comment = req.getParameter("comment").trim();
				if(comment == null || comment.trim().length()==0) {
					errorMsgs.add("會員簡介請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if(address == null || comment.trim().length()==0) {
					errorMsgs.add("會員地址請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if(email == null || email.trim().length()==0) {
					errorMsgs.add("會員信箱請勿空白");
				}
				
				String account = req.getParameter("account").trim();
				if(account == null || account.trim().length()==0) {
					errorMsgs.add("會員帳號請勿空白");
				}
				
				String password = req.getParameter("password").trim();
				if(password == null || password.trim().length()==0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String gender = req.getParameter("gender").trim();
				if(gender == null || gender.trim().length()==0) {
					errorMsgs.add("會員性別請勿空白");
				}
				
				Integer meb_money = null;
				try {
					meb_money = new Integer(req.getParameter("meb_money").trim());
				}catch(NumberFormatException e) {
					meb_money = 0;
					errorMsgs.add("儲存金額請填數字");
				}
				
				String post_permission = req.getParameter("post_permission").trim();
				if(post_permission == null || post_permission.trim().length()==0) {
					errorMsgs.add("發文權限請勿空白");
				}
				
				

				GeneralMemberVO gmVO = new GeneralMemberVO();
				gmVO.setMeb_name(meb_name);
				gmVO.setPhone(phone);
				gmVO.setBirthday(birthday);
				gmVO.setComment(comment);
				gmVO.setAddress(address);
				gmVO.setEmail(email);
				gmVO.setAccount(account);
				gmVO.setPassword(password);
				gmVO.setGender(gender);
				gmVO.setMeb_money(meb_money);
				gmVO.setPost_permission(post_permission);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gmVO", gmVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/addGeneralMember.jsp");
					failureView.forward(req, res);
					return;
				}
									
									/***************************2.開始新增資料***************************************/
					GeneralMemberService gmSvc = new GeneralMemberService();
					gmVO = gmSvc.addGeneralMember(meb_name, phone, birthday, photo, comment, address, email, account, password, gender, meb_money, post_permission, gmno);
									
									/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/GeneralMember/listAllGeneralMember.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
									
									/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/addGeneralMember.jsp");
					failureView.forward(req, res);
				}
			}
		
		
		if("delete".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
				Integer gmno = new Integer(req.getParameter("gmno"));
				
				GeneralMemberService gmSvc = new GeneralMemberService();
				gmSvc.deleteGeneralMember(gmno);
				
				String url = "/GeneralMember/listAllGeneralMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
					
			}	catch(Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/GeneralMember/listAllGeneralMember.jsp");
				failureView.forward(req,res);
			}
					
		}
	}

}
