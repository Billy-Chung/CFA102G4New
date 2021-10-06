package com.emp.model;

import java.io.Serializable;
import java.sql.Date;

public class EmpVO implements Serializable {

	private Integer emp_no;
	private String emp_account;
	private String emp_password;
	private String emp_name;
	private String emp_on_job;
	private byte[] emp_img;
	private String emp_mail;
	private String emp_phone;
	private String emp_gender;
	private Date emp_day;
	private String emp_add;
	private String emp_school;
	private Date emp_date;
	
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_account() {
		return emp_account;
	}
	public void setEmp_account(String emp_account) {
		this.emp_account = emp_account;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_on_job() {
		return emp_on_job;
	}
	public void setEmp_on_job(String emp_on_job) {
		this.emp_on_job = emp_on_job;
	}
	public byte[] getEmp_img() {
		return emp_img;
	}
	public void setEmp_img(byte[] emp_img) {
		this.emp_img = emp_img;
	}
	public String getEmp_mail() {
		return emp_mail;
	}
	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	public Date getEmp_day() {
		return emp_day;
	}
	public void setEmp_day(Date emp_day) {
		this.emp_day = emp_day;
	}
	public String getEmp_add() {
		return emp_add;
	}
	public void setEmp_add(String emp_add) {
		this.emp_add = emp_add;
	}
	public String getEmp_school() {
		return emp_school;
	}
	public void setEmp_school(String emp_school) {
		this.emp_school = emp_school;
	}
	public Date getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}
	
}
