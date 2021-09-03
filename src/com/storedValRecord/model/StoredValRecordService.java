package com.storedValRecord.model;

import java.sql.Date;
import java.util.List;

import com.storedValRecord.model.StoredValRecordVO;

public class StoredValRecordService {
	private StoredValRecordDAO_Interface dao;
	
	public StoredValRecordService() {
		dao = new StoredValRecordDAO();
	}
	
	
	public StoredValRecordVO addStoredValRecord(Integer stored_val_recode_no,Integer ger_meb_no,Date stored_val_date,Integer stored_val_amout) 
	{
		StoredValRecordVO svrVO = new StoredValRecordVO();
		
		svrVO.setStored_val_recode_no(stored_val_recode_no);
		svrVO.setGer_meb_no(ger_meb_no);
		svrVO.setStored_val_date(stored_val_date);
		svrVO.setStored_val_amout(stored_val_amout);
		
		dao.insert(svrVO);
		
		return svrVO;
		
	}
	
	//預留給 Struts 2 用的
	public void addStoredValRecord(StoredValRecordVO svrVO) {
		dao.insert(svrVO);
	}
	
	public StoredValRecordVO updateStoredValRecord(Integer stored_val_recode_no,Integer ger_meb_no,Date stored_val_date,Integer stored_val_amout) {
		StoredValRecordVO svrVO = new StoredValRecordVO();
		
		svrVO.setStored_val_recode_no(stored_val_recode_no);
		svrVO.setGer_meb_no(ger_meb_no);
		svrVO.setStored_val_date(stored_val_date);
		svrVO.setStored_val_amout(stored_val_amout);
		
		dao.update(svrVO);
		
		return dao.findByPrimaryKey(stored_val_recode_no); 
	}
	
	//預留給 Struts 2 用的
	public void updateStoredValRecord(StoredValRecordVO svrVO) {
		dao.update(svrVO);
	}
	
	public void deleteStoredValRecord(Integer stored_val_recode_no) {
		dao.delete(stored_val_recode_no);
	}
	
	public StoredValRecordVO getOneStoredValRecord(Integer stored_val_recode_no) {
		return dao.findByPrimaryKey(stored_val_recode_no);
	}
	
	public List<StoredValRecordVO> getAll(){
		return dao.getAll();
	}
	

	
}
