package com.storedValRecord.model;

import java.util.List;

import com.adoptMemberReport.model.AdoptMemberReportVO;

public class SvrTest {

	public static void main(String[] args) {
		
		//新增
//		StoredValRecordVO svr = new StoredValRecordVO();
//		StoredValRecordDAO_Interface svrdao = new StoredValRecordDAO();
//		svr.setStored_val_recode_no(6);
//		svr.setGer_meb_no(2);
//		svr.setStored_val_date(java.sql.Date.valueOf("2016-01-01"));
//		svr.setStored_val_amout(700);
//		
//		svrdao.insert(svr);
//		System.out.println("新增成功");
		
		//更新
//		StoredValRecordVO svr = new StoredValRecordVO();
//		StoredValRecordDAO_Interface svrdao = new StoredValRecordDAO();
//		svr.setStored_val_recode_no(6);
//		svr.setGer_meb_no(2);
//		svr.setStored_val_date(java.sql.Date.valueOf("2016-01-01"));
//		svr.setStored_val_amout(800);
//		
//		
//		svrdao.update(svr);
//		System.out.println("更新成功");
		
		//刪除
//		StoredValRecordVO svr = new StoredValRecordVO();
//		StoredValRecordDAO_Interface svrdao = new StoredValRecordDAO();
//		svrdao.delete(6);
//		System.out.println("刪除成功");
		
		
		//查詢單一
//		StoredValRecordDAO_Interface svrdao = new StoredValRecordDAO();
//		StoredValRecordVO svr = svrdao.findByPrimaryKey(2);
//		System.out.print(svr.getStored_val_recode_no() + ",");
//		System.out.print(svr.getGer_meb_no() + ",");
//		System.out.print(svr.getStored_val_date() + ",");
//		System.out.print(svr.getStored_val_amout() + ",");
//		System.out.println();
		
		//查詢全部
		StoredValRecordDAO_Interface svrdao = new StoredValRecordDAO();
		List<StoredValRecordVO> svrlist = svrdao.getAll();
		for (StoredValRecordVO svr : svrlist) {
			System.out.print(svr.getStored_val_recode_no() + ",");
			System.out.print(svr.getGer_meb_no() + ",");
			System.out.print(svr.getStored_val_date() + ",");
			System.out.print(svr.getStored_val_amout() + ",");
			System.out.println();
		}
	}

}
