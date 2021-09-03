package com.forum_article_cls.model;

import java.io.Serializable;

public class Forum_article_clsVO implements Serializable{
	
	private Integer forum_article_cla_no;
	private String forum_article_cla_name;
	
	
	public Integer getForum_article_cla_no() {
		return forum_article_cla_no;
	}
	public void setForum_article_cla_no(Integer forum_article_cla_no) {
		this.forum_article_cla_no = forum_article_cla_no;
	}
	public String getForum_article_cla_name() {
		return forum_article_cla_name;
	}
	public void setForum_article_cla_name(String forum_article_cla_name) {
		this.forum_article_cla_name = forum_article_cla_name;
	}

}
