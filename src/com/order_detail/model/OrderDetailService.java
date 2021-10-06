package com.order_detail.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;



/**
 * @author Tibame_T14
 *
 */
public class OrderDetailService {
	private order_detailDAO_interface dao;

	public OrderDetailService() {
		dao = new order_detailDAO();
	}

	public OrderDetailVO order_detail(Integer order_detail_no, Integer order_no, Integer product_no,
			Integer order_product_number, Integer product_price, String product_name, Integer product_pro_detail_no,
			String promot_name) {

		OrderDetailVO order_detailVO = new OrderDetailVO();
		order_detailVO.setOrder_detail_no(order_detail_no);
		order_detailVO.setOrder_no(order_no);
		order_detailVO.setProduct_no(product_no);
		order_detailVO.setOrder_product_number(order_product_number);
		order_detailVO.setProduct_price(product_price);
		order_detailVO.setProduct_name(product_name);
		order_detailVO.setProduct_pro_detail_no(product_pro_detail_no);
		order_detailVO.setPromot_name(promot_name);
		dao.add(order_detailVO);
		return order_detailVO;
	}
	
	
	public OrderDetailVO updateorder_detail(Integer order_no, Integer product_no,
			Integer order_product_number, Integer product_price, String product_name, Integer product_pro_detail_no,
			String promot_name) {
		
		OrderDetailVO order_detailVO = new OrderDetailVO();
		order_detailVO.setOrder_no(order_no);
		order_detailVO.setProduct_no(product_no);
		order_detailVO.setOrder_product_number(order_product_number);
		order_detailVO.setProduct_price(product_price);
		order_detailVO.setProduct_name(product_name);
		order_detailVO.setProduct_pro_detail_no(product_pro_detail_no);
		order_detailVO.setPromot_name(promot_name);
		dao.update(order_detailVO);
		return order_detailVO;		
	}
	public void deleteorder_detail(Integer order_detail_no) {
		dao.delete(order_detail_no);
	}

	//主鍵查詢
	public OrderDetailVO getOneorder_detail(Integer order_detail_no) {
		return dao.findOrderDetailPk(order_detail_no);
	}

	//查詢
	public List<OrderDetailVO> getAll() {
		return dao.getAllorder_detail();
	}
	

	
	
	/**
	 * 用訂單編號查詢訂單明細
	 * @param orderFormNo
	 * @return
	 */
	public List<OrderDetailVO> findOrderDetailsByOrderFormNo(Integer orderFormNo) {
		return dao.findOrderDetailsByOrderFormNo(orderFormNo);
	}
	
	
	

}
