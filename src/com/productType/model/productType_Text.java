package com.productType.model;

import java.util.List;

public class productType_Text {
	
public static void main(String[] args) {
		
		productTypeDAO ptdao = new productTypeDAO();
		
		//新增
		productTypeVO pt1 = new productTypeVO();
		pt1.setProduct_type_no(11);
		pt1.setProduct_type_name("寵物保險");
		ptdao.insert(pt1);
		System.out.println("新增成功");
		System.out.println("---------------------");
		
		//更新
		productTypeVO pt2 = new productTypeVO();
		pt2.setProduct_type_no(11);
		pt2.setProduct_type_name("寵物醫療");
		ptdao.update(pt2);
		System.out.println("更新成功");
		System.out.println("---------------------");
		
		//刪除單一
		ptdao.delete(2);
		System.out.println("刪除成功");
		System.out.println("---------------------");
		
		//查詢單一
		productTypeVO pt3 = ptdao.findByPrimaryKey(1);
		System.out.print(pt3.getProduct_type_no() + ",");
		System.out.print(pt3.getProduct_type_name());
		System.out.println();
		System.out.println("---------------------");
		
		//查詢全部
		List<productTypeVO> list = ptdao.getAll();
		for (productTypeVO pt4 : list) {
			System.out.print(pt4.getProduct_type_no() + ",");
			System.out.print(pt4.getProduct_type_name());
			System.out.println();
			System.out.println("---------------------");
		}
	}
}