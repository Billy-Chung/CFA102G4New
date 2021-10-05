package com.promotions.controller;//

import java.io.IOException;//
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;//
import javax.servlet.annotation.MultipartConfig;//
import javax.servlet.http.HttpServlet;//
import javax.servlet.http.HttpServletRequest;//
import javax.servlet.http.HttpServletResponse;//
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.promo_photo.model.PromoPhotoService;
import com.promo_photo.model.PromoPhotoVO;
import com.promotions.model.*;

@MultipartConfig // 檔案上傳支援
public class PromotionsServlet extends HttpServlet {//
	private static final long serialVersionUID = 1L;//

	private PromoPhotoService photoSvc = new PromoPhotoService();
	private final PromotionsService promotionsSvc = new PromotionsService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {//
		doPost(req, res);//
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {//

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("promot_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotions/promotionsSelect_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer promot_no = null;
				try {
					promot_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotions/promotionsSelect_page.jsp");// 頁面跳轉
					failureView.forward(req, res);// forward請求轉發
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				
				PromotionsVO promotionsVO = promotionsSvc.getOnePromotions(promot_no);
				if (promotionsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotions/promotionsSelect_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("promotionsVO", promotionsVO); // 資料庫取出的promotionsVO物件,存入req
				String url = "/back_end/promotions/listOnePromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotions.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotions/promotionsSelect_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer promot_no = new Integer(req.getParameter("promot_no"));
				// 1. 從請求(HttpServletRequest)拿出參數，參數名稱是 promot_no,1，值是行銷編號的pk(字串型別)
				// 2. 將字串型別的 pk，用 new Interger的建構子，轉成數字
				/*************************** 2.開始查詢資料 ****************************************/
				PromotionsVO promotionsVO = promotionsSvc.getOnePromotions(promot_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("promotionsVO", promotionsVO); // 資料庫取出的promotionsVO物件,存入req
				String url = "/back_end/promotions/update_promotions_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_promotions_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/promotions/listAllPromotions.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_promotions_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer promot_no = new Integer(req.getParameter("promot_no").trim());// 資料轉換
				PromotionsVO promotionsVO = promotionsSvc.getOnePromotions(promot_no);
				
				String promot_name = req.getParameter("promot_name");
				String promot_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (promot_name == null || promot_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if (!promot_name.trim().matches(promot_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				java.sql.Date promot_date_start = null;// 開始日期不為空
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String promot_date_startStr = req.getParameter("promot_date_start");
				if (promot_date_startStr == null || promot_date_startStr.trim().isEmpty()) {
					errorMsgs.add("未填寫時間起始日");
				} else {
					try {
						// 將時間字串用 SimpleDateFormat 轉型成 java.util.Date型別
						java.util.Date utilDate = sdf.parse(promot_date_startStr);

						// 用 java.util.Date 的 getTime()方法取得毫秒數
						long milliSec = utilDate.getTime(); // 毫秒在手

						// 將毫秒數 注入 java.sql.Date(long) 建構子，產生 java.sql.Date 物件
						promot_date_start = new java.sql.Date(milliSec);
					} catch (Exception e) {
						promot_date_start = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入活動開始日期!");
					}
				}

				java.sql.Date promot_date_end = null;// 結束日期不為空
				try {
					promot_date_end = java.sql.Date.valueOf(req.getParameter("promot_date_end").trim());
				} catch (IllegalArgumentException e) {
					promot_date_end = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動結束日期!");
				}

				String promot_status = req.getParameter("promot_status").trim();
				if (promot_status == null || promot_status.trim().length() == 0) {
					errorMsgs.add("活動狀態請勿空白");
				}

				String promot_comment = req.getParameter("promot_comment").trim();
				if (promot_comment == null || promot_comment.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}
	
				Map<String, PromoPhotoVO> oriMap= photoSvc.getPromoPhotoVOMap(promot_no);
				req.getSession().setAttribute("photoMap", oriMap);
				
				Map<String, PromoPhotoVO> photoMap = getPhotoMap(req,errorMsgs);

				promotionsVO.setPromot_name(promot_name);
				promotionsVO.setPromot_date_start(promot_date_start);
				promotionsVO.setPromot_date_end(promot_date_end);
				promotionsVO.setPromot_status(promot_status);
				promotionsVO.setPromot_comment(promot_comment);
				promotionsVO.setPromot_photo(null);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotions/update_promotions_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				promotionsSvc.updatePromotion(promotionsVO, photoMap);
				req.getSession().removeAttribute("photoMap");
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("promotionsVO", promotionsVO); // 資料庫update成功後,正確的的promotionsVO物件,存入req
				String url = "/back_end/promotions/listOnePromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotions.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotions/update_promotions_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addpromotions.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String promot_name = req.getParameter("promot_name");
			String promot_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (promot_name == null || promot_name.trim().length() == 0) {
				errorMsgs.add("活動名稱: 請勿空白");
			} else if (!promot_name.trim().matches(promot_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			java.sql.Date promot_date_start = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String promot_date_startStr = req.getParameter("promot_date_start");
			if (promot_date_startStr == null || promot_date_startStr.trim().isEmpty()) {
				errorMsgs.add("未填寫時間起始日");
			} else {
				try {
					java.util.Date utilDate = sdf.parse(promot_date_startStr);
					long milliSec = utilDate.getTime();
					promot_date_start = new java.sql.Date(milliSec);
				} catch (IllegalArgumentException | ParseException e) {
					promot_date_start = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動開始日期!");
				}
			}
			java.sql.Date promot_date_end = null;
			try {
				promot_date_end = java.sql.Date.valueOf(req.getParameter("promot_date_end").trim());
			} catch (IllegalArgumentException e) {
				promot_date_end = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入活動結束日期!");
			}

			String promot_status = req.getParameter("promot_status").trim();
			if (promot_status == null || promot_status.trim().length() == 0) {
				errorMsgs.add("活動狀態請勿空白");
			}

			String promot_type = req.getParameter("promot_type").trim();
			if (promot_type == null || promot_type.trim().length() == 0) {
				errorMsgs.add("活動種類請勿空白");
			}

			String promot_discount_type = req.getParameter("promot_discount_type").trim();
			if (promot_discount_type == null || promot_discount_type.trim().length() == 0) {
				errorMsgs.add("折扣方式請勿空白");
			}

			String promot_discountStr = req.getParameter("promot_discount");
			Integer promot_discount = null;
			if (promot_discountStr == null || promot_discountStr.trim().length() == 0) {
				errorMsgs.add("折數請勿空白");
			} else {
				try {
					promot_discount = new Integer(promot_discountStr.trim());
					if (promot_discount < 0 || promot_discount > 100) {
						errorMsgs.add("折數必須是介於0-100之間");
					}
				} catch (NumberFormatException nfe) {
					errorMsgs.add("折數必須是數字");
				}
			}

			String promot_reduceStr = req.getParameter("promot_reduce");// 宣告字串，req.getParameter()取得請求參數方法，jap讀取出來，封裝使用，getParameter()
																		// 傳回的是 String 物件
			Integer promot_reduce;// 宣告數字
			Integer defaultPromoReduce = 0;
			if (promot_reduceStr == null || promot_reduceStr.trim().length() == 0) {
				promot_reduce = defaultPromoReduce;
				errorMsgs.add("減價請勿空白");
			} else {
				try {
					promot_reduce = new Integer(promot_reduceStr.trim());// 資料轉換
				} catch (NumberFormatException nfe) {
					promot_reduce = defaultPromoReduce;
					errorMsgs.add("減價必須是數字");
				}
			}

			String promot_comment = req.getParameter("promot_comment").trim();
			if (promot_comment == null || promot_comment.trim().length() == 0) {
				errorMsgs.add("活動描述請勿空白");
			}

			Map<String, PromoPhotoVO> photoMap = getPhotoMap(req, errorMsgs);
			HttpSession session = req.getSession();
			session.setAttribute("photoMap", photoMap);

			PromotionsVO promotionsVO = new PromotionsVO();// promotionsVO()
			promotionsVO.setPromot_name(promot_name);
			promotionsVO.setPromot_date_start(promot_date_start);
			promotionsVO.setPromot_date_end(promot_date_end);
			promotionsVO.setPromot_status(promot_status);
			promotionsVO.setPromot_type(promot_type);
			promotionsVO.setPromot_discount_type(promot_discount_type);
			promotionsVO.setPromot_discount(promot_discount);
			promotionsVO.setPromot_reduce(promot_reduce);
			promotionsVO.setPromot_comment(promot_comment);
			promotionsVO.setPromot_photo(null);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req

				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/promotions/addPromotions.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/

			if ("0".equals(promot_type)) {
				// 全館行銷 (0)
				promotionsVO = promotionsSvc.addPromoWithAllProdDiscount(promotionsVO, photoMap);
			} else if ("1".equals(promot_type)) {
				System.out.println("尚未實作個別行銷");
				// 個別行銷 (1)
				// TODO 尚未實作
			} else {
				System.out.println("錯誤行銷類型");
			}

			session.removeAttribute("photoMap");
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/promotions/listOnePromotions.jsp";
			req.setAttribute("promotionsVO", promotionsVO);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOnePromotions.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotions/addPromotions.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("delete".equals(action)) { // 來自listAllPromotions.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer promot_no = new Integer(req.getParameter("promot_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				promotionsSvc.deletepromotions(promot_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/promotions/listAllPromotions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/promotions/listAllPromotions.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

	private Map<String, PromoPhotoVO> getPhotoMap(HttpServletRequest req, List<String> errorMsgs)
			throws IOException, ServletException {
		List<String> photoNames = Arrays.asList("banner1", "banner2", "banner3", "custPhoto");

		Map<String, PromoPhotoVO> photoMap = new HashMap<>();

		String errorPhotoMsg = "%s 圖片格式錯誤或未上傳";

		Map<String, PromoPhotoVO> sessionMap = (Map<String, PromoPhotoVO>) req.getSession().getAttribute("photoMap");

		for (String name : photoNames) {
			Part photoPart = req.getPart(name);
			String contentType = photoPart.getContentType();
			InputStream stream = photoPart.getInputStream();
			int partSize = stream.available();
			byte[] photoArray;
			
			PromoPhotoVO promoPhoto = null;
			if (sessionMap != null) {
				promoPhoto = sessionMap.get(name);
			}

			if (partSize != 0 && contentType.startsWith("image/")) {
				// 如果畫面有上傳新圖片，以新圖片為主
				photoArray = new byte[partSize];
				stream.read(photoArray);
				stream.close();
			} else if (promoPhoto != null) {
				// 如果畫面沒新圖片，用session的
				photoArray = promoPhoto.getPhoto();
			} else {
				// 如果session沒圖片，踢出錯誤
				errorMsgs.add(String.format(errorPhotoMsg, name));
				continue;
			}

			PromoPhotoVO promoPhotoVO = new PromoPhotoVO();
			promoPhotoVO.setMimetype(contentType);
			promoPhotoVO.setPhoto(photoArray);
			promoPhotoVO.setPromo_function(name);

			photoMap.put(name, promoPhotoVO);
		}

		return photoMap;
	}
}
