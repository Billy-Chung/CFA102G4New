package com.promotions.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.promo_photo.model.PromoPhotoService;
import com.promo_photo.model.PromoPhotoVO;
import com.promotions.model.*;

@WebServlet("/PromoPhotos")
public class PromoPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PromoPhotoService promotionsSvc = new PromoPhotoService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		ServletOutputStream out = res.getOutputStream();

		try {
			int promoNo = Integer.parseInt(req.getParameter("promoNo"));// 拿來用
			String functionName = req.getParameter("functionName");

			PromoPhotoVO promoPhotoVO = promotionsSvc.getPromoPhotoVO(promoNo, functionName);
			res.setContentType(promoPhotoVO.getMimetype());
			byte[] b = promoPhotoVO.getPhoto();
			out.write(b);// 裝滿送

		} catch (Exception e) {
			System.out.println(e.getMessage());
			InputStream in = getServletContext().getResourceAsStream("/back_end/promotions/images/null2.jpg");// 找不到圖的專案路徑
			byte[] b = new byte[in.available()];// 宣告
			in.read(b);// 取值
			out.write(b);// 拿來用
			in.close();// 關檔
		}
	}

}