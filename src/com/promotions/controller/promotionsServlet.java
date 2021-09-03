package com.promotions.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotions.model.*;

public class promotionsServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promot_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotions/promotionsSelect_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer promot_no = null;
				try {
					promot_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotions/promotionsSelect_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				promotionsService promotionsSvc = new promotionsService();
				promotionsVO promotionsVO = promotionsSvc.getOnePromotions(promot_no);
				if (promotionsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotions/promotionsSelect_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotionsVO", promotionsVO); // 資料庫取出的empVO物件,存入req
				String url = "/promotions/listOnePromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotions.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/promotions/promotionsSelect_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer promot_no = new Integer(req.getParameter("promot_no"));
				
				/***************************2.開始查詢資料****************************************/
				promotionsService promotionsSvc = new promotionsService();
				promotionsVO promotionsVO = promotionsSvc.getOnePromotions(promot_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("promotionsVO", promotionsVO);         // 資料庫取出的promotionsVO物件,存入req
				String url = "/promotions/update_promotions_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotions_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/promotions/listAllPromotions.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_promotions_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer promot_no = new Integer(req.getParameter("promot_no").trim());
				
				String promot_name = req.getParameter("promot_name");
				String promot_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (promot_name == null || promot_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!promot_name.trim().matches(promot_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				
				java.sql.Date promot_date_start = null;
				try {
					promot_date_start = java.sql.Date.valueOf(req.getParameter("promot_date_start").trim());
				} catch (IllegalArgumentException e) {
					promot_date_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動開始日期!");
				}
				
				java.sql.Date promot_date_end = null;
				try {
					promot_date_end = java.sql.Date.valueOf(req.getParameter("promot_date_end").trim());
				} catch (IllegalArgumentException e) {
					promot_date_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動結束日期!");
				}
				
				String promot_status = req.getParameter("promot_status").trim();
				if (promot_status == null || promot_status.trim().length() == 0) {
					errorMsgs.add("活動狀態請勿空白");
				}
				
				
				
				
				String promot_type = req.getParameter("promot_type").trim();
				if (promot_type == null || promot_type.trim().length() == 0) {
					errorMsgs.add("活動種類請勿空白");
				}
				
				String promot_discount_type = req.getParameter("promot_discount_type").trim();
				if (promot_discount_type == null || promot_discount_type.trim().length() == 0) {
					errorMsgs.add("折扣方式請勿空白");
				}
				
				String promot_discount = req.getParameter("promot_discount").trim();
				if (promot_discount == null || promot_discount.trim().length() == 0) {
					errorMsgs.add("折數請勿空白");
				}
				
				String promot_reduce = req.getParameter("promot_reduce").trim();
				if (promot_status == null || promot_status.trim().length() == 0) {
					errorMsgs.add("減價請勿空白");
				}
				
				String promot_comment = req.getParameter("promot_comment").trim();
				if (promot_comment == null || promot_comment.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}
				
				
				//可能有問題
                Integer promot_no1 = new Integer(req.getParameter("promot_no").trim());
				promotionsVO promotionsVO = new promotionsVO();//promotionsVO()
				promotionsVO.setPromot_name(promot_name);
				promotionsVO.setPromot_date_start(promot_date_start);
				promotionsVO.setPromot_date_end(promot_date_end);
				promotionsVO.setPromot_status(promot_status);
				promotionsVO.setPromot_type(promot_type);
				promotionsVO.setPromot_discount_type(promot_discount_type);
				promotionsVO.setPromot_discount(promot_discount);
				promotionsVO.setPromot_reduce(promot_reduce);
				promotionsVO.setPromot_comment(promot_comment);
				byte[] promot_photo = null;
				promotionsVO.setPromot_photo(promot_photo);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotions/update_promotions_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				promotionsService promotionsSvc = new promotionsService();
				promotionsVO = promotionsSvc.updatepromotions(promot_no, promot_name, promot_date_start,
							   promot_date_end, promot_status, promot_type, promot_discount_type,
							   promot_discount, promot_reduce, promot_comment, promot_photo);
						                        
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotionsVO", promotionsVO); // 資料庫update成功後,正確的的promotionsVO物件,存入req
				String url = "/promotions/listOnePromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotions.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/promotions/update_promotions_input.jsp");
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
				String promot_name = req.getParameter("promot_name");
				String promot_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (promot_name == null || promot_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!promot_name.trim().matches(promot_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				java.sql.Date promot_date_start = null;
				try {
					promot_date_start = java.sql.Date.valueOf(req.getParameter("promot_date_start").trim());
				} catch (IllegalArgumentException e) {
					promot_date_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動開始日期!");
				}
				
				java.sql.Date promot_date_end = null;
				try {
					promot_date_end = java.sql.Date.valueOf(req.getParameter("promot_date_end").trim());
				} catch (IllegalArgumentException e) {
					promot_date_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動結束日期!");
				}
				
				String promot_status = req.getParameter("promot_status").trim();
				if (promot_status == null || promot_status.trim().length() == 0) {
					errorMsgs.add("活動狀態請勿空白");
				}
				
				
				
				
				String promot_type = req.getParameter("promot_type").trim();
				if (promot_type == null || promot_type.trim().length() == 0) {
					errorMsgs.add("活動種類請勿空白");
				}
				
				String promot_discount_type = req.getParameter("promot_discount_type").trim();
				if (promot_discount_type == null || promot_discount_type.trim().length() == 0) {
					errorMsgs.add("折扣方式請勿空白");
				}
				
				String promot_discount = req.getParameter("promot_discount").trim();
				if (promot_discount == null || promot_discount.trim().length() == 0) {
					errorMsgs.add("折數請勿空白");
				}
				
				String promot_reduce = req.getParameter("promot_reduce").trim();
				if (promot_status == null || promot_status.trim().length() == 0) {
					errorMsgs.add("減價請勿空白");
				}
				
				String promot_comment = req.getParameter("promot_comment").trim();
				if (promot_comment == null || promot_comment.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}
				
				Integer promot_no = new Integer(req.getParameter("promot_no").trim());
				promotionsVO promotionsVO = new promotionsVO();//promotionsVO()
				promotionsVO.setPromot_name(promot_name);
				promotionsVO.setPromot_date_start(promot_date_start);
				promotionsVO.setPromot_date_end(promot_date_end);
				promotionsVO.setPromot_status(promot_status);
				promotionsVO.setPromot_type(promot_type);
				promotionsVO.setPromot_discount_type(promot_discount_type);
				promotionsVO.setPromot_discount(promot_discount);
				promotionsVO.setPromot_reduce(promot_reduce);
				promotionsVO.setPromot_comment(promot_comment);
				byte[] promot_photo = null;
				promotionsVO.setPromot_photo(promot_photo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotions/addPromotions.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				promotionsService promotionsSvc = new promotionsService();
				promotionsVO = promotionsSvc.addpromotions( promot_name, promot_date_start,
							   promot_date_end, promot_status, promot_type, promot_discount_type,
							   promot_discount, promot_reduce, promot_comment, promot_photo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/promotions/listAllPromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotions.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/promotions/addPromotions.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllPromotions.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer promot_no = new Integer(req.getParameter("promot_no"));
				
				/***************************2.開始刪除資料***************************************/
				promotionsService promotionsSvc = new promotionsService();
				promotionsSvc.deletepromotions(promot_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/promotions/listAllPromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/promotions/listAllPromotions.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
