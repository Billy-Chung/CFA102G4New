package com.emp.model;

import java.util.List;


public interface EmpDAO_interface {
	
	public void insert(EmpVO emp);
	
	public void update(EmpVO emp);
	
	public void delete(Integer EMP_NO);
	
	public EmpVO findByEmpNoPK(Integer EMP_NO);
	
	public List<EmpVO> getemp();

}
