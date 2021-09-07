package com.emp.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class EmpService {
	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO insertEmp(String emp_account, String emp_password, String emp_name, String emp_on_job, byte[] emp_img,
			String emp_mail, String emp_phone, String emp_gender, Date emp_day, String emp_add, String emp_school,
			Date emp_date) {
		EmpVO emp = new EmpVO();

		emp.setEmp_account(emp_account);
		emp.setEmp_password(emp_password);
		emp.setEmp_name(emp_name);
		emp.setEmp_on_job(emp_on_job);
		emp.setEmp_img(emp_img);
		emp.setEmp_mail(emp_mail);
		emp.setEmp_phone(emp_phone);
		emp.setEmp_gender(emp_gender);
		emp.setEmp_day(emp_day);
		emp.setEmp_add(emp_add);
		emp.setEmp_school(emp_school);
		emp.setEmp_date(emp_date);
		emp = dao.insert(emp);

		return emp;

	}

	public void updateEmp(String emp_account, String emp_password, String emp_name, String emp_on_job, byte[] emp_img,
			String emp_mail, String emp_phone, String emp_gender, Date emp_day, String emp_add, String emp_school,
			Date emp_date, Integer emp_no) {
		EmpVO emp = new EmpVO();

		emp.setEmp_account(emp_account);
		emp.setEmp_password(emp_password);
		emp.setEmp_name(emp_name);
		emp.setEmp_on_job(emp_on_job);
		emp.setEmp_img(emp_img);
		emp.setEmp_mail(emp_mail);
		emp.setEmp_phone(emp_phone);
		emp.setEmp_gender(emp_gender);
		emp.setEmp_day(emp_day);
		emp.setEmp_add(emp_add);
		emp.setEmp_school(emp_school);
		emp.setEmp_date(emp_date);
		emp.setEmp_no(emp_no);
		dao.update(emp);
	}

	public EmpVO findByEmpNoPK(Integer emp_no) {
		EmpVO emp = new EmpVO();

		emp = dao.findByEmpNoPK(emp_no);

		return emp;
	}

	public List<EmpVO> getemp() {

		List<EmpVO> emp = dao.getemp();

		return emp;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
