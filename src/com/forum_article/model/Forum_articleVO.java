package com.forum_article.model;

import java.io.Serializable;
import java.sql.Date;

public class Forum_articleVO implements Serializable{
	
	private Integer forum_article_no;
	private Integer forum_article_cls_no;
	private Integer gen_meb_no;
	private String forum_comm_com_name;
	private String forum_comm_com;
	private Date forum_comm_date;
	private String forum_comm_state;
	
	public Integer getForum_article_no() {
		return forum_article_no;
	}
	public void setForum_article_no(Integer forum_article_no) {
		this.forum_article_no = forum_article_no;
	}
	public Integer getForum_article_cls_no() {
		return forum_article_cls_no;
	}
	public void setForum_article_cls_no(Integer forum_article_cls_no) {
		this.forum_article_cls_no = forum_article_cls_no;
	}
	public Integer getGen_meb_no() {
		return gen_meb_no;
	}
	public void setGen_meb_no(Integer gen_meb_no) {
		this.gen_meb_no = gen_meb_no;
	}
	public String getForum_comm_com_name() {
		return forum_comm_com_name;
	}
	public void setForum_comm_com_name(String forum_comm_com_name) {
		this.forum_comm_com_name = forum_comm_com_name;
	}
	public String getForum_comm_com() {
		return forum_comm_com;
	}
	public void setForum_comm_com(String forum_comm_com) {
		this.forum_comm_com = forum_comm_com;
	}
	public Date getForum_comm_date() {
		return forum_comm_date;
	}
	public void setForum_comm_date(Date forum_comm_date) {
		this.forum_comm_date = forum_comm_date;
	}
	public String getForum_comm_state() {
		return forum_comm_state;
	}
	public void setForum_comm_state(String forum_comm_state) {
		this.forum_comm_state = forum_comm_state;
	}

}
