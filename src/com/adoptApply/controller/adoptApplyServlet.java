package com.adoptApply.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adoptApply.model.*;

public class adoptApplyServlet extends HttpServlet {
	
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
				String str = req.getParameter("ADOPT_APPLY_NO");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入領養申請編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adoptApply/padoptApplySelect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ADOPT_APPLY_NO = null;
				try {
					ADOPT_APPLY_NO = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("領養申請編號格式錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adoptApply/adoptApplySelect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				adoptApplyService adoptApplySvc = new adoptApplyService();
				adoptApplyVO adoptApplyVO = adoptApplySvc.getOneadoptApply(ADOPT_APPLY_NO);
				if (adoptApplyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adoptApply/adoptApplySelect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adoptApplyVO", adoptApplyVO); // 資料庫取出的empVO物件,存入req
				String url = "/adoptApply/listOneadoptApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotions.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adoptApply/adoptApplySelect.jsp");
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
				Integer ADOPT_APPLY_NO = new Integer(req.getParameter("ADOPT_APPLY_NO"));
				
				/***************************2.開始查詢資料****************************************/
				adoptApplyService adoptApplySvc = new adoptApplyService();
				adoptApplyVO adoptApplyVO = adoptApplySvc.getOneadoptApply(ADOPT_APPLY_NO);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("adoptApplyVO", adoptApplyVO);         // 資料庫取出的promotionsVO物件,存入req
				String url = "/adoptApply/update_adoptApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotions_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adoptApply/listAllAdoptApply.jsp");
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
				Integer ADOPT_APPLY_NO = new Integer(req.getParameter("ADOPT_APPLY_NO").trim());
				Integer ADOPT_MEB_NO = new Integer(req.getParameter("ADOPT_MEB_NO").trim());
				Integer GEN_MEB_NO = new Integer(req.getParameter("GEN_MEB_NO").trim());
				Integer ADOPT_PET_NO = new Integer(req.getParameter("ADOPT_PET_NO").trim());
				
				String ADOPT_AUDIT_STATE = req.getParameter("ADOPT_AUDIT_STATE");
				try {
					Integer reviewAtatus = new Integer(ADOPT_AUDIT_STATE.trim());
					if (reviewAtatus != 0 && reviewAtatus != 1 && reviewAtatus != 2) {
						errorMsgs.add("寵物領養審核狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.add("寵物領養審核狀態: 請勿竄改資料!");
				}
				
				String ADOPT_APPLY_PEOPLE_ID = req.getParameter("ADOPT_APPLY_PEOPLE_ID");
				String IDcard = "/^[A-Za-z][12]\\d{8}$/";
				if (ADOPT_APPLY_PEOPLE_ID.trim().length() == 0) {
				} else if (!ADOPT_APPLY_PEOPLE_ID.trim().matches(IDcard)) {
					errorMsgs.add("請輸入正確身分證字號");
				}
				
				java.sql.Date ADOPT_APPLY_DATE = null;	
				try {
					ADOPT_APPLY_DATE = java.sql.Date.valueOf(req.getParameter("ADOPT_APPLY_DATE").trim());
				} catch (IllegalArgumentException e) {
//					long miliseconds = System.currentTimeMillis();
//					java.sql.Date date = new java.sql.Date(miliseconds);
//					ADOPT_APPLY_DATE = date;
					
					ADOPT_APPLY_DATE=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String ADOPT_APPLY_STATE = req.getParameter("ADOPT_APPLY_STATE");
				try {
					Integer applicationReview = new Integer(ADOPT_APPLY_STATE.trim());
					if (applicationReview != 0 && applicationReview != 1) {
						errorMsgs.add("寵物領養申請審核狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.add("寵物領養申請審核狀態: 請勿竄改資料!");
				}
				
				//可能有問題
                Integer ADOPT_APPLY_NO1 = new Integer(req.getParameter("ADOPT_APPLY_NO").trim());
                adoptApplyVO adoptApplyVO = new adoptApplyVO();//promotionsVO()
				adoptApplyVO.setAdopt_meb_no(ADOPT_MEB_NO);
				adoptApplyVO.setGen_meb_no(GEN_MEB_NO);
				adoptApplyVO.setAdopt_pet_no(ADOPT_PET_NO);
				adoptApplyVO.setAdopt_audit_state(ADOPT_AUDIT_STATE);
				adoptApplyVO.setAdopt_apply_people_id(ADOPT_APPLY_PEOPLE_ID);
				adoptApplyVO.setAdopt_apply_date(ADOPT_APPLY_DATE);
				adoptApplyVO.setAdopt_apply_state(ADOPT_APPLY_STATE);
	
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptApplyVO", adoptApplyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/promotionsadoptApply/update_adoptApply.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				adoptApplyService adoptApplySvc = new adoptApplyService();
				adoptApplyVO = adoptApplySvc.updateadoptApply(ADOPT_APPLY_NO, ADOPT_MEB_NO, GEN_MEB_NO,
						ADOPT_PET_NO, ADOPT_AUDIT_STATE, ADOPT_APPLY_PEOPLE_ID, ADOPT_APPLY_DATE,
						ADOPT_APPLY_STATE);
						                        
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adoptApplyVO", adoptApplyVO); // 資料庫update成功後,正確的的promotionsVO物件,存入req
				String url = "/adoptApply/listOneadoptApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotions.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adoptApply/update_adoptApply.jsp");
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
				Integer ADOPT_APPLY_NO = new Integer(req.getParameter("ADOPT_APPLY_NO").trim());
				Integer ADOPT_MEB_NO = new Integer(req.getParameter("ADOPT_MEB_NO").trim());
				Integer GEN_MEB_NO = new Integer(req.getParameter("GEN_MEB_NO").trim());
				Integer ADOPT_PET_NO = new Integer(req.getParameter("ADOPT_PET_NO").trim());
				
				String ADOPT_AUDIT_STATE = req.getParameter("ADOPT_AUDIT_STATE");
				try {
					Integer reviewAtatus = new Integer(ADOPT_AUDIT_STATE.trim());
					if (reviewAtatus != 0 && reviewAtatus != 1 && reviewAtatus != 2) {
						errorMsgs.add("寵物領養審核狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.add("寵物領養審核狀態: 請勿竄改資料!");
				}
				
				String ADOPT_APPLY_PEOPLE_ID = req.getParameter("ADOPT_APPLY_PEOPLE_ID");
				String IDcard = "/^[A-Za-z][12]\\d{8}$/";
				if (ADOPT_APPLY_PEOPLE_ID.trim().length() == 0) {
				} else if (!ADOPT_APPLY_PEOPLE_ID.trim().matches(IDcard)) {
					errorMsgs.add("請輸入正確身分證字號");
				}
				
				java.sql.Date ADOPT_APPLY_DATE = null;	
				try {
					ADOPT_APPLY_DATE = java.sql.Date.valueOf(req.getParameter("ADOPT_APPLY_DATE").trim());
				} catch (IllegalArgumentException e) {
//					long miliseconds = System.currentTimeMillis();
//					java.sql.Date date = new java.sql.Date(miliseconds);
//					ADOPT_APPLY_DATE = date;
					
					ADOPT_APPLY_DATE=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String ADOPT_APPLY_STATE = req.getParameter("ADOPT_APPLY_STATE");
				try {
					Integer applicationReview = new Integer(ADOPT_APPLY_STATE.trim());
					if (applicationReview != 0 && applicationReview != 1) {
						errorMsgs.add("寵物領養申請審核狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.add("寵物領養申請審核狀態: 請勿竄改資料!");
				}
				
				Integer ADOPT_APPLY_NO2 = new Integer(req.getParameter("ADOPT_APPLY_NO").trim());
                adoptApplyVO adoptApplyVO = new adoptApplyVO();//promotionsVO()
				adoptApplyVO.setAdopt_meb_no(ADOPT_MEB_NO);
				adoptApplyVO.setGen_meb_no(GEN_MEB_NO);
				adoptApplyVO.setAdopt_pet_no(ADOPT_PET_NO);
				adoptApplyVO.setAdopt_audit_state(ADOPT_AUDIT_STATE);
				adoptApplyVO.setAdopt_apply_people_id(ADOPT_APPLY_PEOPLE_ID);
				adoptApplyVO.setAdopt_apply_date(ADOPT_APPLY_DATE);
				adoptApplyVO.setAdopt_apply_state(ADOPT_APPLY_STATE);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptApplyVO", adoptApplyVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adoptApply/addAdoptApply.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				adoptApplyService adoptApplySvc = new adoptApplyService();
				adoptApplyVO = adoptApplySvc.addadoptApply(ADOPT_APPLY_NO, ADOPT_MEB_NO, GEN_MEB_NO,
						ADOPT_PET_NO, ADOPT_AUDIT_STATE, ADOPT_APPLY_PEOPLE_ID, ADOPT_APPLY_DATE,
						ADOPT_APPLY_STATE);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/adoptApply/listAllAdoptApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPromotions.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adoptApply/addAdoptApply.jsp");
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
				Integer ADOPT_APPLY_NO = new Integer(req.getParameter("ADOPT_APPLY_NO"));
				
				/***************************2.開始刪除資料***************************************/
				adoptApplyService promotionsSvc = new adoptApplyService();
				promotionsSvc.deleteadoptApply(ADOPT_APPLY_NO);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/adoptApply/listAllAdoptApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adoptApply/listAllAdoptApply.jsp");
				failureView.forward(req, res);
			}
		}
	}
}