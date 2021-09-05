package com.promotions.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class promotionsService {

	
	private promotionsDAO_interface dao;
	public promotionsService() {
		dao=new promotionsDAO();
	}//實作DAO
	
	//新增
	public promotionsVO addpromotions(String promot_name, java.sql.Date promot_date_start, java.sql.Date promot_date_end,
			String promot_status, String promot_type, String promot_discount_type, String promot_discount,
			String promot_reduce, String promot_comment , byte[] promot_photo) {
		
		promotionsVO promotionsVO = new promotionsVO();//promotionsVO()
		promotionsVO.setPromot_name(promot_name);
		promotionsVO.setPromot_date_start(promot_date_start);
		promotionsVO.setPromot_date_end(promot_date_end);
		promotionsVO.setPromot_status(promot_status);
		promotionsVO.setPromot_type(promot_type);
		promotionsVO.setPromot_discount_type(promot_discount_type);
		promotionsVO.setPromot_discount(promot_discount);
		promotionsVO.setPromot_reduce(promot_reduce);
		promotionsVO.setPromot_comment(promot_comment);
		promotionsVO.setPromot_photo(promot_photo);
		dao.add(promotionsVO);
	
		return promotionsVO;
	
	}
	
	//更新
	public promotionsVO updatepromotions(Integer promot_no,
			String promot_name, java.sql.Date promot_date_start, java.sql.Date promot_date_end,
			String promot_status, String promot_type, String promot_discount_type, String promot_discount,
			String promot_reduce, String promot_comment , byte[] promot_photo) {
		
		promotionsVO promotionsVO = new promotionsVO();//promotionsVO()
		promotionsVO.setPromot_no(promot_no);
		promotionsVO.setPromot_name(promot_name);
		promotionsVO.setPromot_date_start(promot_date_start);
		promotionsVO.setPromot_date_end(promot_date_end);
		promotionsVO.setPromot_status(promot_status);
		promotionsVO.setPromot_type(promot_type);
		promotionsVO.setPromot_discount_type(promot_discount_type);
		promotionsVO.setPromot_discount(promot_discount);
		promotionsVO.setPromot_reduce(promot_reduce);
		promotionsVO.setPromot_comment(promot_comment);
		promotionsVO.setPromot_photo(promot_photo);
		dao.update(promotionsVO);
		return promotionsVO;
	}
	
	//刪除
	public void deletepromotions(Integer promot_no) {
		dao.delete(promot_no);
	}

	//主鍵查詢
	public promotionsVO getOnePromotions(Integer promot_no) {
		return dao.findByPromotNoPk(promot_no);
	}

	//查詢
	public List<promotionsVO> getAll() {
		return dao.getAll();
	}
	//新增照片 使用byte[]方式	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	//	將照片顯示出來
	public static void readPicture(byte[] bytes) throws IOException {
		String imgurl = "images/promotions1.png";
		FileOutputStream fos = new FileOutputStream(imgurl);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}	
	}
