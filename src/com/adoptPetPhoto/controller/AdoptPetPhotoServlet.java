package com.adoptPetPhoto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;

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
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			 Part photo = req.getPart("adopt_pet_photo");
			 System.out.println(req.getParameter("adopt_pet_no"));
			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));		
			InputStream in = photo.getInputStream();
			if(in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
				adoptPetPhotoService.insertAdoptPetPhoto(adoptPetNo,buf);
				String url = "/front_end/adoptPet/adoptPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			}else {
				errorMsgs.put("errorPhoto", "請選擇要新增的圖片!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/adoptPet/adoptPet.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if("findByadoptPetNo".equals(action)) {
			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			List<AdoptPetPhotoVO> adoptMemberPhotoList = adoptPetPhotoService.findByadoptPetNo(adoptPetNo);
			Map<String,String> imgMap = new HashMap();
			for(AdoptPetPhotoVO adoptPetPhoto:adoptMemberPhotoList){
				byte[] photo = adoptPetPhoto.getAdopt_pet_photo();
				String newAdoptPetNo = adoptPetPhoto.getAdopt_pet_photo_no().toString();
				String base64Img = Base64.getEncoder().encodeToString(photo);
				imgMap.put(newAdoptPetNo,base64Img);
			}		
			
			req.setAttribute("adoptPetNo", adoptPetNo);
			req.setAttribute("adoptMemberPhotoMap", imgMap);
			String url = "/front_end/adoptPet/allPetPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		
		
	}

}
