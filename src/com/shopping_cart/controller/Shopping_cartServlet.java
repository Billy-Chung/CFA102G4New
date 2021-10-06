package com.shopping_cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.order_detail.model.OrderDetailVO;
import com.product.model.ProductVO;
import com.product.model.productService;
import com.product_promotions_detail.model.ProductPromotionService;
import com.product_promotions_detail.model.ProductPromotionsDetailVO;
import com.promotions.model.PromotionsService;
import com.promotions.model.PromotionsVO;

/**
 * Servlet implementation class Shopping_cartServlet
 */
@WebServlet("/Shopping_cartServlet")
// 繼承
public class Shopping_cartServlet extends HttpServlet {
	// 預設自動產生因為實現了shopping_cartVO的Serializable介面
	private static final long serialVersionUID = 1L;
	private static final char[] total = null;
	// 回傳值型態 方法名稱=創建一個新的productService
	private productService prodSVC = new productService();
	private ProductPromotionService productPromoSvc = new ProductPromotionService();
	private PromotionsService promotionSvc = new PromotionsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// HttpSession為資料型態，使用 Request的 getSession()方法取得 HttpSession物件，命名session
		HttpSession session = request.getSession();

		// product_no當作key
		// order_detailVO當作value
		// 從客戶端得到 key為cart的參數， session.getAttribute("cart")是object，強轉型為Map型別
		Map<Integer, OrderDetailVO> cart = (Map<Integer, OrderDetailVO>) session.getAttribute("cart");

		// 來自FakeProduct.jsp action請求，收到結果是...，往下走到...
		String action = request.getParameter("action");
		System.out.println("action: " + action);
		if (action.equals("addToCart")) {// 來自FakeProductList.jsp的請求，如果action=addToCart
			// Integer資料型態，創建一個新的商品編號，命名為product_no
			Integer product_no = new Integer(request.getParameter("product_no"));
			// order_detailVO資料型態，使用getOrder_detail方法，拿出商品的PK(product_no)，利用產品的service
			OrderDetailVO orderDetailVO = getOneOrder_detail(product_no);
			// Integer資料型態 ，預設購買數量(defaultOrderProductNumber)，以後購買數量都加一
			Integer defaultOrderProductNumber = 1;
			// orderDetailVO的setOrder_product_number方法，把購買數量(defaultOrderProductNumber)放進來
			orderDetailVO.setOrder_product_number(defaultOrderProductNumber);
			// 判斷購物車的存在
			if (cart == null) {
				// 因為購物車不存在，所以建立購物車
				cart = new HashMap<Integer, OrderDetailVO>();
				// 將商品放入購物車
				cart.put(product_no, orderDetailVO);
				// 將購物車放入session
				session.setAttribute("cart", cart);
			} else {
				// 用產品號碼(product_no)，使用購物車(cart)用get方法，取出訂單明細，並且放入 物件型別為
				// order_detailVO的order_detail內
				OrderDetailVO order_detail = cart.get(product_no);
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
					// 原有購買數量(oriProductNumber)+預設購買數量(defaultOrderProductNumber)，放入物件型別為Integer的newProductNumber內
					Integer newProductNumber = oriProductNumber + defaultOrderProductNumber;
					// 訂單明細(order_detail)用set方法，放入新的購買數量(newProductNumber)，更新訂單明細的購買數量
					order_detail.setOrder_product_number(newProductNumber);
					// 更新購物車(cart)的訂單明細(order_detail)
					cart.put(product_no, order_detail);
				}
			}

