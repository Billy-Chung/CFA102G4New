package com.productPhotos.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.productPhotos.model.productPhotosService;
import com.productPhotos.model.productPhotosVO;
import com.promotions.model.*;

@WebServlet("/prodPhoto")
public class DBGifReader5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();

		try {
			      String prodNo = req.getParameter("prodNo");//拿來用
			      productPhotosService prodSvc = new productPhotosService();
			      productPhotosVO prodPhoto = prodSvc.getOneproductPhotos(new Integer(prodNo));
			      
			      byte[] b = prodPhoto.getProduct_photo();
				  out.write(b);//裝滿送

		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("/back_end/promotions/images/null2.jpg");//找不到圖的專案路徑
			byte[]b=new byte[in.available()];//宣告
			in.read(b);//取值
			out.write(b);//拿來用
			in.close();//關檔
		}
	}



}