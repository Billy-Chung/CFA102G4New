package com.order_form.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.promotions.model.promotionsVO;

public class Order_formService {
	private order_formDAO_interface dao;

	public Order_formService() {
		dao = new order_formDAO();
	}

	// 新增
	public order_formVO addorder_form(Integer gen_meb_no, Integer pay_no, Integer logistics_no, Integer promot_no,
			Integer befort_amount, Integer order_amount, String delivery_address, java.sql.Date order_time,
			String order_status) {

		order_formVO order_formVO = new order_formVO();
		order_formVO.setGen_meb_no(gen_meb_no);
		order_formVO.setPay_no(pay_no);
		order_formVO.setLogistics_no(logistics_no);
		order_formVO.setPromot_no(promot_no);
		order_formVO.setBefort_amount(befort_amount);
		order_formVO.setOrder_amount(order_amount);
		order_formVO.setDelivery_address(delivery_address);
		order_formVO.setOrder_time(order_time);
		order_formVO.setOrder_status(order_status);
		dao.add(order_formVO);
		return order_formVO;
	}

	// 更新
	public order_formVO addorder_form(Integer order_no, Integer gen_meb_no, Integer pay_no, Integer logistics_no,
			Integer promot_no, Integer befort_amount, Integer order_amount, String delivery_address,
			java.sql.Date order_time, String order_status) {

		order_formVO order_formVO = new order_formVO();
		order_formVO.setOrder_no(order_no);
		order_formVO.setGen_meb_no(gen_meb_no);
		order_formVO.setPay_no(pay_no);
		order_formVO.setLogistics_no(logistics_no);
		order_formVO.setPromot_no(promot_no);
		order_formVO.setBefort_amount(befort_amount);
		order_formVO.setOrder_amount(order_amount);
		order_formVO.setDelivery_address(delivery_address);
		order_formVO.setOrder_time(order_time);
		order_formVO.setOrder_status(order_status);
		dao.update(order_formVO);
		return order_formVO;
	}

	// 刪除
	public void deleteorder_form(Integer order_no) {
		dao.delete(order_no);
	}
	// 主鍵查詢
	public order_formVO getOneOrder_form(Integer order_no) {
		return dao.findOrderFormPk(order_no);
	}

	// 查詢
	public List<order_formVO> getAll() {
		return dao.getAllorderForm();
	}

}
