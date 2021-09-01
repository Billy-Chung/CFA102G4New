package com.adoptPetPhoto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptPetPhoto.model.AdoptPetPhotoService;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;

@javax.servlet.annotation.MultipartConfig
public class AdoptPetPhotoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("addNewPetPhoto".equals(action)) {
			 Part photo = req.getPart("adopt_pet_photo");			
			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
			InputStream in = photo.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			adoptPetPhotoService.insertAdoptPetPhoto(adoptPetNo,buf);
			String url = "/front_end/adoptPet/adoptPet.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		
		if("findByadoptPetNo".equals(action)) {
			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			List<AdoptPetPhotoVO> adoptMemberPhotoList = adoptPetPhotoService.findByadoptPetNo(adoptPetNo);
			
			req.setAttribute("adoptMemberPhotoList", adoptMemberPhotoList);
			String url = "/front_end/adoptPet/allPetPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		
		
	}

}
