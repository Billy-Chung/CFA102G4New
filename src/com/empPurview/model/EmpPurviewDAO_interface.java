package com.empPurview.model;

import java.util.List;


public interface EmpPurviewDAO_interface {
	
	
	public void insert(EmpPurviewVO emp_purview);
	
	public void update(EmpPurviewVO emp_purview);
	
	public void delete(Integer emp_purview);
	
	public List<EmpPurviewVO> findByFunctionNoPK(Integer FUNCTION_NO);
	
	public List<EmpPurviewVO> getemp_purview();

}
