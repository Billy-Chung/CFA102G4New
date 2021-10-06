package com.product_promotions_detail.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.product.model.productService;
import com.product_promotions_detail.model.ProductPromotionService;
import com.product_promotions_detail.model.ProductPromotionsDetailVO;
import com.promotions.model.PromotionsService;
import com.promotions.model.PromotionsVO;

/**
 * Servlet implementation class ProductPromoDetailServlet
 */
@WebServlet("/ProductPromoDetailServlet")
public class ProductPromoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductPromotionService productPromotionSvc = new ProductPromotionService();
	private productService productSvc = new productService();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		
		if("listProductPromos".equals(action)) {
			//取得請求參數
			Integer promot_no = new Integer(req.getParameter("promot_no"));
			
			//使用 service 拿取資料
			List<ProductPromotionsDetailVO> promoProudctList= productPromotionSvc.findProductPromoDetailsByPromoNo(promot_no);
			
			List<ProductVO> productList = productSvc.getAll();

			req.setAttribute("list", promoProudctList);
			req.setAttribute("productList", productList);
			
			PromotionsService promotionsService = new PromotionsService();
			PromotionsVO promotionsVO = promotionsService.getOnePromotions(promot_no);
			req.setAttribute("promotionsVO", promotionsVO);
			//將資料導向
			String path ="/back_end/promotions/listAllProductPromotions.jsp";
			RequestDispatcher dispatch = req.getRequestDispatcher(path);
			dispatch.forward(req, res);
		}
		
	}

}
