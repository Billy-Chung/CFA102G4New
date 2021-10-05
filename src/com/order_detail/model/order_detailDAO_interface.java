package com.order_detail.model;

import java.util.List;


public interface order_detailDAO_interface {
	public void add(OrderDetailVO order_detailVO);//新增
	public void update(OrderDetailVO order_detailVO);//修改
	public void delete(Integer pay_no);//刪除
	public List<OrderDetailVO> getAllorder_detail();//查	
	
	//同時新增訂單與訂單明細 (訂單主檔與明細檔一次新增成功)
    public void insert2 (OrderDetailVO order_detailVO , java.sql.Connection con);
	//查詢一(訂單)對多(訂單明細)
    public OrderDetailVO findOrderDetailPk(Integer order_no);//主鍵findByPk
    
    public List<OrderDetailVO> findOrderDetailsByOrderFormNo(Integer orderFormNo);
}
