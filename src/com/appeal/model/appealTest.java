package com.appeal.model;

import java.util.List;


public class appealTest {
	public static void main(String[] args) {
		//實作DAO
		appealDAO_interface dao = new appealDAO();

		
		// 新增
//		appealVO appeal1 = new appealVO();
//		appeal1.setGen_meb_no(1);
//		appeal1.setOrder_no(1);
//		appeal1.setAppeal_comment("毛小孩不愛");
//		appeal1.setAppeal_state("0");
//		dao.add(appeal1);
//		System.out.println("新增成功");
			
		// 修改
//		appealVO appeal2 = new appealVO();
//		appeal2.setAppeal_no(1);//修改目標
//		appeal2.setGen_meb_no(1);
//		appeal2.setOrder_no(1);
//		appeal2.setAppeal_comment("客服人員態度不良");
//		appeal2.setAppeal_state("1");
//		dao.update(appeal2);
//		System.out.println("更新成功");
		
		// 刪除，有綁FK參考表要先砍
//		dao.delete(1);
//		System.out.println("刪除成功");
		
		//主鍵查詢
//		appealVO appeal3 =dao.findAppealPk(1);//主鍵名稱
//		System.out.print(appeal3.getAppeal_no()+ ",");
//		System.out.print(appeal3.getGen_meb_no()+ ",");
//		System.out.print(appeal3.getOrder_no()+ ",");
//		System.out.print(appeal3.getAppeal_comment()+ ",");
//		System.out.print(appeal3.getAppeal_state());
	
	    // 查詢
		List<appealVO> list = dao.getAllappeal();
		for (appealVO appeal : list) {
			System.out.print(appeal.getAppeal_no()+ ",");
			System.out.print(appeal.getGen_meb_no()+ ",");
			System.out.print(appeal.getOrder_no()+ ",");
			System.out.print(appeal.getAppeal_comment()+ ",");
			System.out.print(appeal.getAppeal_state());
			System.out.println();
		}
		
	
}
		
}