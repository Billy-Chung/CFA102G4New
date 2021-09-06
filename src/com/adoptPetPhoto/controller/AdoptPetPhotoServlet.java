package com.adoptPetPhoto.controller;

//System.out.println(getServletContext().getRealPath("front_end/adoptMember/images/dog1.jpg"));
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptPetPhoto.model.AdoptPetPhotoService;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;

@javax.servlet.annotation.MultipartConfig
public class AdoptPetPhotoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("img/jepg");
		String action = req.getParameter("action");
		if ("allPhoto".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			AdoptPetPhotoVO petPhotos = adoptPetPhotoService.findByPK(PK);
			byte[] petPhoto = petPhotos.getAdopt_pet_photo();
			ServletOutputStream out = res.getOutputStream();		
			out.write(petPhoto);
			out.close();
		}

		if ("cover".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			AdoptPetPhotoVO petPhotos = adoptPetPhotoService.findByPhotoCover(PK);
			try {
				byte[] petPhoto = petPhotos.getAdopt_pet_photo();
				ServletOutputStream out = res.getOutputStream();
				out.write(petPhoto);
				out.close();
			} catch (NullPointerException np) {

				FileInputStream fis = new FileInputStream(
						getServletContext().getRealPath("back_end/adopt/image/dog1.jpg"));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				ServletOutputStream out = res.getOutputStream();
				out.write(buffer);
				out.close();
			}

		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addNewPetPhoto".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			Part photo = req.getPart("adopt_pet_photo");

			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
			String adoptPetCoverState = new String(req.getParameter("adopt_pet_cover_state"));
			Date date = new Date();
			Timestamp changeTime = new Timestamp(date.getTime());
			
			InputStream in = photo.getInputStream();
			if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
				adoptPetPhotoService.insertAdoptPetPhoto(adoptPetNo, buf, adoptPetCoverState,changeTime);
				String url = "/back_end/adopt/adoptPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} else {
				errorMsgs.put("errorPhoto", "請選擇要新增的圖片!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adopt/adoptPet.jsp");
				failureView.forward(req, res);
			}

		}

		if ("findByadoptPetNo".equals(action)) {
			Integer adoptPetNo = new Integer(req.getParameter("adopt_pet_no"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			List<AdoptPetPhotoVO> adoptMemberPhotoList = adoptPetPhotoService.findByadoptPetNo(adoptPetNo);
//			base64送的寫法
//			Map<String,String> imgMap = new HashMap();
//			for(AdoptPetPhotoVO adoptPetPhoto:adoptMemberPhotoList){
//				byte[] photo = adoptPetPhoto.getAdopt_pet_photo();
//				String newAdoptPetNo = adoptPetPhoto.getAdopt_pet_photo_no().toString();
//				String base64Img = Base64.getEncoder().encodeToString(photo);
//				imgMap.put(newAdoptPetNo,base64Img);
//			}		
			req.setAttribute("adoptMemberPhotoList", adoptMemberPhotoList);
			String url = "/back_end/adopt/allPetPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("deletePhoto".equals(action)) {
			Integer adoptPetNo = new Integer(req.getParameter("adoptPetNo"));
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			adoptPetPhotoService.deleteAdoptPetPhoto(adoptPetNo);
//			forward是空白頁
//			List<AdoptPetPhotoVO> adoptMemberPhotoList = adoptPetPhotoService.findByadoptPetNo(adoptPetNo);
//			req.setAttribute("adoptMemberPhotoList", adoptMemberPhotoList);
//			String url = "/front_end/adoptPet/allPetPhoto.jsp";			
			String url = "/back_end/adopt/adoptPet.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			Integer newState = null;
			Integer adoptPetNo = new Integer(req.getParameter("adoptPetNo"));
			Integer adoptPetCoverState = new Integer(req.getParameter("adoptPetCoverState"));
			Date date = new Date();
			Timestamp changeTime = new Timestamp(date.getTime());
			newState = (adoptPetCoverState == 0 ? 1 : 0);
			AdoptPetPhotoService adoptPetPhotoService = new AdoptPetPhotoService();
			adoptPetPhotoService.updateAdoptPetPhoto(newState.toString(),changeTime, adoptPetNo);
			String url = "/back_end/adopt/adoptPet.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

}
