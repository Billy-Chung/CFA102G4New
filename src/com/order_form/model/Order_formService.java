package com.order_form.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.order_detail.model.OrderDetailVO;


public class Order_formService {
	private Order_formDAO_interface dao;

	public Order_formService() {
		dao = new Order_formDAO();
	}

	// 新增
	public OrderFormVO addorder_form(Integer gen_meb_no, Integer pay_no, Integer logistics_no, Integer promot_no,
			Integer befort_amount, Integer order_amount,String order_name,String order_phone,String delivery_address,
			java.sql.Timestamp order_time,String order_status) {

		OrderFormVO order_formVO = new OrderFormVO();
		order_formVO.setGen_meb_no(gen_meb_no);
		order_formVO.setPay_no(pay_no);
		order_formVO.setLogistics_no(logistics_no);
		order_formVO.setPromot_no(promot_no);
		order_formVO.setBefort_amount(befort_amount);
		order_formVO.setOrder_amount(order_amount);
		order_formVO.setOrder_name(order_name);
		order_formVO.setOrder_phone(order_phone);
		order_formVO.setDelivery_address(delivery_address);
		order_formVO.setOrder_time(order_time);
		order_formVO.setOrder_status(order_status);
		dao.add(order_formVO);
		return order_formVO;
	}

	// 更新
	public void updateOrderForm(OrderFormVO orderFormVO) {
		dao.update(orderFormVO);
	}

	// 刪除
	public void deleteorder_form(Integer order_no) {
		dao.delete(order_no);
	}
	// 主鍵查詢
	public OrderFormVO getOneOrder_form(Integer order_no) {
		return dao.findOrderFormPk(order_no);
	}

	// 查詢
	public List<OrderFormVO> getAll() {
		return dao.getAllorderForm();
	}

	//自增主鍵
	public Integer insertWithorder_detail(Integer gen_meb_no, Integer pay_no, Integer logistics_no, Integer promot_no,
			Integer befort_amount, Integer order_amount,String order_name,String order_phone, String delivery_address, java.sql.Timestamp order_time,
			String order_status,List<OrderDetailVO> cartList) {

		OrderFormVO order_formVO = new OrderFormVO();
		order_formVO.setGen_meb_no(gen_meb_no);
		order_formVO.setPay_no(pay_no);
		order_formVO.setLogistics_no(logistics_no);
		order_formVO.setPromot_no(promot_no);
		order_formVO.setBefort_amount(befort_amount);
		order_formVO.setOrder_amount(order_amount);
		order_formVO.setOrder_name(order_name);
		order_formVO.setOrder_phone(order_phone);
		order_formVO.setDelivery_address(delivery_address);
		order_formVO.setOrder_time(order_time);
		order_formVO.setOrder_status(order_status);
		Integer next_order_no = dao.insertWithorder_detail(order_formVO,cartList);
		return next_order_no;
	}

	public List<OrderFormVO> getMemberOrdForm(Integer memNo) {
		List<OrderFormVO>  orderList = dao.getOrderListByMemNo(memNo);
		return orderList;
	}
}
