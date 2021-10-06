package com.web_pade_data.model;

import java.io.Serializable;
import java.sql.Date;

public class Web_pade_dataVO implements Serializable{
	
	private Integer Web_pade_data_no;
	private Integer Web_pade_data;
	private Date Web_pade_date;
	public Integer getWeb_pade_data_no() {
		return Web_pade_data_no;
	}
	public void setWeb_pade_data_no(Integer web_pade_data_no) {
		Web_pade_data_no = web_pade_data_no;
	}
	public Integer getWeb_pade_data() {
		return Web_pade_data;
	}
	public void setWeb_pade_data(Integer web_pade_data) {
		Web_pade_data = web_pade_data;
	}
	public Date getWeb_pade_date() {
		return Web_pade_date;
	}
	public void setWeb_pade_date(Date web_pade_date) {
		Web_pade_date = web_pade_date;
	}
	


}
