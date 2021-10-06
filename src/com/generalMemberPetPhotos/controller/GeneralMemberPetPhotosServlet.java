package com.generalMemberPetPhotos.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptPet.model.AdoptPetVO;
import com.generalMemberPet.model.GeneralMemberPetService;
import com.generalMemberPet.model.GeneralMemberPetVO;
import com.generalMemberPetPhotos.model.GeneralMemberPetPhotosService;
import com.generalMemberPetPhotos.model.GeneralMemberPetPhotosVO;

@javax.servlet.annotation.MultipartConfig
public class GeneralMemberPetPhotosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GeneralMemberPetPhotosServlet() {
        super();
    }    



	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("img/jepg");
		String action = req.getParameter("action");
		
		if("allPhoto".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			GeneralMemberPetPhotosVO gmppVO = gmppSvc.findByPK(PK);
			byte[] petPhoto = gmppVO.getGen_meb_pet_photo();
			ServletOutputStream out = res.getOutputStream();
			out.write(petPhoto);
			out.close();
		}
		
		if("cover".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			GeneralMemberPetPhotosVO gmppVO = gmppSvc.findByPhotoCover(PK);
			
			try {
				byte[] petPhoto = gmppVO.getGen_meb_pet_photo();
				ServletOutputStream out = res.getOutputStream();
				out.write(petPhoto);
				out.close();
			} catch (NullPointerException ne) {
				
				FileInputStream fis = new FileInputStream(getServletContext().getRealPath("front_end/GeneralMemberPet/image/no.jpg"));
				
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
		
		if("addNewPetPhoto".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs",errorMsgs);
			Part photo = req.getPart("gmpp");
			String requestURL = req.getParameter("requestURL");
			
			
			Integer genNo = new Integer(req.getParameter("gmpno"));
			String genPetCoverState = new String(req.getParameter("gen_pet_cover_state"));
			Date date = new Date();
			Timestamp changeTime = new Timestamp(date.getTime());
			
			InputStream in = photo.getInputStream();
			if(in.available()!=0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
				gmppSvc.insertGeneralMemberPetPhotos(genNo,buf,genPetCoverState,changeTime);
				if(requestURL.equals("/front_end/GeneralMembetPet/searchPetPage.jsp")) {
					GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
					List<GeneralMemberPetVO> searchPet = gmpSvc.getAll();
					List<GeneralMemberPetVO> searchList = new ArrayList<>();
					
					searchList = searchPet.stream()
							.filter(p -> p.getGen_meb_pet_chip().contains(req.getParameter("whichChip")))
							.collect(Collectors.toList());
					
					req.setAttribute("searchList",searchList);
					
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} else {
				errorMsgs.put("errorPhoto", "請選擇要新增的圖片!!");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("findBygenNo".equals(action)) {
			Integer genNo = new Integer(req.getParameter("genNo"));
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			List<GeneralMemberPetPhotosVO> gmppList = gmppSvc.findByGeneralMemberPetNO(genNo);
			
			req.setAttribute("gmppList",gmppList);
			String url = "/front_end/GeneralMemberPet/allPetPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if("deletePhoto".equals(action)) {
			Integer gmppno = new Integer(req.getParameter("gmppno"));
			Integer gmno = new Integer(req.getParameter("gmno"));
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			gmppSvc.deleteGeneralMemberPetPhotos(gmppno);
			List<GeneralMemberPetPhotosVO> gmppList = gmppSvc.findByGeneralMemberPetNO(gmno);
			
			req.setAttribute("gmppList", gmppList);
			String url = "/front_end/GeneralMemberPet/allPetPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
			Integer newState = null;
			Integer gmno = new Integer(req.getParameter("gmno"));
			String requestURL = req.getParameter("requestURL");
			Integer genPetCoverState = new Integer(req.getParameter("genPetCoverState"));
			Date date = new Date();
			Timestamp changeTime = new Timestamp(date.getTime());
			newState =(genPetCoverState == 0 ? 1:0);
			GeneralMemberPetPhotosService gmppSvc = new GeneralMemberPetPhotosService();
			gmppSvc.updateGeneralMemberPetPhotos(newState.toString(),changeTime,gmno);
			
			if(requestURL.equals("front_end/GeneralMemberPet/searchPetPage.jsp")) {
				GeneralMemberPetService gmpSvc = new GeneralMemberPetService();
				List<GeneralMemberPetVO> searchPet = gmpSvc.getAll();
				List<GeneralMemberPetVO> searchList = new ArrayList<>();
				searchList = searchPet.stream()
						.filter(p -> p.getGen_meb_pet_chip().contains(req.getParameter("whichChip")))
						.collect(Collectors.toList());
				
				req.setAttribute("searchList", searchList);
			}
			
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
			
			
		}
	}

}
