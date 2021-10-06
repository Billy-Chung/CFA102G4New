package com.storedValRecord.model;

import java.util.List;

public interface StoredValRecordDAO_Interface {
	public void insert(StoredValRecordVO svrVO);
	public void update(StoredValRecordVO svrVO);
	public void delete(Integer svrno);
	public StoredValRecordVO findByPrimaryKey(Integer svrno);
	public List<StoredValRecordVO> getAll();

}
