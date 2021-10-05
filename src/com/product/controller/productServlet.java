package com.product.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.productService;
import com.product.model.ProductVO;

public class productServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("cover".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			productPhotosService productPhotosSvc = new productPhotosService();

			try {
				byte[] photo = productPhotosSvc.getAll().stream().filter(e -> e.getProduct_no() == PK.intValue()).findFirst().get()
						.getProduct_photo();

				System.out.println(photo.length);
				ServletOutputStream out = res.getOutputStream();
				out.write(photo);
				out.close();
			} catch (Exception np) {

				FileInputStream fis = new FileInputStream(
						getServletContext().getRealPath("back_end/adopt/image/news.png"));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				ServletOutputStream out = res.getOutputStream();
				out.write(buffer);
				out.close();
			}
		}

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addPet".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer product_type_no = null;
				String product_name = req.getParameter("product_name");
				Integer product_price = null;
				String product_comment = req.getParameter("product_comment");

				String product_status = req.getParameter("product_status");
				Integer product_all_stars = null;
				Integer product_all_comments = null;

				String adoptPetNoSignReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]*$";
				String comment = "^[\\u4E00-\\u9FA5A-Za-z0-9]+[^%&',;=?$\\x22]{0,65}$";
				String price = "^[0-9]*$";

//				商品種類編號
				try {
					product_type_no = new Integer(req.getParameter("adopt_meb_no").trim());
				} catch (Exception e) {
					errorMsgs.put("product_type_no", "商品種類編號");
				}

//				商品名稱
				if (product_name.trim().length() == 0) {
					product_name = "商品名稱";
				} else if (!product_name.trim().matches(adoptPetNoSignReg)) {
					errorMsgs.put("product_name", "商品名稱: 只能是中、英文字母!!");
				}

//				商品價格
				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (Exception e) {
					errorMsgs.put("product_price", "商品價格: 只能是數字!!");
				}

//				商品簡介
				if (product_comment == null || product_comment.trim().length() == 0) {
					product_comment = "商品名稱";
				} else if (!product_comment.trim().matches(comment)) {
					errorMsgs.put("product_comment", "商品簡介: 只能是中、英文字母，且長度必需在0到65之間!!");
				}

//				商品狀態
				try {
					Integer adoptPetStatetest = new Integer(product_status.trim());
					if (adoptPetStatetest != 0 && adoptPetStatetest != 1) {
						errorMsgs.put("product_status", "商品狀態: 請勿竄改資料!");
					}
				} catch (Exception e) {
					errorMsgs.put("adoptPetStatetest", "商品狀態: 請勿竄改資料!");
				}

//				商品評價總星數
				try {
					product_all_stars = new Integer(req.getParameter("product_all_stars").trim());
				} catch (Exception e) {
					errorMsgs.put("product_all_stars", "商品價格: 只能是數字!!");
				}

//				商品評價總數量
				try {
					product_all_comments = new Integer(req.getParameter("product_all_comments").trim());
				} catch (Exception e) {
					errorMsgs.put("product_all_comments", "商品價格: 只能是數字!!");
				}



				
				ProductVO apVO = new ProductVO();
				apVO.setProduct_type_no(product_type_no);
				apVO.setProduct_name(product_name);
				apVO.setProduct_price(product_price);
				apVO.setProduct_comment(product_comment);
				apVO.setProduct_status(product_status);
				apVO.setProduct_all_stars(product_all_stars);
				apVO.setProduct_all_comments(product_all_comments);

//				資料錯誤return
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", apVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/product.jsp");
					failureView.forward(req, res);
					return;
				}

				productService productSvc = new productService();
				productSvc.addproduct(product_type_no, product_name, product_price, product_comment, product_status, product_all_stars, product_all_comments);

				String url = "/front_end/product/product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/product.jsp");
				failureView.forward(req, res);
			}

		}

	}
}