			RequestDispatcher successfulView = request
					.getRequestDispatcher("/front_end/shopping_cart/shoppingCart.jsp");
			successfulView.forward(request, response);

		} else if (action.equals("CHECKOUT")) {
			// 將Map內的所有值轉存成集合Collection，取值所有的值 ，存到購物車的清單中cartlist
			Collection<OrderDetailVO> cartlist = cart.values();

			Integer total = 0;
			// foreach 使用魔法蜜糖for(資料型態 命名 : 遍歷對象) {
			for (OrderDetailVO order_detail : cartlist) {
				Integer price = order_detail.getProduct_price();
				Integer quantity = order_detail.getOrder_product_number();
				total += (price * quantity);
			}

			// 轉成字串
			System.out.println("total=" + total);
			String amount = String.valueOf(total);
			// 從Checkout.jsp發出請求amount的總金額
			session.setAttribute("amount", amount);
			String url = "/front_end/shopping_cart/Checkout.jsp";
			// 從請求去呼叫forward方法，收到請求後轉發到Checkout
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (action.equals("delete")) {
			// 1.取得要刪除的商品PK(map裡面的key)情境
			// 取得Map裡要刪除的key(product_no)強轉型為Integer
			cart.remove(new Integer(request.getParameter("product_no")));

			// 2.將移除後的購物車重新設定到session覆蓋舊的購物車
			session.setAttribute("cart", cart);

			// 3.forword
			RequestDispatcher successfulView = request
					.getRequestDispatcher("/front_end/shopping_cart/shoppingCart.jsp");
			successfulView.forward(request, response);
		} else if (action.equals("update")) {

			RequestDispatcher successfulView = request
					.getRequestDispatcher("/front_end/shopping_cart/shoppingCartForUpdate.jsp");
			successfulView.forward(request, response);

		} else if ("updateCartNum".equals(action)) {
			Map<String, String[]> paramMap = request.getParameterMap();
			for (String key : paramMap.keySet()) {
				System.out.println(key);
				if (key.startsWith("cart") && key.endsWith("Num")) {
					Integer prodNo = getProdNum(key);
					Integer updatedNum = new Integer(paramMap.get(key)[0]);

					if (updatedNum == 0) {
						cart.remove(prodNo);
					} else {
						OrderDetailVO item = cart.get(prodNo);
						item.setOrder_product_number(updatedNum);
						cart.put(prodNo, item);
					}

				}
			}
			RequestDispatcher successfulView = request
					.getRequestDispatcher("/front_end/shopping_cart/shoppingCart.jsp");
			successfulView.forward(request, response);
		} else if ("addCartAjax".equals(action)) {
			// Integer資料型態，創建一個新的商品編號，命名為product_no
			Integer product_no = new Integer(request.getParameter("productNo"));
			// order_detailVO資料型態，使用getOrder_detail方法，拿出商品的PK(product_no)，利用產品的service
			OrderDetailVO orderDetailVO = getOneOrder_detail(product_no);
			// Integer資料型態 ，預設購買數量(defaultOrderProductNumber)，以後購買數量都加一
			Integer defaultOrderProductNumber = 1;
			// orderDetailVO的setOrder_product_number方法，把購買數量(defaultOrderProductNumber)放進來
			orderDetailVO.setOrder_product_number(defaultOrderProductNumber);
			// 判斷購物車的存在
			if (cart == null) {
				// 因為購物車不存在，所以建立購物車
				cart = new HashMap<Integer, OrderDetailVO>();
				// 將商品放入購物車
				cart.put(product_no, orderDetailVO);
				// 將購物車放入session
				session.setAttribute("cart", cart);
			} else {
				// 用產品號碼(product_no)，使用購物車(cart)用get方法，取出訂單明細，並且放入 物件型別為
				// order_detailVO的order_detail內
				OrderDetailVO order_detail = cart.get(product_no);
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
					// 原有購買數量(oriProductNumber)+預設購買數量(defaultOrderProductNumber)，放入物件型別為Integer的newProductNumber內
					Integer newProductNumber = oriProductNumber + defaultOrderProductNumber;
					// 訂單明細(order_detail)用set方法，放入新的購買數量(newProductNumber)，更新訂單明細的購買數量
					order_detail.setOrder_product_number(newProductNumber);
					// 更新購物車(cart)的訂單明細(order_detail)
					cart.put(product_no, order_detail);
				}
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			int cartNum = cart.size();
			Gson gson = new Gson();
			Map<String, String> result = new HashMap<>();
			result.put("result", "success");
			result.put("cartNum", Objects.toString(cartNum));
			String resultStr = gson.toJson(result);
			
			out.print(resultStr);

		} else {
			System.out.println("還沒設計情境, action: " + action);
		}

	}

	private Integer getProdNum(String key) {
		String[] keyArray = key.split("_");
		String prodNoStr = keyArray[1];
		return new Integer(prodNoStr);
	}

	/**
	 * 用產品pk，從service 取出產品，再轉換成訂單明細物件
	 * 
	 * @param product_no 商品編號
	 * @return
	 */

	private OrderDetailVO getOneOrder_detail(Integer product_no) {
		// 在產品service中找出商品(利用產品pk)
		ProductVO prod = prodSVC.getOneproduct(product_no);

		// 將商品資訊送入訂單明細內
		OrderDetailVO detailVO = new OrderDetailVO();
		detailVO.setProduct_no(prod.getProduct_no());
		detailVO.setProduct_name(prod.getProduct_name());
		detailVO.setProduct_price(prod.getProduct_price());

		// 行銷活動的優惠價格

		ProductPromotionsDetailVO productPromo = productPromoSvc.getCurrentProductPromotion(prod.getProduct_no());
		PromotionsVO promotionVO = promotionSvc.getCurrentPromotion();
		if (productPromo != null) {
			detailVO.setProduct_price(productPromo.getProductSpecialPrice());
			detailVO.setProduct_pro_detail_no(productPromo.getProduct_pro_detail_no());
			detailVO.setPromot_name(promotionVO.getPromot_name());
		}

		return detailVO;
	}

}
