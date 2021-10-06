package com.order_form.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import javax.servlet.http.Part;

import com.generalMember.model.GeneralMemberVO;
import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;
import com.order_form.model.Order_formService;
import com.order_form.model.OrderFormVO;
import com.promo_photo.model.PromoPhotoVO;
import com.promotions.model.PromotionsVO;

public class Order_formServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Order_formService order_formSvc = new Order_formService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {//
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {//

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		Map<String, String[]> paramMap = req.getParameterMap();
		for (String key : paramMap.keySet()) {
			for (String value : paramMap.get(key)) {
				System.out.printf("key= %s, value= %s", key, value);
				System.out.println();
			}
		}

		if ("fillform".equals(action)) { // 來自Checkout.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			Integer genMebNo = Integer.valueOf(req.getParameter("genMebNo"));
			req.setAttribute("errorMsgs", errorMsgs);

			Integer order_amount = null;
			try {
				System.out.println(req.getParameter("order_amount"));
				order_amount = new Integer(req.getParameter("order_amount").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("結帳金額請勿空白");
			}

			String order_name = req.getParameter("order_name");

			String order_phone = null;
			try {
				System.out.println(req.getParameter("order_phone"));
				order_phone = new String(req.getParameter("order_phone").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("連絡電話請勿空白");
			}

			String city = req.getParameter("city");
			String distinctValue = req.getParameter("distinctValue");
			String delivery_address = req.getParameter("delivery_address");
			String delivery_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (delivery_address == null || delivery_address.trim().length() == 0) {
				errorMsgs.add("配送地址: 請勿空白");
			} else if (!delivery_address.trim().matches(delivery_addressReg)) {
				errorMsgs.add("配送地址: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}
			String address = city + distinctValue + delivery_address;

			java.util.Date now = new java.util.Date();
			java.sql.Timestamp timeStamp = new java.sql.Timestamp(now.getTime());

			OrderFormVO order_formVO = new OrderFormVO();
			order_formVO.setOrder_amount(order_amount);
			order_formVO.setOrder_name(order_name);
			order_formVO.setOrder_phone(order_phone);
			order_formVO.setDelivery_address(address);
			order_formVO.setOrder_time(timeStamp);
			order_formVO.setOrder_status("0");

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("order_formVO", order_formVO);

				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/shopping_cart/Checkout.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			HttpSession session = req.getSession();
			Map<Integer, OrderDetailVO> cart = (Map<Integer, OrderDetailVO>) session.getAttribute("cart");

			if (cart == null) {
				// 3.新增完成,準備轉交(Send the Success view)
				String url = "/front_end/shopping_cart/thank.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交shopping_cart.jsp
				successView.forward(req, res);
				return;
			}

			Collection<OrderDetailVO> cartlist = cart.values();
			List<OrderDetailVO> cartList = new ArrayList<>(cartlist);

			// 2.開始新增資料
			Order_formService order_formSvc = new Order_formService();
			Integer next_order_no = order_formSvc.insertWithorder_detail(genMebNo, null, null, null, null, order_amount,
					order_name, order_phone, address, timeStamp, order_formVO.getOrder_status(), cartList);

//			List<OrderDetailVO> allDetailList = orderDetailSvc.getAll();
//			List<OrderDetailVO> detailList = new ArrayList<OrderDetailVO>();
//			for( OrderDetailVO orderDetailVO:allDetailList) {
//				
//				if(orderDetailVO.getOrder_no().intValue() == next_order_no.intValue()) {
//					detailList.add(orderDetailVO);
//				}
//				
//			}
//			
//			List<OrderDetailVO> orderDetailList = orderDetailSvc.findOrderDetailsByOrderFormNo(next_order_no);

			req.setAttribute("list", cartList);
			req.setAttribute("orderFormVO", order_formVO);

			session.removeAttribute("cart");

			// 3.新增完成,準備轉交(Send the Success view)
			String url = "/front_end/shopping_cart/thank.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交shopping_cart.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/order_form/Checkout.jsp");
//				failureView.forward(req, res);
//			}

		} else if ("getMemOrderList".equals(action)) {
			GeneralMemberVO memVO = (GeneralMemberVO) req.getSession().getAttribute("meb");
			Order_formService order_formSvc = new Order_formService();
			List<OrderFormVO> orderList = order_formSvc.getMemberOrdForm(memVO.getGer_meb_no());

			req.setAttribute("list", orderList);

			String path = "/front_end/OrderForm/MemOrderList.jsp";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);
		} else if ("cancelOrderForm".equals(action)) {
			Integer orderNo = new Integer(req.getParameter("order_no"));
			OrderFormVO orderFormVO = order_formSvc.getOneOrder_form(orderNo);
			orderFormVO.setOrder_status("-1");
			order_formSvc.updateOrderForm(orderFormVO);
		
			String path = "/order_form/order_form.do?action=getMemOrderList";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);	
		} else if ("doneOrderForm".equals(action)) {
			Integer orderNo = new Integer(req.getParameter("order_no"));
			OrderFormVO orderFormVO = order_formSvc.getOneOrder_form(orderNo);
			orderFormVO.setOrder_status("2");
			order_formSvc.updateOrderForm(orderFormVO);
		
			String path = "/order_form/order_form.do?action=getMemOrderList";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);	
			
		}	else if ("cancelBackOrderForm".equals(action)) {
			Integer orderNo = new Integer(req.getParameter("order_no"));
			OrderFormVO orderFormVO = order_formSvc.getOneOrder_form(orderNo);
			orderFormVO.setOrder_status("-1");
			order_formSvc.updateOrderForm(orderFormVO);
			
			String path = "/back_end/order_form/listAllOrder_form.jsp";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);
		}else if ("deliverOrderForm".equals(action)) {
			Integer orderNo = new Integer(req.getParameter("order_no"));
			OrderFormVO orderFormVO = order_formSvc.getOneOrder_form(orderNo);
			orderFormVO.setOrder_status("1");
			order_formSvc.updateOrderForm(orderFormVO);
			
			String path = "/back_end/order_form/listAllOrder_form.jsp";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);
		}else {
			System.out.println("還沒設計...");
		}
	}

}