package com.adoptMemberNews.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adoptMember.model.AdoptMemberService;
import com.adoptMember.model.AdoptMemberVO;
import com.adoptMemberNews.model.AdoptMemberNewService;
import com.adoptMemberNews.model.AdoptMemberNewsVo;
import com.adoptPetPhoto.model.AdoptPetPhotoService;
import com.adoptPetPhoto.model.AdoptPetPhotoVO;

import javafx.beans.binding.StringBinding;

@javax.servlet.annotation.MultipartConfig
public class AdoptMemberNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("img/jepg");
		String action = req.getParameter("action");

		if ("showNewsPhoto".equals(action)) {

			Integer PK = new Integer(req.getParameter("PK"));
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			AdoptMemberNewsVo thisNewsPhoto = adoptMemberNewSvc.findByadoptMemberNewsNoPK(PK);
			try {
				byte[] photo = thisNewsPhoto.getAdopt_meb_news_photo();

				ServletOutputStream out = res.getOutputStream();
				out.write(photo);
				out.close();
			} catch (NullPointerException np) {

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
		
		if("showNewsPage".equals(action)) {
			Integer PK = new Integer(req.getParameter("PK"));
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			AdoptMemberNewsVo thisNew = adoptMemberNewSvc.findByadoptMemberNewsNoPK(PK);
			StringBuilder newComment = new StringBuilder();
			String [] oldContext = thisNew.getAdopt_meb_news_comment().split("\\r\\n");
			for(String comment : oldContext) {
				System.out.println(comment);
				newComment.append(comment + "<br>");
			}
			thisNew.setAdopt_meb_news_comment(newComment.toString());
			req.setAttribute("thisNew", thisNew);
			String url = "/front_end/adoptMember/adoptMemberNewsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addNews".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adoptMebNo = null;
			String adoptMebNewsTitle = new String(req.getParameter("adopt_meb_news_title"));
			String adoptMebNewsComment = new String(req.getParameter("adopt_meb_news_comment"));
			String adoptMebNewsDate = new String(req.getParameter("adopt_meb_news_date"));
			Date sqlNowDate = java.sql.Date.valueOf(adoptMebNewsDate);
			String adoptMebNewsState = new String(req.getParameter("adopt_meb_news_state"));

			try {
				adoptMebNo = new Integer(req.getParameter("adopt_meb_no").trim());
			} catch (Exception e) {
				errorMsgs.put("adoptMebNo", "領養會員編號: 請勿竄改資料!");
			}

			if (adoptMebNewsTitle.trim().length() == 0) {
				errorMsgs.put("adoptMebNewsTitle", "請輸入最新消息標題!!");
			}

			if (adoptMebNewsComment.trim().length() == 0) {
				errorMsgs.put("adoptMebNewsComment", "請輸入最新消息內文!!");
			}

			AdoptMemberNewsVo adoptMemberNewsVo = new AdoptMemberNewsVo();
			adoptMemberNewsVo.setAdopt_meb_news_title(adoptMebNewsTitle);
			adoptMemberNewsVo.setAdopt_meb_news_comment(adoptMebNewsComment);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adoptMemberNewsVo", adoptMemberNewsVo);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/addNews.jsp");
				failureView.forward(req, res);
				return;
			}

			Part newsPhoto = req.getPart("adopt_meb_news_photo");
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			InputStream in = newsPhoto.getInputStream();
			if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				adoptMemberNewSvc.insertAdoptMemberNew(adoptMebNo, adoptMebNewsTitle, adoptMebNewsComment, buf,
						adoptMebNewsState, sqlNowDate);
				String url = "/back_end/adoptMember/adoptMemberNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				adoptMemberNewSvc.insertAdoptMemberNew(adoptMebNo, adoptMebNewsTitle, adoptMebNewsComment, null,
						adoptMebNewsState, sqlNowDate);
				String url = "/back_end/adoptMember/adoptMemberNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}

		if ("updateNewsPage".equals(action)) {
			Integer adoptMebNewsNo = new Integer(req.getParameter("adopt_meb_news_no"));
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			AdoptMemberNewsVo thisNews = adoptMemberNewSvc.findByadoptMemberNewsNoPK(adoptMebNewsNo);
			StringBuilder newComment = new StringBuilder();
			String [] oldContext = thisNews.getAdopt_meb_news_comment().split("\\r\\n");
			for(String comment : oldContext) {
				newComment.append(comment + "\\n");
			}
			thisNews.setAdopt_meb_news_comment(newComment.toString());
			req.setAttribute("thisNews", thisNews);
			String url = "/back_end/adoptMember/updateNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("updateNews".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adoptMebNo = null;
			String adoptMebNewsTitle = new String(req.getParameter("adopt_meb_news_title"));
			String adoptMebNewsComment = new String(req.getParameter("adopt_meb_news_comment"));
			String adoptMebNewsDate = new String(req.getParameter("adopt_meb_news_date"));
			Date sqlNowDate = java.sql.Date.valueOf(adoptMebNewsDate);
			String adoptMebNewsState = new String(req.getParameter("adopt_meb_news_state"));
			Integer adoptMebNewsNo = new Integer(req.getParameter("adopt_meb_news_no"));
			String requestURL = req.getParameter("requestURL");
			try {
				adoptMebNo = new Integer(req.getParameter("adopt_meb_no").trim());
			} catch (Exception e) {
				errorMsgs.put("adoptMebNo", "領養會員編號: 請勿竄改資料!");
			}

			if (adoptMebNewsTitle.trim().length() == 0) {
				errorMsgs.put("adoptMebNewsTitle", "請輸入最新消息標題!!");
			}

			if (adoptMebNewsComment.trim().length() == 0) {
				errorMsgs.put("adoptMebNewsComment", "請輸入最新消息內文!!");
			}

			AdoptMemberNewsVo updateFail = new AdoptMemberNewsVo();
			updateFail.setAdopt_meb_news_no(adoptMebNewsNo);
			updateFail.setAdopt_meb_news_title(adoptMebNewsTitle);
			updateFail.setAdopt_meb_news_state(adoptMebNewsState);
			updateFail.setAdopt_meb_news_comment(adoptMebNewsComment);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("thisNews", updateFail);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adoptMember/updateNews.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Part newsPhoto = req.getPart("adopt_meb_news_photo");
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			InputStream in = newsPhoto.getInputStream();
			if (in.available() != 0) {
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				adoptMemberNewSvc.updateAdoptMemberNew(adoptMebNewsTitle, adoptMebNewsComment, buf, adoptMebNewsState, sqlNowDate, adoptMebNewsNo);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				AdoptMemberNewsVo oldNewsVO = adoptMemberNewSvc.findByadoptMemberNewsNoPK(adoptMebNewsNo);
				byte[] buf = oldNewsVO.getAdopt_meb_news_photo();
				adoptMemberNewSvc.updateAdoptMemberNew(adoptMebNewsTitle, adoptMebNewsComment, buf, adoptMebNewsState, sqlNowDate, adoptMebNewsNo);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}		
		}
		
		if("updateNewsStatus".equals(action)) {
			AdoptMemberNewService adoptMemberNewSvc = new AdoptMemberNewService();
			Integer adoptMebNewsNo = new Integer(req.getParameter("adopt_meb_news_no"));
			String requestURL = req.getParameter("requestURL");
			AdoptMemberNewsVo nowNewsVO = adoptMemberNewSvc.findByadoptMemberNewsNoPK(adoptMebNewsNo);
			Integer nowStatus = new Integer(nowNewsVO.getAdopt_meb_news_state());
			if(nowStatus == 1) {
				nowNewsVO.setAdopt_meb_news_state("0");
			}else {
				nowNewsVO.setAdopt_meb_news_state("1");
			}

			byte[] adoptMebNewsPhoto = nowNewsVO.getAdopt_meb_news_photo();
			String adoptMebNewsTitle = nowNewsVO.getAdopt_meb_news_title();
			String adoptMebNewsComment = nowNewsVO.getAdopt_meb_news_comment();
			Date adoptMebNewsDate = nowNewsVO.getAdopt_meb_news_date();		
			String adoptMebNewsState = nowNewsVO.getAdopt_meb_news_state();
			adoptMemberNewSvc.updateAdoptMemberNew(adoptMebNewsTitle, adoptMebNewsComment, adoptMebNewsPhoto, adoptMebNewsState, adoptMebNewsDate, adoptMebNewsNo);
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
