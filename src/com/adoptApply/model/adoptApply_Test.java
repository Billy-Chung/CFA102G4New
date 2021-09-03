package com.adoptApply.model;

import java.util.List;

public class adoptApply_Test {
	
public static void main(String[] args) {
		
		adoptApplyDAO aadao = new adoptApplyDAO();
		
		//新增
		adoptApplyVO aa1 = new adoptApplyVO();
		aa1.setAdopt_apply_no(11);
		aa1.setAdopt_meb_no(2);
		aa1.setGen_meb_no(2);
		aa1.setAdopt_pet_no(2);
		aa1.setAdopt_audit_state("1");
		aa1.setAdopt_apply_people_id("E192245198");
		aa1.setAdopt_apply_date(java.sql.Date.valueOf("2021-08-26"));
		aa1.setAdopt_apply_state("0");
		aadao.insert(aa1);
		System.out.println("新增成功");
		System.out.println("---------------------");
		
		//更新
		adoptApplyVO aa2 = new adoptApplyVO();
		aa2.setAdopt_apply_no(11);
		aa2.setAdopt_meb_no(2);
		aa2.setGen_meb_no(2);
		aa2.setAdopt_pet_no(2);
		aa2.setAdopt_audit_state("2");
		aa2.setAdopt_apply_people_id("A112107917");
		aa2.setAdopt_apply_date(java.sql.Date.valueOf("2021-08-26"));
		aa2.setAdopt_apply_state("1");
		aadao.update(aa2);
		System.out.println("更新成功");
		System.out.println("---------------------");
		
		//刪除單一
		aadao.delete(11);
		System.out.println("刪除成功");
		System.out.println("---------------------");
		
		//查詢單一
		adoptApplyVO aa3 = aadao.findByPrimaryKey(1);
		System.out.print(aa3.getAdopt_apply_no() + ",");
		System.out.print(aa3.getAdopt_meb_no() + ",");
		System.out.print(aa3.getGen_meb_no() + ",");
		System.out.print(aa3.getAdopt_pet_no() + ",");
		System.out.print(aa3.getAdopt_audit_state() + ",");
		System.out.print(aa3.getAdopt_apply_people_id() + ",");
		System.out.print(aa3.getAdopt_apply_date() + ",");
		System.out.print(aa3.getAdopt_apply_state());
		System.out.println();
		System.out.println("---------------------");
		
		//查詢全部
		List<adoptApplyVO> list = aadao.getAll();
		for (adoptApplyVO aa4 : list) {
			System.out.print(aa4.getAdopt_apply_no() + ",");
			System.out.print(aa4.getAdopt_meb_no() + ",");
			System.out.print(aa4.getGen_meb_no() + ",");
			System.out.print(aa4.getAdopt_pet_no() + ",");
			System.out.print(aa4.getAdopt_audit_state() + ",");
			System.out.print(aa4.getAdopt_apply_people_id() + ",");
			System.out.print(aa4.getAdopt_apply_date() + ",");
			System.out.print(aa4.getAdopt_apply_state());
			System.out.println();
			System.out.println("---------------------");
		}
	}
}
