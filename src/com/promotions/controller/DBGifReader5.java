package com.promotions.controller;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.promotions.model.*;

@WebServlet("/DBGifReader5")
public class DBGifReader5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			      String promot_photo = req.getParameter("promot_photo");
			      promotionsService promotionsSvc = new promotionsService();
			      promotionsVO promotionsVO = promotionsSvc.getOnePromotions(new Integer(promot_photo));
			      byte[] b = promotionsVO.getPromot_photo();
				  out.write(b);//裝滿送

		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("/NoData/null2.jpg");//找不到圖的專案路徑
			byte[]b=new byte[in.available()];//宣告
			in.read(b);//取值
			out.write(b);//拿來用
			in.close();//關檔
		}
	}



}