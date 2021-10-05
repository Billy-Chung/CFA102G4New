package com.order_form.model;

import java.util.List;

import com.order_detail.model.OrderDetailVO;

public interface Order_formDAO_interface {

	
	public void add(OrderFormVO order_formVO);//新增
	public void update(OrderFormVO order_formVO);//修改
	public void delete(Integer ORDER_NO);//刪除
	public OrderFormVO findOrderFormPk(Integer ORDER_NO);//主鍵findByPk
	public List<OrderFormVO> getAllorderForm();//查	
	
	//同時新增訂單與訂單明細 (訂單主檔與明細檔一次新增成功)
	//自增主鍵(一order_form對多order_detail)	
	public Integer insertWithorder_detail(OrderFormVO order_formVO, List<OrderDetailVO> list);
	public List<OrderFormVO> getOrderListByMemNo(Integer memNo);
	
	
	
	
	
}




