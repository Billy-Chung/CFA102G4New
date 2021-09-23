package com.shopping_cart.controller;

import java.io.IOException;
import java.io.Serializable;
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
                                  //繼承
public class Shopping_cartServlet extends HttpServlet {
	// 預設自動產生因為實現了shopping_cartVO的Serializable介面
	private static final long serialVersionUID = 1L;
	        // 回傳值型態 方法名稱=創建一個新的productService
	private productService prodSVC = new productService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//HttpSession為資料型態，使用 Request的 getSession()方法取得 HttpSession物件，命名session
		HttpSession session = request.getSession();

		// product_no當作key
		// order_detailVO當作value
		// 從客戶端得到 key為cart的參數， session.getAttribute("cart")是object，強轉型為Map型別        
		Map<Integer, order_detailVO> cart = (Map<Integer, order_detailVO>) session.getAttribute("cart");
		
		// 來自FakeProduct.jsp action請求，收到結果是...，往下走到...
		String action = request.getParameter("action");
		System.out.println("action: " + action);

		// Integer資料型態，創建一個新的商品編號，命名為product_no
		Integer product_no = new Integer(request.getParameter("product_no"));
		
		if (action.equals("addToCart")) {// 來自FakeProductList.jsp的請求，如果action=addToCart
		
			// order_detailVO資料型態，使用getOrder_detail方法，拿出商品的PK(product_no)，利用產品的service
			order_detailVO orderDetailVO = getOneOrder_detail(product_no);
			// Integer資料型態 ，預設購買數量(defaultOrderProductNumber)，以後購買數量都加一
			Integer defaultOrderProductNumber = 1;
			// orderDetailVO的setOrder_product_number方法，把購買數量(defaultOrderProductNumber)放進來
			orderDetailVO.setOrder_product_number(defaultOrderProductNumber);
			// 判斷購物車的存在
			if (cart == null) {
				// 因為購物車不存在，所以建立購物車
				cart = new HashMap<Integer, order_detailVO>();
				// 將商品放入購物車
				cart.put(product_no, orderDetailVO);
				// 將購物車放入session
				session.setAttribute("cart", cart);
			} else {
				// 用產品號碼(product_no)，使用購物車(cart)用get方法，取出訂單明細，並且放入 物件型別為 order_detailVO的order_detail內
				order_detailVO order_detail = cart.get(product_no);
				if (order_detail == null) {
					// 情境1.以前沒有商品
					// 將商品放入購物車
					cart.put(product_no, orderDetailVO);
					// 將購物車放入session
					session.setAttribute("cart", cart);
				} else {
					// 情境2.購物車以前有商品，將所選擇的數量(ORDER_PRODUCT_NUMBER)加入購物車(cart)
					// 最新購買數量=原有購買數量+預設購買數量
					
					// 訂單明細(order_detail)用get方法，取出原有購買數量(oriProductNumber)，並且放入物件型別為Integer的order_detail內
					Integer oriProductNumber = order_detail.getOrder_product_number();
					//原有購買數量(oriProductNumber)+預設購買數量(defaultOrderProductNumber)，放入物件型別為Integer的newProductNumber內
					Integer newProductNumber = oriProductNumber + defaultOrderProductNumber;
					// 訂單明細(order_detail)用set方法，放入新的購買數量(newProductNumber)，更新訂單明細的購買數量
					order_detail.setOrder_product_number(newProductNumber);
					// 更新購物車(cart)的訂單明細(order_detail)
					cart.put(product_no, order_detail);
				}
			}
			System.out.println(cart);
			//forword                  									
			RequestDispatcher successfulView = request.getRequestDispatcher("/front_end/shopping_cart/shoppingCart.jsp");
			successfulView.forward(request, response);
			
		} else {
			System.out.println("還沒設計情境, action: " + action);
		}

	}

	/**
	 * 用產品pk，從service 取出產品，再轉換成訂單明細物件
	 * 
	 * @param product_no 商品編號
	 * @return
	 */
	

	
	private order_detailVO getOneOrder_detail(Integer product_no) {
		// 在產品service中找出商品(利用產品pk)
		productVO prod = prodSVC.getOneproduct(product_no);
		
		// 將商品資訊送入訂單明細內
		order_detailVO detailVO = new order_detailVO();
		detailVO.setProduct_no(prod.getProduct_no());
		detailVO.setProduct_name(prod.getProduct_name());
		detailVO.setProduct_price(prod.getProduct_price());

		// 行銷活動的優惠價格
		// TODO

		return detailVO;
	}

}



//2.
//找到商品放進購物車
//更換商品
//放棄商品
//找不到商品