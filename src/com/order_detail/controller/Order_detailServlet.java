package com.order_detail.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;

/**
 * Servlet implementation class Order_detailServlet
 */
@WebServlet("/Order_detailServlet")
public class Order_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Order_detailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("getOne_For_Display".equals(action)) {
			Integer order_no = new Integer(request.getParameter("order_no"));
			OrderDetailService Order_detailSvc = new OrderDetailService();
			//泛型	
			List<OrderDetailVO> allDetail = Order_detailSvc.getAll().stream().filter(e -> e.getOrder_no() == order_no.intValue())
					.collect(Collectors.toList());
			request.setAttribute("allDetail", allDetail);
			
//			for(OrderDetailVO allDetails: allDetail) {
//				System.out.println(allDetails.getOrder_no());
//			}
			RequestDispatcher failureView = request
					.getRequestDispatcher("/back_end/order_detail/listOneOrder_detail.jsp");//頁面跳轉
			failureView.forward(request, response);//forward請求轉發
		}
		
		if("frontEndOrderDetail".equals(action)) {
			Integer order_no = new Integer(request.getParameter("order_no"));
			OrderDetailService Order_detailSvc = new OrderDetailService();
			//泛型	
			List<OrderDetailVO> allDetail = Order_detailSvc.getAll().stream().filter(e -> e.getOrder_no() == order_no.intValue())
					.collect(Collectors.toList());
			request.setAttribute("allDetail", allDetail);
			
//			for(OrderDetailVO allDetails: allDetail) {
//				System.out.println(allDetails.getOrder_no());
//			}
			RequestDispatcher failureView = request
					.getRequestDispatcher("/front_end/order_detail/listOneOrder_detail.jsp");//頁面跳轉
			failureView.forward(request, response);//forward請求轉發
		}
	
	}

}
