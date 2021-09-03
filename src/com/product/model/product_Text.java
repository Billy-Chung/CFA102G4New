package com.product.model;

import java.util.List;

public class product_Text {
	
public static void main(String[] args) {
		
		productDAO pdao = new productDAO();
		
		//新增
		productVO p1 = new productVO();
		p1.setProduct_no(7021);
		p1.setProduct_type_no(2);
		p1.setProduct_name("B牌貓咪飼料");
		p1.setProduct_price(999);
		p1.setProduct_comment("頂級鮭魚口味，並附贈主子試吃包");
		p1.setProduct_status("0");
		p1.setProduct_all_stars(0);
		p1.setProduct_all_comments(0);
		pdao.insert(p1);
		System.out.println("新增成功");
		System.out.println("---------------------");
		
		//更新
		productVO p2 = new productVO();
		p2.setProduct_no(7021);
		p2.setProduct_type_no(2);
		p2.setProduct_name("A牌貓咪飼料");
		p2.setProduct_price(1100);
		p2.setProduct_comment("雞肉碗豆口味，特價中!");
		p2.setProduct_status("1");
		p2.setProduct_all_stars(5);
		p2.setProduct_all_comments(999);
		pdao.update(p2);
		System.out.println("更新成功");
		System.out.println("---------------------");
		
		//刪除單一
		pdao.delete(7021);
		System.out.println("刪除成功");
		System.out.println("---------------------");
		
		//查詢單一
		productVO p3 = pdao.findByPrimaryKey(7001);
		System.out.print(p3.getProduct_no() + ",");
		System.out.print(p3.getProduct_type_no() + ",");
		System.out.print(p3.getProduct_name() + ",");
		System.out.print(p3.getProduct_price() + ",");
		System.out.print(p3.getProduct_comment() + ",");
		System.out.print(p3.getProduct_status() + ",");
		System.out.print(p3.getProduct_all_stars() + ",");
		System.out.print(p3.getProduct_all_comments());
		System.out.println();
		System.out.println("---------------------");
		
		//查詢全部
		List<productVO> list = pdao.getAll();
		for (productVO p4 : list) {
			System.out.print(p4.getProduct_no() + ",");
			System.out.print(p4.getProduct_type_no() + ",");
			System.out.print(p4.getProduct_name() + ",");
			System.out.print(p4.getProduct_price() + ",");
			System.out.print(p4.getProduct_comment() + ",");
			System.out.print(p4.getProduct_status() + ",");
			System.out.print(p4.getProduct_all_stars() + ",");
			System.out.print(p4.getProduct_all_comments());
			System.out.println();
			System.out.println("---------------------");
		}
	}
}
