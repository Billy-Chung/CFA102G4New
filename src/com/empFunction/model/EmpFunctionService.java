package com.empFunction.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class EmpFunctionService {

	private EmpFunctionDAO_interface dao;

	public EmpFunctionService() {
		dao = new EmpFunctionDAO();		
	}
	
	public EmpFunctionVO insertEmpFunction(String function_name) {
		EmpFunctionVO empFunction = new EmpFunctionVO();
					
	
		empFunction.setFunction_name(function_name);
	
		empFunction = dao.insert(empFunction);
		
		return empFunction;
	}
	public void updateEmpFunction(String function_name,Integer function_no) {
		EmpFunctionVO empFunction = new EmpFunctionVO();
	
		empFunction.setFunction_name(function_name);
		empFunction.setFunction_no(function_no);
		dao.update(empFunction);
		
		}
	
	public	EmpFunctionVO findByFunctionNoPK(Integer function_no) {
		EmpFunctionVO empFunction = new EmpFunctionVO();
		
		empFunction = dao.findByFunctionNoPK(function_no);
		
		return empFunction;
	}
	
	public List<EmpFunctionVO> getemp_function() {

		List<EmpFunctionVO> empFunction = dao.getemp_function();

		return empFunction;
	}
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}	
}
