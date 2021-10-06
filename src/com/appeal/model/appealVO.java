package com.appeal.model;

import java.io.Serializable;

public class appealVO implements Serializable{
	private Integer appeal_no;
	private Integer gen_meb_no;
	private Integer order_no;
	private String appeal_comment;
	private String appeal_state;

	public Integer getAppeal_no() {
		return appeal_no;
	}
	public void setAppeal_no(Integer appeal_no) {
		this.appeal_no = appeal_no;
	}
	public Integer getGen_meb_no() {
		return gen_meb_no;
	}
	public void setGen_meb_no(Integer gen_meb_no) {
		this.gen_meb_no = gen_meb_no;
	}
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public String getAppeal_comment() {
		return appeal_comment;
	}
	public void setAppeal_comment(String appeal_comment) {
		this.appeal_comment = appeal_comment;
	}
	public String getAppeal_state() {
		return appeal_state;
	}
	public void setAppeal_state(String appeal_state) {
		this.appeal_state = appeal_state;
	}	
}
