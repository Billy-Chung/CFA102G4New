package com.empFunction.model;

import java.util.*;

public interface EmpFunctionDAO_interface {
	
	public void insert(EmpFunctionVO emp_function);
	
	public void update(EmpFunctionVO emp_function);
	
	public void delete(Integer FUNCTION_NO);
	
	public EmpFunctionVO findByFunctionNoPK(Integer FUNCTION_NO);
	
	public List<EmpFunctionVO> getemp_function();

}
