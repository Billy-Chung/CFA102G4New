package com.empPurview.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;







public class EmpPurviewService {

	private EmpPurviewDAO_interface dao;
	
	public EmpPurviewService() {
		dao = new EmpPurviewDAO();		
	}
	public EmpPurviewVO insertEmpPurview(Integer function_no) {
		EmpPurviewVO empPurview = new EmpPurviewVO();
	
		empPurview.setFunction_no(function_no);
	
		empPurview = dao.insert(empPurview);
			
			return empPurview;
	
	}
	public void updateEmp(Integer function_no,Integer emp_no) {
		EmpPurviewVO empPurview = new EmpPurviewVO();
	
		empPurview.setFunction_no(function_no);
		empPurview.setFunction_no(emp_no);
		dao.update(empPurview);	
		}
	
	
	public List<EmpPurviewVO> findByFunctionNoPK(Integer FUNCTION_NO){
		
		List<EmpPurviewVO> empPurview = dao.findByFunctionNoPK(FUNCTION_NO);
		
		return empPurview;
	}
	
	public  List<EmpPurviewVO> getemp_purview(){
		
		List<EmpPurviewVO> empPurview = dao.getemp_purview();
		
		return empPurview;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	

	
	
	
	
}
