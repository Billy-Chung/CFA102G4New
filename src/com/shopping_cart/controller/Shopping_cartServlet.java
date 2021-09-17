package com.shopping_cart.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order_detail.model.order_detailVO;
import com.product.model.productService;
import com.product.model.productVO;
import com.promotions.model.PromotionsService;
import com.promotions.model.promotionsVO;
import com.shopping_cart.model.Shopping_cartService;
import com.shopping_cart.model.shopping_cartVO;

/**
 * Servlet implementation class Shopping_cartServlet
 */
@WebServlet("/Shopping_cartServlet")
public class Shopping_cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private productService prodSVC = new productService();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

	
		// productId, 訂單明細物件                //總值                                                                                                                                               //key
		Map<Integer, order_detailVO> cart = (Map<Integer, order_detailVO>) session.getAttribute("cart");
		
		String action = request.getParameter("action");
		System.out.println("action: " + action);
		
		Integer product_no =new Integer (request.getParameter("product_no"));
		if ("addToCart".equals(action)) {// 來自FakeProductList.jsp的請求
			//從請求中拿出商品的PK，利用產品的service
			order_detailVO orderDetailVO=getOrder_detail(product_no);	
						
			//判斷購物車的存在
			if(cart==null) {
				//因為購物車不存在，所以建立購物車
				cart = new HashMap<Integer, order_detailVO>();
				//將商品加入購物車
				cart.put(product_no, orderDetailVO);
				//將購物車放入session
				session.setAttribute("cart", cart);
			}else {
				//1. 以前有商品
				
				//2. 以前沒有商品
			}
		} else {
			System.out.println("還沒設計情境, action: "+ action);
		}
		
	}

	/**
	 * 用產品pk，從service 取出產品，再轉換成訂單明細物件
	 * @param product_no 商品編號
	 * @return 
	 */
	private order_detailVO getOrder_detail(Integer product_no) {
		//在產品service中找出商品(利用產品pk)
		productVO prod = prodSVC.getOneproduct(product_no);
		
		//將商品資訊送入訂單明細內
		order_detailVO detailVO = new order_detailVO();
		detailVO.setProduct_no(prod.getProduct_no());
		//...將商品資料倒入訂單明細
		
		
		//行銷活動的優惠價格
		//TODO
		
		return detailVO;
	}

}



//1.
//沒東西null沒購物車	 	
//有東西有購物車
//以前買過有購物車 
//productservice findbypk 名稱 價格 數量
//印購物車內容
//orderto
			
			
			
//2.
//找到商品放進購物車
//更換商品
//放棄商品
//找不到商品