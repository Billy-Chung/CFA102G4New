package com.trackProduct.model;

import java.util.List;

public class trackProduct_Text {

	public static void main(String[] args) {

		trackProductDAO tpdao = new trackProductDAO();
		
		//新增
		trackProductVO tp1 = new trackProductVO();
		tp1.setGen_meb_no(11);
		tp1.setProduct_no(12);
		tpdao.insert(tp1);
		System.out.println("新增成功");
		System.out.println("---------------------");
		
		//更新
		trackProductVO tp2 = new trackProductVO();
		tp2.setGen_meb_no(11);
		tp2.setProduct_no(13);
		tpdao.update(tp2);
		System.out.println("更新成功");
		System.out.println("---------------------");
		
		//刪除
		tpdao.delete(11,13);
		System.out.println("刪除成功");
		System.out.println("---------------------");
		
		//查詢單一
		trackProductVO tp3 = tpdao.findByPrimaryKey(1,1);
		System.out.print(tp3.getGen_meb_no() + ",");
		System.out.print(tp3.getProduct_no());
		System.out.println();
		System.out.println("---------------------");
		
		//查詢全部
		List<trackProductVO> list = tpdao.getAll();
		for (trackProductVO tp4 : list) {
			System.out.print(tp4.getGen_meb_no() + ",");
			System.out.print(tp4.getProduct_no());
			System.out.println();
		}
	}
}
