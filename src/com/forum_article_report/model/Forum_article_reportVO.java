package com.forum_article_report.model;

import java.io.Serializable;
import java.sql.Date;

public class Forum_article_reportVO implements Serializable {

	private Integer article_report_no;
	private Integer forum_article_no;
	private Integer gen_meb_no;
	private String forum_comm_report_com;
	private String forum_comm_report_state;
	private Date forum_comm_report_date;
	
	public Integer getArticle_report_no() {
		return article_report_no;
	}
	public void setArticle_report_no(Integer article_report_no) {
		this.article_report_no = article_report_no;
	}
	public Integer getForum_article_no() {
		return forum_article_no;
	}
	public void setForum_article_no(Integer forum_article_no) {
		this.forum_article_no = forum_article_no;
	}
	public Integer getGen_meb_no() {
		return gen_meb_no;
	}
	public void setGen_meb_no(Integer gen_meb_no) {
		this.gen_meb_no = gen_meb_no;
	}
	public String getForum_comm_report_com() {
		return forum_comm_report_com;
	}
	public void setForum_comm_report_com(String forum_comm_report_com) {
		this.forum_comm_report_com = forum_comm_report_com;
	}
	public String getForum_comm_report_state() {
		return forum_comm_report_state;
	}
	public void setForum_comm_report_state(String forum_comm_report_state) {
		this.forum_comm_report_state = forum_comm_report_state;
	}
	public Date getForum_comm_report_date() {
		return forum_comm_report_date;
	}
	public void setForum_comm_report_date(Date forum_comm_report_date) {
		this.forum_comm_report_date = forum_comm_report_date;
	}

}