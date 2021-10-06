package com.appeal.model;

import java.util.List;



public interface appealDAO_interface {
	public void add(appealVO appealVO);//新增
	public void update(appealVO appealVO);//修改
	public void delete(Integer appeal_no);//刪除
	public appealVO findAppealPk(Integer appeal_no);//主鍵findByPk
	public List<appealVO> getAllappeal();//查	
}
